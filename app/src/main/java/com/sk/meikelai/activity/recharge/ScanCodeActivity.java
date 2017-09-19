package com.sk.meikelai.activity.recharge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/14.
 */

public class ScanCodeActivity extends BaseActivity implements HandleDataCallBack {


    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.ok)
    Button mOk;
    public static final String IS_SCAN_USER_ID = "is_scan_user_id";
    public static final String IS_SCAN_PASSWORD = "is_scan_password";
    private static final int TAG_MEMBER_LOGIN = 0;
    public static final String SP_VIP_DATA = "sp_vip_data";
    public String where;


    @Override
    protected int getContentView() {
        return R.layout.activity_scan_code;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        where = bundle.getString("where");
    }

    @OnClick({R.id.back, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ok:
                AppApi.memberLogin("814999382832200", MyUtils.getShopCode(mContext), this, TAG_MEMBER_LOGIN);
//                if (where == "Recharge") {
                    Intent loginResult = new Intent();
                    loginResult.putExtra("loginResult", true);//放登陆成功或者失败的结果
                    setResult(Activity.RESULT_OK, loginResult);
//                } else {
//                    Intent loginResult = new Intent();
//                    loginResult.putExtra("loginResult", true);//放登陆成功或者失败的结果
//                    setResult(Activity.RESULT_CANCELED, loginResult);
//                }
                finish();
                break;

        }
    }


    @Override
    public void handlerSuccessData(String result, int tag) {
        SPUtils.put(mContext, SP_VIP_DATA, result);
        Toast.makeText(mContext, "登陆成功！", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void handlerFailData(String failString, int tag) {

    }

    @Override
    public void handleErrorData(String error, int tag) {

    }
}
