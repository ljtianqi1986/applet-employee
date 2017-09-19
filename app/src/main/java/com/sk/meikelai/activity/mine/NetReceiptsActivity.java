package com.sk.meikelai.activity.mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.sk.meikelai.Adapter.MyPagerAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.activity.mine.fragment.LastMonthNewRecepitsFragment;
import com.sk.meikelai.activity.mine.fragment.ThisMonthNetRecepitsFragment;
import com.sk.meikelai.activity.mine.fragment.TodayNetRecepitsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sk on 2017/7/10.
 */

public class NetReceiptsActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.my_table)
    TabLayout myTable;
    @BindView(R.id.my_pager)
    ViewPager myPager;

    List<Fragment> fragmentList = new ArrayList<>();
    LastMonthNewRecepitsFragment lastMonthNewRecepitsFragment;
    ThisMonthNetRecepitsFragment thisMonthNetRecepitsFragment;
    TodayNetRecepitsFragment todayNetRecepitsFragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_net_recepits;
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
        todayNetRecepitsFragment = new TodayNetRecepitsFragment();
        thisMonthNetRecepitsFragment = new ThisMonthNetRecepitsFragment();
        lastMonthNewRecepitsFragment = new LastMonthNewRecepitsFragment();

        fragmentList.add(todayNetRecepitsFragment);
        fragmentList.add(thisMonthNetRecepitsFragment);
        fragmentList.add(lastMonthNewRecepitsFragment);

        myPager.setOffscreenPageLimit(3);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.net_receipts), fragmentList);
        myPager.setAdapter(adapter);
        myTable.setupWithViewPager(myPager);
    }
}
