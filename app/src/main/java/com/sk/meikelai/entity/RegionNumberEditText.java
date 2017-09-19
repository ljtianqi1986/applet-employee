package com.sk.meikelai.entity;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by sk on 2017/7/18.
 */

public class RegionNumberEditText extends EditText {


    private Context context;
    private double max;
    private double min;
    private EditChangeListener editChangeListener;

    public RegionNumberEditText(Context context) {
        super(context);
        this.context = context;
    }

    public RegionNumberEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public RegionNumberEditText(Context context, AttributeSet attrs, int defStyleAttr) {
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

                    if (min >=0 && max >=0) {
                        try {
                            double num;
                            if (s != null && s.length() > 0) {
                                num = Double.parseDouble(s.toString());
                            } else {
                                num = 0;
                            }
                            //判断当前edittext中的数字(可能一开始Edittext中有数字)是否大于max
                            if (num > max) {
                                s = String.valueOf(max);//如果大于max，则内容为max
                                setText(s);

                            } else if (num < min) {
                                s = String.valueOf(min);//如果小于min,则内容为min
                                setText(s);
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                        //edittext中的数字在max和min之间，则不做处理，正常显示即可。
                        if(editChangeListener!=null){
                            editChangeListener.changeLimit(s);
                        }
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public interface EditChangeListener{
        void changeLimit(CharSequence s);
    }

    public void setEditChangeListener(EditChangeListener editChangeListener){
        this.editChangeListener = editChangeListener;
    }


}
