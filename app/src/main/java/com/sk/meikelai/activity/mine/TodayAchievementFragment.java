package com.sk.meikelai.activity.mine;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.sk.meikelai.Adapter.CardTabAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 今日业绩
 */

public class TodayAchievementFragment extends BaseFragment {

    @BindView(R.id.tab_title)
    TabLayout mTabLayout;                            //定义TabLayout
    @BindView(R.id.vp_pager)
    ViewPager mViewPager;                             //定义viewPager

    private FragmentPagerAdapter fAdapter;                               //定义adapter

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表
    TabCashTodayFragment mDayFragment;              //储值卡fragment
    TabCashMonthFragment mMonthFragment;            //次卡fragment
    TabCashLastMonthFragment mLastMonthFragment;                      //年fragment

    @Override
    protected int getContentView() {
        return R.layout.fragment_today_cash;
    }


    public void initView() {


        //初始化各fragment
        mDayFragment = new TabCashTodayFragment();
        mMonthFragment = new TabCashMonthFragment();
        mLastMonthFragment = new TabCashLastMonthFragment();

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(mDayFragment);
        list_fragment.add(mMonthFragment);
        list_fragment.add(mLastMonthFragment);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add(getString(R.string.mine_today));
        list_title.add(getString(R.string.mine_month));
        list_title.add(getString(R.string.mine_last_month));

        //设置TabLayout的模式
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(2)));

        fAdapter = new CardTabAdapter(getActivity().getSupportFragmentManager(), list_fragment, list_title);

        //viewpager加载adapter
        mViewPager.setAdapter(fAdapter);
        //TabLayout加载viewpager
        mTabLayout.setupWithViewPager(mViewPager);
    }

}