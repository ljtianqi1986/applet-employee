package com.sk.meikelai.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.MonthFuMiBean;

import java.util.List;

/**
 * Created by lenovo on 2017/7/13.
 */

public class MonthFumiAdapter extends BaseQuickAdapter<MonthFuMiBean.ReturnDataBean, BaseViewHolder> {



    public MonthFumiAdapter(@LayoutRes int layoutResId, @Nullable List<MonthFuMiBean.ReturnDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MonthFuMiBean.ReturnDataBean item) {
          helper.setText(R.id.item_date,item.getDateTime())
                  .setText(R.id.item_add,"+"+item.getAddSum()/100f)
                  .setText(R.id.item_reduce,"-"+item.getDeduct()/100f)
                  .setText(R.id.item_total,String.valueOf(item.getNums()/100f));
    }
}
