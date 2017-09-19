package com.sk.meikelai.activity.cash;

import android.content.Context;
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
import com.sk.meikelai.Adapter.CashProjectAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;
import com.sk.meikelai.callback.KindSelectCallBack;
import com.sk.meikelai.callback.PriceSelectedCallBack;
import com.sk.meikelai.entity.ProjectListBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.view.AllKindsLayout;
import com.sk.meikelai.view.AllPriceLayout;
import com.sk.meikelai.view.CashAddNumEvent;
import com.sk.meikelai.view.CashReduceNumEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * 收银-服务项目页面
 */

public class TabServiceCashFragment extends BaseFragment implements HandleDataCallBack, BaseQuickAdapter.RequestLoadMoreListener, KindSelectCallBack, PriceSelectedCallBack {
    @BindView(R.id.rc_content)
    RecyclerView mRecyclerView;
    LinearLayoutManager linearLayoutManager;
    CashProjectAdapter mAdapter;
    @BindView(R.id.money_all)
    TextView mMoneyAll;
    @BindView(R.id.card_all)
    TextView mCardAll;
    @BindView(R.id.search_ll)
    TextView mSearchLl;
    @BindView(R.id.rl_function)
    RelativeLayout mRlFunction;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;
    @BindView(R.id.search_back)
    ImageView mSearchBack;
    @BindView(R.id.container)
    FrameLayout container;
    Unbinder unbinder;
    private int mNumber = 0;
    private int mSnumber = 0;
    private TextView mTv_number;
    private ImageView mImg_reduce;


    //价格 产品类别
    private AllKindsLayout allKindsLayout;
    private AllPriceLayout allPriceLayout;

    //指示器
    private Drawable redDrawable;
    private Drawable greyDrawable;
    private boolean isopen = false;
    private boolean moneyOpen = false;
    private boolean kindOpen = false;

    private String mType = "";
    private String mTypeCode = "";
    private String mSearch = "";
    private String[] mPrice = new String[]{"", ""};

    private static int Count_Tag = 0;
    private List<ProjectListBean.ReturnDataBean> allDataList = new ArrayList<>();

    //选择的项目
    private List<ProjectListBean.ReturnDataBean> selectedList = new ArrayList<>();

    private CashProductActivity mActivity;
    private int page = 0;
    private boolean isLoadMore = false;

    @Override
    protected int getContentView() {
        return R.layout.fragment_function_cash;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = (CashProductActivity) getActivity();
    }

    @Override
    public void initView() {
        initSelectList();
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
                    mSearchLl.setText(mSearch);
                    AppApi.getServiceCard(MyUtils.getCode(mContext), mTypeCode, mPrice[0], mPrice[1], mSearch, page, TabServiceCashFragment.this, Count_Tag);
                }
                return false;
            }
        });


        initDrawable();

        allKindsLayout = new AllKindsLayout(mContext, this, 2);
        allPriceLayout = new AllPriceLayout(mContext, this);

        container.addView(allKindsLayout);
        container.addView(allPriceLayout);

