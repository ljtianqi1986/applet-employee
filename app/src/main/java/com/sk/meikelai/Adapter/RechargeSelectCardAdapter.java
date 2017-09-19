package com.sk.meikelai.Adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.CardBalanceBean;

import java.util.List;

/**
 * 储值卡item页面
 */

public class RechargeSelectCardAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RechargeSelectCardAdapter(@LayoutRes int layoutResId, @Nullable List<String> items) {
        super(layoutResId, items);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_card_name,item);
        helper.addOnClickListener(R.id.rl_item);
    }

}