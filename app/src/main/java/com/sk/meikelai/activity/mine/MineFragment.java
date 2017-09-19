package com.sk.meikelai.activity.mine;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;
import com.sk.meikelai.activity.main.LoadActivity;
import com.sk.meikelai.dialog.UpDateDialogFragment;
import com.sk.meikelai.entity.HomeCountBean;
import com.sk.meikelai.entity.WorkerInfoBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.CropCircleTransformation;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.utils.StaticCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.sk.meikelai.utils.StaticCode.SP_USER_DATA;


/**
 * Created by sk on 2017/6/5.
 */

public class MineFragment extends BaseFragment implements HandleDataCallBack {

    @BindView(R.id.mine_icon)
    ImageView mMineIcon;
    @BindView(R.id.mine_name)
    TextView mMineName;
    @BindView(R.id.user_number)
    TextView mUserNumber;
    @BindView(R.id.address)
    TextView mAddress;
    @BindView(R.id.mine_setting)
    ImageView mMineSetting;
    @BindView(R.id.mine_mail)
    ImageView mMineMail;
    @BindView(R.id.ll_today_fumi)
    LinearLayout mLlTodayFumi;
    @BindView(R.id.ll_month_fumi)
    LinearLayout mLlMonthFumi;
    @BindView(R.id.ll_today_cash)
    LinearLayout mLlTodayCash;
    @BindView(R.id.ll_today_achievement)
    LinearLayout mLlTodayAchievement;
    @BindView(R.id.ll_today_appointment)
    LinearLayout mLlTodayAppointment;
    @BindView(R.id.ll_today_member)
    LinearLayout mLlTodayMember;
    @BindView(R.id.mine_text)
    TextView mMineText;
    @BindView(R.id.mine_msg)
    TextView mMineMsg;
    @BindView(R.id.today_icon)
    ImageView mTodayIcon;
    @BindView(R.id.rl_today_order)
    RelativeLayout mRlTodayOrder;
    @BindView(R.id.rl_today_handover)
    RelativeLayout mRlTodayHandover;
    @BindView(R.id.rl_mine_order)
    RelativeLayout mRlMineOrder;
    @BindView(R.id.rl_mine_score)
    RelativeLayout mRlMineScore;
    Unbinder unbinder;
    @BindView(R.id.mine_new)
    TextView tvNew;
    @BindView(R.id.tip_ll)
    FrameLayout tipLl;
    @BindView(R.id.tv_today_fumi)
    TextView tvTodayFumi;
    @BindView(R.id.tv_month_fumi)
    TextView tvMonthFumi;
    @BindView(R.id.today_cash)
    TextView todayCash;
    @BindView(R.id.month_cash)
    TextView monthCash;
    @BindView(R.id.today_achievement)
    TextView todayAchievement;
    @BindView(R.id.month_achievement)
    TextView monthAchievement;
    @BindView(R.id.today_order)
    TextView todayOrder;
    @BindView(R.id.tomorrow_order)
    TextView tomorrowOrder;
    @BindView(R.id.today_vip)
    TextView todayVip;
    @BindView(R.id.all_vip)
    TextView allVip;

    private String headUrl;


    @Override
    protected int getContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();
    }

    @Override
        public void onResume() {
        super.onResume();
        headUrl = MyUtils.getUrl(mContext);
        Glide.with(mContext).load(headUrl).bitmapTransform(new CropCircleTransformation(mContext)).into(mMineIcon);
        WorkerInfoBean bean = JSONObject.parseObject((String) SPUtils.get(mContext, SP_USER_DATA, ""), WorkerInfoBean.class);
        if (bean != null) {
            AppApi.getHomeCount(bean.getReturn_data().getUser_code(), MineFragment.this, 0);
        }
    }


    @OnClick({R.id.mine_icon, R.id.mine_setting, R.id.mine_mail, R.id.ll_today_fumi, R.id.ll_month_fumi, R.id.ll_today_cash, R.id
            .ll_today_achievement, R.id.ll_today_appointment, R.id.ll_today_member, R.id.rl_today_order, R.id.rl_today_handover, R.id
            .rl_mine_order, R.id.rl_mine_score,R.id.rl_mine_update})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.mine_icon:
