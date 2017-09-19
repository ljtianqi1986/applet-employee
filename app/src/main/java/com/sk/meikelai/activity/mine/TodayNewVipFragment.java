package com.sk.meikelai.activity.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sk.meikelai.Adapter.VipAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;
import com.sk.meikelai.entity.VipBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by sk on 2017/7/6.
 */

public class TodayNewVipFragment extends BaseFragment implements HandleDataCallBack, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.vip_list)
    RecyclerView vipList;
    private int page = 0;
    private VipAdapter vipAdapter;
    private List<VipBean.ReturnDataBean> allDataList = new ArrayList<>();
    private String search = "";
    private boolean isLoadMore = false;

    @Override
    protected int getContentView() {
        return R.layout.fragment_today_vip;
    }

    @Override
    public void initView() {
        edSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    // 先隐藏键盘
                    ((InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getActivity().getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //进行搜索操作的方法，在该方法中可以加入mEditSearchUser的非空判断
                    resetList();
                    search = edSearch.getText().toString();
                    getVipData(search);
                }
                return false;
            }
        });

        vipList.setLayoutManager(new LinearLayoutManager(mContext));

        vipAdapter = new VipAdapter(R.layout.item_new_member, allDataList);
//设置adapter
        vipList.setAdapter(vipAdapter);

        vipAdapter.setOnLoadMoreListener(this, vipList);

        getVipData(search);
    }

    private void getVipData(String searchName) {
        AppApi.getMyVipList(searchName, MyUtils.getCode(mContext), "1", String.valueOf(page), this, 0);
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        VipBean vipBean = JSONObject.parseObject(result, VipBean.class);
        List<VipBean.ReturnDataBean> dataList = vipBean.getReturn_data();
        allDataList.addAll(dataList);
        if(allDataList.size()<10){
            vipAdapter.setEnableLoadMore(false);
        }else {
            vipAdapter.setEnableLoadMore(true);
        }
        if(isLoadMore){
            vipAdapter.loadMoreComplete();
            if(dataList.size() == 0){
                vipAdapter.loadMoreEnd();
            }
            isLoadMore = false;
        }
        vipAdapter.notifyDataSetChanged();
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
        getVipData(search);
    }

    private void resetList(){
        allDataList.clear();
        page = 0;
        vipAdapter.notifyDataSetChanged();
    }

}
