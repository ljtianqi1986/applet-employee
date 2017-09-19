package com.sk.meikelai.activity.mine;

import android.os.Bundle;
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
 * Created by sk on 2017/7/6.
 */

public class MyVipActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.my_table)
    TabLayout myTable;
    @BindView(R.id.my_pager)
    ViewPager myPager;

    List<Fragment> fragmentList = new ArrayList<>();
    TodayNewVipFragment todayNewFragment;
    AllVipFragment allVipFragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_my_vip;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    private void initViews(){
        todayNewFragment = new TodayNewVipFragment();
        allVipFragment = new AllVipFragment();
        fragmentList.add(todayNewFragment);
        fragmentList.add(allVipFragment);
        myPager.setOffscreenPageLimit(2);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.my_vip), fragmentList);
        myPager.setAdapter(adapter);
        myTable.setupWithViewPager(myPager);
    }
}
