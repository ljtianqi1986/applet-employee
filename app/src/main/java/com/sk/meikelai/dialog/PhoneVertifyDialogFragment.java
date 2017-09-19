package com.sk.meikelai.dialog;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sk.meikelai.R;
import com.sk.meikelai.common.Constants;
import com.sk.meikelai.entity.FirstEvent;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MD5;
import com.sk.meikelai.utils.MobileUtils;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.utils.ToastUtils;
import com.sk.meikelai.view.VerificationCodeButton;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.sk.meikelai.utils.StaticCode.SP_VIP_DATA;

/**
 * Created by sk on 2017/8/28.
 */

public class PhoneVertifyDialogFragment extends DialogFragment implements HandleDataCallBack {


    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.send_code)
    VerificationCodeButton sendCode;
    @BindView(R.id.ed_code)
    EditText edCode;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    Unbinder unbinder;
    private String code = "";
    private String md5Code = "";
    private int pageTag = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int style = DialogFragment.STYLE_NO_TITLE, theme = 0;
        setStyle(style, theme);
        pageTag = getArguments().getInt("tag",Constants.HOME_CASH);
        AppApi.getShopCode(MyUtils.getCode(getActivity()),PhoneVertifyDialogFragment.this,100);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_phone_vertify, container, false);
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

    @OnClick({R.id.send_code, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_code:
                if(MobileUtils.isMobileNumber(getActivity(),edPhone.getText().toString(),"")){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    String date = sdf.format(new Date());
                    AppApi.getVerificationCode(edPhone.getText().toString(),date,PhoneVertifyDialogFragment.this,0);
                }

                break;
            case R.id.btn_login:
                if(isFormat()){
                    AppApi.memberPhoneLogin(edPhone.getText().toString(), MyUtils.getShopCode(getActivity()),PhoneVertifyDialogFragment.this,1);
                }
                break;
        }
    }

    private boolean isFormat(){
        if(TextUtils.isEmpty(edPhone.getText().toString())){
            Toast.makeText(getActivity(),"请输入手机号码!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(edCode.getText().toString())){
            Toast.makeText(getActivity(),"请输入验证码!",Toast.LENGTH_SHORT).show();
            return false;
        }
        Log.v("isFormat",MD5.md5(edCode.getText().toString()));
        if(MD5.md5(edCode.getText().toString()).equals(md5Code)){
            return true;
        }
        if(!code.equals(edCode.getText().toString())){
            Toast.makeText(getActivity(),"验证码不正确!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    @Override
    public void handlerSuccessData(String result, int tag) {
        switch (tag){
            case 0:
                sendCode.startTimer();
                ToastUtils.showToast(getActivity(), getString(R.string.verification_code_sended));
                JSONObject jsonObject = JSON.parseObject(result);
                code = jsonObject.getString("return_info");
                edCode.requestFocus();
                break;
            case 1:
                Toast.makeText(getActivity(),"认证成功!",Toast.LENGTH_SHORT).show();
                SPUtils.put(getActivity(), SP_VIP_DATA, result);
                EventBus.getDefault().post(new FirstEvent("refresh"));
                EventBus.getDefault().post(new FirstEvent("cash"));
                if(pageTag == Constants.HOME_OPEN){
                    EventBus.getDefault().post(new FirstEvent("open"));
                }
                if (pageTag == Constants.HOME_RECHARGE){
                    EventBus.getDefault().post(new FirstEvent("recharge"));
                }
                dismiss();
                break;
            case 100:
                JSONObject jsonObject1 = JSON.parseObject(result);
                md5Code = jsonObject1.getString("return_data");
                break;

        }
    }

    @Override
    public void handlerFailData(String failString, int tag) {

    }

    @Override
    public void handleErrorData(String error, int tag) {

    }
}
