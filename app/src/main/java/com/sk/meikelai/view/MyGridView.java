package com.sk.meikelai.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * Created by ZSX on 2016/4/18.
 */
public class MyGridView extends GridView {


    public MyGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public MyGridView(Context context) {
        this(context, null);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightSpec;
        if (getLayoutParams().height == LayoutParams.WRAP_CONTENT) {

            heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        } else {

            heightSpec = heightMeasureSpec;

        }
        super.onMeasure(widthMeasureSpec, heightSpec);
    }

}
