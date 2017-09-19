package com.sk.meikelai.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created by sk on 2017/8/18.
 */

public class CustomSpinner extends Spinner {

    private int mSelection = -1;

    public CustomSpinner(Context context) {
        super(context);
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setSelection(int position) {

        mSelection = position;
        super.setSelection(position);
    }

    @Override
    public void setSelection(int position, boolean animate) {

        mSelection = position;
        super.setSelection(position, animate);
    }

    @Override
    public int getSelectedItemPosition() {
        return mSelection;
    }
}
