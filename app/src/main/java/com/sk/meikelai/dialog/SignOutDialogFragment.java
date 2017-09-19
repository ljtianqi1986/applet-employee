package com.sk.meikelai.dialog;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.sk.meikelai.R;
import com.sk.meikelai.activity.mine.MySettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sk on 2017/7/25.
 */

public class SignOutDialogFragment extends DialogFragment {


    @BindView(R.id.exit_system)
    TextView exitSystem;
    @BindView(R.id.sign_out)
    TextView signOut;
    @BindView(R.id.stay_put)
    TextView stayPut;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int style = DialogFragment.STYLE_NO_TITLE, theme = 0;
        setStyle(style, theme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_quit_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels * 14 / 18, getDialog().getWindow().getAttributes().height);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        getDialog().getWindow().setAttributes(layoutParams);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.exit_system, R.id.sign_out, R.id.stay_put})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exit_system:
                dismiss();
                MySettingsActivity activity1 = (MySettingsActivity) getActivity();
                activity1.exitSystem();
                break;
            case R.id.sign_out:
                MySettingsActivity activity2 = (MySettingsActivity) getActivity();
                activity2.exitLogin();
                break;
            case R.id.stay_put:
                dismiss();
                break;
        }
    }
}
