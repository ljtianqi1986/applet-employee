package com.sk.meikelai.activity.recharge;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sk.meikelai.Adapter.SelectCardAdapter;
import com.sk.meikelai.Adapter.SetWorkerAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;
import com.sk.meikelai.activity.cash.CaptureActivity;
import com.sk.meikelai.activity.cash.CashFragment;
import com.sk.meikelai.activity.cash.CashResultActivity;
import com.sk.meikelai.activity.main.MainActivity;
import com.sk.meikelai.callback.ExitCallBack;
import com.sk.meikelai.callback.SureSelectedCallBack;
import com.sk.meikelai.common.Constants;
import com.sk.meikelai.dialog.DialogCardFragment;
import com.sk.meikelai.dialog.DialogPayFragment;
import com.sk.meikelai.dialog.DialogSelectWorkerFragment;
import com.sk.meikelai.dialog.ExitDialogFragment;
import com.sk.meikelai.entity.CardBalanceBean;
import com.sk.meikelai.entity.CardBean;
import com.sk.meikelai.entity.CreateCardBean;
import com.sk.meikelai.entity.FirstEvent;
import com.sk.meikelai.entity.MemberInfoBean;
import com.sk.meikelai.entity.UpdateCardBean;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.sk.meikelai.common.Constants.APPLICATIONID;
import static com.sk.meikelai.common.Constants.SALE_DIR;
import static com.sk.meikelai.utils.StaticCode.SP_VIP_DATA;

/**
 * Created by sk on 2017/6/5.
 */

