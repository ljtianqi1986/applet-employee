package com.sk.meikelai.activity.cash;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kyleduo.switchbutton.SwitchButton;
import com.sk.meikelai.Adapter.CashOrderProjectAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.common.Constants;
import com.sk.meikelai.dialog.CashTipDialogFragment;
import com.sk.meikelai.entity.CardBean;
import com.sk.meikelai.entity.CardPay;
import com.sk.meikelai.entity.CashCardBean;
import com.sk.meikelai.entity.PayDetail;
import com.sk.meikelai.entity.ProductListBean;
import com.sk.meikelai.entity.ProjectListBean;
import com.sk.meikelai.entity.SaveCardBean;
import com.sk.meikelai.permission.EasyPermission;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.DeviceUtils;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MD5;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.utils.StaticCode;
import com.sk.meikelai.view.LoadingDialog;
import com.sk.meikelai.view.MyListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.sk.meikelai.common.Constants.APPLICATIONID;
import static com.sk.meikelai.common.Constants.SALE_DIR;


/**
 * Created by Administrator on 2017/6/14.
 */

public class CashOrderPayActivity extends BaseActivity implements EasyPermission.PermissionCallback, HandleDataCallBack {

    @BindView(R.id.rc_content)
    MyListView mRcContent;

    CashOrderProjectAdapter mAdapter;
    @BindView(R.id.saving_card)
    RelativeLayout mSavingCard;
    @BindView(R.id.switchButton)
    SwitchButton mSwitchButton;
    @BindView(R.id.tv_card)
    RadioButton mTvCard;
    @BindView(R.id.tv_cash)
    RadioButton mTvCash;
    @BindView(R.id.tv_epay)
    RadioButton mTvEpay;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.once_cash_num)
    TextView onceCashNum;
    @BindView(R.id.year_cash_num)
    TextView yearCashNum;
    @BindView(R.id.czk_money)
    TextView czkMoney;
    @BindView(R.id.wait_pay)
    TextView waitPay;
    @BindView(R.id.ed_remarks)
    EditText edRemarks;
    @BindView(R.id.go_pay)
    Button goPay;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.once_cash_ll)
    LinearLayout onceCashLl;
    @BindView(R.id.year_cash_ll)
    LinearLayout yearCashLl;
    @BindView(R.id.save_cash_ll)
    LinearLayout saveCashLl;
    @BindView(R.id.pay_way_ll)
    LinearLayout payWayLl;

    private ArrayList<CashCardBean.ReturnDataBean> onceList = new ArrayList<>();
    private ArrayList<ProjectListBean.ReturnDataBean> serviceList = new ArrayList<>();
    private ArrayList<ProductListBean.ReturnDataBean> productList = new ArrayList<>();


    //应收金额
    private int should_total = 0;
    //订单总额
    private int order_total = 0;
    //修改的金额
    private int actual_total = 0;
    //实际支付金额
    private int cashTotal = 0;
    //储值卡能抵扣的金额
    private int discount_total = 0;
    //储值卡实际扣款金额
    private int card_total = 0;
    //抹零金额
    private int odd_total = 0;

    private int yearCardCount = 0;
    private int onceCardCount = 0;

    private int cyzkMoney = 0;
    private int bcyzkMoney = 0;

    private static final int CAMERA = 1000;
    private static final String[] sPermissions = new String[]{
            Manifest.permission.CAMERA};

    private ArrayMap<String, Object> paramsMap = new ArrayMap<>();
    private List<PayDetail> payDetails = new ArrayList<>();

    private String scanCode;

    private LoadingDialog loadingDialog;

    private String order_code = "";
    private int count = 0;
    private static int Save_Card = 1111;
    private ArrayList<CardPay> cardPays = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_cash_order_pay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = MyUtils.getLoadingDialog(mContext, false);

        mSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    int total = (int) (cashTotal / 100f);
                    goPay.setText("结账:" + total);
                    odd_total = cashTotal - total * 100;
                    paramsMap.put("odd_total", odd_total);
                } else {
                    goPay.setText("结账:" + String.valueOf((cashTotal) / 100f));
                }
            }
        });

        onceList = getIntent().getParcelableArrayListExtra("once");
        serviceList = getIntent().getParcelableArrayListExtra("service");
        productList = getIntent().getParcelableArrayListExtra("product");

        cyzkMoney = calculateCyzkMoney();
        bcyzkMoney = caculateBcyzkMoney();



        if (onceList.size() == 0) {
            onceCashLl.setVisibility(View.GONE);
            yearCashLl.setVisibility(View.GONE);

        }

        if (!SPUtils.contains(mContext, StaticCode.SP_VIP_DATA)) {
            saveCashLl.setVisibility(View.GONE);
        } else {
            int saveCount = MyUtils.getSaveCount(mContext);
            if (saveCount <= 0) {
                saveCashLl.setVisibility(View.GONE);
            } else {
                saveCashLl.setVisibility(View.VISIBLE);
                if(cyzkMoney>0){
                    AppApi.getSaveCardList(MyUtils.getShopCode(mContext), MyUtils.getCashUserCode(mContext), this, Save_Card);
                }
            }

        }

        mAdapter = new CashOrderProjectAdapter(mContext, onceList, serviceList, productList);
        mRcContent.setAdapter(mAdapter);
        for (int i = 0; i < onceList.size(); i++) {
            if (onceList.get(i).getCardType() == 0) {
                onceCardCount++;
            } else if (onceList.get(i).getCardType() == 1) {
                yearCardCount++;
            }
        }
        if (onceCardCount <= 0) {
            onceCashLl.setVisibility(View.GONE);
        }
        if (yearCardCount <= 0) {
            yearCashLl.setVisibility(View.GONE);
        }
        yearCashNum.setText(yearCardCount + "次");
        onceCashNum.setText(onceCardCount + "次");


        should_total = calculateTotal();
        order_total = calculateAllTotal();
        actual_total = calculateActualtotal();
        cashTotal = actual_total;
        if (cashTotal == 0) {
            payWayLl.setVisibility(View.GONE);
            saveCashLl.setVisibility(View.GONE);
        }

        waitPay.setText(String.valueOf((double) should_total / 100f - discount_total));

        payDetails = getAllProdetails();

        paramsMap.put("odd_total", String.valueOf(odd_total));
        paramsMap.put("order_total", String.valueOf(order_total));
        paramsMap.put("should_total", String.valueOf(should_total));
        paramsMap.put("actual_total", String.valueOf(actual_total));
        paramsMap.put("base_user_code", MyUtils.getCashUserCode(mContext));
        paramsMap.put("user_code", MyUtils.getCode(mContext));
        paramsMap.put("device_ip", DeviceUtils.getIp());
        paramsMap.put("device_info", DeviceUtils.getDeviceInfo(mContext));
        paramsMap.put("detail_list", payDetails);
        paramsMap.put("discount_total", "0");
        paramsMap.put("card_total", "0");
        paramsMap.put("cash_total", String.valueOf(cashTotal));

        waitPay.setText(String.valueOf(actual_total / 100f));
        goPay.setText("结账:" + cashTotal / 100f);
    }

    private int calculateActualtotal() {
        int money = 0;
        for (int i = 0; i < serviceList.size(); i++) {
            money += serviceList.get(i).getActual_price();
        }
        for (int i = 0; i < productList.size(); i++) {
            money += productList.get(i).getActual_price();
        }
        return money;
    }

    private List<PayDetail> getAllProdetails() {
        List<PayDetail> payDetails = new ArrayList<>();
        for (int i = 0; i < onceList.size(); i++) {
            PayDetail entity = new PayDetail();
            if (onceList.get(i).getCardType() == 0) {
                entity.setPay_type("1");
            } else if (onceList.get(i).getCardType() == 1) {
                entity.setPay_type("2");
            }
            entity.setPrice(String.valueOf(onceList.get(i).getProject_price()));
            entity.setActual_price(String.valueOf(onceList.get(i).getProject_price()));
            entity.setProject_code(onceList.get(i).getProject_code());
            entity.setProject_name(onceList.get(i).getProject_name());
            entity.setIs_discount("0");
            entity.setService_type("0");
            entity.setUser_code(onceList.get(i).getPepCode());
            entity.setCard_user_code(onceList.get(i).getCard_user_code());
            entity.setCard_user_project_code(onceList.get(i).getCode());
            payDetails.add(entity);
        }
        for (int i = 0; i < serviceList.size(); i++) {
            PayDetail entity = new PayDetail();
            entity.setPay_type("0");
            entity.setPrice(String.valueOf(serviceList.get(i).getPrice()));
            entity.setActual_price(String.valueOf(serviceList.get(i).getActual_price()));
            entity.setProject_code(serviceList.get(i).getCode());
            entity.setProject_name(serviceList.get(i).getName());
            if (serviceList.get(i).isDiscount()) {
                entity.setIs_discount("1");
            } else {
                entity.setIs_discount("0");
            }
            entity.setService_type("0");
            entity.setUser_code(serviceList.get(i).getPepCode());
            payDetails.add(entity);
        }
        for (int i = 0; i < productList.size(); i++) {
            PayDetail entity = new PayDetail();
            entity.setPay_type("0");
            entity.setPrice(String.valueOf(productList.get(i).getPrice()));
            entity.setActual_price(String.valueOf(productList.get(i).getActual_price()));
            entity.setProduce_code(productList.get(i).getCode());
            entity.setProduce_name(productList.get(i).getName());
            if (productList.get(i).isDiscount()) {
                entity.setIs_discount("1");
            } else {
                entity.setIs_discount("0");
            }
            entity.setService_type("1");
            entity.setUser_code(productList.get(i).getPepCode());
            payDetails.add(entity);
        }
        return payDetails;

    }

    private int calculateTotal() {
        int money = 0;
        for (int i = 0; i < serviceList.size(); i++) {
            money += serviceList.get(i).getPrice();
        }
        for (int i = 0; i < productList.size(); i++) {
            money += productList.get(i).getPrice();
        }
        return money;
    }

    private int calculateAllTotal() {
        int money = 0;
        for (int i = 0; i < onceList.size(); i++) {
            money += onceList.get(i).getProject_price();
        }
        for (int i = 0; i < serviceList.size(); i++) {
            money += serviceList.get(i).getPrice();
        }
        for (int i = 0; i < productList.size(); i++) {
            money += productList.get(i).getPrice();
        }
        return money;
    }

    //计算参与折扣的money
    private int calculateCyzkMoney() {
        int money = 0;
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).isDiscount()) {
                money += serviceList.get(i).getActual_price();
            }
        }
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).isDiscount()) {
                money += productList.get(i).getActual_price();
            }
        }
        return money;
    }

    //计算不参与折扣的money
    private int caculateBcyzkMoney() {
        int money = 0;
        for (int i = 0; i < serviceList.size(); i++) {
            if (!serviceList.get(i).isDiscount()) {
                money += serviceList.get(i).getActual_price();
            }
        }
        for (int i = 0; i < productList.size(); i++) {
            if (!productList.get(i).isDiscount()) {
                money += productList.get(i).getActual_price();
            }
        }
        return money;
    }

    @OnClick({R.id.back, R.id.saving_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.saving_card:
                Intent intent = new Intent(this, SavingCardPayActivity.class);
                intent.putExtra("cyzkMoney", cyzkMoney);
                intent.putExtra("bcyzkMoney", bcyzkMoney);
                intent.putParcelableArrayListExtra("pays",cardPays);
                startActivityForResult(intent, 0);
                break;
        }
    }

    @OnClick(R.id.go_pay)
    public void onViewClicked() {
        if (payWayLl.getVisibility() == View.VISIBLE) {
            switch (radioGroup.getCheckedRadioButtonId()) {

                //银行卡消费
                case R.id.tv_card:
                    if (cashTotal > 0) {
                        if (Build.MANUFACTURER.contains("basewin")) {
                            paramsMap.put("trade_type", "CARD");
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
                    } else {
                        CashTipDialogFragment cashTipDialogFragment = new CashTipDialogFragment();
                        cashTipDialogFragment.show(getFragmentManager(), "tip");
                    }

                    break;
                //现金消费
                case R.id.tv_cash:
                    paramsMap.put("trade_type", "CASH");
                    Log.v("paramsMap", String.valueOf(order_total) + String.valueOf(discount_total) + String.valueOf(should_total) +
                            String.valueOf(actual_total) + String.valueOf(card_total) + String.valueOf(cashTotal) + MyUtils
                            .getCashUserCode(mContext) + MyUtils.getCode(mContext));
                    paramsMap.put("key", MD5.md5(String.valueOf(order_total) + String.valueOf(discount_total) + String.valueOf
                            (should_total) + String.valueOf(actual_total) + String.valueOf(card_total) + String.valueOf(cashTotal) +
                            MyUtils.getCashUserCode(mContext) + MyUtils.getCode(mContext)));
                    paramsMap.put("remark", edRemarks.getText().toString());
                    loadingDialog.show();
                    Log.v("loadingDialog", JSON.toJSONString(paramsMap));
                    AppApi.payOrder(JSON.toJSONString(paramsMap), this, Constants.Cash_Code);
                    break;
                //扫码支付
                case R.id.tv_epay:
                    if (cashTotal > 0) {
                        paramsMap.put("trade_type", "ONLINE");
                        EasyPermission.with(CashOrderPayActivity.this)
                                .addRequestCode(CAMERA)
                                .permissions(sPermissions)
                                .rationale("没有授权!")
                                .request();
                    } else {
                        CashTipDialogFragment cashTipDialogFragment = new CashTipDialogFragment();
                        cashTipDialogFragment.show(getFragmentManager(), "tip");
                    }
                    break;
            }
        } else {
            paramsMap.put("trade_type", "CASH");
            Log.v("paramsMap", String.valueOf(order_total) + String.valueOf(discount_total) + String.valueOf(should_total) + String
                    .valueOf(actual_total) + String.valueOf(card_total) + String.valueOf(cashTotal) + MyUtils.getCashUserCode(mContext) +
                    MyUtils.getCode(mContext));
            paramsMap.put("key", MD5.md5(String.valueOf(order_total) + String.valueOf(discount_total) + String.valueOf(should_total) +
                    String.valueOf(actual_total) + String.valueOf(card_total) + String.valueOf(cashTotal) + MyUtils.getCashUserCode
                    (mContext) + MyUtils.getCode(mContext)));
            paramsMap.put("remark", edRemarks.getText().toString());
            loadingDialog.show();
            Log.v("loadingDialog", JSON.toJSONString(paramsMap));
            AppApi.payOrder(JSON.toJSONString(paramsMap), this, Constants.Cash_Code);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            if (requestCode == 0) {
                //选用储值卡消费的回调
                cardPays = data.getParcelableArrayListExtra("save");
                int skAllmoney = data.getIntExtra("skMoney", 0);
                int dkAllmoney = data.getIntExtra("skAllMoney", 0);
                czkMoney.setText(String.valueOf(dkAllmoney / 100f));
                discount_total = dkAllmoney;
                card_total = skAllmoney;
                paramsMap.put("discount_total", String.valueOf(dkAllmoney));
                paramsMap.put("card_total", String.valueOf(skAllmoney));
                cashTotal = actual_total - dkAllmoney;
                paramsMap.put("cash_total", String.valueOf(cashTotal));
                paramsMap.put("o_card_list", cardPays);
                waitPay.setText(String.valueOf((cashTotal) / 100f));
                goPay.setText("结账:" + String.valueOf((cashTotal) / 100f));
                if (cashTotal == 0) {
                    payWayLl.setVisibility(View.GONE);
                }else{
                    payWayLl.setVisibility(View.VISIBLE);
                }

            } else if (requestCode == 1) {
                scanCode = data.getStringExtra("result");
                paramsMap.put("author_code", scanCode);
                Log.v("paramsMap", String.valueOf(order_total) + String.valueOf(discount_total) + String.valueOf(should_total) + String
                        .valueOf(actual_total) + String.valueOf(card_total) + String.valueOf(cashTotal) + MyUtils.getCashUserCode
                        (mContext) + MyUtils.getCode(mContext));
                paramsMap.put("key", MD5.md5(String.valueOf(order_total) + String.valueOf(discount_total) + String.valueOf(should_total)
                        + String.valueOf(actual_total) + String.valueOf(card_total) + String.valueOf(cashTotal) + MyUtils.getCashUserCode
                        (mContext) + MyUtils.getCode(mContext)));
                paramsMap.put("remark", edRemarks.getText().toString());
                loadingDialog.show();
                AppApi.payOrder(JSON.toJSONString(paramsMap), this, Constants.Scan_Code);
            } else if (requestCode == 100) {//银行卡消费回掉
                String reason = data.getStringExtra("success");
                Log.v("reason", reason);

                JSONObject jsonObject = JSON.parseObject(reason);
                String amount = jsonObject.getString("amount");// 交易金额
                String cardNo = jsonObject.getString("pan");// 卡号
                String referenceNo = jsonObject.getString("referenceNo");// 参考号
                String traceNo = jsonObject.getString("trace");// 凭证号
                String batchNo = jsonObject.getString("batch");
                CardBean cardBean = new CardBean();
                cardBean.setCardNo(cardNo);
                cardBean.setReferenceNo(referenceNo);
                cardBean.setTraceNo(traceNo);
                cardBean.setBatchNo(batchNo);
                paramsMap.put("union_pay", cardBean);
                loadingDialog.show();
                Log.v("paramsMap", String.valueOf(order_total) + String.valueOf(discount_total) + String.valueOf(should_total) + String
                        .valueOf(actual_total) + String.valueOf(card_total) + String.valueOf(cashTotal) + MyUtils.getCashUserCode
                        (mContext) + MyUtils.getCode(mContext));
                paramsMap.put("key", MD5.md5(String.valueOf(order_total) + String.valueOf(discount_total) + String.valueOf(should_total)
                        + String.valueOf(actual_total) + String.valueOf(card_total) + String.valueOf(cashTotal) + MyUtils.getCashUserCode
                        (mContext) + MyUtils.getCode(mContext)));
                AppApi.payOrder(JSON.toJSONString(paramsMap), this, Constants.Card_Code);

            } else if (requestCode == 101) {
                setResult(RESULT_OK);
                finish();
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (requestCode == 100) {
                if (data != null) {
                    String reason = data.getStringExtra("reason");
                    if (reason != null) {
                        Toast.makeText(getApplicationContext(), reason, Toast.LENGTH_SHORT).show();
                    }
                }
            }
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
            intent.putExtra("tag", "pay");
            startActivityForResult(intent, 1);
        }
    }

    @Override
    public void onPermissionDenied(int requestCode, List<String> perms) {
        Toast.makeText(mContext, "未授权", Toast.LENGTH_SHORT)
                .show();
        EasyPermission.checkDeniedPermissionsNeverAskAgain(this, "该设备没有授权," + "请去设置里授权!", perms);
    }


    @Override
    public void handlerSuccessData(String result, int tag) {

        if (tag == Save_Card) {
            SaveCardBean bean = JSONObject.parseObject(result, SaveCardBean.class);
            List<SaveCardBean.ReturnDataBean> dataBeanList = bean.getReturn_data();
            Collections.sort(dataBeanList);
            cardPays = calculateSaveCardTotal(dataBeanList, calculateCyzkMoney());
            paramsMap.put("discount_total", String.valueOf(discount_total));
            paramsMap.put("card_total", String.valueOf(card_total));
            cashTotal = actual_total - discount_total;
            paramsMap.put("cash_total", String.valueOf(cashTotal));
            paramsMap.put("o_card_list", cardPays);
            waitPay.setText(String.valueOf((cashTotal) / 100f));
            czkMoney.setText(String.valueOf(discount_total / 100f));
            goPay.setText("结账:" + String.valueOf((cashTotal) / 100f));
            if (cashTotal == 0) {
                payWayLl.setVisibility(View.GONE);
            }
        } else if (tag == Constants.Again_Code) {
            loadingDialog.cancelDialog();
            Toast.makeText(getApplicationContext(), "收银成功!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, CashResultActivity.class);
            intent.putExtra("tag", 1);
            intent.putExtra("order_code", order_code);
            startActivityForResult(intent, 101);
        } else {
            JSONObject jsonObject = JSON.parseObject(result);
            order_code = jsonObject.getString("return_order_code");

            loadingDialog.cancelDialog();
            Toast.makeText(getApplicationContext(), "收银成功!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, CashResultActivity.class);
            intent.putExtra("tag", 1);
            intent.putExtra("order_code", order_code);
            startActivityForResult(intent, 101);
        }

    }

    @Override
    public void handlerFailData(String failString, int tag) {

        if (tag == Constants.Scan_Code) {
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
                        AppApi.againCheckOrder(order_code, MyUtils.getCode(mContext), CashOrderPayActivity.this, Constants.Again_Code);
                    }
                }, 1500);

            } else {
                loadingDialog.cancelDialog();
                Toast.makeText(mContext, info, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, CashResultActivity.class);
                intent.putExtra("tag", 0);
                startActivityForResult(intent, 101);
            }
        } else if (tag == Constants.Again_Code) {
            JSONObject jsonObject = JSON.parseObject(failString);
            String info = jsonObject.getString("return_info");
            if (info.contains(Constants.SCAN_WAIT1)
                    || info.contains(Constants.SCAN_WAIT2)
                    || info.contains(Constants.SCAN_WAIT3)) {

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
                            AppApi.againCheckOrder(order_code, MyUtils.getCode(mContext), CashOrderPayActivity.this, Constants.Again_Code);
                        }

                    }
                }, 1500);

            } else {
                loadingDialog.cancelDialog();
                Toast.makeText(mContext, info, Toast.LENGTH_SHORT).show();

            }
        } else {
            loadingDialog.cancelDialog();
            Toast.makeText(mContext, "支付失败!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, CashResultActivity.class);
            intent.putExtra("tag", 0);
            startActivityForResult(intent, 101);
        }
    }

    @Override
    public void handleErrorData(String error, int tag) {
        loadingDialog.cancelDialog();
    }

    private ArrayList<CardPay> calculateSaveCardTotal(List<SaveCardBean.ReturnDataBean> dataBeanList, int cyzkMoney) {
        ArrayList<CardPay> pays = new ArrayList<>();
        for (int i = 0, leftMoney = cyzkMoney; i < dataBeanList.size(); i++) {
            SaveCardBean.ReturnDataBean entity = dataBeanList.get(i);
            int balance = entity.getBalance();
            if(balance>0){
                int dkBalance = (int) (entity.getBalance() /(float) entity.getDiscount() * 10);
                if (leftMoney > dkBalance) {
                    leftMoney = leftMoney - dkBalance;
                    CardPay cardPay = new CardPay();
                    cardPay.setIs_discount("1");
                    cardPay.setCard_user_code(entity.getCode());
                    //储值卡扣款
                    cardPay.setCard_total(String.valueOf(balance));
                    //抵扣
                    cardPay.setActual_total(String.valueOf(dkBalance));
                    card_total += balance;
                    discount_total += dkBalance;
                    pays.add(cardPay);

                } else {
                    int skMoney = leftMoney * entity.getDiscount() / 10;
                    CardPay cardPay = new CardPay();
                    cardPay.setIs_discount("1");
                    cardPay.setCard_user_code(entity.getCode());
                    //储值卡扣款
                    cardPay.setCard_total(String.valueOf(skMoney));
                    //抵扣
                    cardPay.setActual_total(String.valueOf(leftMoney));
                    card_total += skMoney;
                    discount_total += leftMoney;
                    pays.add(cardPay);
                    leftMoney = 0;
                    break;
                }
            }
        }
        return pays;
    }

}
