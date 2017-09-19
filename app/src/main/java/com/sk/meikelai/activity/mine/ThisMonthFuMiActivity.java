package com.sk.meikelai.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.sk.meikelai.Adapter.MonthFumiAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.entity.MonthFuMiBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.view.EasyLoadingMoreView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sk on 2017/6/29.
 */

public class ThisMonthFuMiActivity extends BaseActivity implements HandleDataCallBack {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.data_list)
    RecyclerView dataList;
    @BindView(R.id.tv_month)
    TextView tvMonth;

    private List<MonthFuMiBean.ReturnDataBean> allDataList = new ArrayList<>();
    private MonthFumiAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_this_month_fumi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new MonthFumiAdapter(R.layout.item_mine_month_history, allDataList);
        adapter.setEnableLoadMore(true);
        EasyLoadingMoreView loadMoreView = new EasyLoadingMoreView();
        adapter.setLoadMoreView(loadMoreView);
        dataList.setAdapter(adapter);

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        String date = sDateFormat.format(new Date());
        tvMonth.setText(format.format(new Date()));
        AppApi.getMonthFuMi(MyUtils.getCode(mContext), date, this, 0);

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        MonthFuMiBean bean = JSONObject.parseObject(result, MonthFuMiBean.class);
        List<MonthFuMiBean.ReturnDataBean> dataList = bean.getReturn_data();
        allDataList.addAll(dataList);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void handlerFailData(String failString, int tag) {
    }

    @Override
    public void handleErrorData(String error, int tag) {

    }

}
