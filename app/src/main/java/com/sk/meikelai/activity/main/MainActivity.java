package com.sk.meikelai.activity.main;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.activity.card.OpenCardFragment;
import com.sk.meikelai.activity.card.OpenCardInfoFragment;
import com.sk.meikelai.activity.cash.CashFragment;
import com.sk.meikelai.activity.mine.MineFragment;
import com.sk.meikelai.activity.recharge.RechargeCardFragment;
import com.sk.meikelai.activity.recharge.RechargeFragment;
import com.sk.meikelai.activity.recharge.ScanCodeActivity;
import com.sk.meikelai.entity.FirstEvent;
import com.sk.meikelai.utils.HttpUtils;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.PushUtil;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.utils.StaticCode;
import com.sk.meikelai.utils.ToastUtils;
import com.sk.meikelai.xutils.HttpUtilsFacade;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.io.File;
import java.util.Set;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by sk on 2017/6/5.
 */


public class MainActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.rb_cash)
    RadioButton rbCash;
    @BindView(R.id.rb_card)
    RadioButton rbCard;
    @BindView(R.id.rb_recharge)
    RadioButton rbRecharge;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    CashFragment cashFragment;
    OpenCardFragment openCardFragment;
    RechargeFragment rechargeFragment;
    MineFragment mineFragment;
    RechargeCardFragment rechargeCardFragment;
    OpenCardInfoFragment openCardInfoFragment;

    private static final int TAG_OPENCARD_LOGIN = 3;
    public static boolean isForeground = false;

