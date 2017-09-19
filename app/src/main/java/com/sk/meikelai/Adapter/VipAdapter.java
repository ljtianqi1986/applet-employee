package com.sk.meikelai.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.VipBean;

import java.util.List;

/**
 * Created by lenovo on 2017/7/12.
 */

public class VipAdapter extends BaseQuickAdapter<VipBean.ReturnDataBean, BaseViewHolder> {

    public VipAdapter(@LayoutRes int layoutResId, @Nullable List<VipBean.ReturnDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VipBean.ReturnDataBean item) {
        helper.setText(R.id.tv_name, item.getPerson_name())
                .setText(R.id.phone, "手机:" + item.getPhone())
                .setText(R.id.card_num, item.getYear_count() + item.getCount_count() + item.getRecharge_count() + "张卡")
                .setText(R.id.last_time, "最近到店:" + item.getCome_time())
                .setText(R.id.total_count, "累计:" + item.getOrder_counts() + "次")
                .setText(R.id.total_money, "共消费:" + item.getSum_actual_total() / 100f + "元")
                .setText(R.id.open_card_time, "开卡时间:" + item.getMin_card_create_time());
        final LinearLayout card_ll = helper.getView(R.id.more_item);
        helper.getView(R.id.switch_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(card_ll.getVisibility() == View.VISIBLE){
                  card_ll.setVisibility(View.GONE);
              }else {
                  card_ll.setVisibility(View.VISIBLE);
              }
            }
        });
        for (int i = 0; i < item.getYear_count(); i++) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_card_details, card_ll, false);
            TextView card_name = (TextView) view.findViewById(R.id.card_name);
            TextView tv_details = (TextView) view.findViewById(R.id.tv_details);
            card_name.setText(item.getYear_arr().get(i).getName());
            tv_details.setText("有效期至:"+item.getYear_arr().get(i).getEndDate());
            card_ll.addView(view);
        }
        for (int i = 0; i < item.getCount_count(); i++) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_card_details, card_ll, false);
            TextView card_name = (TextView) view.findViewById(R.id.card_name);
            TextView tv_details = (TextView) view.findViewById(R.id.tv_details);
            card_name.setText(item.getCount_arr().get(i).getName());
            tv_details.setText("剩余:"+item.getCount_arr().get(i).getSurplus_times()+"次");
            card_ll.addView(view);
        }
        for (int i = 0; i < item.getRecharge_count(); i++) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_card_details, card_ll, false);
            TextView card_name = (TextView) view.findViewById(R.id.card_name);
            TextView tv_details = (TextView) view.findViewById(R.id.tv_details);
            card_name.setText(item.getRecharge_arr().get(i).getName());
            tv_details.setText("余额:"+item.getRecharge_arr().get(i).getBalance()/100f+"元");
            card_ll.addView(view);
        }
    }
}
