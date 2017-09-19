package com.sk.meikelai.activity.cash;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.sk.meikelai.Adapter.CashSelectCardAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;
import com.sk.meikelai.entity.MemberInfoBean;
import com.sk.meikelai.utils.CropCircleTransformation;
import com.sk.meikelai.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * Created by sk on 2017/6/5.
 */

public class CashCardFragment extends BaseFragment {

    @BindView(R.id.cash_icon)
    ImageView mIcon;
    @BindView(R.id.cash_add)
    Button mCashAdd;
    Unbinder unbinder;
    @BindView(R.id.member_scan)
    LinearLayout mMemberScan;
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.user_number)
    TextView mUserNumber;
    @BindView(R.id.saving_card)
    LinearLayout mSavingCard;
    @BindView(R.id.once_card)
    LinearLayout mOnceCard;
    @BindView(R.id.year_card)
    LinearLayout mYearCard;
    @BindView(R.id.center_title)
    LinearLayout mCenterTitle;
    @BindView(R.id.pay)
    ImageView mPay;
    @BindView(R.id.rc_content)
    RecyclerView mRcContent;
    @BindView(R.id.ll_add)
    LinearLayout mLlAdd;
    @BindView(R.id.ll_add_more)
    LinearLayout mLlAddMore;
    @BindView(R.id.ll_add_first)
    LinearLayout mLlAddFirst;
    LinearLayoutManager linearLayoutManager;
    CashSelectCardAdapter mAdapter;
    @BindView(R.id.saving_card_num)
    TextView mSavingCardNum;
    @BindView(R.id.once_card_num)
    TextView mOnceCardNum;
    @BindView(R.id.year_card_num)
    TextView mYearCardNum;

    public static final String SP_VIP_DATA = "sp_vip_data";
    private static final int CAMERA = 1000;
    private static final String[] sPermissions = new String[]{
            Manifest.permission.CAMERA};


    @Override
    protected int getContentView() {
        return R.layout.fragment_cash_card;
    }

    @Override
    public void initView() {
        MemberInfoBean memberInfoBean = JSONObject.parseObject((String) SPUtils.get(mContext, SP_VIP_DATA, ""), MemberInfoBean.class);
        if (memberInfoBean == null) {
            return;
        } else {
            mUserName.setText(memberInfoBean.getReturn_data().getPerson_name());
            mUserNumber.setText(memberInfoBean.getReturn_data().getPhone());
            mSavingCardNum.setText(String.valueOf(memberInfoBean.getReturn_data().getRecharge_count()));
            mOnceCardNum.setText(String.valueOf(memberInfoBean.getReturn_data().getCount_count()));
            mYearCardNum.setText(String.valueOf(memberInfoBean.getReturn_data().getYear_count()));
            Glide.with(mContext).load(memberInfoBean.getReturn_data().getCover()).bitmapTransform(new CropCircleTransformation(mContext)).into(mIcon);
        }
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//设置布局管理器
        mRcContent.setLayoutManager(linearLayoutManager);
//设置adapter
        mRcContent.setAdapter(mAdapter = new CashSelectCardAdapter());
    }


    @OnClick({R.id.member_scan, R.id.saving_card, R.id.once_card, R.id.year_card, R.id.pay, R.id.ll_add_more, R.id.cash_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.member_scan:

                break;
            case R.id.saving_card:
                break;
            case R.id.once_card:
                break;
            case R.id.year_card:
                break;
            case R.id.pay:
                break;
            case R.id.ll_add_more:
                Intent addMoreIntent = new Intent(getContext(), CashProductActivity.class);
                startActivity(addMoreIntent);
                break;
            case R.id.cash_add:
                Intent addIntent = new Intent(getContext(), CashProductActivity.class);
                startActivityForResult(addIntent, 0);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
//                Bundle b = data.getExtras(); //data为B中回传的Intent
//                String str = b.getString("str1");//str即为回传的值
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