//    private PrinterListener printer_callback = new PrinterListener();
//    JSONObject printJson = new JSONObject();

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        init_fragment();
        initListeners();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onResume", "onResume");
    }

    //初始化fragment页面
    private void init_fragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        cashFragment = (CashFragment) fm.findFragmentByTag(CashFragment.class.getSimpleName());
        if (cashFragment == null) {
            cashFragment = new CashFragment();
            ft.add(R.id.fl_content, cashFragment, cashFragment.getClass().getSimpleName());
        }


        openCardFragment = (OpenCardFragment) fm.findFragmentByTag(OpenCardFragment.class.getSimpleName());
        if (openCardFragment == null) {
            openCardFragment = new OpenCardFragment();
            ft.add(R.id.fl_content, openCardFragment, openCardFragment.getClass().getSimpleName());
        }

        rechargeFragment = (RechargeFragment) fm.findFragmentByTag(RechargeFragment.class.getSimpleName());
        if (rechargeFragment == null) {
            rechargeFragment = new RechargeFragment();
            ft.add(R.id.fl_content, rechargeFragment, rechargeFragment.getClass().getSimpleName());
        }

        mineFragment = (MineFragment) fm.findFragmentByTag(MineFragment.class.getSimpleName());
        if (mineFragment == null) {
            mineFragment = new MineFragment();
            ft.add(R.id.fl_content, mineFragment, mineFragment.getClass().getSimpleName());
        }

        rechargeCardFragment = (RechargeCardFragment) fm.findFragmentByTag(RechargeCardFragment.class.getSimpleName());
        if (rechargeCardFragment == null) {
            rechargeCardFragment = new RechargeCardFragment();
            ft.add(R.id.fl_content, rechargeCardFragment, rechargeCardFragment.getClass().getSimpleName());
        }
        openCardInfoFragment = (OpenCardInfoFragment) fm.findFragmentByTag(OpenCardInfoFragment.class.getSimpleName());
        if (openCardInfoFragment == null) {
            openCardInfoFragment = new OpenCardInfoFragment();
            ft.add(R.id.fl_content, openCardInfoFragment, openCardInfoFragment.getClass().getSimpleName());
        }


        ft.show(cashFragment);
        ft.hide(openCardFragment);
        ft.hide(rechargeFragment);
        ft.hide(mineFragment);
        ft.hide(rechargeCardFragment);
        ft.hide(openCardInfoFragment);
        ft.commit();
    }

    private void initListeners() {
        rbCash.setOnCheckedChangeListener(this);
        rbCard.setOnCheckedChangeListener(this);
        rbRecharge.setOnCheckedChangeListener(this);
        rbMine.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.rb_cash:
                if (isChecked) {
                    showCash();
                }
                break;
            case R.id.rb_card:
                if (isChecked) {
                    if (SPUtils.contains(this, ScanCodeActivity.SP_VIP_DATA)) {
                        showCardList();
                    } else {
                        showCard();
                    }
                }
                break;
            case R.id.rb_recharge: //充值
                if (isChecked) {
                    //如果本地有数据
                    if (SPUtils.contains(this, ScanCodeActivity.SP_VIP_DATA)) {
                        showRechargeCardFragment();
                    } else {
                        showRecharge();
                    }
//                    showRecharge();
                }
                break;
            case R.id.rb_mine:
                if (isChecked) {
                    showMine();
                }
                break;
        }
    }

    private void showCash() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(cashFragment);
        ft.hide(openCardFragment);
        ft.hide(rechargeFragment);
        ft.hide(mineFragment);
        ft.hide(rechargeCardFragment);
        ft.hide(openCardInfoFragment);
        ft.commitAllowingStateLoss();
    }

    private void showCard() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(cashFragment);
        ft.show(openCardFragment);
        ft.hide(rechargeFragment);
        ft.hide(mineFragment);
        ft.hide(rechargeCardFragment);
        ft.hide(openCardInfoFragment);
        ft.commitAllowingStateLoss();
    }

    private void showCardList() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(cashFragment);
        ft.hide(openCardFragment);
        ft.hide(rechargeFragment);
        ft.hide(mineFragment);
        ft.hide(rechargeCardFragment);
        ft.show(openCardInfoFragment);
        ft.commitAllowingStateLoss();
    }

    private void showMine() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(cashFragment);
        ft.hide(openCardFragment);
        ft.hide(rechargeFragment);
        ft.show(mineFragment);
        ft.hide(rechargeCardFragment);
        ft.hide(openCardInfoFragment);
        ft.commit();
    }

    //充值登录前页面
    private void showRecharge() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(cashFragment);
        ft.hide(openCardFragment);
        ft.show(rechargeFragment);
        ft.hide(mineFragment);
        ft.hide(rechargeCardFragment);
        ft.hide(openCardInfoFragment);
        ft.commitAllowingStateLoss();
    }

    //充值登录后页面
    private void showRechargeCardFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(cashFragment);
        ft.hide(openCardFragment);
        ft.hide(rechargeFragment);
        ft.hide(mineFragment);
        ft.show(rechargeCardFragment);
        ft.hide(openCardInfoFragment);
        ft.commitAllowingStateLoss();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == -1) {
            if (requestCode == StaticCode.TAG_RECHARGE_LOGIN) {
                showRechargeCardFragment();
            } else if (requestCode == StaticCode.TAG_OPENCARD_LOGIN) {
                showCardList();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("main_onStart", "main_onStart");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("main_onStop", "main_onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("main_onPause", "main_onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("main_onRestart", "main_onRestart");
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.i("main_onDestroy", "main_onDestroy");
        EventBus.getDefault().unregister(this);
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //退出时的时间
    private long mExitTime;

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出app", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    //接收信息改变充值页面

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("Goto_Recharge")) {
            showRecharge();
            rbRecharge.setChecked(true);
        } else if (event.getMsg().equals("Goto_Recharge_Info")) {
            showRechargeCardFragment();
            rbRecharge.setChecked(true);
        } else if (event.getMsg().equals("Goto_Card")) {
            showCard();
            rbCard.setChecked(true);
        } else if (event.getMsg().equals("Goto_Card_Info")) {
            showCardList();
            rbCard.setChecked(true);
        } else if (event.getMsg().equals("Goto_Cash_Info")) {
            showCash();
            rbCash.setChecked(true);
        } else if (event.getMsg().equals("refresh")) {
            rechargeCardFragment.refreshPage();
        } else if(event.getMsg().equals("cash")){
            cashFragment.refreshPage();
        } else if(event.getMsg().equals("open")){
            showCardList();
        }else if(event.getMsg().equals("recharge")){
            showRechargeCardFragment();
        }
    }


    private void setJpushAlisa() {
        String user_code = MyUtils.getCode(mContext);
        JPushInterface.setAliasAndTags(getApplicationContext(),
                user_code, null, mAliasCallback);
    }


    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            switch (code) {
                case 0:
                    break;

                case 6002:
                    if (PushUtil.isConnected(getApplicationContext())) {
                        mHandler.sendMessageDelayed(
                                mHandler.obtainMessage(MSG_SET_ALIAS, alias),
                                1000 * 60);
                    } else {
                    }
                    break;

                default:
            }

        }

    };

    private final TagAliasCallback mTagsCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            switch (code) {
                case 0:
                    break;

                case 6002:
                    if (PushUtil.isConnected(getApplicationContext())) {
                        mHandler.sendMessageDelayed(
                                mHandler.obtainMessage(MSG_SET_TAGS, tags),
                                1000 * 60);
                    } else {
                    }
                    break;

                default:
            }

        }

    };

    private static final int MSG_SET_ALIAS = 1001;
    private static final int MSG_SET_TAGS = 1002;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            (String) msg.obj, null, mAliasCallback);
                    break;

                case MSG_SET_TAGS:
                    JPushInterface.setAliasAndTags(getApplicationContext(), null,
                            (Set<String>) msg.obj, mTagsCallback);
                    break;

                default:
            }
        }
    };

    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    private void init() {
        String ss = MyUtils.getCode(mContext);
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS,
                ss));
    }
}
