package com.sk.meikelai.activity.cash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sk.meikelai.Adapter.CashSelectCardAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.utils.CropCircleTransformation;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/6/14.
 */

public class CashOrderActivity extends BaseActivity {


    @BindView(R.id.member_title)
    LinearLayout mMemberTitle;
    @BindView(R.id.cash_icon)
    ImageView mCashIcon;
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.user_number)
    TextView mUserNumber;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.center_title)
    LinearLayout mCenterTitle;
    @BindView(R.id.pay)
    ImageView mPay;
    @BindView(R.id.rc_content)
    RecyclerView mRcContent;
    @BindView(R.id.ll_add)
    LinearLayout mLlAdd;

    LinearLayoutManager linearLayoutManager;
    CashSelectCardAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_cash_order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//设置布局管理器
        mRcContent.setLayoutManager(linearLayoutManager);
//设置adapter
        mRcContent.setAdapter(mAdapter = new CashSelectCardAdapter());
    }

    @OnClick({R.id.pay, R.id.ll_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pay:
                Intent payIntent=new Intent(CashOrderActivity.this,CashOrderPayActivity.class);
                startActivity(payIntent);
                break;
            case R.id.ll_add:
                Intent addIntent=new Intent(CashOrderActivity.this,CashProductActivity.class);
                startActivityForResult(addIntent,1);
                break;
        }
    }

}
