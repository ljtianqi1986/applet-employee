package com.sk.meikelai.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by sk on 2017/7/27.
 */

public class HandOverHistoryAdapter extends BaseQuickAdapter<HandOverHistoryBean.ReturnDataBean,BaseViewHolder> {


    public HandOverHistoryAdapter(@LayoutRes int layoutResId, @Nullable List<HandOverHistoryBean.ReturnDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HandOverHistoryBean.ReturnDataBean item) {

    }
}
