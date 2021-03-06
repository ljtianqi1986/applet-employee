package com.sk.meikelai.Adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.OrderFlowBean;

import java.util.List;

/**
 * 收银次卡item页面
 */

public class MineOrderAdapter extends BaseQuickAdapter<OrderFlowBean.ReturnDataBean, BaseViewHolder> {


    public MineOrderAdapter(@LayoutRes int layoutResId, @Nullable List<OrderFlowBean.ReturnDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderFlowBean.ReturnDataBean item) {
        helper.setText(R.id.order_num, "订单号:" + item.getOrder_code())
                .setText(R.id.tv_time, item.getPay_time())
                .setText(R.id.tv_cashier, "收银员:" + item.getPerson_name())
                .setText(R.id.tv_pay_way, "支付方式:" + item.getTrade_type());
        LinearLayout order_ll = helper.getView(R.id.order_container);
        order_ll.removeAllViews();
        if (item.getType() == 0) {
            if (item.getDetail_arr().size() > 0) {
                for (int i = 0; i < item.getDetail_arr().size(); i++) {
                    OrderFlowBean.ReturnDataBean.DetailArrBean orderBean = item.getDetail_arr().get(i);
                    View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_flow, order_ll, false);
                    ImageView icon = (ImageView) view.findViewById(R.id.icon);
                    icon.setVisibility(View.VISIBLE);
                    TextView tv_icon = (TextView) view.findViewById(R.id.tv_icon);
                    tv_icon.setVisibility(View.INVISIBLE);
                    TextView is_disCount = (TextView) view.findViewById(R.id.is_discount);
                    if (orderBean.getIs_discount() == 1) {
                        is_disCount.setVisibility(View.INVISIBLE);
                    } else {
                        is_disCount.setVisibility(View.VISIBLE);
                    }
                    TextView tv_js = (TextView) view.findViewById(R.id.tv_js);
                    tv_js.setText("技师 " + orderBean.getPerson_name());
                    TextView tv_num = (TextView) view.findViewById(R.id.tv_num);
                    TextView service_name = (TextView) view.findViewById(R.id.service_name);
                    if (orderBean.getPay_type() == 1 || orderBean.getPay_type() == 2) {
                        icon.setImageResource(R.mipmap.member);
                        tv_num.setText("1次");
                        service_name.setText(orderBean.getProject_name());
                    } else if (orderBean.getPay_type() == 0) {
                        if (orderBean.getService_type() == 0) {
                            icon.setImageResource(R.mipmap.project);
                            service_name.setText(orderBean.getProject_name());
                            tv_num.setText(String.valueOf(orderBean.getActual_price() / 100f));
                        } else if (orderBean.getService_type() == 1) {
                            icon.setImageResource(R.mipmap.goods);
                            service_name.setText(orderBean.getProduce_name());
                            tv_num.setText(String.valueOf(orderBean.getActual_price() / 100f));
                        }
                    }
                    order_ll.addView(view);
                }
            }
        } else if (item.getType() == 1) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_check_card, order_ll, false);
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            icon.setVisibility(View.INVISIBLE);
            TextView tv_icon = (TextView) view.findViewById(R.id.tv_icon);
            tv_icon.setVisibility(View.VISIBLE);
            TextView item_name = (TextView) view.findViewById(R.id.item_name);
            TextView get_money = (TextView) view.findViewById(R.id.get_money);
            get_money.setText("到账:" + (item.getRecharge_money() + item.getGift_money()) / 100f);
            TextView cash_money = (TextView) view.findViewById(R.id.cash_money);
            cash_money.setText((String.valueOf(item.getActual_money() / 100f) + "元"));
            TextView tv_js = (TextView) view.findViewById(R.id.tv_js);
            tv_js.setText("技师 " + item.getCardSysPersonName());
            switch (item.getRecharge_type()) {
                case "NEW_CARD":
                    tv_icon.setText("开卡");
                    tv_icon.setBackgroundResource(R.drawable.open_card_bg);
                    item_name.setText(item.getCard_name());
                    break;
                case "RECHARGE_CARD":
                    tv_icon.setText("充值");
                    tv_icon.setBackgroundResource(R.drawable.recharge_card_bg);
                    item_name.setText(item.getCard_name());
                    break;
                case "UPGRADE_CARD":
                    tv_icon.setText("升级");
                    item_name.setText(item.getCard_name() + "[升级" + item.getUpgrade_card_name() + "]");
                    tv_icon.setBackgroundResource(R.drawable.undata_card_bg);
                    break;
            }
            order_ll.addView(view);
        }
    }
}
