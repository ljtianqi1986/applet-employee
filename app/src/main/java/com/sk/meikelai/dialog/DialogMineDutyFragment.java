package com.sk.meikelai.dialog;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sk.meikelai.R;
import com.sk.meikelai.activity.mine.TodayOrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class DialogMineDutyFragment extends DialogFragment {

    public static final String TAG = DialogMineDutyFragment.class.getSimpleName();
    @BindView(R.id.item_icon)
    ImageView itemIcon;
    @BindView(R.id.item_text)
    TextView itemText;
    @BindView(R.id.item_no)
    Button itemNo;
    @BindView(R.id.item_yes)
    Button itemYes;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_dialog_duty, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.item_no, R.id.item_yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_no:
                dismiss();
                TodayOrderActivity activity1 = (TodayOrderActivity) getActivity();
                activity1.applyOrder(0);
                break;
            case R.id.item_yes:
                dismiss();
                TodayOrderActivity activity2 = (TodayOrderActivity) getActivity();
                activity2.applyOrder(1);
                break;
        }
    }
}
