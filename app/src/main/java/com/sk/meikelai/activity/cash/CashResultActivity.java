package com.sk.meikelai.activity.cash;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.basewin.services.ServiceManager;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.callback.IsPrintCallBack;
import com.sk.meikelai.callback.PrinterListener;
import com.sk.meikelai.dialog.IsPrintDialogFragment;
import com.sk.meikelai.entity.CashPrintBean;
import com.sk.meikelai.entity.MemberInfoBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.utils.StaticCode;
import com.sk.meikelai.view.LoadingDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.sk.meikelai.utils.StaticCode.SP_VIP_DATA;

/**
 * Created by sk on 2017/8/11.
 */

public class CashResultActivity extends BaseActivity implements HandleDataCallBack, IsPrintCallBack {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.go_cash_f)
    TextView goCashF;
    @BindView(R.id.quit_account_f)
    TextView quitAccountF;
    @BindView(R.id.go_cash)
    TextView goCash;
    @BindView(R.id.quit_account)
    TextView quitAccount;
    @BindView(R.id.cash_success)
    LinearLayout cashSuccess;
    @BindView(R.id.cash_fail)
    LinearLayout cashFail;

    private int isSuccess = 0;

    private String order_code = "";
    private PrinterListener printer_callback = new PrinterListener();
    private LoadingDialog loadingDialog;
    private String brand_name = "";
    private String shop_name = "";
    StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected int getContentView() {
        return R.layout.activity_cash_result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isSuccess = getIntent().getIntExtra("tag", 0);
        order_code = getIntent().getStringExtra("order_code");


        if (isSuccess == 0) {
            cashFail.setVisibility(View.VISIBLE);
            cashSuccess.setVisibility(View.GONE);
        } else {
            loadingDialog = MyUtils.getLoadingDialog(mContext, false);
            loadingDialog.show();

            if (SPUtils.contains(mContext, StaticCode.SP_VIP_DATA)) {
                MemberInfoBean memberInfoBean = com.alibaba.fastjson.JSONObject.parseObject((String) SPUtils.get(mContext, SP_VIP_DATA, ""), MemberInfoBean.class);
                String str_VipCode = memberInfoBean.getReturn_data().getCode();
                AppApi.getMemberInfo(str_VipCode, MyUtils.getShopCode(mContext), CashResultActivity.this, 100);
            }
            AppApi.queryPrintInfo(order_code, this, 0);
            cashFail.setVisibility(View.GONE);
            cashSuccess.setVisibility(View.VISIBLE);
        }

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.go_cash_f, R.id.quit_account_f, R.id.go_cash, R.id.quit_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_cash_f:
                finish();
                break;
            case R.id.quit_account_f:
                SPUtils.remove(mContext, StaticCode.SP_VIP_DATA);
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.go_cash:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.quit_account:
                SPUtils.remove(mContext, StaticCode.SP_VIP_DATA);
                setResult(RESULT_OK);
                finish();
                break;
        }
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
            json1.put("content", MyUtils.getAgent_name(mContext) + "\n" + MyUtils.getShopName(mContext) + "\n\n");
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

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Toast.makeText(mContext, "请选择相应操作!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    @Override
    public void handlerSuccessData(String result, int tag) {
        if (tag == 0) {
            loadingDialog.cancelDialog();
            CashPrintBean cashPrintBean = JSON.parseObject(result, CashPrintBean.class);

            String order_code = cashPrintBean.getReturn_data().getOrder_code();
            String order_time = cashPrintBean.getReturn_data().getOrder_time();
            String cash_pep = cashPrintBean.getReturn_data().getPerson_name();
            String base_user_name = cashPrintBean.getReturn_data().getBase_user_name();
            stringBuilder.append("订单编号:").append(order_code).append("\n");
            stringBuilder.append("订单时间:").append(order_time).append("\n");
            stringBuilder.append("收银员　:").append(cash_pep).append("\n");
            stringBuilder.append("客户　　:").append(base_user_name).append("\n");
            List<CashPrintBean.ReturnDataBean.DetailListBean> beanList = cashPrintBean.getReturn_data().getDetail_list();
            for (CashPrintBean.ReturnDataBean.DetailListBean bean : beanList) {
                stringBuilder.append("\n").append("名称:").append(bean.getConsume_name()).append("\n");
                stringBuilder.append("售价:").append(bean.getMinor_total() / 100f).append("元\n");

            }
            int order_total = cashPrintBean.getReturn_data().getOrder_total();
            int not_discount_total = cashPrintBean.getReturn_data().getNo_discount_total();
            int count_cash_num = cashPrintBean.getReturn_data().getCkNums();
            int year_cash_num = cashPrintBean.getReturn_data().getNkNums();
            int czk_total = cashPrintBean.getReturn_data().getCard_total();
            int cash_total = cashPrintBean.getReturn_data().getCash_total();
            String remark = cashPrintBean.getReturn_data().getRemark();

            stringBuilder.append("\n合计:").append(String.valueOf(order_total / 100f)).append("元").append(",")
                    .append("\n其中不参与折扣:").append(String.valueOf(not_discount_total / 100f)).append("元");
            if (count_cash_num > 0) {
                stringBuilder.append("\n次卡消费:").append(count_cash_num).append("次");
            }
            if (year_cash_num > 0) {
                stringBuilder.append("\n年卡消费:").append(year_cash_num).append("次");
            }
            if (czk_total > 0) {
                stringBuilder.append("\n储值卡扣款:").append(czk_total / 100f).append("元");
            }
            if (cashPrintBean.getReturn_data().getTrade_type().equals("CASH") && cash_total > 0) {
                stringBuilder.append("\n现金支付:").append(cash_total / 100f).append("元");
            } else if (cashPrintBean.getReturn_data().getTrade_type().equals("ONLINE") && cash_total > 0) {
                stringBuilder.append("\n电子支付:").append(cash_total / 100f).append("元");
            } else if (cashPrintBean.getReturn_data().getTrade_type().equals("CARD") && cash_total > 0) {
                stringBuilder.append("\n银行卡付款:").append(cash_total / 100f).append("元");
            }

            if (remark.equals("") || remark.equals(null) || remark.equals("null")) {
                stringBuilder.append("\n备注:").append("").append("\n");
            } else {
                stringBuilder.append("\n备注:").append(remark).append("\n");
            }

            String phone = cashPrintBean.getReturn_data().getShop_telephone();
            String address = cashPrintBean.getReturn_data().getShop_address();
            stringBuilder.append("\n门店电话:").append(phone);
            stringBuilder.append("\n门店地址:").append(address);

            if (Build.MANUFACTURER.contains("basewin")) {
                // 初始化service manager
                // init service manager
                IsPrintDialogFragment isPrintDialogFragment = new IsPrintDialogFragment();
                isPrintDialogFragment.setCallBack(CashResultActivity.this);
                isPrintDialogFragment.show(getFragmentManager(), "print");
            }
        } else {
            SPUtils.put(mContext, SP_VIP_DATA, result);
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
    public void surePrint() {
        printText(stringBuilder.toString());
    }
}
