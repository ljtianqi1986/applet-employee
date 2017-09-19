package com.sk.meikelai.activity.cash;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.sk.meikelai.Adapter.SelectedAllProjiectAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;
import com.sk.meikelai.callback.ExitCallBack;
import com.sk.meikelai.common.Constants;
import com.sk.meikelai.dialog.ExitDialogFragment;
import com.sk.meikelai.dialog.PhoneVertifyDialogFragment;
import com.sk.meikelai.entity.CashCardBean;
import com.sk.meikelai.entity.MemberInfoBean;
import com.sk.meikelai.entity.ProductListBean;
import com.sk.meikelai.entity.ProjectListBean;
import com.sk.meikelai.permission.EasyPermission;
import com.sk.meikelai.utils.CropCircleTransformation;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.utils.StaticCode;
import com.sk.meikelai.view.EditTextWithNumer;
import com.sk.meikelai.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.sk.meikelai.utils.StaticCode.SP_VIP_DATA;

/**
 * Created by sk on 2017/6/5.
 */

public class CashFragment extends BaseFragment implements EasyPermission.PermissionCallback, ExitCallBack {

    @BindView(R.id.cash_icon)
    ImageView mIcon;
    @BindView(R.id.cash_add)
    Button mCashAdd;
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
    RelativeLayout mCenterTitle;
    @BindView(R.id.pay)
    ImageView mPay;
    @BindView(R.id.rc_content)
    SwipeMenuRecyclerView mRcContent;
    @BindView(R.id.ll_add)
    LinearLayout mLlAdd;
    @BindView(R.id.ll_add_more)
    LinearLayout mLlAddMore;


