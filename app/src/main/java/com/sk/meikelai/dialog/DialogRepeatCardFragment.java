package com.sk.meikelai.dialog;


import android.os.Bundle;
import android.support.annotation.Nullable;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class DialogRepeatCardFragment extends DialogFragment {
    @BindView(R.id.pay_back)
    Button mPayBack;
    @BindView(R.id.pay_sure)
    Button mPaySure;
    Unbinder unbinder;

    public static final String TAG = DialogRepeatCardFragment.class.getSimpleName();

    public DialogRepeatCardFragment() {
        // Required empty public constructor
    }

    public static DialogRepeatCardFragment newInstance() {
        DialogRepeatCardFragment fragment = new DialogRepeatCardFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("type", type);
//        bundle.putString("pay", pay);
//        bundle.putString("recharge", recharge);
//        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle arguments = getArguments();
//        if (arguments != null) {
//            mType = arguments.getString("type");
//            mPay = arguments.getString("pay");
//            mRecharge = arguments.getString("recharge");
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_dialog_repeat_card, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels-100, getDialog().getWindow().getAttributes().height);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.pay_back, R.id.pay_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pay_back:
                dismiss();
                break;
            case R.id.pay_sure:
                if (mOnSureSetListener != null) {
                    mOnSureSetListener.onSureSet("");
                    dismiss();
                }
                break;
        }
    }
    //确定按钮回调接口
    private OnSureSetListener mOnSureSetListener;

    public void setOnSureSetListener(OnSureSetListener mOnSureSetListener) {
        this.mOnSureSetListener = mOnSureSetListener;
    }

    public interface OnSureSetListener {
        void onSureSet(String sure);
    }

}
