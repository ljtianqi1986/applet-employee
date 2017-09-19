package com.sk.meikelai.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.ProductListBean;

import java.util.List;

/**
 * Created by sk on 2017/7/19.
 */

public class CashProductAdapter extends BaseQuickAdapter<ProductListBean.ReturnDataBean ,BaseViewHolder> {


    public CashProductAdapter(@LayoutRes int layoutResId, @Nullable List<ProductListBean.ReturnDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductListBean.ReturnDataBean item) {

        helper.setText(R.id.item_goods, item.getName())
                .setText(R.id.item_number, "编号:" + item.getNumber())
                .setText(R.id.item_pinyin, "拼音码:" + item.getName_pinyin())
                .setText(R.id.item_msg,item.getPrice()/100f+"元")
                .setImageResource(R.id.item_img, R.mipmap.goods) //图片
                .addOnClickListener(R.id.item_reduce)
                .addOnClickListener(R.id.item_add);

        if (item.getCount() > 0) {
            helper.setVisible(R.id.item_reduce, true);
            helper.setVisible(R.id.item_num, true);
            helper.setText(R.id.item_num, String.valueOf(item.getCount()));
        } else {
            helper.setVisible(R.id.item_reduce, false);
            helper.setText(R.id.item_num, String.valueOf(item.getCount()));
        }

    }
}
