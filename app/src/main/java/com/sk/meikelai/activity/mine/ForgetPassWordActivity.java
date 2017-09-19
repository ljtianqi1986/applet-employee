package com.sk.meikelai.activity.mine;

import android.os.Bundle;
import android.widget.ImageView;

import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/22.
 */

public class ForgetPassWordActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView mBack;

    @Override
    protected int getContentView() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
