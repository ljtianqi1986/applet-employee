package com.sk.meikelai.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.dialog.DialogFramentUploadPhoto;
import com.sk.meikelai.dialog.SignOutDialogFragment;
import com.sk.meikelai.permission.EasyPermission;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.CropCircleTransformation;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.HttpUtils;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.utils.StaticCode;
import com.sk.meikelai.xutils.HttpUtilsFacade;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sk on 2017/7/3.
 */

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class MySettingsActivity extends BaseActivity implements EasyPermission.PermissionCallback, HandleDataCallBack {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.ll_change_num)
    LinearLayout llChangeNum;
    @BindView(R.id.ll_change_password)
    LinearLayout llChangePassword;
    @BindView(R.id.sign_out)
    Button signOut;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.ll_change_head)
    LinearLayout llChangeHead;

    private String headUrl;
    private static String path = "/sdcard/meikelai/";//sd路径


    private static int START_CAMERA = 4;
    private static int CHOOSE_PHOTO = 5;
    private static int CROP_CODE = 6;

    private static final int Request_Code = 1024;


    private static final String[] sPermissions = new String[]{
            Manifest.permission.CAMERA,//
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };


    @Override
    protected int getContentView() {
        return R.layout.activity_my_settings;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headUrl = getIntent().getStringExtra("headUrl");
        if (headUrl != null && headUrl.length() > 0) {
            Glide.with(mContext).load(headUrl).bitmapTransform(new CropCircleTransformation(mContext)).into(head);
        }
    }

    @OnClick({R.id.back, R.id.ll_change_num, R.id.ll_change_password, R.id.sign_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ll_change_num:
                Intent intentNum = new Intent(mContext, ChangeNumberActivity.class);
                startActivity(intentNum);
                break;
            case R.id.ll_change_password:
                Intent intent = new Intent(mContext, ChangePasswordAct.class);
                startActivity(intent);
                break;
            case R.id.sign_out:
                SignOutDialogFragment signOutDialogFragment = new SignOutDialogFragment();
                signOutDialogFragment.show(getFragmentManager(), "signout");
                break;
        }
    }

    public void exitLogin() {
        Intent intent = new Intent();
        intent.putExtra("code", 0);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void exitSystem() {
        Intent intent = new Intent();
        intent.putExtra("code", 1);
        setResult(RESULT_OK, intent);
        finish();
    }

    @OnClick(R.id.ll_change_head)
    public void onViewClicked() {
        EasyPermission.with(MySettingsActivity.this)
                .addRequestCode(Request_Code)
                .permissions(sPermissions)
                .rationale("没有授权!")
                .request();
    }

    public void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                "head.jpg")));
        startActivityForResult(intent, START_CAMERA);//采用ForResult打开
    }

    public void ChoosePhoto() {
        Intent intent1 = new Intent(Intent.ACTION_PICK, null);
        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent1, CHOOSE_PHOTO);
    }

    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_CODE);
    }

    private File setPicToView(Bitmap mBitmap) throws IOException {
        //创建目录
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        //创建文件
        file = new File(path + "head.jpg");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    @Override
    public void onPermissionGranted(int requestCode, List<String> perms) {
        if (requestCode == Request_Code) {
            DialogFramentUploadPhoto dialogFramentUploadPhoto = new DialogFramentUploadPhoto();
            dialogFramentUploadPhoto.show(getFragmentManager(), "photo");
        }
    }

    @Override
    public void onPermissionDenied(int requestCode, List<String> perms) {
        Toast.makeText(mContext, "未授权", Toast.LENGTH_SHORT)
                .show();
        EasyPermission.checkDeniedPermissionsNeverAskAgain(this, "该设备没有授权," + "请去设置里授权!", perms);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        EasyPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            if (requestCode == START_CAMERA) {

                File temp = new File(Environment.getExternalStorageDirectory()
                        + "/head.jpg");
                cropPhoto(Uri.fromFile(temp));//裁剪图片

            } else if (requestCode == CHOOSE_PHOTO) {

                cropPhoto(data.getData());//裁剪图片

            } else if (requestCode == CROP_CODE) {
                if (data != null) {
                    Bundle extras = data.getExtras();
                    Bitmap head = extras.getParcelable("data");
                    try {
                        File userPic = setPicToView(head);//保存在SD卡
                        upLoadAppendixImg(userPic);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    //上传头像

    private void upLoadAppendixImg(File file) {
        RequestParams requestParams = new RequestParams(HttpUtils.TEST_HOST + StaticCode.Up_Load_Img);
        Log.v("upLoadAppendixImg", requestParams.toString());
        requestParams.addHeader("Content-Type", "multipart/form-data");
        requestParams.setMultipart(true);
        requestParams.addBodyParameter("file", file);
        HttpUtilsFacade httpUtilsFacade = new HttpUtilsFacade();
        httpUtilsFacade.post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v("upLoadAppendixImg", result);
                JSONObject jsonObject = JSONObject.parseObject(result);
                headUrl = jsonObject.getString("return_data");
                if (headUrl != null && headUrl.length() > 0) {
                    Glide.with(mContext).load(headUrl).bitmapTransform(new CropCircleTransformation(mContext)).into(head);
                }
                AppApi.changeHead(MyUtils.getCode(mContext),headUrl,MySettingsActivity.this,0);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.v("upLoadAppendixImg", ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    @Override
    public void handlerSuccessData(String result, int tag) {
        Toast.makeText(mContext,"头像上传成功!",Toast.LENGTH_SHORT).show();
        SPUtils.put(mContext,"head",headUrl);
    }

    @Override
    public void handlerFailData(String failString, int tag) {

    }

    @Override
    public void handleErrorData(String error, int tag) {

    }
}
