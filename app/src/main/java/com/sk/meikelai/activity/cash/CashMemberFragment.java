package com.sk.meikelai.activity.cash;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;
import com.sk.meikelai.utils.CropCircleTransformation;

import butterknife.BindView;

/**
 * Created by sk on 2017/6/5.
 */

public class CashMemberFragment extends BaseFragment {

    @BindView(R.id.cash_icon)
    ImageView mIcon;
    @Override
    protected int getContentView() {
        return R.layout.fragment_cash_member;
    }

    @Override
    public void initView() {

    }


}
