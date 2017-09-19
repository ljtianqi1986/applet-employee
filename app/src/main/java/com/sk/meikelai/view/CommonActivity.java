package com.sk.meikelai.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;

/**
 * 可共用的父类
 */
public class CommonActivity extends BaseActivity implements View.OnClickListener {

    public static final int VIEW_GONE_TAG = 0;
    public final static String BUNDLE_KEY_FRAGMENT = "bundle_key_fragment";
    private static final String TAG = "FLAG_TAG";
    private CommonFragmentInfo mCommonFragmentInfo;

    @Override
    protected int getContentView() {
        return R.layout.activity_common_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarTintResource(R.color.theme);
        View title_view = findViewById(R.id.title_ll);
        Intent data = getIntent();
        int fragmentKey = data.getIntExtra(BUNDLE_KEY_FRAGMENT, 0);
        mCommonFragmentInfo = CommonFragmentInfo.getCommonFragmentBeanByKey(fragmentKey);
        int leftIconRes = mCommonFragmentInfo.getmLeftIcon();
        int rightIconRes = mCommonFragmentInfo.getmRightIcon();
        int titleRes = mCommonFragmentInfo.getmTitle();
        TextView tvTitle = (TextView) findViewById(R.id.title_bar_title);
        TextView back = (TextView) findViewById(R.id.title_bar_back);
        ImageView rightIcon = (ImageView) findViewById(R.id.title_bar_right_icon);
        if (titleRes != VIEW_GONE_TAG) {
//            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(getString(titleRes));
        }
        if (leftIconRes != VIEW_GONE_TAG) {
//            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(this);
        }

        if (rightIconRes != VIEW_GONE_TAG) {
            rightIcon.setVisibility(View.VISIBLE);
            rightIcon.setImageResource(rightIconRes);
            rightIcon.setOnClickListener(this);
        }


        try {
            Fragment fragment = (Fragment) mCommonFragmentInfo.getmClz().newInstance();
            FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction();
            trans.replace(R.id.content_fragment, fragment, TAG);
            trans.commit();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
        }
    }
}
