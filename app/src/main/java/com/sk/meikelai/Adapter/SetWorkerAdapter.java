package com.sk.meikelai.Adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.WorkerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 储值卡item页面
 */

public class SetWorkerAdapter extends BaseQuickAdapter<WorkerBean.ReturnDataBean, BaseViewHolder> {

    private List<WorkerBean.ReturnDataBean> dataBeanList = new ArrayList<>();

    public SetWorkerAdapter(@LayoutRes int layoutResId, @Nullable List<WorkerBean.ReturnDataBean> data) {
        super(layoutResId, data);
        dataBeanList = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final WorkerBean.ReturnDataBean item) {
        if (helper.getAdapterPosition() == 0) {
            helper.setVisible(R.id.type, true);
            item.setIs_main("1");
        } else {
            helper.setVisible(R.id.type, false);
            item.setIs_main("0");
        }
        helper.setText(R.id.et_money, String.valueOf(item.getCountMoney() / 100));
        helper.setText(R.id.user_name, item.getPerson_name()).addOnClickListener(R.id.reduce);
        EditText countMoney = helper.getView(R.id.et_money);
        final int  pos = helper.getAdapterPosition();
        countMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    int money = Integer.parseInt(s.toString());
                    dataBeanList.get(pos).setCountMoney(money*100);
                } else {
                    dataBeanList.get(pos).setCountMoney(0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public List<WorkerBean.ReturnDataBean> getDataBeanList(){
        return dataBeanList;
    }


}
