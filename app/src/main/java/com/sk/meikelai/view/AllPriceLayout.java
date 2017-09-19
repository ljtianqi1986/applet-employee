package com.sk.meikelai.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.sk.meikelai.R;
import com.sk.meikelai.callback.PriceSelectedCallBack;

import java.util.ArrayList;

/**
 * Created by sk on 2017/7/4.
 */

public class AllPriceLayout extends LinearLayout {

    private PriceSelectedCallBack callBack;

    public AllPriceLayout(Context context, AttributeSet attrs, int defStyle, PriceSelectedCallBack callBack) {
        super(context, attrs, defStyle);
        this.callBack = callBack;
        initView(context);
    }

    public AllPriceLayout(Context context, PriceSelectedCallBack callBack) {
        this(context, null, callBack);
    }

    public AllPriceLayout(Context context, AttributeSet attrs, PriceSelectedCallBack callBack) {
        this(context, attrs, 0, callBack);

    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_all_price_view, this, true);
        RadioButton radioButton1 = (RadioButton) view.findViewById(R.id.radio_all);
        RadioButton radioButton2 = (RadioButton) view.findViewById(R.id.radio_50);
        RadioButton radioButton3 = (RadioButton) view.findViewById(R.id.radio_100);
        RadioButton radioButton4 = (RadioButton) view.findViewById(R.id.radio_200);
        RadioButton radioButton5 = (RadioButton) view.findViewById(R.id.radio_500);
        RadioButton radioButton6 = (RadioButton) view.findViewById(R.id.radio_more_500);

        final ArrayList<RadioButton> arrayList = new ArrayList<>();
        arrayList.add(radioButton1);
        arrayList.add(radioButton2);
        arrayList.add(radioButton3);
        arrayList.add(radioButton4);
        arrayList.add(radioButton5);
        arrayList.add(radioButton6);

        for (int i = 0; i < arrayList.size(); i++) {
            final int finalI = i;
            arrayList.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        callBack.selectPrice(buttonView.getText().toString());
                        for (int k = 0; k < arrayList.size(); k++) {
                            if (k != finalI) {
                                arrayList.get(k).setChecked(false);
                            }
                        }
                    }
                }
            });
        }
    }

}
