package com.sk.meikelai.activity.cash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.entity.CardPay;
import com.sk.meikelai.entity.RegionNumberEditText;
import com.sk.meikelai.entity.SaveCardBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.utils.StaticCode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/18.
 */

public class SavingCardPayActivity extends BaseActivity implements HandleDataCallBack {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.call_ll_1)
    LinearLayout cardLl1;
    @BindView(R.id.card_ll_2)
    LinearLayout cardLl2;
    @BindView(R.id.new_ewm)
    Button newEwm;
    @BindView(R.id.all_money)
    TextView allMoney;
    @BindView(R.id.dk_money)
    TextView dkMoney;

    List<EditText> allEditTestD = new ArrayList<>();
    List<EditText> allEditTestND = new ArrayList<>();
    @BindView(R.id.cyzk_money)
    TextView cyzkMoneyTv;
    @BindView(R.id.bcyzk_money)
    TextView bcyzkMoneyTv;
    @BindView(R.id.cyzk_money_ll)
    LinearLayout cyzkMoneyLl;
    @BindView(R.id.bcyzk_money_ll)
    LinearLayout bcyzkMoneyLl;

    private int cyzkMoney = 0;
    private int bcyzkMoney = 0;

    DecimalFormat dcmFmt = new DecimalFormat("0.00");
    private ArrayList<CardPay> cardPays = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_saving_card_pay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cyzkMoney = getIntent().getIntExtra("cyzkMoney", 0);
        bcyzkMoney = getIntent().getIntExtra("bcyzkMoney", 0);
        cardPays = getIntent().getParcelableArrayListExtra("pays");
        if (cyzkMoney == 0) {
            cyzkMoneyLl.setVisibility(View.GONE);
            cardLl1.setVisibility(View.GONE);
        }
        if (bcyzkMoney == 0) {
            bcyzkMoneyLl.setVisibility(View.GONE);
            cardLl2.setVisibility(View.GONE);
        }
        cyzkMoneyTv.setText("参与折扣 " + cyzkMoney / 100f + "元");
        bcyzkMoneyTv.setText("不参与折扣 " + bcyzkMoney / 100f + "元");
        if (SPUtils.contains(mContext, StaticCode.SP_VIP_DATA)) {
            AppApi.getSaveCardList(MyUtils.getShopCode(mContext), MyUtils.getCashUserCode(mContext), this, 0);
        } else {
            Toast.makeText(mContext, "当前非会员登陆，无法获取储值卡信息!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.back, R.id.new_ewm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.new_ewm:
                if (isFormat()) {
                    if (caculateDetails().size() > 0) {
                        double skAllMoney = Double.parseDouble(allMoney.getText().toString());
                        double dkAllMoney = Double.parseDouble(dkMoney.getText().toString());
                        Intent intent = new Intent();
                        intent.putParcelableArrayListExtra("save", caculateDetails());
                        intent.putExtra("skMoney", (int) (skAllMoney * 100));
                        intent.putExtra("skAllMoney", (int) (dkAllMoney * 100));
                        setResult(RESULT_OK, intent);
                    }else{
                        Intent intent = new Intent();
                        intent.putParcelableArrayListExtra("save", null);
                        intent.putExtra("skMoney", 0);
                        intent.putExtra("skAllMoney", 0);
                        setResult(RESULT_OK, intent);
                    }
                    finish();
                }
                break;
        }
    }

    @Override
    public void handlerSuccessData(String result, final int tag) {
        SaveCardBean bean = JSONObject.parseObject(result, SaveCardBean.class);
        List<SaveCardBean.ReturnDataBean> dataBeanList = bean.getReturn_data();
        List<EditText> allEditTest1 = new ArrayList<>();
        List<CheckBox> checkBoxes1 = new ArrayList<>();
        List<EditText> allEditTest2 = new ArrayList<>();
        List<CheckBox> checkBoxes2 = new ArrayList<>();

        for (int i = 0; i < dataBeanList.size(); i++) {
            SaveCardBean.ReturnDataBean entity = dataBeanList.get(i);
            View view1 = LayoutInflater.from(mContext).inflate(R.layout.item_save_card, cardLl1, false);
            CheckBox checkBox1 = (CheckBox) view1.findViewById(R.id.checkbox);
            if (cyzkMoney == 0) {
                checkBox1.setClickable(false);
            }
            TextView cardName1 = (TextView) view1.findViewById(R.id.card_name);
            TextView tvbalance1 = (TextView) view1.findViewById(R.id.tv_balance);
            TextView tvDeductible1 = (TextView) view1.findViewById(R.id.tv_deductible);
            final RegionNumberEditText edMoney1 = (RegionNumberEditText) view1.findViewById(R.id.ed_money);
            cardName1.setText(entity.getCard_name());
            tvbalance1.setText("余额:" + entity.getBalance() / 100f + "元");
            Log.v("handlerSuccessData", String.valueOf((entity.getBalance() / (float) entity.getDiscount()) * 10 / 100f));
            tvDeductible1.setText("抵扣:" + dcmFmt.format((entity.getBalance() / (float) entity.getDiscount()) * 10 / 100f) + "元");
            checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        edMoney1.setText("");
                        edMoney1.setVisibility(View.VISIBLE);
                    } else {
                        edMoney1.setText("");
                        edMoney1.setVisibility(View.INVISIBLE);
                    }
                }
            });
            cardLl1.addView(view1);
            edMoney1.setTag(entity);
            allEditTest1.add(edMoney1);
            checkBoxes1.add(checkBox1);
            allEditTestD.add(edMoney1);
            View view2 = LayoutInflater.from(mContext).inflate(R.layout.item_save_card, cardLl2, false);
            CheckBox checkBox2 = (CheckBox) view2.findViewById(R.id.checkbox);
            if (bcyzkMoney == 0) {
                checkBox2.setClickable(false);
            }
            TextView cardName2 = (TextView) view2.findViewById(R.id.card_name);
            TextView tvbalance2 = (TextView) view2.findViewById(R.id.tv_balance);
            TextView tvDeductibl2 = (TextView) view2.findViewById(R.id.tv_deductible);
            final RegionNumberEditText edMoney2 = (RegionNumberEditText) view2.findViewById(R.id.ed_money);
            cardName2.setText(entity.getCard_name());
            tvbalance2.setText("余额:" + entity.getBalance() / 100f + "元");
            tvDeductibl2.setText("抵扣:" + dcmFmt.format((entity.getBalance() * entity.getCoefficient()) / 100) + "元");
            checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        edMoney2.setText("");
                        edMoney2.setVisibility(View.VISIBLE);
                    } else {
                        edMoney2.setText("");
                        edMoney2.setVisibility(View.INVISIBLE);
                    }
                }
            });
            cardLl2.addView(view2);
            edMoney2.setTag(entity);
            allEditTest2.add(edMoney2);
            checkBoxes2.add(checkBox2);
            allEditTestND.add(edMoney2);
            MyUtils.setEditTextContact(edMoney1, edMoney2, entity.getBalance() / 100f, true, allEditTest1, allEditTest2, allMoney, dkMoney);
            if (cardPays != null && cardPays.size() > 0) {
                for (int p = 0; p < cardPays.size(); p++) {
                    for (int j = 0; j < allEditTest1.size(); j++) {
                        EditText editText = allEditTest1.get(j);
                        final SaveCardBean.ReturnDataBean dataBean = (SaveCardBean.ReturnDataBean) editText.getTag();
                        if (cardPays.get(p).getCard_user_code().equals(dataBean.getCode())) {
                            if (cardPays.get(p).getIs_discount().equals("1")) {
                                checkBoxes1.get(j).setChecked(true);
                                editText.setVisibility(View.VISIBLE);
                                editText.setText(String.valueOf(Double.parseDouble(cardPays.get(p).getActual_total()) / 100f));
                            }
                        }
                    }

                    for (int m = 0; m < allEditTest2.size(); m++) {
                        EditText editText = allEditTest2.get(m);
                        final SaveCardBean.ReturnDataBean dataBean = (SaveCardBean.ReturnDataBean) editText.getTag();
                        if (cardPays.get(p).getCard_user_code().equals(dataBean.getCode())) {
                            if (cardPays.get(p).getIs_discount().equals("0")) {
                                checkBoxes2.get(m).setChecked(true);
                                editText.setVisibility(View.VISIBLE);
                                editText.setText(String.valueOf(Double.parseDouble(cardPays.get(p).getActual_total()) / 100f));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void handlerFailData(String failString, int tag) {

    }

    @Override
    public void handleErrorData(String error, int tag) {

    }

    //计算储值卡付款详情
    private ArrayList<CardPay> caculateDetails() {
        ArrayList<CardPay> list = new ArrayList<>();
        for (int i = 0; i < allEditTestD.size(); i++) {
            EditText editText = allEditTestD.get(i);
            SaveCardBean.ReturnDataBean bean = (SaveCardBean.ReturnDataBean) editText.getTag();
            if (editText.getText() != null && editText.getText().toString().length() > 0 && Double.parseDouble(editText.getText().toString()) > 0) {
                CardPay cardPay = new CardPay();
                cardPay.setIs_discount("1");
                double nowMoney = Double.parseDouble(editText.getText().toString());
                double skMoney = Double.parseDouble(dcmFmt.format(nowMoney * bean.getDiscount() / 10d));
                Log.v("skMoney", skMoney + "");
                cardPay.setCard_user_code(bean.getCode());
                cardPay.setCard_total(String.valueOf((int) (skMoney * 100)));
                cardPay.setActual_total(String.valueOf(nowMoney * 100));
                list.add(cardPay);
            }

        }
        for (int i = 0; i < allEditTestND.size(); i++) {

            EditText editText = allEditTestND.get(i);
            SaveCardBean.ReturnDataBean bean = (SaveCardBean.ReturnDataBean) editText.getTag();
            if (editText.getText() != null && editText.getText().toString().length() > 0 && Double.parseDouble(editText.getText().toString()) > 0) {
                CardPay cardPay = new CardPay();
                cardPay.setIs_discount("0");
                double nowMoney = Double.parseDouble(editText.getText().toString());
                double skMoney = Double.parseDouble(dcmFmt.format(nowMoney / (bean.getCoefficient() * 1.0)));
                cardPay.setCard_user_code(bean.getCode());
                Log.v("skMoney", skMoney + "");
                cardPay.setCard_total(String.valueOf((int) (skMoney * 100)));
                cardPay.setActual_total(String.valueOf(nowMoney * 100));
                list.add(cardPay);
            }
        }
        return list;

    }

    //判断是否符合要求
    private boolean isFormat() {
        int cyzkDkMoney = 0;
        int bcyzkDkMoney = 0;
        for (int i = 0; i < allEditTestD.size(); i++) {
            EditText editText = allEditTestD.get(i);
            if (editText.getText() != null && editText.getText().toString().length() > 0 && Double.parseDouble(editText.getText().toString()) > 0) {
                double nowMoney = Double.parseDouble(editText.getText().toString()) * 100;
                cyzkDkMoney += nowMoney;
            }
        }

        for (int i = 0; i < allEditTestND.size(); i++) {
            EditText editText = allEditTestND.get(i);
            if (editText.getText() != null && editText.getText().toString().length() > 0 && Double.parseDouble(editText.getText().toString()) > 0) {
                double nowMoney = Double.parseDouble(editText.getText().toString()) * 100;
                bcyzkDkMoney += nowMoney;
            }
        }
        if (cyzkDkMoney > cyzkMoney) {
            Toast.makeText(mContext, "参与折扣抵扣总额大于" + cyzkMoney / 100f + "元", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (bcyzkDkMoney > bcyzkMoney) {
            Toast.makeText(mContext, "不参与折扣抵扣总额大于" + bcyzkMoney / 100f + "元", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }


}
