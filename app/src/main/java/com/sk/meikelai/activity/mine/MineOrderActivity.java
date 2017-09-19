package com.sk.meikelai.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sk.meikelai.Adapter.MineOrderAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.entity.OrderFlowBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.view.LoadingDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 订单流水
 */

public class MineOrderActivity extends BaseActivity implements HandleDataCallBack, BaseQuickAdapter.RequestLoadMoreListener {

    MineOrderAdapter mAdapter;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.rc_content)
    RecyclerView rcContent;
    private List<OrderFlowBean.ReturnDataBean> allDataList = new ArrayList<>();

    private int page = 0;
    private boolean isLoadMore = false;
    private String date;
    private LoadingDialog loadingDialog;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = MyUtils.getLoadingDialog(mContext, false);
        initView();
    }

    private void initView() {
        rcContent.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new MineOrderAdapter(R.layout.item_my_order_flow, allDataList);
        mAdapter.setOnLoadMoreListener(this, rcContent);
        rcContent.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this, rcContent);

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = sDateFormat.format(new Date());
        loadingDialog.show();
        AppApi.getMyOrderFlow(MyUtils.getCode(mContext), String.valueOf(page), date, date,1, this, 0);
    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        loadingDialog.cancelDialog();
        OrderFlowBean orderFlowBean = JSONObject.parseObject(result, OrderFlowBean.class);
        List<OrderFlowBean.ReturnDataBean> dataBeanList = orderFlowBean.getReturn_data();
        allDataList.addAll(dataBeanList);

        if (allDataList.size() < 10) {
            mAdapter.setEnableLoadMore(false);
        } else {
            mAdapter.setEnableLoadMore(true);
        }
        if (isLoadMore) {
            mAdapter.loadMoreComplete();
            if (dataBeanList.size() == 0) {
                mAdapter.loadMoreEnd();
            }
            isLoadMore = false;
        }

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void handlerFailData(String failString, int tag) {
        loadingDialog.cancelDialog();
    }

    @Override
    public void handleErrorData(String error, int tag) {
        loadingDialog.cancelDialog();
    }

    @Override
    public void onLoadMoreRequested() {
        isLoadMore = true;
        page++;
        AppApi.getMyOrderFlow(MyUtils.getCode(mContext), String.valueOf(page), date, date,1, this, 0);
    }
}