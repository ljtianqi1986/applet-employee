package com.sk.meikelai.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.CashHandBean;

import java.util.List;

/**
 * Created by sk on 2017/7/28.
 */

public class CashApplyAdapter extends BaseQuickAdapter<CashHandBean.ReturnDataBean,BaseViewHolder> {


    public CashApplyAdapter(@LayoutRes int layoutResId, @Nullable List<CashHandBean.ReturnDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CashHandBean.ReturnDataBean item) {
        helper.setText(R.id.tv_date,item.getTime_M_D())
                .setText(R.id.should_money,String.valueOf(item.getOught_connect()/100f))
                .setText(R.id.sure_money,String.valueOf(item.getReality_connect()/100f));

        TextView already_apply = helper.getView(R.id.already_apply);
        TextView already_sure = helper.getView(R.id.already_sure);
        if(item.getDeclare_style() == 2){
            already_apply.setVisibility(View.VISIBLE);
        }else {
            already_apply.setVisibility(View.INVISIBLE);
        }
        if(item.getAuditor_style() == 2){
            already_sure.setVisibility(View.VISIBLE);
        }else {
            already_sure.setVisibility(View.INVISIBLE);
        }

    }
}
