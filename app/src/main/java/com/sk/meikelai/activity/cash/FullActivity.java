package com.sk.meikelai.activity.cash;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sk.meikelai.app.MyApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by sk on 2017/8/12.
 */

public abstract class FullActivity extends AppCompatActivity{

    protected String TAG = "BaseActivity";
    protected Context mContext;
    protected Unbinder unbinder;


    protected abstract int getContentView();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        mContext = this;
        unbinder = ButterKnife.bind(this);
        MyApplication myApplication = MyApplication.getInstance();
        myApplication.getActivityList().add(this);

    }

}
