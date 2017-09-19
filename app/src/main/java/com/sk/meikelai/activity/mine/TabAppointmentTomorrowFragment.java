package com.sk.meikelai.activity.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.sk.meikelai.Adapter.AppointmentTomorrowAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;
import com.sk.meikelai.entity.AppointmentBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 本月
 */

public class TabAppointmentTomorrowFragment extends BaseFragment implements HandleDataCallBack {
    @BindView(R.id.rc_content)
    RecyclerView mRecyclerView;
    LinearLayoutManager linearLayoutManager;
    AppointmentTomorrowAdapter mAdapter;
    private int page = 0;
    private List<AppointmentBean.ReturnDataBean> allDataLsit  = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.fragment_appointment_tomorrow;
    }

    @Override
    public void initView() {
        linearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
//设置布局管理器
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new AppointmentTomorrowAdapter(R.layout.item_mine_appointment,allDataLsit);
//设置adapter
        mRecyclerView.setAdapter(mAdapter);

        AppApi.getMyOrderList(MyUtils.getCode(mContext),"2",String.valueOf(page),this,0);
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        AppointmentBean bean = JSONObject.parseObject(result,AppointmentBean.class);
        List<AppointmentBean.ReturnDataBean> dataList = bean.getReturn_data();
        allDataLsit.addAll(dataList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void handlerFailData(String failString, int tag) {

    }

    @Override
    public void handleErrorData(String error, int tag) {

    }
}
