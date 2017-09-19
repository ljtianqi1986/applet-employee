package com.sk.meikelai.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.sk.meikelai.Adapter.CheckListAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.dialog.DialogMineDutyFragment;
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
 * Created by Administrator on 2017/6/19.
 */

public class TodayOrderActivity extends BaseActivity implements HandleDataCallBack {
    @BindView(R.id.order_ok)
    TextView mOrderOk;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.order_list)
    RecyclerView orderList;
    @BindView(R.id.ed_remarks)
    EditText edRemarks;
    String date;
    LoadingDialog loadingDialog;
    private CheckListAdapter adapter;

    List<OrderFlowBean.ReturnDataBean> allData = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_today_order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = MyUtils.getLoadingDialog(mContext, false);

        orderList.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new CheckListAdapter(R.layout.item_check_list, allData);
        orderList.setAdapter(adapter);
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = sDateFormat.format(new Date());

        loadingDialog.show();
        AppApi.getMyOrderFlow(MyUtils.getCode(mContext), "-1", date, date, 0, this, 0);

    }


    private void showDialog() {
        DialogMineDutyFragment dialogMineDutyFragment = new DialogMineDutyFragment();
        dialogMineDutyFragment.show(getSupportFragmentManager(), DialogMineDutyFragment.TAG);
    }

    @OnClick({R.id.back, R.id.order_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.order_ok:
                showDialog();
                break;
        }
    }

    public void applyOrder(int i) {
        AppApi.applyOrderCheck(edRemarks.getText().toString(), MyUtils.getCode(mContext), i, TodayOrderActivity.this, 1);
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        switch (tag) {
            case 0:
                loadingDialog.cancelDialog();
                OrderFlowBean checkOrderList = JSON.parseObject(result, OrderFlowBean.class);
                List<OrderFlowBean.ReturnDataBean> checkList = checkOrderList.getReturn_data();
                allData.addAll(checkList);
                adapter.notifyDataSetChanged();
                break;
            case 1:
                Toast.makeText(getApplicationContext(), "订单审核提交成功!", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void handlerFailData(String failString, int tag) {
        loadingDialog.cancelDialog();
    }

    @Override
    public void handleErrorData(String error, int tag) {
        loadingDialog.cancelDialog();
    }
}