//                UIHelper.showWorkerLogin(getActivity());
                break;
            case R.id.mine_setting:
                Intent settingIntent = new Intent(mContext, MySettingsActivity.class);
                settingIntent.putExtra("headUrl",headUrl);
                startActivityForResult(settingIntent, 10);
                break;
            case R.id.mine_mail:
                break;
            case R.id.ll_today_fumi:
                Intent intent = new Intent();
                intent.setClass(mContext, ToadyFuMiActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_month_fumi:
                Intent intentMonth = new Intent();
                intentMonth.setClass(mContext, ThisMonthFuMiActivity.class);
                startActivity(intentMonth);
                break;
            case R.id.ll_today_cash:
                Intent cashIntent = new Intent(mContext, NetReceiptsActivity.class);
                startActivity(cashIntent);
                break;
            case R.id.ll_today_achievement:
                Intent achieveIntent = new Intent(mContext, AchievementActivity.class);
                startActivity(achieveIntent);
                break;
            case R.id.ll_today_appointment:
//                UIHelper.showTodayAppointment(getContext());
                Intent appointmentIntent = new Intent(mContext, MyAppointmentActivity.class);
                startActivity(appointmentIntent);
                break;
            case R.id.ll_today_member:
                Intent vipIntent = new Intent(mContext, MyVipActivity.class);
                startActivity(vipIntent);
                break;
            case R.id.rl_today_order:
                //今日订单审核
                Intent intent1 = new Intent(mContext, TodayOrderActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_today_handover:
                //今日现金申报
                Intent handIntent = new Intent(mContext,HandOverActivity.class);
                startActivity(handIntent);
                break;
            case R.id.rl_mine_order:
                //订单流水
                Intent orderIntent = new Intent(mContext, MineOrderActivity.class);
                getContext().startActivity(orderIntent);
                break;
            case R.id.rl_mine_score:
                Intent scoreIntent = new Intent(getContext(), ScoreActivity.class);
                getContext().startActivity(scoreIntent);
                break;
            case R.id.rl_mine_update:
                 Intent updateIntent  = new Intent(getContext(), UpdateActivity.class);
                getContext().startActivity(updateIntent);
                break;
        }
    }

    private void initData() {
        WorkerInfoBean workerInfoBean = JSONObject.parseObject((String) SPUtils.get(mContext, SP_USER_DATA, ""), WorkerInfoBean.class);
        if (workerInfoBean != null) {
            mMineName.setText(workerInfoBean.getReturn_data().getPerson_name());
            mUserNumber.setText("(ID:" + workerInfoBean.getReturn_data().getJobNumber()+ ")");
            mAddress.setText(workerInfoBean.getReturn_data().getShop_name());
        }
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        switch (tag) {
            case 0:
                HomeCountBean dataBean = JSONObject.parseObject(result, HomeCountBean.class);
                tvTodayFumi.setText(String.valueOf(dataBean.getReturn_data().getReturn_today_commission() / 100f));
                tvMonthFumi.setText(String.valueOf(dataBean.getReturn_data().getReturn_month_commission() / 100f));
                todayCash.setText(String.valueOf(dataBean.getReturn_data().getReturn_today_cash() / 100f));
                monthCash.setText("本月实收：" + dataBean.getReturn_data().getReturn_month_cash() / 100f);
                todayAchievement.setText(String.valueOf(dataBean.getReturn_data().getReturn_today_achievement() / 100f));
                monthAchievement.setText("本月业绩：" + dataBean.getReturn_data().getReturn_month_achievement() / 100f);
                todayOrder.setText(String.valueOf(dataBean.getReturn_data().getReturn_today_appointment()));
                tomorrowOrder.setText("明日预约：" + dataBean.getReturn_data().getReturn_tomorrow_appointment());
                todayVip.setText(String.valueOf(dataBean.getReturn_data().getReturn_today_baseUser()));
                allVip.setText("累计会员：" + dataBean.getReturn_data().getReturn_all_baseUser());
                break;
        }


    }

    @Override
    public void handlerFailData(String failString, int tag) {

    }

    @Override
    public void handleErrorData(String error, int tag) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            if (requestCode == 10) {
                if (data.getIntExtra("code", -1) == 0) {
                    SPUtils.remove(mContext, SP_USER_DATA);
                    SPUtils.remove(mContext, StaticCode.SP_VIP_DATA);
                    Intent intent = new Intent(mContext, WorkerLoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else if (data.getIntExtra("code", -1) == 1) {
                    getActivity().finish();
                    System.exit(0);
                }

            }
        }
    }
}