public class RechargeCardFragment extends BaseFragment implements EasyPermission.PermissionCallback,
        HandleDataCallBack, ExitCallBack, SureSelectedCallBack {


    @BindView(R.id.cash_icon)
    ImageView mCashIcon;
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.user_number)
    TextView mUserNumber;
    @BindView(R.id.saving_card_num)
    TextView mSavingCardNum;
    @BindView(R.id.saving_card)
    LinearLayout mSavingCard;
    @BindView(R.id.once_card_num)
    TextView mOnceCardNum;
    @BindView(R.id.once_card)
    LinearLayout mOnceCard;
    @BindView(R.id.year_card_num)
    TextView mYearCardNum;
    @BindView(R.id.year_card)
    LinearLayout mYearCard;
    @BindView(R.id.rc_content_card)
    RecyclerView mRcContentCard;
    @BindView(R.id.card_type)
    TextView mCardType;
    @BindView(R.id.tv_card)
    RadioButton mTvCard;
    @BindView(R.id.tv_cash)
    RadioButton mTvCash;
    @BindView(R.id.tv_epay)
    RadioButton mTvEpay;
    @BindView(R.id.add)
    TextView mAdd;
    @BindView(R.id.rc_content)
    RecyclerView mRcContent;
    @BindView(R.id.recharge_ok)
    Button mRechargeOk;
    @BindView(R.id.cb_update)
    CheckBox mCbUpdate;
    @BindView(R.id.et_recharge_price)
    EditText mEtRechargePrice;
    @BindView(R.id.et_pay_price)
    EditText mEtPayPrice;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.ll_update)
    LinearLayout mLlUpdate;
    @BindView(R.id.exit_num)
    TextView exitNum;
    @BindView(R.id.item_no_card)
    TextView mLlNoCard;
    @BindView(R.id.item_card_info)
    LinearLayout mLlCardInfo;

    private String str_CardCode = "";  //卡的code
    private String str_UserCode;   //收银员
    private String str_VipCode = "";  //客户code
    private String str_PayType = "ONLINE";  //	支付类型
    private String str_RrchargePrice = "";  //应付金额
    private String str_PayPrice = "";    //实付金额
    private String str_Type = "";
    private String str_Ip;
    private String str_Info;
    private String str_Remark;
    private String str_PayCode;
    private String str_UpdateCode;
    private String str_WorkerCode;
    private String str_Money;
    private int mTotle;
    private int mBeforeMoney;
    private int mRechargeMoney;
    private int mSaleMoney;
    private String order_code = "";
    private int count = 0;
    private double str_Coefficient;

    //    private String str_Open_CardCode;
    private TextView mTv_Open_CardName;
    private EditText mEt_Money;
    private DialogCardFragment mDialogCardFragment;
    private DialogPayFragment mDialogPayFragment;
    LoadingDialog loadingDialog;

    private LinearLayoutManager mLinearLayoutManager;


    private List<WorkerBean.ReturnDataBean> selectWorkList = new ArrayList<>();

    private List<CreateCardBean.JsonStringBean.CommissionListBean> list = new ArrayList<>();
    private SetWorkerAdapter mSetWorkerAdapter;
    private SelectCardAdapter mSelectCardAdapter;

    private List<CardBalanceBean.ReturnDataBean> allDataList = new ArrayList<>();
    private List<UpdateCardBean.ReturnDataBean> updataCardList = new ArrayList<>();

    private static final int TAG_GET_SAVINGCARD = 2;
    private static final int TAG_GET_SAVINGCARD_NEW = 4;
    private static final int TAG_GET_MEMBER_INFO = 5;
    private static final int CAMERA = 1000;
    private static final String[] sPermissions = new String[]{
            Manifest.permission.CAMERA};
    private CardBean cardBean;

    @Override
    protected int getContentView() {
        return R.layout.activity_recharge;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshPage();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void initView() {
        loadingDialog = MyUtils.getLoadingDialog(mContext, false);
        setCardList();
        setWorkerList();
    }

    private void clearData() {
        mEtRechargePrice.setText("");
        mEtPayPrice.setText("");
        str_RrchargePrice = "";
        str_PayPrice = "";
        mSaleMoney = 0;
        str_Coefficient = 0;
        selectWorkList.clear();
        mSetWorkerAdapter.notifyDataSetChanged();
        mCbUpdate.setChecked(false);
        mLlUpdate.setEnabled(false);
        mCardType.setEnabled(false);
        mCardType.setTextColor(this.getResources().getColor(R.color.light_gray));
        mCardType.setText("请选择卡");
        str_Type = "RECHARGE_CARD";
        mEtRemark.setText("");
    }

    private void setData() {
        MemberInfoBean memberInfoBean = JSONObject.parseObject((String) SPUtils.get(mContext, StaticCode.SP_VIP_DATA, ""), MemberInfoBean
                .class);
        if (memberInfoBean == null) {
            return;
        } else {
            str_VipCode = memberInfoBean.getReturn_data().getCode();
            mUserName.setText(memberInfoBean.getReturn_data().getPerson_name());
            mUserNumber.setText(memberInfoBean.getReturn_data().getPhone());
            mSavingCardNum.setText(String.valueOf(memberInfoBean.getReturn_data().getRecharge_count()));
            mOnceCardNum.setText(String.valueOf(memberInfoBean.getReturn_data().getCount_count()));
            mYearCardNum.setText(String.valueOf(memberInfoBean.getReturn_data().getYear_count()));
            Glide.with(mContext).load(memberInfoBean.getReturn_data().getCover()).bitmapTransform(new CropCircleTransformation(mContext))
                    .into(mCashIcon);
        }
        WorkerInfoBean bean = JSONObject.parseObject((String) SPUtils.get(mContext, StaticCode.SP_USER_DATA, ""), WorkerInfoBean.class);
        if (bean == null) {
            return;
        } else {
            str_UserCode = bean.getReturn_data().getUser_code();
        }
        str_PayType = "ONLINE";
        mTvEpay.setChecked(true);
    }

    private void setCardList() {

        mSelectCardAdapter = new SelectCardAdapter(R.layout.item_card_list, allDataList);
        mRcContentCard.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        mRcContentCard.setAdapter(mSelectCardAdapter);
//设置adapter里item的点击事件
        mSelectCardAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                for (CardBalanceBean.ReturnDataBean data : allDataList) {
                    data.setSelected(false);
                }
//                mEtRechargePrice.setText("");
//                mEtPayPrice.setText("");
                mCardType.setEnabled(false);
                mCbUpdate.setChecked(false);
                mCardType.setTextColor(getResources().getColor(R.color.light_gray));
                mCardType.setText("请选择卡");
                allDataList.get(position).setSelected(true);
                mSelectCardAdapter.notifyDataSetChanged();
                mSaleMoney = allDataList.get(position).getSale_price() / 100;
                str_CardCode = allDataList.get(position).getCard_code();
                str_RrchargePrice = String.valueOf(allDataList.get(position).getSale_price());
                mEtRechargePrice.setText(String.valueOf(allDataList.get(position).getSale_price() / 100));
                mEtRechargePrice.setSelection(str_RrchargePrice.length() / 100);
                str_PayPrice = String.valueOf(allDataList.get(position).getSale_price());
                mEtPayPrice.setText(String.valueOf(allDataList.get(position).getSale_price() / 100));
                mEtPayPrice.setSelection(str_PayPrice.length() / 100);
                mBeforeMoney = allDataList.get(position).getBalance() / 100;
                str_Coefficient = allDataList.get(position).getCoefficient();
                mLlUpdate.setEnabled(true);
            }
        });

    }


    //选择的技师列表
    private void setWorkerList() {
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRcContent.setLayoutManager(mLinearLayoutManager);
        refreshSelectedWorker();

    }

    private void refreshSelectedWorker() {
        mSetWorkerAdapter = new SetWorkerAdapter(R.layout.item_set_worker, selectWorkList);
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


    @OnClick({R.id.ll_update, R.id.saving_card, R.id.once_card, R.id.year_card, R.id.tv_card, R.id.tv_cash, R.id.tv_epay, R.id.add, R.id
            .recharge_ok, R.id.card_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_update:
                selectUpdate();
                break;
            case R.id.saving_card:
                if (MyUtils.getRechargeInfo(mContext).length() > 0) {
                    MyUtils.showPopWindow(mContext, MyUtils.getRechargeInfo(mContext), mSavingCard);
                }
                break;
            case R.id.once_card:
                if (MyUtils.getCountInfo(mContext).length() > 0) {
                    MyUtils.showPopWindow(mContext, MyUtils.getCountInfo(mContext), mOnceCard);
                }
                break;
            case R.id.year_card:
                if (MyUtils.getYearInfo(mContext).length() > 0) {
                    MyUtils.showPopWindow(mContext, MyUtils.getYearInfo(mContext), mYearCard);
                }
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
            case R.id.card_type:
                showCardDialog();
                break;
            case R.id.add:
                DialogSelectWorkerFragment dialogSelectWorkerFragment = new DialogSelectWorkerFragment();
                Bundle b = new Bundle();
                b.putString("name", "recharge");
                dialogSelectWorkerFragment.setArguments(b);
                dialogSelectWorkerFragment.setSureSelectedCallBack(RechargeCardFragment.this);
                dialogSelectWorkerFragment.show(getFragmentManager(), "tag");
                break;
            case R.id.recharge_ok:
                if (checkFormat()) {
                    switch (str_PayType) {
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
                        case "CASH":
                            showPayDialog();
                            break;
                        default:
                            showScanning();
                            break;
                    }
                }
                break;
        }
    }

    @OnClick(R.id.exit_num)
    public void onViewClicked() {
        ExitDialogFragment dialogFragment = new ExitDialogFragment();
        dialogFragment.setCallBack(this);
        dialogFragment.show(getActivity().getFragmentManager(), "exit");
    }

    private void selectUpdate() {
        if (str_CardCode.equals("")) {
            mLlUpdate.setEnabled(false);
        } else {
            if (str_Coefficient == 1) {
                if (mCbUpdate.isChecked()) {
                    mCbUpdate.setChecked(false);
                    mCardType.setEnabled(false);
                    mCardType.setTextColor(this.getResources().getColor(R.color.light_gray));
                    mCardType.setText("请选择卡");
                    str_UpdateCode = "";
                    str_Type = "RECHARGE_CARD";
                } else {
                    mCbUpdate.setChecked(true);
                    mCardType.setEnabled(true);
                    mCardType.setTextColor(this.getResources().getColor(R.color.text_normal));
                    AppApi.getUpdateCardList(str_UserCode, str_CardCode, str_VipCode, RechargeCardFragment.this, TAG_GET_SAVINGCARD);
                    str_Type = "UPGRADE_CARD";
                }
            } else {
                ToastUtils.showToast(getContext(), "该卡不可以升级");
            }
        }
    }

    private void showScanning() {
        EasyPermission.with(RechargeCardFragment.this)
                .addRequestCode(CAMERA)
                .permissions(sPermissions)
                .rationale("没有授权!")
                .request();
    }

    private void setDataInfo(int type) {
        CreateCardBean.JsonStringBean createCardBean = new CreateCardBean.JsonStringBean();
        createCardBean.setCard_code(str_CardCode);
        createCardBean.setUser_code(str_UserCode);
        createCardBean.setBase_user_code(str_VipCode);
        createCardBean.setTrade_type(str_PayType);
        createCardBean.setShould_money(Integer.valueOf(mEtRechargePrice.getText().toString()) * 100 + "");
        createCardBean.setActual_money(Integer.valueOf(mEtPayPrice.getText().toString()) * 100 + "");
        createCardBean.setType(str_Type);
        createCardBean.setDevice_ip(DeviceUtils.getIp());
        createCardBean.setDevice_info(DeviceUtils.getDeviceInfo(getContext()));
        createCardBean.setRemark(mEtRemark.getText().toString().trim());
        Log.v("createCardBean", str_CardCode + str_UserCode + str_VipCode + str_PayType + (Integer.valueOf(mEtRechargePrice.getText()
                .toString()) * 100 + "") + (Integer.valueOf(mEtPayPrice.getText().toString()) * 100 + "") +
                str_Type);
        createCardBean.setKey(MD5.md5(str_CardCode + str_UserCode + str_VipCode + str_PayType + (Integer.valueOf(mEtRechargePrice.getText
                ().toString()) * 100 + "") + (Integer.valueOf(mEtPayPrice.getText().toString()) * 100 + "") +
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
                AppApi.createCard(createCardBean, RechargeCardFragment.this, Constants.Card_Code);
                break;
            case 1:
                AppApi.createCard(createCardBean, RechargeCardFragment.this, Constants.Cash_Code);
                break;
            case 2:
                AppApi.createCard(createCardBean, RechargeCardFragment.this, Constants.Scan_Code);
                break;
        }
    }

    private void showCardDialog() {
        if (mDialogCardFragment != null) {
            mDialogCardFragment = null;
        }
        ArrayList<String> cardList = new ArrayList<>();
        if (updataCardList != null && updataCardList.size() > 0) {
            for (UpdateCardBean.ReturnDataBean dataList : updataCardList) {
                cardList.add(dataList.getName());
            }
        }
        mDialogCardFragment = DialogCardFragment.newInstance(cardList);
        mDialogCardFragment.setOnCardSetListener(new DialogCardFragment.OnCardSetListener() {

            @Override
            public void onCardSet(String card) {
                //TODO 点击确定按钮返回文字//修改点击返回index
                int index = Integer.parseInt(card);
                mDialogCardFragment.setPosition(index);
                mCardType.setText(updataCardList.get(index).getName());
                //TODO CODE 这边根据index取asBeanList里面的数据
                str_UpdateCode = String.valueOf(updataCardList.get(index).getCode());
            }
        });
        mDialogCardFragment.show(getChildFragmentManager(), DialogCardFragment.TAG);
    }

    //现金支付
    private void showPayDialog() {
        str_RrchargePrice = Integer.valueOf(mEtRechargePrice.getText().toString()) * 100 + "";
        mRechargeMoney = Integer.valueOf(mEtRechargePrice.getText().toString());
        str_PayPrice = Integer.valueOf(mEtPayPrice.getText().toString()) * 100 + "";
        mTotle = mBeforeMoney + mRechargeMoney;
        mDialogPayFragment = DialogPayFragment.newInstance(str_PayType, mEtRechargePrice.getText().toString(), mTotle + "");
        mDialogPayFragment.setOnSureSetListener(new DialogPayFragment.OnSureSetListener() {
            @Override
            public void onSureSet(String sure) {
                //TODO 点击确定按钮返回文字//修改点击返回index
                setDataInfo(1);
            }
        });
        mDialogPayFragment.show(getFragmentManager(), DialogPayFragment.TAG);
    }

    public void getCardDataList() {
        if (!SPUtils.contains(mContext, SP_VIP_DATA)) {
            return;
        } else {
            AppApi.getCardBalance(MyUtils.getShopCode(mContext), str_VipCode, RechargeCardFragment.this, StaticCode.TAG);
        }
    }


    @Override
    public void handlerSuccessData(String result, int tag) {
        switch (tag) {
            case StaticCode.TAG:
                CardBalanceBean cardBalanceBean = JSONObject.parseObject(result, CardBalanceBean.class);
                List<CardBalanceBean.ReturnDataBean> dataBeanList = cardBalanceBean.getReturn_data();
                if (dataBeanList != null) {
                    mLlNoCard.setVisibility(View.GONE);
                    mLlCardInfo.setVisibility(View.VISIBLE);
                } else {
                    mLlNoCard.setVisibility(View.VISIBLE);
                    mLlCardInfo.setVisibility(View.GONE);
                }
                allDataList.addAll(dataBeanList);
                if (mSelectCardAdapter != null) {
                    mSelectCardAdapter.notifyDataSetChanged();
                }
                break;
            case Constants.Cash_Code:
                allDataList.clear();
                selectWorkList.clear();
                mSetWorkerAdapter.notifyDataSetChanged();
                JSONObject jsonObject = JSON.parseObject(result);
                order_code = jsonObject.getString("return_order_code");
                AppApi.getCardBalance(MyUtils.getShopCode(mContext), str_VipCode, RechargeCardFragment.this, TAG_GET_SAVINGCARD_NEW);
                break;
            case TAG_GET_SAVINGCARD:
                updataCardList.clear();
                UpdateCardBean updateCardBean = JSONObject.parseObject(result, UpdateCardBean.class);
                List<UpdateCardBean.ReturnDataBean> updateDataBeanList = updateCardBean.getReturn_data();
                updataCardList.addAll(updateDataBeanList);
                break;
            case TAG_GET_SAVINGCARD_NEW:
                CardBalanceBean cardBalanceBean1 = JSONObject.parseObject(result, CardBalanceBean.class);
                List<CardBalanceBean.ReturnDataBean> dataBeanList1 = cardBalanceBean1.getReturn_data();
                allDataList.addAll(dataBeanList1);
                mEtRechargePrice.setText("");
                mEtPayPrice.setText("");
                selectWorkList.clear();
                mSetWorkerAdapter.notifyDataSetChanged();
                mSelectCardAdapter.notifyDataSetChanged();
                AppApi.getMemberInfo(str_VipCode, MyUtils.getShopCode(mContext), RechargeCardFragment.this, TAG_GET_MEMBER_INFO);
                break;
            case TAG_GET_MEMBER_INFO:
                loadingDialog.cancelDialog();
                refreshPage();
                SPUtils.put(mContext, SP_VIP_DATA, result);
                Intent intent = new Intent(getContext(), RechargeSuccessActivity.class);
                Log.v("order_code", order_code);
                intent.putExtra("order_code", order_code);
                startActivity(intent);
                break;
            case Constants.Scan_Code:
                Log.v("handlerSuccessData", result);
                allDataList.clear();
                selectWorkList.clear();
                mSetWorkerAdapter.notifyDataSetChanged();
                JSONObject jsonObject1 = JSON.parseObject(result);
                order_code = jsonObject1.getString("return_order_code");
                Log.v("order_code", order_code);
                AppApi.getCardBalance(MyUtils.getShopCode(mContext), str_VipCode, RechargeCardFragment.this, TAG_GET_SAVINGCARD_NEW);
                break;
            case Constants.Card_Code:
                Log.v("handlerSuccessData", result);
                allDataList.clear();
                selectWorkList.clear();
                mSetWorkerAdapter.notifyDataSetChanged();
                JSONObject jsonObject2 = JSON.parseObject(result);
                order_code = jsonObject2.getString("return_order_code");
                Log.v("order_code", order_code);
                AppApi.getCardBalance(MyUtils.getShopCode(mContext), str_VipCode, RechargeCardFragment.this, TAG_GET_SAVINGCARD_NEW);
                break;
            case Constants.Again_Code:
                Log.v("handlerSuccessData", result);
                allDataList.clear();
                selectWorkList.clear();
                mSetWorkerAdapter.notifyDataSetChanged();
                AppApi.getCardBalance(MyUtils.getShopCode(mContext), str_VipCode, RechargeCardFragment.this, TAG_GET_SAVINGCARD_NEW);
                break;
        }


    }

    @Override
    public void handlerFailData(String failString, int tag) {
        switch (tag) {
            case TAG_GET_SAVINGCARD:
                updataCardList.clear();
                break;
            case Constants.Cash_Code:
                Intent failIntent = new Intent(getContext(), RechargeFailActivity.class);
                startActivity(failIntent);
                loadingDialog.cancelDialog();
                break;
            case Constants.Scan_Code:
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

                            AppApi.queryOrderAgain(order_code, MyUtils.getCode(mContext), RechargeCardFragment.this, Constants.Again_Code);
                        }
                    }, 1500);

                } else {
                    loadingDialog.cancelDialog();
                    Toast.makeText(mContext, info, Toast.LENGTH_SHORT).show();
                    Intent failIntent1 = new Intent(getContext(), RechargeFailActivity.class);
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
                                AppApi.queryOrderAgain(order_code, MyUtils.getCode(mContext), RechargeCardFragment.this, Constants
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        EasyPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionGranted(int requestCode, List<String> perms) {
        if (requestCode == CAMERA) {
            Intent intent = new Intent();
            intent.setClass(mContext, CaptureActivity.class);
            intent.putExtra("tag", "rechargecard");
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public void onPermissionDenied(int requestCode, List<String> perms) {
        Toast.makeText(mContext, "未授权", Toast.LENGTH_SHORT)
                .show();
        EasyPermission.checkDeniedPermissionsNeverAskAgain(this, "该设备没有授权," + "请去设置里授权!", perms);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_OK:
                switch (requestCode) {
                    case 0:  //扫码回调
                        str_PayCode = data.getStringExtra("result");
                        setDataInfo(2);
                        break;
                    case 100:  //银行卡回调
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
        }
    }

    @Override
    public void exit() {
        SPUtils.remove(mContext, SP_VIP_DATA);
        MainActivity mainActivity = (MainActivity) getActivity();
        CashFragment cashFragment = new CashFragment();
        CashFragment fragment = (CashFragment) mainActivity.getSupportFragmentManager().findFragmentByTag
                (cashFragment.getClass().getSimpleName());
        fragment.exit();
        EventBus.getDefault().post(new FirstEvent("Goto_Recharge"));
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
        if (str_CardCode.equals("") || str_CardCode.equals("0")) {
            ToastUtils.showToast(getContext(), "请至少选择一张储值卡");
            return false;
        } else if (mEtRechargePrice.getText().toString().equals("") || Integer.parseInt(mEtRechargePrice.getText().toString()) <= 0) {
            ToastUtils.showToast(getContext(), "请填写充值金额");
            return false;
        } else if (mEtPayPrice.getText().toString().equals("") || Integer.parseInt(mEtPayPrice.getText().toString()) <= 0) {
            ToastUtils.showToast(getContext(), "请填写实付金额");
            return false;
        } else if (mSetWorkerAdapter.getData().size() <= 0) {
            ToastUtils.showToast(getContext(), "请至少选择一个技师");
            return false;
        } else if (mCardType.getText().toString().equals("请选择卡") && mCbUpdate.isChecked()) {
            ToastUtils.showToast(getContext(), "请选择要升级到的卡");
            return false;
        } else if (str_Coefficient != 1 && Integer.valueOf(mEtRechargePrice.getText().toString()) < mSaleMoney) {
            ToastUtils.showToast(getContext(), "该卡充值金额需大于或等于" + mSaleMoney + "元");
            return false;
        }

        if (calculate() / 100 != Integer.parseInt(mEtPayPrice.getText().toString())) {
            ToastUtils.showToast(mContext, "技师总提成金额不等于支付金额!");
            return false;
        }
        return true;
    }

    public void refreshPage() {
        allDataList.clear();
        if (mSelectCardAdapter != null) {
            mSelectCardAdapter.notifyDataSetChanged();
        }
        setData();
        getCardDataList();
        clearData();
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
