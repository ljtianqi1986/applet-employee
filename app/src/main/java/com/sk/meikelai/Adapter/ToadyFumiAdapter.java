package com.sk.meikelai.Adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.FuMiBean;

import java.util.List;

/**
 * Created by sk on 2017/6/30.
 */

public class ToadyFumiAdapter extends BaseMultiItemQuickAdapter<FuMiBean.ReturnDataBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ToadyFumiAdapter(List<FuMiBean.ReturnDataBean> data) {
        super(data);
        addItemType(0, R.layout.item_history_fumi);  //必须设置Item类型,否则空职指针异常
        addItemType(1, R.layout.item_history_buckle_fumi);
    }

    @Override
    protected void convert(BaseViewHolder helper, FuMiBean.ReturnDataBean item) {
        switch (item.getItemType()){
            case 0:
                if(item.getType() == 2||item.getType() == 3){
                    helper.setText(R.id.item_number,"订单号:"+item.getMainCode());
                    if(item.getType() == 2){
                        helper.setText(R.id.item_type,item.getProduce_name());
                    }else if(item.getType() == 3){
                        helper.setText(R.id.item_type,item.getProject_name());
                    }
                }else if(item.getType() == 0 || item.getType() == 1){
                    helper.setText(R.id.item_number,item.getMainCode());
                    helper.setText(R.id.item_type,item.getCard_name());
                }
                helper.setText(R.id.tv_cash,"实收金额:"+item.getCash_total()/100f+"元");
                helper.setText(R.id.item_add,"+"+item.getCommission()/100f);
                helper.setText(R.id.tv_time,item.getCreate_time());
                break;
            case 1:
                helper.setText(R.id.tv_why,"")
                        .setText(R.id.tv_pep,"")
                        .setText(R.id.item_reduce,"-"+item.getCommission()/100f)
                        .setText(R.id.tv_time,item.getCreate_time());
                break;
        }

    }

}
