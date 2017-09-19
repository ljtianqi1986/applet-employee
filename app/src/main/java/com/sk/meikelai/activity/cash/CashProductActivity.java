package com.sk.meikelai.activity.cash;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sk.meikelai.Adapter.CardTabAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.entity.CashCardBean;
import com.sk.meikelai.entity.ProductListBean;
import com.sk.meikelai.entity.ProjectListBean;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.utils.StaticCode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/6/14.
 */

public class CashProductActivity extends BaseActivity {
    @BindView(R.id.select_number)
    TextView mSelectNumber;
    @BindView(R.id.next)
    TextView mNext;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.shopping)
    ImageView mShopping;

    @BindView(R.id.tab_card_title)
    TabLayout mTabLayout;                            //定义TabLayout
    @BindView(R.id.vp_card_pager)
    ViewPager mViewPager;

    private FragmentPagerAdapter fAdapter;                               //定义adapter

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表
    TabOnceCashFragment mOnceCashFragment;            //次卡项目fragment
    TabServiceCashFragment mServiceCashFragment;                      //服务项目fragment
    TabGoodsCashFragment mGoodsFragment;              //产品fragment

    private CashCardBean mCashCardBean;

    private ArrayList<CashCardBean.ReturnDataBean> onceList = new ArrayList<>();
    private ArrayList<ProjectListBean.ReturnDataBean> serviceList = new ArrayList<>();
    private ArrayList<ProductListBean.ReturnDataBean> productList = new ArrayList<>();


    int mNum = 0;


    @Override
    protected int getContentView() {
        return R.layout.activity_cash_product;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onceList = getIntent().getParcelableArrayListExtra("once");
        serviceList = getIntent().getParcelableArrayListExtra("service");
        productList = getIntent().getParcelableArrayListExtra("product");
        initView();
//        mNext.setEnabled(false);
    }

    public void initView() {

        if (SPUtils.contains(mContext, StaticCode.SP_VIP_DATA)) {
            //初始化各fragment
            mOnceCashFragment = new TabOnceCashFragment();
            mServiceCashFragment = new TabServiceCashFragment();
            mGoodsFragment = new TabGoodsCashFragment();


            //将fragment装进列表中
            list_fragment = new ArrayList<>();
            list_fragment.add(mOnceCashFragment);
            list_fragment.add(mServiceCashFragment);
            list_fragment.add(mGoodsFragment);

            //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
            list_title = new ArrayList<>();
            list_title.add(getString(R.string.cash_once));
            list_title.add(getString(R.string.cash_service));
            list_title.add(getString(R.string.cash_goods));

            //设置TabLayout的模式
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);
            //为TabLayout添加tab名称
            mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(0)));
            mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(1)));
            mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(2)));

            fAdapter = new CardTabAdapter(getSupportFragmentManager(), list_fragment, list_title);

            //viewpager加载adapter
            mViewPager.setAdapter(fAdapter);
            mViewPager.setOffscreenPageLimit(3);
            //TabLayout加载viewpager
            mTabLayout.setupWithViewPager(mViewPager);
        } else {
            //初始化各fragment
            mServiceCashFragment = new TabServiceCashFragment();
            mGoodsFragment = new TabGoodsCashFragment();


            //将fragment装进列表中
            list_fragment = new ArrayList<>();
            list_fragment.add(mServiceCashFragment);
            list_fragment.add(mGoodsFragment);

            //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
            list_title = new ArrayList<>();
            list_title.add(getString(R.string.cash_service));
            list_title.add(getString(R.string.cash_goods));

            //设置TabLayout的模式
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);
            //为TabLayout添加tab名称
            mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(0)));
            mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(1)));

            fAdapter = new CardTabAdapter(getSupportFragmentManager(), list_fragment, list_title);

            //viewpager加载adapter
            mViewPager.setAdapter(fAdapter);
            mViewPager.setOffscreenPageLimit(2);
            //TabLayout加载viewpager
            mTabLayout.setupWithViewPager(mViewPager);
        }
    }


    public void addProduct() {
        mNum++;
        setShowNumText();
    }

    public void reduceProduct() {
        mNum--;
        setShowNumText();
    }

    private void setShowNumText() {
        if (mNum == 0) {
            mShopping.setImageResource(R.mipmap.shopping_gray);
            mSelectNumber.setTextColor(this.getResources().getColor(R.color.light_gray));
            mSelectNumber.setText("未选购项目");
            mNext.setBackgroundResource(R.drawable.bg_next_click);

        } else {
            mShopping.setImageResource(R.mipmap.shopping);
            mSelectNumber.setTextColor(this.getResources().getColor(R.color.theme));
            mSelectNumber.setText("已选" + mNum + "个项目");
            mNext.setBackgroundResource(R.drawable.bg_next_click);

        }
    }


    @OnClick({R.id.next, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.next:
                calculateSelectedList();
                Intent selectItent = new Intent();
                selectItent.putExtra("once", onceList);
                selectItent.putExtra("service", serviceList);
                selectItent.putExtra("product", productList);
                setResult(RESULT_OK, selectItent);
                finish();
                break;
            case R.id.back:
                finish();//此处一定要调用finish()方法
                break;
        }
    }

    private void calculateSelectedList() {
        if (SPUtils.contains(mContext, StaticCode.SP_VIP_DATA)) {
            onceList = mOnceCashFragment.calculateSelectList();
        }
        serviceList = mServiceCashFragment.calculateSelectList();
        productList = mGoodsFragment.calculateSelectList();
    }

    public ArrayList<CashCardBean.ReturnDataBean> getOnceList() {
        return onceList;
    }

    public void setOnceList(ArrayList<CashCardBean.ReturnDataBean> onceList) {
        this.onceList = onceList;
    }

    public ArrayList<ProjectListBean.ReturnDataBean> getServiceList() {
        return serviceList;
    }

    public void setServiceList(ArrayList<ProjectListBean.ReturnDataBean> serviceList) {
        this.serviceList = serviceList;
    }

    public ArrayList<ProductListBean.ReturnDataBean> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<ProductListBean.ReturnDataBean> productList) {
        this.productList = productList;
    }
}
