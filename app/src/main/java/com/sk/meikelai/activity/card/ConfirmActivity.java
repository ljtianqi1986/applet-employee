package com.sk.meikelai.activity.card;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.sk.meikelai.Adapter.SetWorkerAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.activity.cash.CaptureActivity;
import com.sk.meikelai.activity.cash.CashResultActivity;
import com.sk.meikelai.callback.ExitCallBack;
import com.sk.meikelai.callback.SureSelectedCallBack;
import com.sk.meikelai.common.Constants;
import com.sk.meikelai.dialog.DialogSelectWorkerFragment;
import com.sk.meikelai.dialog.ExitDialogFragment;
import com.sk.meikelai.entity.CardBean;
import com.sk.meikelai.entity.ComeWayBean;
import com.sk.meikelai.entity.CreateCardBean;
import com.sk.meikelai.entity.FirstEvent;
import com.sk.meikelai.entity.MemberInfoBean;
import com.sk.meikelai.entity.OpenCardBean;
import com.sk.meikelai.entity.WorkerBean;
import com.sk.meikelai.entity.WorkerInfoBean;
import com.sk.meikelai.permission.EasyPermission;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.CropCircleTransformation;
import com.sk.meikelai.utils.DeviceUtils;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MD5;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.utils.StaticCode;
import com.sk.meikelai.utils.ToastUtils;
import com.sk.meikelai.view.LoadingDialog;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

import static com.sk.meikelai.common.Constants.APPLICATIONID;
import static com.sk.meikelai.common.Constants.Again_Code;
import static com.sk.meikelai.common.Constants.SALE_DIR;
import static com.sk.meikelai.utils.StaticCode.SP_VIP_DATA;

/**
 * Created by Administrator on 2017/6/14.
 */

public class ConfirmActivity extends BaseActivity implements HandleDataCallBack, EasyPermission
        .PermissionCallback, ExitCallBack, SureSelectedCallBack {


    @BindView(R.id.cash_icon)
    ImageView mCashIcon;
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.user_number)
    TextView mUserNumber;
    @BindView(R.id.center_title)
    LinearLayout mCenterTitle;
    @BindView(R.id.reduce)
    TextView mReduce;
    @BindView(R.id.type)
    TextView mType;
    @BindView(R.id.tv_card)
    TextView mTvCard;
    @BindView(R.id.tv_cash)
    TextView mTvCash;
    @BindView(R.id.tv_epay)
    TextView mTvEpay;
    @BindView(R.id.rc_content)
    RecyclerView mRcContent;
    @BindView(R.id.sale_price)
    TextView mSalePrice;
    @BindView(R.id.price)
    TextView mPrice;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.add)
    TextView mAdd;
    @BindView(R.id.et_recharge_price)
    TextView mTvRechargePrice;
    @BindView(R.id.et_pay_price)
    EditText mEtPayPrice;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.btn_sure)
    Button mBtnSure;
    @BindView(R.id.exit_num)
    TextView mExitNum;
    @BindView(R.id.price_now)
    TextView mPriceNow;
    @BindView(R.id.second_ui)
    LinearLayout mSecondUi;
    private String str_CardCode;  //卡的code
    private String str_UserCode;   //收银员
    private String str_VipCode;  //客户code
    private String str_PayType = "ONLINE";  //	支付类型
    private String str_RrchargePrice;  //应付金额
    private String str_PayPrice;    //实付金额
    private String str_Type = "NEW_CARD";
    private String str_Ip;
    private String str_Info;
    private String str_Remark;
    private String str_PayCode;
    private String str_UpdateCode;
    private String str_WorkerCode;
    private String str_Money;
    private EditText mEt_Money;
    LoadingDialog loadingDialog;
    private String mStime;
    private String mEtime;
    private String mDate;
