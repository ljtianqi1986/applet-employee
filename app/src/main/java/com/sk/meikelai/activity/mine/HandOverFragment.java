package com.sk.meikelai.activity.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 今日现金交接申报
 */

public class HandOverFragment extends BaseFragment {
    @BindView(R.id.icon_right)
    ImageView mIconRight;
    @BindView(R.id.history_sure)
    Button mHistorySure;
    Unbinder unbinder;

    @Override
    protected int getContentView() {
        return R.layout.activity_history;
    }

    @Override
    public void initView() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.history_sure)
    public void onViewClicked() {

        Toast.makeText(getActivity(), getResources().getString(R.string.recharge_ok), Toast.LENGTH_LONG).show();
    }
}
