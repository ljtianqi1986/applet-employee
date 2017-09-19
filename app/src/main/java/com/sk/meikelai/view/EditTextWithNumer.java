package com.sk.meikelai.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.sk.meikelai.entity.RegionNumberEditText;

/**
 * Created by sk on 2017/9/16.
 */

public class EditTextWithNumer extends EditText {


    private Context context;
    private double max;
    private double min;
    private RegionNumberEditText.EditChangeListener editChangeListener;

    public EditTextWithNumer(Context context) {
        super(context);
        this.context = context;
    }

    public EditTextWithNumer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public EditTextWithNumer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    /**
     * 设置输入数字的范围
     *
     * @param maxNum 最大数
     * @param minNum 最小数
     */
    public void setRegion(double maxNum, double minNum) {
        this.max = maxNum;
        this.min = minNum;
    }


    public void setTextWatcher() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start >= 0) {//从一输入就开始判断，'

                    if (s.toString().contains(".")) {
                        if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                            s = s.toString().subSequence(0,
                                    s.toString().indexOf(".") + 3);
                            setText(s);
                            setSelection(s.length());
                        }
                    }
                    if (s.toString().trim().substring(0).equals(".")) {
                        s = "0" + s;
                        setText(s);
                        setSelection(2);
                    }

                    if (s.toString().startsWith("0")
                            && s.toString().trim().length() > 1) {
                        if (!s.toString().substring(1, 2).equals(".")) {
                            setText(s.subSequence(0, 1));
                            setSelection(1);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public interface EditChangeListener {
        void changeLimit(CharSequence s);
    }

    public void setEditChangeListener(RegionNumberEditText.EditChangeListener editChangeListener) {
        this.editChangeListener = editChangeListener;
    }


}
