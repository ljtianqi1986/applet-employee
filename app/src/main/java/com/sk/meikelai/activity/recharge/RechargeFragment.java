package com.sk.meikelai.activity.recharge;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;
import com.sk.meikelai.activity.cash.CaptureActivity;
import com.sk.meikelai.common.Constants;
import com.sk.meikelai.dialog.PhoneVertifyDialogFragment;
import com.sk.meikelai.permission.EasyPermission;
import com.sk.meikelai.utils.StaticCode;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sk on 2017/6/5.
 */

public class RechargeFragment extends BaseFragment implements EasyPermission.PermissionCallback {


    private static final int CAMERA = 1000;
    private static final String[] sPermissions = new String[]{
            Manifest.permission.CAMERA};
    Unbinder unbinder;

    @Override
    protected int getContentView() {
        return R.layout.fragment_recharge_scan;
    }

    @Override
    public void initView() {

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
            intent.putExtra("tag", "recharge");
            getActivity().startActivityForResult(intent, StaticCode.TAG_RECHARGE_LOGIN);
        }
    }

    @Override
    public void onPermissionDenied(int requestCode, List<String> perms) {
        Toast.makeText(mContext, "未授权", Toast.LENGTH_SHORT)
                .show();
        EasyPermission.checkDeniedPermissionsNeverAskAgain(this, "该设备没有授权," + "请去设置里授权!", perms);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_scan, R.id.phone_vertify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_scan:
                EasyPermission.with(RechargeFragment.this)
                        .addRequestCode(CAMERA)
                        .permissions(sPermissions)
                        .rationale("没有授权!")
                        .request();
                break;
            case R.id.phone_vertify:
                PhoneVertifyDialogFragment phoneVertifyDialogFragment = new PhoneVertifyDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("tag", Constants.HOME_RECHARGE);
                phoneVertifyDialogFragment.setArguments(bundle);
                phoneVertifyDialogFragment.show(getActivity().getFragmentManager(), "phone");
                break;
        }
    }
}
