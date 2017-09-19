package com.sk.meikelai.Adapter;


import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.CardBalanceBean;
import com.sk.meikelai.entity.OpenCardBean;
import com.sk.meikelai.entity.RechargeCard;
import com.sk.meikelai.entity.Status;
import com.sk.meikelai.entity.Worker;
import com.sk.meikelai.testdata.DataServer;
import com.sk.meikelai.utils.ToastUtils;

import java.util.List;

/**
 * 储值卡item页面
 */

public class SelectCardAdapter extends BaseQuickAdapter<CardBalanceBean.ReturnDataBean, BaseViewHolder> {

    public SelectCardAdapter(@LayoutRes int layoutResId, @Nullable List<CardBalanceBean.ReturnDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, CardBalanceBean.ReturnDataBean item) {

        helper.setText(R.id.card_type, item.getCard_name())
                .setText(R.id.card_balance, String.valueOf(item.getBalance()/100))
                .addOnClickListener(R.id.card);



        if (item.getDiscount()>=10){
            helper.setText(R.id.card_type,"储值卡");
            helper.setText(R.id.card_balance, String.valueOf(item.getBalance()/100));
        }else {
            helper.setText(R.id.card_type,String.valueOf((int)item.getDiscount())+"折卡");
            helper.setText(R.id.card_balance, String.valueOf(item.getBalance()/100));
        }

        if (item.isSelected()) {
            helper.setTextColor(R.id.card_type, mContext.getResources().getColor(R.color.white));//设置项目名称颜色
            helper.setTextColor(R.id.card_balance, mContext.getResources().getColor(R.color.white));//设置金额颜色
            helper.getView(R.id.card).setBackgroundResource(R.drawable.card_selected_bg);
        } else {
            helper.getView(R.id.card).setBackgroundResource(R.drawable.card_unselected_bg);
            helper.setTextColor(R.id.card_balance, mContext.getResources().getColor(R.color.light_gray));//设置项目名称颜色
            helper.setTextColor(R.id.card_type, mContext.getResources().getColor(R.color.light_gray));//设置金额颜色

        }
    }
}