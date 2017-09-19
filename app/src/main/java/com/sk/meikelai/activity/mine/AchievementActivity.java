package com.sk.meikelai.activity.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.sk.meikelai.Adapter.MyPagerAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.activity.mine.fragment.LastMonthAchievementFragment;
import com.sk.meikelai.activity.mine.fragment.ThisMonthAchievementFragment;
import com.sk.meikelai.activity.mine.fragment.TodayAchievementFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sk on 2017/7/10.
 */

public class AchievementActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.my_table)
    TabLayout myTable;
    @BindView(R.id.my_pager)
    ViewPager myPager;
    List<Fragment> fragmentList = new ArrayList<>();
    LastMonthAchievementFragment lastMonthAchievementFragment;
    ThisMonthAchievementFragment thisMonthAchievementFragment;
    TodayAchievementFragment todayAchievementFragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_achievement;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        todayAchievementFragment = new TodayAchievementFragment();
        thisMonthAchievementFragment = new ThisMonthAchievementFragment();
        lastMonthAchievementFragment = new LastMonthAchievementFragment();

        fragmentList.add(todayAchievementFragment);
        fragmentList.add(thisMonthAchievementFragment);
        fragmentList.add(lastMonthAchievementFragment);

        myPager.setOffscreenPageLimit(3);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.net_receipts), fragmentList);
        myPager.setAdapter(adapter);
        myTable.setupWithViewPager(myPager);
    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
