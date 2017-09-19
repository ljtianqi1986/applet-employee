package com.sk.meikelai.activity.card;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 123 on 2017/6/6.
 */

public class ConfirmFragment extends BaseFragment {
    @BindView(R.id.cash_icon)
    ImageView mCashIcon;
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.user_number)
    TextView mUserNumber;
    @BindView(R.id.center_title)
    LinearLayout mCenterTitle;
    @BindView(R.id.reduce)
    TextView mReduce;
    @BindView(R.id.type)
    TextView mType;
    @BindView(R.id.tv_card)
    TextView mTvCard;
    @BindView(R.id.tv_cash)
    TextView mTvCash;
    @BindView(R.id.tv_epay)
    TextView mTvEpay;
    @BindView(R.id.card_more)
    ImageView mCardMore;
    @BindView(R.id.rc_content)
    RecyclerView mRcContent;
    @Override
    protected int getContentView() {
        return R.layout.fragment_confirm;
    }

    @Override
    public void initView() {


    }
    @OnClick({R.id.reduce, R.id.tv_card, R.id.tv_cash, R.id.tv_epay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reduce:
                break;
            case R.id.tv_card:
                break;
            case R.id.tv_cash:
                break;
            case R.id.tv_epay:
                break;
        }
    }
}