package com.sk.meikelai.Adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.OpenCardBean;

import java.util.List;

/**
 * Created by 123 on 2017/6/6.
 */

public class CardYearAdapter extends BaseQuickAdapter<OpenCardBean.ReturnDataBean, BaseViewHolder> {


    public CardYearAdapter(@LayoutRes int layoutResId, @Nullable List<OpenCardBean.ReturnDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OpenCardBean.ReturnDataBean item) {
        helper.setVisible(R.id.item_line, true)
                .setVisible(R.id.rl_more, true)
                .setText(R.id.item_goods, item.getName())  //项目名称
                .setTextColor(R.id.item_goods, mContext.getResources().getColor(R.color.year_blue))//设置项目名称颜色
                .setTextColor(R.id.item_term, mContext.getResources().getColor(R.color.dark_blue))//设置有效期颜色
//                .setText(R.id.item_off, String.valueOf((int) (item.getDiscount()) + "折")) //折扣
                .setText(R.id.item_price, "售价" + String.valueOf(item.getSale_price() / 100))  //售价
                .setImageResource(R.id.item_img, R.drawable.cd_lan)          //设置左边卡类型
                .setImageResource(R.id.item_buy, R.drawable.buy_1)           //设置右边购买图案颜色
                .addOnClickListener(R.id.item_buy)                         //设置购买点击事件
                .addOnClickListener(R.id.rl_more)                           //设置more点击事件
                .addOnClickListener(R.id.item_content);                           //设置more点击事件

        //假如是12个月 则显示一年
        if (item.getUseful() == 12) {
            helper.setText(R.id.item_term, "有效期一年");
        } else {
            helper.setText(R.id.item_term, "有效期" + item.getUseful() + "个月");
        }

        StringBuilder sb = new StringBuilder();
        for (OpenCardBean.ReturnDataBean.ProjectListBean moreDataBean : item.getProject_list()) {
            sb.append(moreDataBean.getProject_name()+" , ");
        }
        sb.toString();
        if (sb.length() > 0) {
            helper.setText(R.id.item_content, sb.substring(0, sb.length() - 3));
        }
    }
}