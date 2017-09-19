package com.sk.meikelai.activity.mine;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;
import com.sk.meikelai.activity.main.MainActivity;
import com.sk.meikelai.entity.WorkerInfoBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.DeviceUtils;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.view.LoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class WorkerLoginFragment extends BaseFragment implements HandleDataCallBack {

    public static final String SP_USER_DATA = "sp_user_data";
    public static final String IS_AUTO_LOGIN = "is_auto_login";
    public static final String IS_REMEMBER_PASSWORD = "is_remember_password";
    public static final String MY_PASSWORD = "my_password";
    public static final String MY_NUMBER = "my_number";


    LoadingDialog loadingDialog;
    Unbinder unbinder;


    //设备信息
    private String device_ver;
    String device_info = "";
    String device_ip = "";

    @BindView(R.id.edt_login)
    EditText mWorkerNumber;
    @BindView(R.id.edt_pwd)
    EditText mPwd;
    @BindView(R.id.cb_remember)
    CheckBox mCbRemember;
    @BindView(R.id.tv_remember)
    TextView mTvRemember;
    @BindView(R.id.automatic)
    CheckBox mAutomatic;
    @BindView(R.id.tv_automatic)
    TextView mTvAutomatic;
    @BindView(R.id.forget)
    TextView mForget;
    @BindView(R.id.btn_login)
    TextView mLogin;

    @Override
    protected int getContentView() {
        return R.layout.fragment_worker_login;
    }

    @Override
    public void initView() {
        loadingDialog = MyUtils.getLoadingDialog(mContext, false);
        loadingDialog.setCancelable(false);

        mWorkerNumber.setText((String) SPUtils.get(mContext, MY_NUMBER, ""));
        //是否记住密码
        if ((int) SPUtils.get(mContext, IS_REMEMBER_PASSWORD, 0) == 1) {
            mPwd.setText((String) SPUtils.get(mContext, MY_PASSWORD, ""));
            mCbRemember.setChecked(true);
        }

        if ((int) SPUtils.get(mContext, IS_AUTO_LOGIN, 0) == 1) {
            mAutomatic.setChecked(true);
        }

        device_info = DeviceUtils.getDeviceInfo(mContext);
        device_ip = DeviceUtils.getIp();
        device_ver = DeviceUtils.getVersionCode(mContext);
        Log.v("initView", device_info);
        Log.v("initView", device_ip);
        Log.v("initView", device_ver);
    }

    @OnClick({R.id.cb_remember, R.id.automatic, R.id.forget, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_remember:
                break;
            case R.id.automatic:
                break;
            case R.id.forget:
                break;
            case R.id.btn_login:
                String workername = mWorkerNumber.getText().toString().trim();
                String pwd = mPwd.getText().toString().trim();
                if (TextUtils.isEmpty(workername)) {
                    Toast.makeText(getActivity(), getString(R.string.input_username_hint), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(getActivity(), getString(R.string.input_password_hint), Toast.LENGTH_SHORT).show();
                    return;
                }
                loadingDialog.show();
                AppApi.login(workername, pwd, device_ver, device_info, device_ip, WorkerLoginFragment.this, 0);
                break;
        }
    }

    @Override
    public void handlerSuccessData(String result, int tag) {

        if (tag == 0) {
            SPUtils.put(mContext, SP_USER_DATA, result);
            WorkerInfoBean bean = JSON.parseObject(result,WorkerInfoBean.class);
            SPUtils.put(mContext,"head",bean.getReturn_data().getPic_url());
            if (mCbRemember.isChecked()) {
                SPUtils.put(mContext, IS_REMEMBER_PASSWORD, 1);
                SPUtils.put(mContext, MY_PASSWORD, mPwd.getText().toString());
            } else {
                SPUtils.put(mContext, IS_REMEMBER_PASSWORD, 0);
            }
            if (mAutomatic.isChecked()) {
                SPUtils.put(mContext, IS_AUTO_LOGIN, 1);
            } else {
                SPUtils.put(mContext, IS_AUTO_LOGIN, 0);
            }
            SPUtils.put(mContext, MY_NUMBER, mWorkerNumber.getText().toString());
            AppApi.getWorkerList(MyUtils.getShopCode(mContext), WorkerLoginFragment.this, 1);
        } else if (tag == 1) {
            SPUtils.put(mContext, "people", result);
            Toast.makeText(mContext, "登陆成功！"+result, Toast.LENGTH_SHORT).show();
            loadingDialog.cancelDialog();
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

    }

    @Override
    public void handlerFailData(String failString, int tag) {
        loadingDialog.cancelDialog();
    }

    @Override
    public void handleErrorData(String error, int tag) {
        loadingDialog.cancelDialog();
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }

    private void closeKeyboard() {
        View view = getActivity().getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @OnClick(R.id.login_bg)
    public void onViewClicked() {
        closeKeyboard();
    }


}