//设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CashProjectAdapter(R.layout.item_service, allDataList);
        mAdapter.setOnLoadMoreListener(TabServiceCashFragment.this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

//设置adapter的子控件点击事件
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mTv_number = (TextView) mAdapter.getViewByPosition(mRecyclerView, position, R.id.item_num);
                mImg_reduce = (ImageView) mAdapter.getViewByPosition(mRecyclerView, position, R.id.item_reduce);
                mNumber = Integer.parseInt(mTv_number.getText().toString());
                switch (view.getId()) {
                    case R.id.item_reduce:
                        valueReduce(position);
                        EventBus.getDefault().post(
                                new CashReduceNumEvent(mSnumber + ""));
                        break;
                    case R.id.item_add:
                        valueAdd(position);
                        EventBus.getDefault().post(
                                new CashAddNumEvent(mSnumber + ""));
                        break;
                }
            }
        });

        AppApi.getServiceCard(MyUtils.getCode(mContext), mTypeCode, mPrice[0], mPrice[1], mSearch, page, TabServiceCashFragment.this, Count_Tag);

    }

    //初始化指示器
    private void initDrawable() {
        redDrawable = getResources().getDrawable(R.drawable.up_red);
        redDrawable.setBounds(0, 0, redDrawable.getMinimumWidth(), redDrawable.getMinimumHeight());
        greyDrawable = getResources().getDrawable(R.drawable.down_grey);
        greyDrawable.setBounds(0, 0, greyDrawable.getMinimumWidth(), greyDrawable.getMinimumHeight());
    }


    private void valueReduce(int postion) {
        if (allDataList != null && allDataList.size() > 0) {
            int count = allDataList.get(postion).getCount() - 1;
            if (count >= 0) {
                allDataList.get(postion).setCount(count);
                reduceSelectedList(allDataList.get(postion));
            } else {
                allDataList.get(postion).setCount(0);
            }
        }
        mActivity.reduceProduct();

        mAdapter.notifyDataSetChanged();

    }

    private void valueAdd(int postion) {
        if (allDataList != null && allDataList.size() > 0) {
            int count = allDataList.get(postion).getCount() + 1;
            if (count >= 0) {
                allDataList.get(postion).setCount(count);
                addSelectedList(allDataList.get(postion));
            } else {
                allDataList.get(postion).setCount(0);
            }

        }
        mActivity.addProduct();
        mAdapter.notifyDataSetChanged();
    }

    private void reduceSelectedList(ProjectListBean.ReturnDataBean bean) {
        int p = -1;
        for (int i = 0; i < selectedList.size(); i++) {
            if (bean.getCode().equals(selectedList.get(i).getCode())) {
                p = i;
            }
        }
        if (p >= 0) {
            selectedList.remove(p);
        }
    }

    private void addSelectedList(ProjectListBean.ReturnDataBean bean) {
        selectedList.add(bean);
    }


    @OnClick({R.id.money_all, R.id.card_all, R.id.search_ll, R.id.search_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.money_all:
                if (moneyOpen) {
                    closeMoneyll();
                    container.setVisibility(View.GONE);
                } else {
                    if (kindOpen) {
                        closeKindll();
                    } else {
                        container.setVisibility(View.VISIBLE);
                    }
                    allPriceLayout.setVisibility(View.VISIBLE);
                    allKindsLayout.setVisibility(View.GONE);
                    openMoneyll();
                }
                break;
            case R.id.card_all:
                if (kindOpen) {
                    closeKindll();
                    container.setVisibility(View.GONE);
                } else {
                    if (moneyOpen) {
                        closeMoneyll();
                    } else {
                        container.setVisibility(View.VISIBLE);
                    }
                    allPriceLayout.setVisibility(View.GONE);
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
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.search_back:
                resetList();
                mRlFunction.setVisibility(View.VISIBLE);
                mLlSearch.setVisibility(View.GONE);
                mSearch = mEtSearch.getText().toString();
                AppApi.getServiceCard(MyUtils.getCode(mContext), mTypeCode, mPrice[0], mPrice[1], mSearch, page, TabServiceCashFragment.this, Count_Tag);
                mRlFunction.setVisibility(View.VISIBLE);
                mLlSearch.setVisibility(View.GONE);
                if (!mSearch.equals("")) {
                    mSearchLl.setText(mSearch);
                } else {
                    mSearchLl.setText(R.string.card_search);
                }
                InputMethodManager imm1 = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm1.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
        }
    }

    private void closeMoneyll() {
        moneyOpen = false;
        mMoneyAll.setCompoundDrawables(null, null, greyDrawable, null);
    }

    private void openMoneyll() {
        moneyOpen = true;
        mMoneyAll.setCompoundDrawables(null, null, redDrawable, null);
    }

    private void closeKindll() {
        kindOpen = false;
        mCardAll.setCompoundDrawables(null, null, greyDrawable, null);
    }

    private void openKindll() {
        kindOpen = true;
        mCardAll.setCompoundDrawables(null, null, redDrawable, null);
    }


    @Override
    public void handlerSuccessData(String result, int tag) {
        ProjectListBean bean = JSONObject.parseObject(result, ProjectListBean.class);
        List<ProjectListBean.ReturnDataBean> dataBeanList = bean.getReturn_data();
        allDataList.addAll(dataBeanList);
        for (int i = 0; i < selectedList.size(); i++) {
            for (int k = 0; k < allDataList.size(); k++) {
                if (allDataList.get(k).getCode().equals(selectedList.get(i).getCode())) {
                    int count = allDataList.get(k).getCount();
                    count++;
                    allDataList.get(k).setCount(count);
                }
            }
        }
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

    }

    @Override
    public void handleErrorData(String error, int tag) {

    }

    @Override
    public void onLoadMoreRequested() {
        isLoadMore = true;
        page++;
        AppApi.getServiceCard(MyUtils.getCode(mContext), mTypeCode, mPrice[0], mPrice[1], mSearch, page, TabServiceCashFragment.this, Count_Tag);
    }

    @Override
    public void selectKind(String kind, String name) {
        if (kindOpen) {
            if (kind.equals("default")) {
                mTypeCode = "";
                mCardAll.setText("所有类别");
            } else {
                mTypeCode = kind;
                mCardAll.setText(name);
            }
            resetList();
            AppApi.getServiceCard(MyUtils.getCode(mContext), mTypeCode, mPrice[0], mPrice[1], mSearch, page, TabServiceCashFragment.this, Count_Tag);
            closeKindll();
        } else {
            closeKindll();
        }
        container.setVisibility(View.GONE);
    }


    @Override
    public void selectPrice(String price) {
        if (price.contains("-")) {
            mPrice = price.split("-");
            mMoneyAll.setText(price);
        } else if (price.equals("全部")) {
            mPrice[0] = "";
            mPrice[1] = "";
            mMoneyAll.setText("全部价格");
        } else {
            mPrice[0] = price.substring(1, price.length());
            mPrice[1] = "";
            mMoneyAll.setText(price);
        }
        if (moneyOpen) {
            resetList();
            AppApi.getServiceCard(MyUtils.getCode(mContext), mTypeCode, mPrice[0], mPrice[1], mSearch, page, TabServiceCashFragment.this, Count_Tag);
            closeMoneyll();
        } else {
            closeMoneyll();
        }
        container.setVisibility(View.GONE);
    }

    public ArrayList<ProjectListBean.ReturnDataBean> calculateSelectList() {
        ArrayList<ProjectListBean.ReturnDataBean> datalist = new ArrayList<>();
        if(selectedList.size()>0){
            for (int i = 0; i < selectedList.size(); i++) {
                ProjectListBean.ReturnDataBean bean = selectedList.get(i);
                datalist.add(bean);
            }
        }else {
            datalist.clear();
            datalist = new ArrayList<>();
        }
        return datalist;
    }

    private void resetList() {
        allDataList.clear();
        page = 0;
        mAdapter.notifyDataSetChanged();
    }

    private void initSelectList() {
        ArrayList<ProjectListBean.ReturnDataBean> datalist = new ArrayList<>();
        CashProductActivity activity = (CashProductActivity) getActivity();
        datalist = activity.getServiceList();
        if (datalist != null) {
            for (int i = 0; i < datalist.size(); i++) {
                activity.addProduct();
                addSelectedList(datalist.get(i));
            }
        }
    }
}

