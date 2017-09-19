package com.sk.meikelai.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/11/28.
 */

public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    protected Context mContext;
    protected View mRootView;
    protected Unbinder unbinder;

    // 标识view 是否初始化完成
    protected boolean isViewInit = false;

    protected abstract int getContentView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentView(), container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        initView();
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStart() {
        super.onStart();
        registEventBus();
    }

    @Override
    public void onStop() {
        unRegistEventBus();
        super.onStop();
    }

    protected void registEventBus() {
        //子类如果需要注册eventbus，则重写此方法
        //EventBus.getDefault().register(this);
    }

    protected void unRegistEventBus() {
        //子类如果需要注销eventbus，则重写此方法
        //EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    public abstract void initView();

}
