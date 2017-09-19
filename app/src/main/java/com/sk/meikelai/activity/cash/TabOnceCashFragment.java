package com.sk.meikelai.activity.cash;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sk.meikelai.Adapter.CashOnceAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;
import com.sk.meikelai.callback.KindSelectCallBack;
import com.sk.meikelai.callback.PriceSelectedCallBack;
import com.sk.meikelai.entity.CashCardBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.utils.StaticCode;
import com.sk.meikelai.view.AllKindsLayout;
import com.sk.meikelai.view.AllPriceLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * 收银-次卡项目页面
 */

public class TabOnceCashFragment extends BaseFragment implements HandleDataCallBack, PriceSelectedCallBack, KindSelectCallBack,
        BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rc_content)
    RecyclerView mRecyclerView;
    LinearLayoutManager linearLayoutManager;
    CashOnceAdapter mAdapter;
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
    private int mNumber = 0;
    private int mSnumber = 0;
    private TextView mTv_number;
    private ImageView mImg_reduce;


    private static int Count_Tag = 0;

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
    private List<CashCardBean.ReturnDataBean> allDataList = new ArrayList<>();

    //选择的项目
    private List<CashCardBean.ReturnDataBean> selectedList = new ArrayList<>();


    private CashProductActivity mActivity;
    private int page = 0;
    private boolean isLoadMore = false;

    @Override
    protected int getContentView() {
        return R.layout.fragment_function_cash;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
                    AppApi.getOnceCard(MyUtils.getCashUserCode(mContext), MyUtils.getShopCode(mContext), mTypeCode, mPrice[0], mPrice[1], mSearch, page, TabOnceCashFragment.this, Count_Tag);
                }
                return false;
            }
        });

        //初始化布局
        initDrawable();
        allKindsLayout = new AllKindsLayout(mContext, this, 2);
        allPriceLayout = new AllPriceLayout(mContext, this);

        container.addView(allKindsLayout);
        container.addView(allPriceLayout);

//设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CashOnceAdapter(R.layout.item_cash, allDataList);
        mAdapter.setOnLoadMoreListener(TabOnceCashFragment.this, mRecyclerView);
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
                        break;
                    case R.id.item_add:
                        valueAdd(position);
                        break;
                }
            }
        });

        if (SPUtils.contains(mContext, StaticCode.SP_VIP_DATA)) {
            AppApi.getOnceCard(MyUtils.getCashUserCode(mContext), MyUtils.getShopCode(mContext), mTypeCode, mPrice[0], mPrice[1], mSearch, page, TabOnceCashFragment.this, Count_Tag);
        }


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

    private void reduceSelectedList(CashCardBean.ReturnDataBean bean) {
        int p = -1;
        for (int i = 0; i < selectedList.size(); i++) {
            if (bean.getProject_code().equals(selectedList.get(i).getProject_code())&& bean.getCard_code().equals(selectedList.get(i).getCard_code())) {
                p = i;
            }
        }
        if (p >= 0) {
            selectedList.remove(p);
        }
    }

    private void addSelectedList(CashCardBean.ReturnDataBean bean) {
        selectedList.add(bean);
    }

    private void valueAdd(int postion) {
        if (allDataList != null && allDataList.size() > 0) {
            if (allDataList.get(postion).getCardType() == 0) {
                if (allDataList.get(postion).getCount() < allDataList.get(postion).getSurplus_times()) {
                    int count = allDataList.get(postion).getCount() + 1;
                    if (count >= 0) {
                        allDataList.get(postion).setCount(count);
                        addSelectedList(allDataList.get(postion));
                    } else {
                        allDataList.get(postion).setCount(0);
                    }
                    mActivity.addProduct();
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "剩余次数不足!", Toast.LENGTH_SHORT).show();
                }

            } else if (allDataList.get(postion).getCardType() == 1) {
                int count = allDataList.get(postion).getCount() + 1;
                if (count >= 0) {
                    allDataList.get(postion).setCount(count);
                    addSelectedList(allDataList.get(postion));
                } else {
                    allDataList.get(postion).setCount(0);
                }
                mActivity.addProduct();
                mAdapter.notifyDataSetChanged();
            }
        }

    }


    //初始化指示器
    private void initDrawable() {
        redDrawable = getResources().getDrawable(R.drawable.up_red);
        redDrawable.setBounds(0, 0, redDrawable.getMinimumWidth(), redDrawable.getMinimumHeight());
        greyDrawable = getResources().getDrawable(R.drawable.down_grey);
        greyDrawable.setBounds(0, 0, greyDrawable.getMinimumWidth(), greyDrawable.getMinimumHeight());
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
                AppApi.getOnceCard(MyUtils.getCashUserCode(mContext), MyUtils.getShopCode(mContext), mTypeCode, mPrice[0], mPrice[1], mSearch, page, TabOnceCashFragment.this, Count_Tag);
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

    @Override
    public void handlerSuccessData(String result, int tag) {
        CashCardBean cashCardBean = JSONObject.parseObject(result, CashCardBean.class);
        List<CashCardBean.ReturnDataBean> dataBeanList = cashCardBean.getReturn_data();
        allDataList.addAll(dataBeanList);
        for (int i = 0; i < selectedList.size(); i++) {
            for (int k = 0; k < allDataList.size(); k++) {
                if (allDataList.get(k).getProject_code().equals(selectedList.get(i).getProject_code()) && allDataList.get(k).getCard_code().equals(selectedList.get(i).getCard_code())) {
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
            if (MyUtils.getCashUserCode(mContext) != null || MyUtils.getCashUserCode(mContext).length() >= 0) {
                AppApi.getOnceCard(MyUtils.getCashUserCode(mContext), MyUtils.getShopCode(mContext), mTypeCode, mPrice[0], mPrice[1], mSearch, page, TabOnceCashFragment.this, Count_Tag);
            }
            closeMoneyll();
        } else {
            closeMoneyll();
        }
        container.setVisibility(View.GONE);
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
            if (MyUtils.getCashUserCode(mContext) != null || MyUtils.getCashUserCode(mContext).length() >= 0) {
                AppApi.getOnceCard(MyUtils.getCashUserCode(mContext), MyUtils.getShopCode(mContext), mTypeCode, mPrice[0], mPrice[1], mSearch, page, TabOnceCashFragment.this, Count_Tag);
            }
            closeKindll();
        } else {
            closeKindll();
        }
        container.setVisibility(View.GONE);
    }

    @Override
    public void onLoadMoreRequested() {
        isLoadMore = true;
        page++;
        if (MyUtils.getCashUserCode(mContext) != null || MyUtils.getCashUserCode(mContext).length() >= 0) {
            AppApi.getOnceCard(MyUtils.getCashUserCode(mContext), MyUtils.getShopCode(mContext), mTypeCode, mPrice[0], mPrice[1], mSearch, page, TabOnceCashFragment.this, Count_Tag);
        }
    }

    public ArrayList<CashCardBean.ReturnDataBean> calculateSelectList() {
        ArrayList<CashCardBean.ReturnDataBean> datalist = new ArrayList<>();
        if(selectedList.size()>0){
            for (int i = 0; i < selectedList.size(); i++) {
                CashCardBean.ReturnDataBean bean = selectedList.get(i);
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
        ArrayList<CashCardBean.ReturnDataBean> datalist = new ArrayList<>();
        CashProductActivity activity = (CashProductActivity) getActivity();
        datalist = activity.getOnceList();
        if (datalist != null) {
            for (int i = 0; i < datalist.size(); i++) {
                activity.addProduct();
                addSelectedList(datalist.get(i));
            }
        }
    }

}
