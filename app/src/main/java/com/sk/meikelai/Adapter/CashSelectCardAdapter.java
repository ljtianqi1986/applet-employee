package com.sk.meikelai.Adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.Status;
import com.sk.meikelai.testdata.DataServer;

/**
 * 收银次卡item页面
 */

public class CashSelectCardAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {


    public CashSelectCardAdapter() {
        super(R.layout.item_cash_select_card, DataServer.getSampleData2(2));
    }

    //设置item的内部信息
    @Override
    protected void convert(BaseViewHolder viewHolder, Status item) {
//        viewHolder.setText(R.id.item_goods, item.getUserName())  //产品名称
//                .setText(R.id.item_pinyin, item.getText())     //拼音
//                .setText(R.id.item_number, item.getUserAvatar()) //编号
//                .setText(R.id.item_msg, item.getCreatedAt())  //剩余次数
//                .setImageResource(R.id.item_img,R.mipmap.member) //图片
//                .addOnClickListener(R.id.item_reduce)
//                .addOnClickListener(R.id.item_add);

    }


}