    private static final int CAMERA = 1000;
    private static final String[] sPermissions = new String[]{
            Manifest.permission.CAMERA};
    @BindView(R.id.saving_card_num)
    TextView savingCardNum;
    @BindView(R.id.once_card_num)
    TextView onceCardNum;
    @BindView(R.id.year_card_num)
    TextView yearCardNum;
    @BindView(R.id.exit_num)
    TextView exitNum;
    Unbinder unbinder;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.phone_vertify)
    LinearLayout phoneVertify;
    @BindView(R.id.tv_cz)
    TextView tvCz;
    Unbinder unbinder1;

    private ArrayList<CashCardBean.ReturnDataBean> onceList = new ArrayList<>();
    private ArrayList<ProjectListBean.ReturnDataBean> serviceList = new ArrayList<>();
    private ArrayList<ProductListBean.ReturnDataBean> productList = new ArrayList<>();
    private SelectedAllProjiectAdapter adapter = null;
    private LinearLayoutManager linearLayoutManager = null;
    private String czInfo, ckInfo, yearInfo;

    @Override
    protected int getContentView() {
        return R.layout.fragment_cash_center;
    }


    @Override
    public void initView() {

        adapter = new SelectedAllProjiectAdapter(mContext, onceList, serviceList, productList);
        linearLayoutManager = new LinearLayoutManager(mContext);
        mRcContent.setLayoutManager(linearLayoutManager);
        mRcContent.addItemDecoration(new ListViewDecoration());// 添加分割线。
        mRcContent.setAdapter(adapter);
        // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
        // 设置菜单创建器。
        mRcContent.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        mRcContent.setSwipeMenuItemClickListener(menuItemClickListener);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView
         *                        #RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。
            View view = linearLayoutManager.findViewByPosition(adapterPosition);
            EditText editText = (EditText) view.findViewById(R.id.ed_price);
            editText.setFocusable(false);
            // TODO 如果是删除：推荐调用Adapter.notifyItemRemoved(position)，不推荐Adapter.notifyDataSetChanged();
            if (menuPosition == 0) {// 删除按钮被点击。
                if (adapterPosition >= 0 && adapterPosition < onceList.size()) {
                    onceList.remove(adapterPosition);
                } else if (adapterPosition >= onceList.size() && adapterPosition < (serviceList.size() + onceList.size())) {
                    serviceList.remove(adapterPosition - onceList.size());
                } else if (adapterPosition >= (serviceList.size() + onceList.size()) && adapterPosition < (productList.size() + onceList.size() + serviceList.size())) {
                    productList.remove(adapterPosition - onceList.size() - serviceList.size());
                }
                adapter = new SelectedAllProjiectAdapter(mContext, onceList, serviceList, productList);
                mRcContent.setAdapter(adapter);
                if (adapter.getItemCount() > 0) {
                    mPay.setVisibility(View.VISIBLE);
                    mLlAddMore.setVisibility(View.VISIBLE);
                    mCashAdd.setVisibility(View.INVISIBLE);
                } else {
                    mPay.setVisibility(View.INVISIBLE);
                    mLlAddMore.setVisibility(View.INVISIBLE);
                    mCashAdd.setVisibility(View.VISIBLE);
                }
            }

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        refreshPage();
    }

    @OnClick({R.id.member_scan, R.id.phone_vertify, R.id.saving_card, R.id.once_card, R.id.year_card, R.id.pay, R.id.cash_add, R.id.ll_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.member_scan:
                EasyPermission.with(CashFragment.this)
                        .addRequestCode(CAMERA)
                        .permissions(sPermissions)
                        .rationale("没有授权!")
                        .request();

                break;
            case R.id.phone_vertify:
                PhoneVertifyDialogFragment phoneVertifyDialogFragment = new PhoneVertifyDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("tag", Constants.HOME_CASH);
                phoneVertifyDialogFragment.setArguments(bundle);
                phoneVertifyDialogFragment.show(getActivity().getFragmentManager(), "phone");
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
            case R.id.ll_add:

                Intent addIntentLL = new Intent(mContext, CashProductActivity.class);
                addIntentLL.putParcelableArrayListExtra("once",onceList);
                addIntentLL.putParcelableArrayListExtra("service",serviceList);
                addIntentLL.putParcelableArrayListExtra("product",productList);
                startActivityForResult(addIntentLL, 1);

                break;
            case R.id.pay:
                if (isFormat()) {
                    Intent payIntent = new Intent(mContext, CashOrderPayActivity.class);
                    payIntent.putExtra("once", adapter.getOnceList());
                    payIntent.putExtra("service", adapter.getProjectList());
                    payIntent.putExtra("product", adapter.getProductList());
                    startActivityForResult(payIntent, 2);
                }

                break;
            case R.id.cash_add:
                Intent addIntent = new Intent(mContext, CashProductActivity.class);
                startActivityForResult(addIntent, 1);

                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case -1:
                if (requestCode == 1) {
                    onceList = data.getParcelableArrayListExtra("once");
                    serviceList = data.getParcelableArrayListExtra("service");
                    productList = data.getParcelableArrayListExtra("product");
                    if(onceList==null){
                        onceList = new ArrayList<>();
                    }
                    refreshDataList();
                } else if (requestCode == 2) {
                    onceList.clear();
                    serviceList.clear();
                    productList.clear();
                    refreshDataList();
                }
                break;
            default:
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
            intent.putExtra("tag", "cash");
            startActivityForResult(intent, StaticCode.TAG_CASH_LOGIN);
        }
    }

    @Override
    public void onPermissionDenied(int requestCode, List<String> perms) {
        Toast.makeText(mContext, "未授权", Toast.LENGTH_SHORT)
                .show();
        EasyPermission.checkDeniedPermissionsNeverAskAgain(this, "该设备没有授权," + "请去设置里授权!", perms);
    }

    private void refreshDataList() {
        adapter = new SelectedAllProjiectAdapter(mContext, onceList, serviceList, productList);
        mRcContent.setAdapter(adapter);
        if (adapter.getItemCount() > 0) {
            mPay.setVisibility(View.VISIBLE);
            mLlAddMore.setVisibility(View.VISIBLE);
            mCashAdd.setVisibility(View.INVISIBLE);
        } else {
            mPay.setVisibility(View.INVISIBLE);
            mLlAddMore.setVisibility(View.INVISIBLE);
            mCashAdd.setVisibility(View.VISIBLE);
        }
    }


    @OnClick(R.id.exit_num)
    public void onViewClicked() {
        ExitDialogFragment dialogFragment = new ExitDialogFragment();
        dialogFragment.setCallBack(this);
        dialogFragment.show(getActivity().getFragmentManager(), "exit");
    }

    private void exitLogin() {
        onceList.clear();
        serviceList.clear();
        productList.clear();
        refreshDataList();
        mCenterTitle.setVisibility(View.GONE);
        mMemberScan.setVisibility(View.VISIBLE);
        phoneVertify.setVisibility(View.VISIBLE);
        mCashAdd.setVisibility(View.VISIBLE);
        mLlAddMore.setVisibility(View.GONE);
        mPay.setVisibility(View.GONE);
        SPUtils.remove(mContext, SP_VIP_DATA);
    }

    @Override
    public void exit() {
        exitLogin();
    }

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                    .setBackgroundDrawable(R.drawable.text_bg)
                    .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                    .setTextColor(Color.WHITE)
                    .setWeight(1)
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
        }
    };

    private boolean isFormat() {
        boolean isFormat = true;
        for (int i = 0; i < linearLayoutManager.getItemCount(); i++) {
            View view = linearLayoutManager.findViewByPosition(i);
            EditTextWithNumer editText = (EditTextWithNumer) view.findViewById(R.id.ed_price);
            if (editText.getVisibility() == View.VISIBLE) {
                if (TextUtils.isEmpty(editText.getText().toString())) {
                    Toast.makeText(mContext, "第" + (i + 1) + "项尚未填写实收金额!", Toast.LENGTH_SHORT).show();
                    isFormat = false;
                    break;
                } else if (Double.parseDouble(editText.getText().toString()) == 0) {
                    Toast.makeText(mContext, "第" + (i + 1) + "实收金额不能为0!", Toast.LENGTH_SHORT).show();
                    isFormat = false;
                    break;
                }
            }
            Spinner spinner = (Spinner) view.findViewById(R.id.spinner_people);
            if (spinner.getSelectedItemPosition() == 0) {
                Toast.makeText(mContext, "第" + (i + 1) + "项未选择服务技师!", Toast.LENGTH_SHORT).show();
                isFormat = false;
                break;
            }
        }
        return isFormat;

    }

    public void refreshPage() {
        if (SPUtils.contains(mContext, SP_VIP_DATA)) {
            mCenterTitle.setVisibility(View.VISIBLE);
            mMemberScan.setVisibility(View.GONE);
            phoneVertify.setVisibility(View.GONE);

            MemberInfoBean memberInfoBean = JSONObject.parseObject((String) SPUtils.get(mContext, SP_VIP_DATA, ""), MemberInfoBean.class);
            if (memberInfoBean != null) {
                mUserName.setText(memberInfoBean.getReturn_data().getPerson_name());
                mUserNumber.setText(memberInfoBean.getReturn_data().getPhone());
                savingCardNum.setText(String.valueOf(memberInfoBean.getReturn_data().getRecharge_count()));
                onceCardNum.setText(String.valueOf(memberInfoBean.getReturn_data().getCount_count()));
                yearCardNum.setText(String.valueOf(memberInfoBean.getReturn_data().getYear_count()));
                Glide.with(mContext).load(memberInfoBean.getReturn_data().getCover()).bitmapTransform(new CropCircleTransformation(mContext)).into(mIcon);
            }
        } else {
            mCenterTitle.setVisibility(View.GONE);
            mMemberScan.setVisibility(View.VISIBLE);
            phoneVertify.setVisibility(View.VISIBLE);
            mCashAdd.setVisibility(View.INVISIBLE);
            mLlAddMore.setVisibility(View.INVISIBLE);
        }

        if (adapter.getItemCount() > 0) {
            mPay.setVisibility(View.VISIBLE);
            mLlAddMore.setVisibility(View.VISIBLE);
            mCashAdd.setVisibility(View.INVISIBLE);
        } else {
            mPay.setVisibility(View.INVISIBLE);
            mLlAddMore.setVisibility(View.INVISIBLE);
            mCashAdd.setVisibility(View.VISIBLE);
        }
    }
}
