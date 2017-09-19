package com.sk.meikelai.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.sk.meikelai.Adapter.ToadyFumiAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.entity.FuMiBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.view.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sk on 2017/6/29.
 */

public class ToadyFuMiActivity extends BaseActivity implements HandleDataCallBack {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.data_list)
    RecyclerView dataList;

    private List<FuMiBean.ReturnDataBean> allDataList = new ArrayList<>();
    private ToadyFumiAdapter adapter;
    LoadingDialog loadingDialog;

    @Override
    protected int getContentView() {
        return R.layout.activity_today_fumi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new ToadyFumiAdapter(allDataList);
        dataList.setAdapter(adapter);

        loadingDialog = MyUtils.getLoadingDialog(mContext,false);
        loadingDialog.show();
        AppApi.getMyFuMi(MyUtils.getCode(mContext),"1",this,0);

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        loadingDialog.cancelDialog();
        FuMiBean bean = JSONObject.parseObject(result,FuMiBean.class);
        List<FuMiBean.ReturnDataBean> dataList = bean.getReturn_data();
        for(int i = 0; i<dataList.size();i++){
            if(dataList.get(i).getMoney_type() == 6){
                dataList.get(i).setItemType(1);
            }else {
                dataList.get(i).setItemType(0);
            }
        }
        allDataList.addAll(dataList);
        adapter.notifyDataSetChanged();

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
