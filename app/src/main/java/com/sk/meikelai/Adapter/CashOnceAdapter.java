package com.sk.meikelai.Adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.CashCardBean;

import java.util.List;

/**
 * 收银次卡item页面
 */

public class CashOnceAdapter extends BaseQuickAdapter<CashCardBean.ReturnDataBean, BaseViewHolder> {


    public CashOnceAdapter(@LayoutRes int layoutResId, @Nullable List<CashCardBean.ReturnDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CashCardBean.ReturnDataBean item) {
            helper.setText(R.id.item_goods,item.getProject_name())
                    .setText(R.id.item_number,"编号:"+item.getProject_number())
                    .setText(R.id.item_pinyin,"拼音码:"+item.getProject_name_pinyin())
                    .setImageResource(R.id.item_img,R.mipmap.member) //图片
                     .addOnClickListener(R.id.item_reduce)
                    .addOnClickListener(R.id.item_add);

            if (item.getCount()>0){
                helper.setVisible(R.id.item_reduce,true);
                helper.setVisible(R.id.item_num,true);
                helper.setText(R.id.item_num,String.valueOf(item.getCount()));
            }else{
                helper.setVisible(R.id.item_reduce,false);
                helper.setVisible(R.id.item_num,false);
            }

            if(item.getCardType() == 0){
                helper.setText(R.id.item_msg,"剩余:"+item.getSurplus_times()+"次");
            }else if(item.getCardType() == 1){
                helper.setText(R.id.item_msg,item.getEndTime()+"到期");
            }

    }
}
