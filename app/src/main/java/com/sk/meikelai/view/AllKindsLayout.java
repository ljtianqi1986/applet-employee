package com.sk.meikelai.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.alibaba.fastjson.JSONObject;
import com.sk.meikelai.R;
import com.sk.meikelai.callback.KindSelectCallBack;
import com.sk.meikelai.entity.ProductKindBean;
import com.sk.meikelai.entity.ProjectKindBean;
import com.sk.meikelai.entity.SearchListBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sk on 2017/7/4.
 */

public class AllKindsLayout extends LinearLayout implements HandleDataCallBack {

    private KindSelectCallBack callBack;
    private FlowLayout flowLayout;
    private int tag;
    private List<RadioButton> radioButtonList = new ArrayList<>();
    private Context context;

    public AllKindsLayout(Context context, AttributeSet attrs, int defStyle, KindSelectCallBack callBack, int tag) {
        super(context, attrs, defStyle);
        this.callBack = callBack;
        this.tag = tag;
        this.context = context;
        initView(context);
    }

    public AllKindsLayout(Context context, KindSelectCallBack callBack, int tag) {
        this(context, null, callBack, tag);

    }

    public AllKindsLayout(Context context, AttributeSet attrs, KindSelectCallBack callBack, int tag) {
        this(context, attrs, 0, callBack, tag);

    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_all_kinds_view, this, true);
        flowLayout = (FlowLayout) view.findViewById(R.id.flow_ll);
        if (tag == 2) {
            AppApi.getPrpjectByCode(MyUtils.getShopCode(context), AllKindsLayout.this, 2);
        } else if (tag == 3) {
            AppApi.getProductByCode(MyUtils.getShopCode(context), AllKindsLayout.this, 3);
        } else if (tag == 4) {
            AppApi.getSearchList(MyUtils.getCode(context), AllKindsLayout.this, 4);
        }

    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        if (tag == 2) {
            ProjectKindBean bean = JSONObject.parseObject(result, ProjectKindBean.class);
            List<ProjectKindBean.ReturnDataBean> dataList = bean.getReturn_data();
            View viewDefault = LayoutInflater.from(context).inflate(R.layout.item_radio_btn, flowLayout, false);
            RadioButton radioButtonDefault = (RadioButton) viewDefault.findViewById(R.id.radio);
            radioButtonDefault.setText("全部");
            radioButtonDefault.setTag("default");
            radioButtonDefault.setChecked(true);
            radioButtonList.add(radioButtonDefault);
            flowLayout.addView(viewDefault);
            for (int i = 0; i < dataList.size(); i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_radio_btn, flowLayout, false);
                RadioButton radioButton = (RadioButton) view.findViewById(R.id.radio);
                radioButton.setText(bean.getReturn_data().get(i).getName());
                radioButton.setTag(bean.getReturn_data().get(i).getCode());
                radioButtonList.add(radioButton);
                flowLayout.addView(view);
            }
        } else if (tag == 3) {
            ProductKindBean bean = JSONObject.parseObject(result, ProductKindBean.class);
            List<ProductKindBean.ReturnDataBean> dataList = bean.getReturn_data();
            View viewDefault = LayoutInflater.from(context).inflate(R.layout.item_radio_btn, flowLayout, false);
            RadioButton radioButtonDefault = (RadioButton) viewDefault.findViewById(R.id.radio);
            radioButtonDefault.setText("全部");
            radioButtonDefault.setTag("default");
            radioButtonDefault.setChecked(true);
            radioButtonList.add(radioButtonDefault);
            flowLayout.addView(viewDefault);
            for (int i = 0; i < dataList.size(); i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_radio_btn, flowLayout, false);
                RadioButton radioButton = (RadioButton) view.findViewById(R.id.radio);
                radioButton.setText(bean.getReturn_data().get(i).getName());
                radioButton.setTag(bean.getReturn_data().get(i).getCode());
                radioButtonList.add(radioButton);
                flowLayout.addView(view);
            }
        } else if (tag == 4) {
            SearchListBean bean = JSONObject.parseObject(result, SearchListBean.class);
            List<SearchListBean.ReturnDataBean> dataList = bean.getReturn_data();
            View viewDefault = LayoutInflater.from(context).inflate(R.layout.item_radio_btn, flowLayout, false);
            RadioButton radioButtonDefault = (RadioButton) viewDefault.findViewById(R.id.radio);
            radioButtonDefault.setText("全部");
            radioButtonDefault.setTag("default");
            radioButtonDefault.setChecked(true);
            radioButtonList.add(radioButtonDefault);
            flowLayout.addView(viewDefault);
            for (int i = 0; i < dataList.size(); i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_radio_btn, flowLayout, false);
                RadioButton radioButton = (RadioButton) view.findViewById(R.id.radio);
                radioButton.setText(bean.getReturn_data().get(i).getName());
                radioButton.setTag(bean.getReturn_data().get(i).getCode());
                radioButtonList.add(radioButton);
                flowLayout.addView(view);
            }
        }

        for (int i = 0; i < radioButtonList.size(); i++) {
            final int finalI = i;
            radioButtonList.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        callBack.selectKind(buttonView.getTag().toString(),buttonView.getText().toString());
                        for (int k = 0; k < radioButtonList.size(); k++) {
                            if (k != finalI) {
                                radioButtonList.get(k).setChecked(false);
                            }
                        }
                    }
                }
            });
        }

    }

    @Override
    public void handlerFailData(String failString, int tag) {

    }

    @Override
    public void handleErrorData(String error, int tag) {

    }

}
