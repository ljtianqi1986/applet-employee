package com.sk.meikelai.activity.card;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sk.meikelai.Adapter.CardYearAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;
import com.sk.meikelai.callback.KindSelectCallBack;
import com.sk.meikelai.entity.OpenCardBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.StaticCode;
import com.sk.meikelai.utils.ToastUtils;
import com.sk.meikelai.view.AllKindsLayout;
import com.sk.meikelai.view.LoadingDialog;
import com.sk.meikelai.view.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * 年卡页面
 */

public class TabYearCardFragment extends BaseFragment implements KindSelectCallBack, BaseQuickAdapter.RequestLoadMoreListener,
        HandleDataCallBack {
    @BindView(R.id.rc_content)
    RecyclerView mRecyclerView;
    LinearLayoutManager linearLayoutManager;
    CardYearAdapter mAdapter;
    @BindView(R.id.card_all)
    TextView cardAll;
    @BindView(R.id.rl_function)
    RelativeLayout mRlFunction;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;
    @BindView(R.id.search_back)
    ImageView mSearchBack;
    @BindView(R.id.search_ll)
    TextView mTv_search;
    @BindView(R.id.container)
    FrameLayout container;
    private Drawable redDrawable;
    private Drawable greyDrawable;
    private boolean isopen = false;
    private AllKindsLayout allKindsLayout;
    private String mTypeCode = "";
    private String mSearch = "";
    private boolean kindOpen = false;
    LoadingDialog loadingDialog;
    private int page = 0;

    private List<OpenCardBean.ReturnDataBean> allDataList = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.fragment_saving_card;
    }

    @Override
    public void initView() {
        loadingDialog = MyUtils.getLoadingDialog(mContext, false);
        loadingDialog.setCancelable(false);
        AppApi.getOpenCardList(MyUtils.getShopCode(getContext()),"1", mTypeCode, mSearch, page, TabYearCardFragment.this, StaticCode.TAG);


        mEtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    // 先隐藏键盘
                    ((InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getActivity().getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //进行搜索操作的方法，在该方法中可以加入mEditSearchUser的非空判断
                    resetList();
                    mSearch = mEtSearch.getText().toString();
                    mRlFunction.setVisibility(View.VISIBLE);
                    mLlSearch.setVisibility(View.GONE);
                    if (!mSearch.equals("")) {
                        mTv_search.setText(mSearch);
                    } else {
                        mTv_search.setText(R.string.card_search);
                    }
                    loadingDialog.show();
                    AppApi.getOpenCardList(MyUtils.getShopCode(getContext()),"1", mTypeCode, mSearch, page, TabYearCardFragment.this, StaticCode
                            .TAG);
                }
                return false;
            }
        });

        //初始化布局
        initDrawable();
        //填充类型选择布局
        allKindsLayout = new AllKindsLayout(mContext, this, 4);
        container.addView(allKindsLayout);

        //填充类型选择布局
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CardYearAdapter(R.layout.item_card, allDataList);
        mAdapter.setOnLoadMoreListener(TabYearCardFragment.this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        //设置item之间的距离
        SpacesItemDecoration decoration=new SpacesItemDecoration(20);
        mRecyclerView.addItemDecoration(decoration);
        //设置adapter里item的点击事件
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TextView rl_content = (TextView) mAdapter.getViewByPosition(mRecyclerView, position, R.id.item_content);
                switch (view.getId()) {
                    case R.id.rl_more:
                        if (rl_content.getVisibility() == View.VISIBLE) {
                            rl_content.setVisibility(View.GONE);
                        } else {
                            rl_content.setVisibility(View.VISIBLE);
                        }
                        break;
                    case R.id.item_content:
                        if (rl_content.getVisibility() == View.VISIBLE) {
                            rl_content.setVisibility(View.GONE);
                        } else {
                            rl_content.setVisibility(View.VISIBLE);
                        }
                        break;
                    case R.id.item_buy:
                        Intent intent = new Intent(mContext, ConfirmActivity.class);
                        intent.putExtra("data", allDataList.get(position));
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDrawable();
    }

    //初始化指示器
    private void initDrawable() {
        redDrawable = getResources().getDrawable(R.drawable.up_red);
        redDrawable.setBounds(0, 0, redDrawable.getMinimumWidth(), redDrawable.getMinimumHeight());
        greyDrawable = getResources().getDrawable(R.drawable.down_grey);
        greyDrawable.setBounds(0, 0, greyDrawable.getMinimumWidth(), greyDrawable.getMinimumHeight());
    }


    @OnClick({R.id.card_all, R.id.search_ll, R.id.search_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_all:
                if (kindOpen) {
                    closeKindll();
                    container.setVisibility(View.GONE);
                } else {
                    container.setVisibility(View.VISIBLE);
                    allKindsLayout.setVisibility(View.VISIBLE);
                    openKindll();
                }
                break;
            case R.id.search_ll:
                mRlFunction.setVisibility(View.GONE);
                mLlSearch.setVisibility(View.VISIBLE);
                mEtSearch.setFocusable(true);
                mEtSearch.setFocusableInTouchMode(true);
                mEtSearch.requestFocus();
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.search_back:
                loadingDialog.show();
                resetList();
                mSearch = mEtSearch.getText().toString();
                AppApi.getOpenCardList(MyUtils.getShopCode(getContext()),"1", mTypeCode, mSearch, page, TabYearCardFragment.this, StaticCode.TAG);
                mRlFunction.setVisibility(View.VISIBLE);
                mLlSearch.setVisibility(View.GONE);
                if (!mSearch.equals("")) {
                    mTv_search.setText(mSearch);
                } else {
                    mTv_search.setText(R.string.card_search);
                }
                InputMethodManager imm1 = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm1.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
        }
    }


    @Override
    public void onLoadMoreRequested() {
        page++;
        AppApi.getOpenCardList(MyUtils.getShopCode(getContext()),"1", mTypeCode, mSearch, page, TabYearCardFragment.this, StaticCode
                .TAG);
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        loadingDialog.cancelDialog();
        OpenCardBean openCardBean = JSONObject.parseObject(result, OpenCardBean.class);
        List<OpenCardBean.ReturnDataBean> dataBeanList = openCardBean.getReturn_data();
        allDataList.addAll(dataBeanList);
        if (allDataList.size() < 10) {
            mAdapter.setEnableLoadMore(false);
        } else {
            mAdapter.setEnableLoadMore(true);
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
    public void selectKind(String kind,String name) {
        if (kindOpen) {
            if(kind.equals("default")){
                mTypeCode = "";
                cardAll.setText("所有类别");
            }else {
                mTypeCode = kind;
                cardAll.setText(name);
            }
            resetList();
            AppApi.getOpenCardList(MyUtils.getShopCode(getContext()),"1", mTypeCode, mSearch, page, TabYearCardFragment.this, StaticCode
                    .TAG);
            closeKindll();
        } else {
            closeKindll();
        }
        container.setVisibility(View.GONE);
        //筛选
    }

    private void closeKindll() {
        kindOpen = false;
        cardAll.setCompoundDrawables(null, null, greyDrawable, null);
    }

    private void openKindll() {
        kindOpen = true;
        cardAll.setCompoundDrawables(null, null, redDrawable, null);
    }

    private void resetList() {
        allDataList.clear();
        page = 0;
        mAdapter.notifyDataSetChanged();
    }
}
