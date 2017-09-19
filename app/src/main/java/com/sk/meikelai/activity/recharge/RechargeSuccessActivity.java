package com.sk.meikelai.activity.recharge;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.basewin.services.ServiceManager;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.callback.IsPrintCallBack;
import com.sk.meikelai.callback.PrinterListener;
import com.sk.meikelai.dialog.IsPrintDialogFragment;
import com.sk.meikelai.entity.FirstEvent;
import com.sk.meikelai.entity.RechargePrint;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.utils.StaticCode;
import com.sk.meikelai.view.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sk on 2017/6/5.
 */

public class RechargeSuccessActivity extends BaseActivity implements HandleDataCallBack, IsPrintCallBack {


    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.go_recharge_f)
    TextView mGoRechargeF;
    @BindView(R.id.quit_account_f)
    TextView mQuitAccountF;
    @BindView(R.id.go_cash_f)
    TextView mGoCashF;

    private String order_code;
    private PrinterListener printer_callback = new PrinterListener();
    private LoadingDialog loadingDialog;
    private String brand_name = "";
    private String shop_name = "";
    StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected int getContentView() {
        return R.layout.fragment_recharge_success;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        order_code = getIntent().getStringExtra("order_code");

        loadingDialog = MyUtils.getLoadingDialog(mContext, false);
        loadingDialog.show();

        AppApi.queryPrintInfo(order_code, this, 0);
    }

    @OnClick({R.id.back, R.id.go_cash_f, R.id.go_recharge_f, R.id.quit_account_f})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.go_cash_f:
                EventBus.getDefault().post(new FirstEvent("Goto_Cash_Info"));
                finish();
                break;
            case R.id.go_recharge_f:
                EventBus.getDefault().post(new FirstEvent("Goto_Recharge_Info"));
                finish();
                break;
            case R.id.quit_account_f:
                SPUtils.remove(mContext, StaticCode.SP_VIP_DATA);
                EventBus.getDefault().post(new FirstEvent("Goto_Recharge"));
                finish();
                break;
        }
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        loadingDialog.cancelDialog();
        RechargePrint rechargePrint = JSON.parseObject(result, RechargePrint.class);
        String order_code = rechargePrint.getReturn_data().getOrder_code();
        String order_time = rechargePrint.getReturn_data().getOrder_time();
        String cash_pep = rechargePrint.getReturn_data().getPerson_name();
        String base_user_name = rechargePrint.getReturn_data().getBase_user_name();
        String name = rechargePrint.getReturn_data().getName();
        stringBuilder.append("订单编号:").append(order_code).append("\n");
        stringBuilder.append("订单时间:").append(order_time).append("\n");
        stringBuilder.append("收银员　:").append(cash_pep).append("\n");
        stringBuilder.append("客户　　:").append(base_user_name).append("\n");
        stringBuilder.append("类型　　:").append(name).append("\n");
        stringBuilder.append("\n").append("名称:").append(rechargePrint.getReturn_data().getCard_name())
                .append("\n").append("升级:").append(rechargePrint.getReturn_data().getUpgrade_card_name())
                .append("\n充值金额:").append(String.valueOf(rechargePrint.getReturn_data().getRecharge_money() / 100f)).append("元");
        stringBuilder.append("\n实付金额:").append(String.valueOf(rechargePrint.getReturn_data().getActual_money() / 100f)).append("元");
        stringBuilder.append("\n赠送金额:").append(String.valueOf(rechargePrint.getReturn_data().getGift_money() / 100f)).append("元");

        int cash_total = rechargePrint.getReturn_data().getActual_money();
        if(rechargePrint.getReturn_data().getTrade_type().equals("CASH")){
            stringBuilder.append("\n现金支付:").append(cash_total/100f).append("元");
        }else if(rechargePrint.getReturn_data().getTrade_type().equals("ONLINE")){
            stringBuilder.append("\n电子支付:").append(cash_total/100f).append("元");
        }else if(rechargePrint.getReturn_data().getTrade_type().equals("CARD")){
            stringBuilder.append("\n银行卡付款:").append(cash_total/100f).append("元");
        }
        int card_balance = rechargePrint.getReturn_data().getCard_balance();
        stringBuilder.append("\n卡内余额:").append(card_balance/100f).append("\n");

        String remark = rechargePrint.getReturn_data().getRemark();
        if(remark.equals("")||remark.equals(null)||remark.equals("null")) {
            stringBuilder.append("备注:").append("").append("\n");
        }else {
            stringBuilder.append("备注:").append(remark).append("\n");
        }

        String phone = rechargePrint.getReturn_data().getShop_telephone();
        String address = rechargePrint.getReturn_data().getShop_address();
        stringBuilder.append("\n门店电话:").append(phone);
        stringBuilder.append("\n门店地址:").append(address);

        if (Build.MANUFACTURER.contains("basewin")) {
            // 初始化service manager
            // init service manager
            IsPrintDialogFragment isPrintDialogFragment = new IsPrintDialogFragment();
            isPrintDialogFragment.setCallBack(RechargeSuccessActivity.this);
            isPrintDialogFragment.show(getFragmentManager(),"print");
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

    /**
     * print text content
     */
    public void printText(String printString) {

        JSONObject printJson = new JSONObject();
        JSONArray printTest = new JSONArray();
        try {
            // Add text printing
            JSONObject json1 = new JSONObject();
            json1.put("content-type", "txt");
            json1.put("content", MyUtils.getAgent_name(mContext) + "\n" + MyUtils.getShopName(mContext)+"\n\n");
            json1.put("size", "3");
            json1.put("position", "center");
            json1.put("bold", "1");
            json1.put("italic", "0");
            json1.put("height", "-1");
            printTest.put(json1);


            JSONObject json2 = new JSONObject();
            json2.put("content-type", "txt");
            json2.put("content", printString);
            json2.put("size", "2");
            json2.put("position", "left");
            json2.put("offset", "0");
            json2.put("bold", "0");
            json2.put("italic", "0");
            json2.put("height", "-1");
            printTest.put(json2);

            // Add text printing
            JSONObject json3 = new JSONObject();
            json3.put("content-type", "txt");
            json3.put("content", "\n\n本软件由美加提供技术支持" + "\n" + "0514-82120299");
            json3.put("size", "2");
            json3.put("position", "center");
            json3.put("bold", "1");
            json3.put("italic", "0");
            json3.put("height", "-1");
            printTest.put(json3);

            com.pos.sdk.printer.PosPrinter.Parameters parameters = ServiceManager
                    .getInstence().getPrinter().getParameters();
            parameters.setPrintGray(5000);
            parameters.setPrintStep(4000);
            parameters.setPrintHeatPoint(1000);
            ServiceManager.getInstence().getPrinter()
                    .setParameters(parameters);
            ServiceManager.getInstence().getPrinter().setPrintGray(2000);
            ServiceManager.getInstence().getPrinter()
                    .printBottomFeedLine(5);

            printJson.put("spos", printTest);
            ServiceManager.getInstence().getPrinter().printBottomFeedLine(3);
            ServiceManager.getInstence().getPrinter().print(printJson.toString(), null, printer_callback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surePrint() {
        printText(stringBuilder.toString());
    }
}