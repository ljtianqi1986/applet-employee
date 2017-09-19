package com.sk.meikelai.Adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.AppointmentBean;

import java.util.List;

/**
 * Created by 123 on 2017/6/6.
 */

public class AppointmentTomorrowAdapter extends BaseQuickAdapter<AppointmentBean.ReturnDataBean, BaseViewHolder> {

    public AppointmentTomorrowAdapter(int layoutId, List<AppointmentBean.ReturnDataBean> dateBeen) {
        super(layoutId, dateBeen);
    }


    @Override
    protected void convert(BaseViewHolder viewHolder, AppointmentBean.ReturnDataBean item) {
        viewHolder.setText(R.id.item_time, item.getAppointmentTime())
                .setText(R.id.item_phone, item.getTelephone())
                .setText(R.id.tv_name, item.getBase_user_name())
                .setText(R.id.tv_appointment, item.getProject_type_name())
                .setText(R.id.tv_memo, item.getRemark().length()>0?item.getRemark():"没特殊要求")
                .setText(R.id.tv_content, item.getContent().length()>0?item.getContent():"暂无任何备注信息。");
    }

}