//
//    //选择日期Dialog
//    private DatePickerDialog datePickerDialog;
//    //选择时间Dialog
//    private TimePickerDialog timePickerDialog;

    private Calendar calendar;

    private List<WorkerBean.ReturnDataBean> selectWorkList = new ArrayList<>();

    private List<CreateCardBean.JsonStringBean.CommissionListBean> list = new ArrayList<>();
    private SetWorkerAdapter mSetWorkerAdapter;
    private static final int TAG_GET_MEMBER_INFO = 2;

    private OpenCardBean.ReturnDataBean mDataBean;
    private static final int CAMERA = 1000;
    private static final String[] sPermissions = new String[]{
            Manifest.permission.CAMERA};
    private List<ComeWayBean.ReturnDataBean> comeWayList = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private CardBean cardBean;
    private String order_code = "";
    private int count = 0;
    private static final long ONE_YEAR = 365 * 1000 * 60 * 60 * 24L;
    private static final long ONE_DAY = 24 * 60 * 60 * 1000;
    private long mMillSecStart = 0;
    private long mMillSecEnd = 0;
    private int mMonth;
    private String endDate;
    private long mEndDate;

    @Override
    protected int getContentView() {
        return R.layout.activity_confirm;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = MyUtils.getLoadingDialog(mContext, false);
        loadingDialog.setCancelable(false);
        setWorkerList();
        setUserData();
        setData();
    }

    //选择的技师列表
    private void setWorkerList() {
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRcContent.setLayoutManager(mLinearLayoutManager);
        refreshSelectedWorker();
    }

    private void refreshSelectedWorker(){
        mSetWorkerAdapter = new SetWorkerAdapter(R.layout.item_set_worker,selectWorkList);
        mRcContent.setAdapter(mSetWorkerAdapter);
        //设置adapter里item的点击事件
        mSetWorkerAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mEt_Money = (EditText) mSetWorkerAdapter.getViewByPosition(mRcContent, position, R.id.et_money);
                selectWorkList.remove(position);
                if (mEtPayPrice.getText().toString().length() > 0) {
                    String CM = mEtPayPrice.getText().toString();
                    for (int i = 0; i < selectWorkList.size(); i++) {
                        if (i == 0) {
                            selectWorkList.get(i).setCountMoney(Integer.valueOf(CM) * 100);
                        } else {
                            selectWorkList.get(i).setCountMoney(0);
                        }
                    }
                }
                refreshSelectedWorker();
                str_Money = mEt_Money.getText().toString().trim();
                mEt_Money.setSelection(str_Money.length());
            }
        });
    }
    private void setData() {

        mDataBean = getIntent().getParcelableExtra("data");

        mType.setText(mDataBean.getName());
        if (mDataBean.getGift_price() > 0) {
            mPrice.setText(String.valueOf((mDataBean.getGift_price() + mDataBean.getSale_price()) / 100) + "元");
        } else {
            mPrice.setText(String.valueOf(mDataBean.getSale_price() / 100) + "元");
        }
        mSalePrice.setText(String.valueOf(mDataBean.getSale_price() / 100) + "元");

        str_CardCode = mDataBean.getCode();
        mStime = mDataBean.getStartTime();
        mEtime = mDataBean.getEndTime();
        mTime.setText("有效期：" + mStime + "~" + mEtime);
        mDate = mStime + "," + mEtime;
        mMonth = mDataBean.getUseful();
        changeDate();
        str_RrchargePrice = String.valueOf(mDataBean.getSale_price());
        mTvRechargePrice.setText(String.valueOf(mDataBean.getSale_price() / 100));
        str_PayPrice = String.valueOf(mDataBean.getSale_price());
        mEtPayPrice.setText(String.valueOf(mDataBean.getSale_price() / 100));
    }

    private void setUserData() {
        MemberInfoBean memberInfoBean = JSONObject.parseObject((String) SPUtils.get(mContext, StaticCode.SP_VIP_DATA, ""), MemberInfoBean
                .class);
        if (memberInfoBean == null) {
            return;
        } else {
            str_VipCode = memberInfoBean.getReturn_data().getCode();
            mUserName.setText(memberInfoBean.getReturn_data().getPerson_name());
            mUserNumber.setText(memberInfoBean.getReturn_data().getPhone());
            Glide.with(mContext).load(memberInfoBean.getReturn_data().getCover()).bitmapTransform(new CropCircleTransformation(mContext))
                    .into(mCashIcon);
        }
        WorkerInfoBean bean = JSONObject.parseObject((String) SPUtils.get(mContext, StaticCode.SP_USER_DATA, ""), WorkerInfoBean.class);
        if (bean == null) {
            return;
        } else {
            str_WorkerCode = bean.getReturn_data().getUser_code();
        }
    }


    @OnClick({R.id.reduce, R.id.tv_card, R.id.tv_cash, R.id.tv_epay, R.id.time, R.id.add, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reduce:
                finish();
                break;
            case R.id.tv_card:
                str_PayType = "CARD";
                break;
            case R.id.tv_cash:
                str_PayType = "CASH";
                break;
            case R.id.tv_epay:
                str_PayType = "ONLINE";
                break;
            case R.id.time:
                showDialogSTime();
                break;
            case R.id.add:
                DialogSelectWorkerFragment dialogSelectWorkerFragment = new DialogSelectWorkerFragment();
                Bundle b = new Bundle();
                b.putString("name", "confirm");
                dialogSelectWorkerFragment.setArguments(b);
                dialogSelectWorkerFragment.setSureSelectedCallBack(ConfirmActivity.this);
                dialogSelectWorkerFragment.show(getSupportFragmentManager(), "tag");
                break;
            case R.id.btn_sure:
                if (checkFormat()) {
                    switch (str_PayType) {
                        case "ONLINE":
                            EasyPermission.with(ConfirmActivity.this)
                                    .addRequestCode(CAMERA)
                                    .permissions(sPermissions)
                                    .rationale("没有授权!")
                                    .request();
                            break;
                        case "CASH":
                            setDataInfo(1);
                            break;
                        case "CARD":
                            if (Build.MANUFACTURER.contains("basewin")) {
//                                paramsMap.put("trade_type", "CARD");
                                String zeros = "000000000000";
//                    String card_moneys = String.valueOf(cashTotal);
                                String card_moneys = "1";
                                card_moneys = zeros.substring(0,
                                        zeros.length() - card_moneys.length())
                                        + card_moneys;
                                Intent intentPOS = new Intent();
                                intentPOS.setComponent(new ComponentName(APPLICATIONID, SALE_DIR));
                                intentPOS.putExtra("amount", card_moneys);
                                startActivityForResult(intentPOS, 100);
                            }
                            break;
                    }

                }
                break;
        }
    }


    private void showDialogSTime() {
        TimePickerDialog dialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setThemeColor(ContextCompat.getColor(this, R.color.theme))
                .setMinMillseconds(System.currentTimeMillis())
                .setTitleStringId("开卡日期")
                .setCallBack(startListener)
                .build();
        dialogYearMonthDay.show(getSupportFragmentManager(), "YEAR_MONTH_DAY");
    }

    private void showDialogETime() {
        TimePickerDialog dialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setThemeColor(ContextCompat.getColor(this, R.color.theme))
                .setMinMillseconds(System.currentTimeMillis() + ONE_DAY)
                .setCurrentMillseconds(mMillSecStart + mEndDate)
                .setTitleStringId("结束日期")
                .setCallBack(endListener)
                .build();
        dialogYearMonthDay.show(getSupportFragmentManager(), "YEAR_MONTH_DAY");
    }

    private void setDataInfo(int type) {
        str_PayPrice = Integer.valueOf(mEtPayPrice.getText().toString()) * 100 + "";
        CreateCardBean.JsonStringBean createCardBean = new CreateCardBean.JsonStringBean();
        createCardBean.setCard_code(str_CardCode);
        createCardBean.setUser_code(str_WorkerCode);
        createCardBean.setBase_user_code(str_VipCode);
        createCardBean.setTrade_type(str_PayType);
        createCardBean.setShould_money(str_RrchargePrice);
        createCardBean.setActual_money(Integer.valueOf(mEtPayPrice.getText().toString()) * 100 + "");
        createCardBean.setType(str_Type);
        createCardBean.setUseful_date(mDate);
        createCardBean.setDevice_ip(DeviceUtils.getIp());
        createCardBean.setDevice_info(DeviceUtils.getDeviceInfo(this));
        createCardBean.setRemark(mEtRemark.getText().toString().trim());
        Log.v("createCardBean", str_CardCode + str_WorkerCode + str_VipCode + str_PayType + str_RrchargePrice + str_PayPrice +
                str_Type);
        createCardBean.setKey(MD5.md5(str_CardCode + str_WorkerCode + str_VipCode + str_PayType + str_RrchargePrice + str_PayPrice +
                str_Type));
        createCardBean.setAuthor_code(str_PayCode);
        createCardBean.setUpgrade_code(str_UpdateCode);
        createCardBean.setUnion_pay(cardBean);
        list.clear();
        for (WorkerBean.ReturnDataBean dataBean : mSetWorkerAdapter.getDataBeanList()) {
            CreateCardBean.JsonStringBean.CommissionListBean bean = new CreateCardBean.JsonStringBean.CommissionListBean();
            bean.setIs_main(String.valueOf(dataBean.getIs_main()));
            bean.setMoney(String.valueOf(dataBean.getCountMoney()));
            bean.setUser_code(dataBean.getUser_code());
            list.add(bean);
        }
        createCardBean.setCommission_list(list);
        loadingDialog.show();
        switch (type) {
            case 0:
                AppApi.createCard(createCardBean, ConfirmActivity.this, Constants.Card_Code);
                break;
            case 1:
                AppApi.createCard(createCardBean, ConfirmActivity.this, Constants.Cash_Code);
                break;
            case 2:
                AppApi.createCard(createCardBean, ConfirmActivity.this, Constants.Scan_Code);
                break;
        }
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        Log.v("handlerSuccessData", result);
        switch (tag) {
            case Constants.Cash_Code:
                Log.v("handlerSuccessData", result);
                loadingDialog.cancelDialog();
                JSONObject jsonObject = JSON.parseObject(result);
                order_code = jsonObject.getString("return_order_code");
                AppApi.getMemberInfo(str_VipCode, MyUtils.getShopCode(mContext), ConfirmActivity.this, TAG_GET_MEMBER_INFO);
                break;
            case TAG_GET_MEMBER_INFO:
                SPUtils.put(mContext, SP_VIP_DATA, result);
                Intent successIntent = new Intent(this, CardSuccessActivity.class);
                successIntent.putExtra("order_code", order_code);
                startActivity(successIntent);
                EventBus.getDefault().post(new FirstEvent("refresh"));
                finish();
                break;
            case Constants.Card_Code:
                loadingDialog.cancelDialog();
                JSONObject jsonObject1 = JSON.parseObject(result);
                order_code = jsonObject1.getString("return_order_code");
                AppApi.getMemberInfo(str_VipCode, MyUtils.getShopCode(mContext), ConfirmActivity.this, TAG_GET_MEMBER_INFO);
                break;

            case Constants.Scan_Code:
                loadingDialog.cancelDialog();
                JSONObject jsonObject2 = JSON.parseObject(result);
                order_code = jsonObject2.getString("return_order_code");
                AppApi.getMemberInfo(str_VipCode, MyUtils.getShopCode(mContext), ConfirmActivity.this, TAG_GET_MEMBER_INFO);
                break;
            case Again_Code:
                loadingDialog.cancelDialog();
                AppApi.getMemberInfo(str_VipCode, MyUtils.getShopCode(mContext), ConfirmActivity.this, TAG_GET_MEMBER_INFO);
                break;
        }
    }

    @Override
    public void handlerFailData(String failString, int tag) {
        switch (tag) {
            case Constants.Cash_Code:
                Intent failIntent = new Intent(this, CardFailActivity.class);
                startActivity(failIntent);
                loadingDialog.cancelDialog();
                break;
            case Constants.Scan_Code:
                loadingDialog.cancelDialog();
                JSONObject jsonObject = JSON.parseObject(failString);
                String info = jsonObject.getString("return_info");
                order_code = jsonObject.getString("return_order_code");
                if (info.contains(Constants.SCAN_WAIT1)
                        || info.contains(Constants.SCAN_WAIT2)
                        || info.contains(Constants.SCAN_WAIT3)) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            count++;

                            AppApi.queryOrderAgain(order_code, MyUtils.getCode(mContext), ConfirmActivity.this, Constants.Again_Code);
                        }
                    }, 1500);

                } else {
                    loadingDialog.cancelDialog();
                    Toast.makeText(mContext, info, Toast.LENGTH_SHORT).show();
                    Intent failIntent1 = new Intent(this, CardFailActivity.class);
                    startActivity(failIntent1);
                }
                break;
            case Constants.Again_Code:
                JSONObject jsonObject1 = JSON.parseObject(failString);
                String info1 = jsonObject1.getString("return_info");
                if (info1.contains(Constants.SCAN_WAIT1)
                        || info1.contains(Constants.SCAN_WAIT2)
                        || info1.contains(Constants.SCAN_WAIT3)) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (count > 40) {
                                loadingDialog.cancelDialog();
                                Toast.makeText(mContext, "支付失败!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, CashResultActivity.class);
                                intent.putExtra("tag", 0);
                                startActivityForResult(intent, 101);
                            } else {
                                count++;
                                AppApi.queryOrderAgain(order_code, MyUtils.getCode(mContext), ConfirmActivity.this, Constants
                                        .Again_Code);
                            }

                        }
                    }, 1500);

                } else {
                    loadingDialog.cancelDialog();
                    Toast.makeText(mContext, info1, Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void handleErrorData(String error, int tag) {
        loadingDialog.cancelDialog();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_OK:
                switch (requestCode) {
                    case 0:
                        str_PayCode = data.getStringExtra("result");
                        setDataInfo(2);
                        break;
                    case 100:
                        String reason = data.getStringExtra("success");
                        Log.v("reason", reason);
                        JSONObject jsonObject = JSON.parseObject(reason);
                        String amount = jsonObject.getString("amount");// 交易金额
                        String cardNo = jsonObject.getString("pan");// 卡号
                        String referenceNo = jsonObject.getString("referenceNo");// 参考号
                        String traceNo = jsonObject.getString("trace");// 凭证号
                        String batchNo = jsonObject.getString("batch");
                        cardBean = new CardBean();
                        cardBean.setCardNo(cardNo);
                        cardBean.setReferenceNo(referenceNo);
                        cardBean.setTraceNo(traceNo);
                        cardBean.setBatchNo(batchNo);
                        setDataInfo(0);
                        break;
                }
                break;
            case RESULT_CANCELED:
                if (requestCode == 100) {
                    if (data != null) {
                        String reason = data.getStringExtra("reason");
                        if (reason != null) {
                            Toast.makeText(getApplicationContext(), reason, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        EasyPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }


    @Override
    public void onPermissionGranted(int requestCode, List<String> perms) {
        if (requestCode == CAMERA) {
            Intent intent = new Intent();
            intent.setClass(mContext, CaptureActivity.class);
            intent.putExtra("tag", "confirm");
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public void onPermissionDenied(int requestCode, List<String> perms) {
        Toast.makeText(mContext, "未授权", Toast.LENGTH_SHORT)
                .show();
        EasyPermission.checkDeniedPermissionsNeverAskAgain(this, "该设备没有授权," + "请去设置里授权!", perms);
    }

    //计算总金额
    private int calculate() {
        int total = 0;
        for (int i = 0; i < mSetWorkerAdapter.getItemCount(); i++) {
            int money = 0;
            View view = mLinearLayoutManager.findViewByPosition(i);
            EditText editText = (EditText) view.findViewById(R.id.et_money);

            if (editText.getText().toString().equals("")) {
                money = 0;
            } else {
                money = Integer.parseInt(editText.getText().toString());
            }
            total += money * 100;
        }
        return total;
    }

    private boolean checkFormat() {
        if (str_RrchargePrice.equals("") || Integer.parseInt(str_RrchargePrice) <= 0) {
            ToastUtils.showToast(this, "请填写应付金额!");
            return false;
        } else if (mEtPayPrice.getText().toString().equals("") || Integer.parseInt(mEtPayPrice.getText().toString()) <= 0) {
            ToastUtils.showToast(this, "请填写实收金额!");
            return false;
        } else if (mSetWorkerAdapter.getData().size() <= 0) {
            ToastUtils.showToast(this, "请至少选择一个技师!");
            return false;
        }
        if (calculate() / 100 != Integer.parseInt(mEtPayPrice.getText().toString())) {
            ToastUtils.showToast(this, "技师总提成金额不等于支付金额!");
            return false;
        }
        return true;
    }


    @OnClick(R.id.exit_num)
    public void onViewClicked() {
        ExitDialogFragment dialogFragment = new ExitDialogFragment();
        dialogFragment.setCallBack(this);
        dialogFragment.show(this.getFragmentManager(), "exit");
    }

    @Override
    public void exit() {
        SPUtils.remove(mContext, StaticCode.SP_VIP_DATA);
        EventBus.getDefault().post(new FirstEvent("Goto_Card"));
        finish();
    }


    private OnDateSetListener startListener = new OnDateSetListener() {
        @Override
        public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
            //TODO 设置开始时间
            if (mMillSecEnd != 0 && millseconds > mMillSecEnd) {
                Toast.makeText(ConfirmActivity.this, "开始时间不能大于结束时间", Toast.LENGTH_LONG).show();
            } else {
                mMillSecStart = millseconds;
                mStime = getDateToString(millseconds);
//                mAppointTime_star.setText(mDate_star);
                showDialogETime();
            }
        }
    };
    private OnDateSetListener endListener = new OnDateSetListener() {
        @Override
        public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
            //TODO 设置结束时间
            if (millseconds < mMillSecStart) {
                Toast.makeText(ConfirmActivity.this, "结束时间不能小于开始时间", Toast.LENGTH_LONG).show();
            } else if (mMillSecStart <= 0) {
                Toast.makeText(ConfirmActivity.this, "请先选择开始时间", Toast.LENGTH_LONG).show();
            } else {
                mMillSecEnd = millseconds;
                mEtime = getDateToString(millseconds);
                mTime.setText("有效期：" + mStime + "~" + mEtime);
                mDate = mStime + "," + mEtime;
            }
        }
    };

    public void changeDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

//					logger.error("==计算有效期====月===="+month);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, mMonth);
//        String startDate = sdf.format(date);
        mEndDate = dateToLong(c.getTime());
        mEndDate = mEndDate - System.currentTimeMillis();
//					logger.error("==计算有效期=startDate===="+startDate+"endDate===="+endDate);
    }

    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public String getDateToString(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date d = new Date(time);
        return sf.format(d);
    }

    public List<WorkerBean.ReturnDataBean> getSelectWorkList() {
        Log.v("RechargeCardFragment", selectWorkList.size() + "");
        return selectWorkList;
    }


    @Override
    public void sureSelect(List<WorkerBean.ReturnDataBean> workList) {
        Log.v("sureSelect", workList.size() + "");
        selectWorkList.clear();
        selectWorkList.addAll(workList);
        if (mEtPayPrice.getText().toString().length() > 0) {
            String CM = mEtPayPrice.getText().toString();
            for (int i = 0; i < selectWorkList.size(); i++) {
                if (i == 0) {
                    selectWorkList.get(i).setCountMoney(Integer.valueOf(CM) * 100);
                } else {
                    selectWorkList.get(i).setCountMoney(0);
                }
            }
        }
        mSetWorkerAdapter.notifyDataSetChanged();
    }
}
