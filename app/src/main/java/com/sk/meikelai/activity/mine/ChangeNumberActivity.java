package com.sk.meikelai.activity.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sk on 2017/7/25.
 */

public class ChangeNumberActivity extends BaseActivity implements HandleDataCallBack {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.save)
    TextView save;

    @Override
    protected int getContentView() {
        return R.layout.activity_change_num;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.save:
                if(checkFormat()){
                    AppApi.changePhoneNumber(MyUtils.getCode(mContext),edPhone.getText().toString(),ChangeNumberActivity.this,0);
                }
                break;
        }
    }

    private boolean checkFormat(){
        if(TextUtils.isEmpty(edPhone.getText().toString())){
            Toast.makeText(mContext,"手机号码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edPhone.getText().toString().length()!=11){
            Toast.makeText(mContext,"手机号码必须为11位!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        Toast.makeText(mContext,"手机号修改成功!",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void handlerFailData(String failString, int tag) {

    }

    @Override
    public void handleErrorData(String error, int tag) {

    }
}
