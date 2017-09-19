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


public class DialogPayFragment extends DialogFragment {
    @BindView(R.id.img_pay)
    ImageView mImgPay;
    @BindView(R.id.pay_type)
    TextView mPayType;
    @BindView(R.id.card_type)
    TextView mCardType;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.recharge_recharge_money)
    TextView mRechargeRechargeMoney;
    @BindView(R.id.tv_recharge_money)
    TextView mTvRechargeMoney;
    @BindView(R.id.recharge_recharge_after)
    TextView mRechargeRechargeAfter;
    @BindView(R.id.tv_recharge_after)
    TextView mTvRechargeAfter;
    @BindView(R.id.pay_back)
    Button mPayBack;
    @BindView(R.id.pay_sure)
    Button mPaySure;
    Unbinder unbinder;
    private String mText;
    private String mType;
    private String mPay;
    private String mRecharge;

    public static final String TAG = DialogPayFragment.class.getSimpleName();

    public DialogPayFragment() {
        // Required empty public constructor
    }

    public static DialogPayFragment newInstance(String type, String pay, String recharge) {
        DialogPayFragment fragment = new DialogPayFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("pay", pay);
        bundle.putString("recharge", recharge);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mType = arguments.getString("type");
            mPay = arguments.getString("pay");
            mRecharge = arguments.getString("recharge");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_dialog_pay, container, false);
//        TextView Tv_Type = (TextView) view.findViewById(R.id.tv_type);
//        Tv_Type.setText(mType);
        TextView Tv_Pay = (TextView) view.findViewById(R.id.tv_recharge_money);
        Tv_Pay.setText(mPay);
        TextView Tv_Recharge = (TextView) view.findViewById(R.id.tv_recharge_after);
        Tv_Recharge.setText(mRecharge);
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
