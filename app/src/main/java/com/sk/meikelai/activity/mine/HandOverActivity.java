package com.sk.meikelai.activity.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.HttpUtils;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.StaticCode;
import com.sk.meikelai.view.LoadingDialog;
import com.sk.meikelai.xutils.HttpUtilsFacade;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sk on 2017/7/27.
 */

public class HandOverActivity extends BaseActivity implements HandleDataCallBack {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.ll_title)
    RelativeLayout llTitle;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.last_money)
    TextView lastMoney;
    @BindView(R.id.should_cash_money)
    TextView shouldCashMoney;
    @BindView(R.id.yu_money)
    EditText yuMoney;
    @BindView(R.id.should_apply_money)
    TextView shouldApplyMoney;
    @BindView(R.id.sure_money)
    EditText sureMoney;
    @BindView(R.id.icon_right)
    ImageView iconRight;
    @BindView(R.id.ed_remarks)
    EditText edRemarks;
    @BindView(R.id.history_sure)
    Button historySure;
    @BindView(R.id.yu_tv)
    TextView yuTv;
    @BindView(R.id.yu_tv1)
    TextView yuTv1;
    @BindView(R.id.appendix_img)
    ImageView appendixImg;
    private String fileUrl = "";
    private Uri uri;

    private int last_money, today_should_cash, today_yu, sure_money, should_apply;
    private String delcard_time = "";
    private LoadingDialog loadingDialog;

    @Override
    protected int getContentView() {
        return R.layout.activity_hand_over;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = MyUtils.getLoadingDialog(mContext, false);
        yuMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && s.toString().length() > 0) {
                    double d = Double.parseDouble(s.toString());
                    today_yu = (int) (d * 100);
                    should_apply = last_money + today_should_cash - today_yu;
                    shouldApplyMoney.setText(should_apply / 100f + "元");
                } else {
                    today_yu = 0;
                    should_apply = last_money + today_should_cash - today_yu;
                    shouldApplyMoney.setText(should_apply / 100f + "元");
                }

            }
        });
        loadingDialog.show();
        AppApi.getToadyHandInfo(MyUtils.getCode(mContext), this, 0);

    }

    @OnClick({R.id.back, R.id.tv_history, R.id.history_sure, R.id.up_ll, R.id.appendix_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_history:
                Intent intent = new Intent(mContext, HandOverHistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.history_sure:
                if(checkFormat()){
                    if (sureMoney.getText().toString().length() > 0) {
                        sure_money = (int) (Double.parseDouble(sureMoney.getText().toString()) * 100);
                    } else {
                        sure_money = 0;
                    }
                    loadingDialog.show();
                    AppApi.saveCashRecord(MyUtils.getCode(mContext), last_money, today_should_cash, today_yu, should_apply, sure_money,
                            fileUrl, edRemarks.getText().toString(), delcard_time, HandOverActivity.this, 1);
                }
                break;
            case R.id.up_ll:

                Matisse.from(HandOverActivity.this)
                        .choose(MimeType.of(MimeType.JPEG, MimeType.PNG))
                        .theme(R.style.photo)
                        .countable(false)
                        .maxSelectable(1)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(1000);

                break;
            case R.id.appendix_img:
                Intent intent1 = new Intent(mContext, PreviewActivity.class);
                intent1.putExtra("uri", uri);
                startActivity(intent1);
                break;

        }
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        loadingDialog.cancelDialog();
        switch (tag) {
            case 0:
                JSONObject jsonObject = JSON.parseObject(result);
                last_money = jsonObject.getInteger("return_yesterday_cash");
                today_should_cash = jsonObject.getInteger("return_CASHSum");
                lastMoney.setText(last_money / 100f + "元");
                shouldCashMoney.setText(today_should_cash / 100 + "元");
                today_yu = last_money;
                should_apply = last_money + today_should_cash - today_yu;
                yuMoney.setText(String.valueOf(today_yu / 100f));
                shouldApplyMoney.setText(should_apply / 100f + "元");
                break;
            case 1:
                finish();
                Toast.makeText(getApplicationContext(), "交接成功!", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    @Override
    public void handlerFailData(String failString, int tag) {
        loadingDialog.cancelDialog();
    }

    @Override
    public void handleErrorData(String error, int tag) {
        loadingDialog.cancelDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            appendixImg.setVisibility(View.VISIBLE);
            List<Uri> mSelected = Matisse.obtainResult(data);
            uri = mSelected.get(0);
            Glide.with(mContext).load(mSelected.get(0)).into(appendixImg);
            File file = MyUtils.getFileByUri(mSelected.get(0), mContext);
            upLoadAppendixImg(file);
            Log.d("Matisse", "mSelected: " + mSelected);
        }
    }


    private void upLoadAppendixImg(File file) {
        RequestParams requestParams = new RequestParams(HttpUtils.TEST_HOST + StaticCode.Up_Load_Img);
        Log.v("upLoadAppendixImg", requestParams.toString());
        requestParams.addHeader("Content-Type", "multipart/form-data");
        requestParams.setMultipart(true);
        requestParams.addBodyParameter("file", file);
        HttpUtilsFacade httpUtilsFacade = new HttpUtilsFacade();
        httpUtilsFacade.post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v("upLoadAppendixImg", result);
                JSONObject jsonObject = JSONObject.parseObject(result);
                fileUrl = jsonObject.getString("return_data");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.v("upLoadAppendixImg", ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private boolean checkFormat(){
        if(TextUtils.isEmpty(sureMoney.getText().toString())){
            Toast.makeText(mContext,"请填写实际交接现金!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
