package com.sk.meikelai.activity.card;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.entity.FirstEvent;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

import static com.sk.meikelai.utils.StaticCode.SP_VIP_DATA;

/**
 * Created by Administrator on 2017/7/26.
 */

public class OpenCardQRCode extends BaseActivity implements HandleDataCallBack {

    String url;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.qd_code)
    ImageView mQdCode;

    @Override
    protected int getContentView() {
        return R.layout.activity_qr_code;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerMessageReceiver();
        url = getIntent().getStringExtra("url");
        Glide.with(mContext).load(url).into(mQdCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMessageReceiver!=null){
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        }

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }


    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        SPUtils.put(mContext, SP_VIP_DATA, result);
        EventBus.getDefault().post(new FirstEvent("Goto_Card_Info"));
        EventBus.getDefault().post(new FirstEvent("refresh"));
        finish();
    }

    @Override
    public void handlerFailData(String failString, int tag) {

    }

    @Override
    public void handleErrorData(String error, int tag) {

    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String user_code = intent.getStringExtra(OpenCardQRCode.KEY_MESSAGE);
                    AppApi.getMemberInfo(user_code, MyUtils.getShopCode(mContext),OpenCardQRCode.this,0);

                }
            } catch (Exception e){
            }
        }
    }

}
