package com.sk.meikelai.dialog;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.sk.meikelai.R;
import com.sk.meikelai.activity.mine.MySettingsActivity;

/**
 * Created by sk on 2017/4/10.
 */

public class DialogFramentUploadPhoto extends DialogFragment implements View.OnClickListener {

    private Button tv_take_photo, tv_choose_photo, tv_cancel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int style = android.support.v4.app.DialogFragment.STYLE_NO_TITLE, theme = 0;
        setStyle(style, theme);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_update_photo, container, false);
        tv_take_photo = (Button) view.findViewById(R.id.tv_take_photo);
        tv_choose_photo = (Button) view.findViewById(R.id.tv_choose_photo);
        tv_cancel = (Button) view.findViewById(R.id.tv_cancel);
        tv_choose_photo.setOnClickListener(this);
        tv_take_photo.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        getDialog().getWindow().setAttributes(layoutParams);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        MySettingsActivity act = (MySettingsActivity) getActivity();
        switch (v.getId()) {
            case R.id.tv_take_photo:
                dismiss();
                act.startCamera();
                break;

            case R.id.tv_choose_photo:
                dismiss();
                act.ChoosePhoto();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }
}
