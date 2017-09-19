package com.sk.meikelai.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sk.meikelai.Adapter.CashApplyAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.entity.CashHandBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sk on 2017/7/27.
 */

public class HandOverHistoryActivity extends BaseActivity implements HandleDataCallBack, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.hand_list)
    RecyclerView handList;
    private int page = 0;


    private boolean isLoadMore = false;
    private CashApplyAdapter adapter;
    private List<CashHandBean.ReturnDataBean> allDataList = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_hand_over_history;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CashApplyAdapter(R.layout.item_mine_history,allDataList);
        handList.setLayoutManager(new LinearLayoutManager(mContext));
        handList.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this,handList);
        AppApi.cashRecordList(MyUtils.getCode(mContext),page,this,0);

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        CashHandBean cashHandBean  = JSONObject.parseObject(result, CashHandBean.class);
        List<CashHandBean.ReturnDataBean> dataBeanList = cashHandBean.getReturn_data();
        allDataList.addAll(dataBeanList);
        if (allDataList.size() < 10) {
            adapter.setEnableLoadMore(false);
        } else {
            adapter.setEnableLoadMore(true);
        }
        if(isLoadMore){
            adapter.loadMoreComplete();
            if(dataBeanList.size() == 0){
                adapter.loadMoreEnd();
            }
            isLoadMore = false;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void handlerFailData(String failString, int tag) {

    }

    @Override
    public void handleErrorData(String error, int tag) {

    }

    @Override
    public void onLoadMoreRequested() {
        isLoadMore = true;
        page++;
        AppApi.cashRecordList(MyUtils.getCode(mContext),page,this,0);

    }
}
