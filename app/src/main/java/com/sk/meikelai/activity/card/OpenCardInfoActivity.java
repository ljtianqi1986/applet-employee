package com.sk.meikelai.activity.card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.sk.meikelai.Adapter.MyPagerAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 开卡的几个种类
 */

public class OpenCardInfoActivity extends BaseActivity {

    @BindView(R.id.tab_card_title)
    TabLayout mTabLayout;                            //定义TabLayout
    @BindView(R.id.vp_card_pager)
    ViewPager mViewPager;                             //定义viewPager


    List<Fragment> list_fragment = new ArrayList<>();

    TabSavingCardFragment mSavingCardFragment;              //储值卡fragment
    TabOnceCardFragment mOnceCardFragment;            //次卡fragment
    TabYearCardFragment mYearCardFragment;                      //年fragment
    @BindView(R.id.back)
    ImageView mBack;

    @Override
    protected int getContentView() {
        return R.layout.fragment_open_card_info;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        //初始化各fragment
        mSavingCardFragment = new TabSavingCardFragment();
        mYearCardFragment = new TabYearCardFragment();
        mOnceCardFragment = new TabOnceCardFragment();

        //将fragment装进列表中
        list_fragment.add(mSavingCardFragment);
        list_fragment.add(mYearCardFragment);
        list_fragment.add(mOnceCardFragment);
        mViewPager.setOffscreenPageLimit(3);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.open_card),
                list_fragment);

        //viewpager加载adapter
        mViewPager.setAdapter(adapter);
        //TabLayout加载viewpager
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
            finish();
    }
}