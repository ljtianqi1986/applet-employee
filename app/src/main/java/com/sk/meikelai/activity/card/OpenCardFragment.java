package com.sk.meikelai.activity.card;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;
import com.sk.meikelai.activity.cash.CaptureActivity;
import com.sk.meikelai.common.Constants;
import com.sk.meikelai.dialog.DialogCardFragment;
import com.sk.meikelai.dialog.DialogComeWayFragment;
import com.sk.meikelai.dialog.PhoneVertifyDialogFragment;
import com.sk.meikelai.entity.ComeWayBean;
import com.sk.meikelai.entity.QrCode;
import com.sk.meikelai.permission.EasyPermission;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MobileUtils;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.StaticCode;
import com.sk.meikelai.utils.ToastUtils;
import com.sk.meikelai.view.VerificationCodeButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 开卡页面
 */

public class OpenCardFragment extends BaseFragment implements HandleDataCallBack, EasyPermission.PermissionCallback {


    @BindView(R.id.ll_scan)
    LinearLayout mLlScan;
    @BindView(R.id.user_name)
    EditText mUserName;
    @BindView(R.id.tv_way_more)
    TextView mTvWayMore;
    @BindView(R.id.way_more)
    RelativeLayout mWayMore;
    @BindView(R.id.phone)
    EditText mPhone;
    @BindView(R.id.tv_verification_register)
    TextView mTvVerificationRegister;
    @BindView(R.id.btn_verification)
    VerificationCodeButton mBtnVerification;
    @BindView(R.id.et_verification_register)
    EditText mEtVerificationRegister;
    @BindView(R.id.new_ewm)
    Button mNewEwm;
    String mComeWay = "";
    Unbinder unbinder;
    private DialogComeWayFragment mDialogComeWayFragment;

    private static final int TAG_GET_VERIFICATION_CODE = 0;
    private static final int TAG_CREATE_QR_CODE = 1;
    private static final int TAG_GET_COME_WAY = 2;
    private static final int CAMERA = 1000;
    private static final String[] sPermissions = new String[]{
            Manifest.permission.CAMERA};
    private List<ComeWayBean.ReturnDataBean> comeWayList = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.fragment_open_card;
    }

    @Override
    public void initView() {
        AppApi.getComeWay(MyUtils.getShopCode(getActivity()), OpenCardFragment.this, TAG_GET_COME_WAY);
    }

    @OnClick({R.id.ll_scan,R.id.phone_vertify,  R.id.way_more, R.id.btn_verification, R.id.new_ewm})
    public void onViewClicked(View view) {
        String phoneNumber = mPhone.getText().toString().trim();
        switch (view.getId()) {
            case R.id.ll_scan:
                EasyPermission.with(OpenCardFragment.this)
                        .addRequestCode(CAMERA)
                        .permissions(sPermissions)
                        .rationale("没有授权!")
                        .request();
                break;
            case R.id.phone_vertify:
                PhoneVertifyDialogFragment phoneVertifyDialogFragment = new PhoneVertifyDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("tag", Constants.HOME_OPEN);
                phoneVertifyDialogFragment.setArguments(bundle);
                phoneVertifyDialogFragment.show(getActivity().getFragmentManager(), "phone");
                break;
            case R.id.tv_way_more:
                showComeWayDialog();
                break;
            case R.id.way_more:
                showComeWayDialog();
                break;
            case R.id.btn_verification:
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String date = sdf.format(new Date());
                if (!MobileUtils.isPhoneNumberEmpty(getContext(), phoneNumber, getString(R.string.phone_number_null))
                        && MobileUtils.isMobileNumber(getContext(), phoneNumber, getString(R.string.phone_number_null))) {
                    AppApi.getVerificationCode(phoneNumber, date, OpenCardFragment.this, TAG_GET_VERIFICATION_CODE);
                }
                break;
            case R.id.new_ewm:
                String vCode = mEtVerificationRegister.getText().toString().trim();
                String mNane = mUserName.getText().toString().trim();
                String mWorkerNane = MyUtils.getCode(mContext);
                String mShopCode = MyUtils.getShopCode(mContext);
                if (TextUtils.isEmpty(mNane)) {
                    ToastUtils.showToast(getActivity(), getString(R.string.card_hint_no_name));
                } else if (TextUtils.isEmpty(phoneNumber)) {
                    ToastUtils.showToast(getActivity(), getString(R.string.card_hint_phone));
                } else if (TextUtils.isEmpty(vCode)) {
                    ToastUtils.showToast(getActivity(), getString(R.string.card_hint_verification));
                } else {
                    AppApi.getQdCode(mNane, phoneNumber, mComeWay, mWorkerNane, mShopCode, vCode, OpenCardFragment.this,
                            TAG_CREATE_QR_CODE);
                }
                break;
        }

    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        switch (tag) {
            case TAG_GET_VERIFICATION_CODE:
                mBtnVerification.startTimer();
                ToastUtils.showToast(getActivity(), getString(R.string.verification_code_sended));
                mEtVerificationRegister.setText("");
                break;
            case TAG_CREATE_QR_CODE:
                QrCode qrCode = JSONObject.parseObject(result, QrCode.class);
                String url = qrCode.getReturn_data();
                Intent intent = new Intent(mContext, OpenCardQRCode.class);
                intent.putExtra("url", url);
                startActivity(intent);
                break;
            case TAG_GET_COME_WAY:
                ComeWayBean comeWayBean = JSONObject.parseObject(result, ComeWayBean.class);
                List<ComeWayBean.ReturnDataBean> dataBeanList = comeWayBean.getReturn_data();
                comeWayList.addAll(dataBeanList);
        }
    }

    private void showComeWayDialog() {
        if (mDialogComeWayFragment == null) {
            List<String> cardList = new ArrayList<>();
            if (comeWayList != null && comeWayList.size() > 0) {
                for (ComeWayBean.ReturnDataBean dataList : comeWayList) {
                    cardList.add(dataList.getName());
                }
            }
            mDialogComeWayFragment = DialogComeWayFragment.newInstance(cardList);
            mDialogComeWayFragment.setOnCardSetListener(new DialogComeWayFragment.OnCardSetListener() {

                @Override
                public void onCardSet(String card) {
                    //TODO 点击确定按钮返回文字//修改点击返回index
                    int index = Integer.parseInt(card);
                    if (index > comeWayList.size()) {
                        //越界提示错误
                    }
                    mDialogComeWayFragment.setPosition(index);
                    mTvWayMore.setText(comeWayList.get(index).getName());
                    //TODO CODE 这边根据index取asBeanList里面的数据
                    mComeWay = String.valueOf(comeWayList.get(index).getCode());

                }
            });
        }
        mDialogComeWayFragment.show(getChildFragmentManager(), DialogCardFragment.TAG);
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
            intent.putExtra("tag", "opencard");
            getActivity().startActivityForResult(intent, StaticCode.TAG_OPENCARD_LOGIN);
        }
    }

    @Override
    public void onPermissionDenied(int requestCode, List<String> perms) {
        Toast.makeText(mContext, "未授权", Toast.LENGTH_SHORT)
                .show();
        EasyPermission.checkDeniedPermissionsNeverAskAgain(this, "该设备没有授权," + "请去设置里授权!", perms);
    }
}