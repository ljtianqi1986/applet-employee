package com.sk.meikelai.Adapter;


import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.OpenCardBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 储值卡item页面
 */

public class CardSavingAdapter extends BaseQuickAdapter<OpenCardBean.ReturnDataBean, BaseViewHolder> {


    public CardSavingAdapter(@LayoutRes int layoutResId, @Nullable List<OpenCardBean.ReturnDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OpenCardBean.ReturnDataBean item) {
                helper.setText(R.id.item_goods, item.getName())  //项目名称
                        .setTextColor(R.id.item_goods, mContext.getResources().getColor(R.color.saving_yellow))//设置项目名称颜色
                        .setText(R.id.item_off, String.valueOf((int)(item.getDiscount())+"折")) //折扣
                        .setText(R.id.item_price,"售价:"+String.valueOf(item.getSale_price()/100))  //售价
                        .setImageResource(R.id.item_img,R.drawable.cd_org)          //设置左边卡类型
                        .setImageResource(R.id.item_buy,R.drawable.buy_0)           //设置右边购买图案颜色
                        .setTextColor(R.id.item_term,mContext.getResources().getColor(R.color.dark_yellow))//设置有效期颜色
                        .addOnClickListener(R.id.item_buy);                         //设置购买点击事件
        //假如有赠送金额就显示到账金额
        if (item.getGift_price()>0){
            helper.setText(R.id.item_price_sum,"到账:"+String.valueOf((item.getSale_price()+item.getGift_price())/100));
            helper.setVisible(R.id.item_price_sum,true);
        }
        //假如折扣为10折 则隐藏折扣
        if (item.getDiscount()>=10){
            helper.setVisible(R.id.item_off,false);
        }else {
            helper.setVisible(R.id.item_off,true);
        }
        //假如是12个月 则显示一年
        if (item.getUseful()==12){
            helper.setText(R.id.item_term,"有效期一年");
        }else{
            helper.setText(R.id.item_term,"有效期"+item.getUseful()+"个月");
        }
    }
}
