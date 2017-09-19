package com.sk.meikelai.activity.cash;

import android.content.Intent;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dtr.zbar.build.ZBarDecoder;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.entity.FirstEvent;
import com.sk.meikelai.manager.CameraManager;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.Base;
import com.sk.meikelai.utils.CameraPreview;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.OnClick;

import static com.sk.meikelai.utils.StaticCode.SP_VIP_DATA;


/**
 * Created by sk on 2017/3/29.
 */

public class CaptureActivity extends BaseActivity implements HandleDataCallBack{


    @BindView(R.id.lay_preview)
    FrameLayout layPreview;
    @BindView(R.id.lay_head)
    RelativeLayout layHead;
    @BindView(R.id.capture_mask_top)
    ImageView captureMaskTop;
    @BindView(R.id.img_bottom)
    ImageView imgBottom;
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.tv_warn)
    TextView tvWarn;
    @BindView(R.id.img_scan_line)
    ImageView imgScanLine;
    @BindView(R.id.tv_alert)
    TextView tvAlert;
    @BindView(R.id.lay_crop)
    RelativeLayout layCrop;
    @BindView(R.id.lay_container)
    RelativeLayout layContainer;
    @BindView(R.id.btn_coupon)
    Button btnCoupon;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.ok)
    TextView mOK;
    private Camera mCamera;
    private CameraPreview mPreview;
    private Handler autoFocusHandler;
    private CameraManager mCameraManager;
    private Rect mCropRect = null;

    private boolean barcodeScanned = false;
    private boolean previewing = true;

    private static final int TAG_MEMBER_LOGIN = 0;
    private static final int TAG_RECHARGE_LOGIN = 2;
    private static final int TAG_OPENCARD_LOGIN = 3;

    private String flag;

    @Override
    protected int getContentView() {
        return R.layout.activity_capture;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag = getIntent().getStringExtra("tag");
        init();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseCamera();
    }

    private void init() {

        RelativeLayout.LayoutParams layoutparams = (RelativeLayout.LayoutParams) layCrop.getLayoutParams();
        layoutparams.width = (int) (0.7D * (double) Base
                .getScreenWidth(CaptureActivity.this));
        layoutparams.height = (int) (0.7D * (double) Base
                .getScreenWidth(CaptureActivity.this));

        autoFocusHandler = new Handler();
        mCameraManager = new CameraManager(this);
        try {
            mCameraManager.openDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mCamera = mCameraManager.getCamera();
        mPreview = new CameraPreview(this, mCamera, previewCb, autoFocusCB);
        layPreview.addView(mPreview);

        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.85f);
        animation.setDuration(3000);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.REVERSE);
        imgScanLine.startAnimation(animation);
    }

    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing)
                mCamera.autoFocus(autoFocusCB);
        }
    };

    Camera.PreviewCallback previewCb = new Camera.PreviewCallback() {
        public void onPreviewFrame(byte[] data, Camera camera) {
            Camera.Size size = camera.getParameters().getPreviewSize();

            // 这里需要将获取的data翻转一下，因为相机默认拿的的横屏的数据
            byte[] rotatedData = new byte[data.length];
            for (int y = 0; y < size.height; y++) {
                for (int x = 0; x < size.width; x++)
                    rotatedData[x * size.height + size.height - y - 1] = data[x
                            + y * size.width];
            }

            // 宽高也要调整
            int tmp = size.width;
            size.width = size.height;
            size.height = tmp;

            initCrop();
            ZBarDecoder zBarDecoder = new ZBarDecoder();
            String result = zBarDecoder.decodeCrop(rotatedData, size.width,
                    size.height, mCropRect.left, mCropRect.top,
                    mCropRect.width(), mCropRect.height());

            if (!TextUtils.isEmpty(result)) {
                previewing = false;
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();
                barcodeScanned = true;
                if(flag.equals("cash")){
                    AppApi.memberLogin(result, MyUtils.getShopCode(mContext), CaptureActivity.this, TAG_MEMBER_LOGIN);
                }else if(flag.equals("pay")){
                    Intent intent = new Intent();
                    intent.putExtra("result",result);
                    setResult(RESULT_OK,intent);
                    finish();
                }else if (flag.equals("recharge")){
                    AppApi.memberLogin(result, MyUtils.getShopCode(mContext), CaptureActivity.this, TAG_RECHARGE_LOGIN);
                }else if (flag.equals("opencard")){
                    AppApi.memberLogin(result, MyUtils.getShopCode(mContext), CaptureActivity.this, TAG_OPENCARD_LOGIN);
                }else if (flag.equals("confirm")){
                    Intent intent = new Intent();
                    intent.putExtra("result",result);
                    setResult(RESULT_OK,intent);
                    finish();
                }else if (flag.equals("rechargecard")){
                    Intent intent = new Intent();
                    intent.putExtra("result",result);
                    setResult(RESULT_OK,intent);
                    finish();
                }

            }
        }
    };


    // Mimic continuous auto-focusing
    Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };

    /**
     * 初始化截取的矩形区域
     */
    private void initCrop() {
        int cameraWidth = mCameraManager.getCameraResolution().y;
        int cameraHeight = mCameraManager.getCameraResolution().x;

        /** 获取布局中扫描框的位置信息 */
        int[] location = new int[2];
        layCrop.getLocationInWindow(location);

        int cropLeft = location[0];
        int cropTop = location[1] - getStatusBarHeight();

        int cropWidth = layCrop.getWidth();
        int cropHeight = layCrop.getHeight();

        /** 获取布局容器的宽高 */
        int containerWidth = layContainer.getWidth();
        int containerHeight = layContainer.getHeight();

        /** 计算最终截取的矩形的左上角顶点x坐标 */
        int x = cropLeft * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的左上角顶点y坐标 */
        int y = cropTop * cameraHeight / containerHeight;

        /** 计算最终截取的矩形的宽度 */
        int width = cropWidth * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的高度 */
        int height = cropHeight * cameraHeight / containerHeight;

        /** 生成最终的截取的矩形 */
        mCropRect = new Rect(x, y, width + x, height + y);
    }


    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @OnClick({R.id.back, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ok:
//                AppApi.memberLogin("815004385416550", MyUtils.getShopCode(mContext), this, TAG_MEMBER_LOGIN);
                break;
        }
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        switch (tag) {
            case  TAG_MEMBER_LOGIN:
                SPUtils.put(mContext, SP_VIP_DATA, result);
                Log.v("result","result_ca="+result);
                Toast.makeText(mContext, "登陆成功！", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                EventBus.getDefault().post(new FirstEvent("refresh"));
                finish();
                break;
            case  TAG_RECHARGE_LOGIN:
                SPUtils.put(mContext, SP_VIP_DATA, result);
                Log.v("result","result_re="+result);
                Toast.makeText(mContext, "登陆成功！", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new FirstEvent("refresh"));
                setResult(RESULT_OK);
                finish();
                break;
            case TAG_OPENCARD_LOGIN:
                SPUtils.put(mContext, SP_VIP_DATA, result);
                Log.v("result","result_op="+result);
                Toast.makeText(mContext, "登陆成功！", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new FirstEvent("refresh"));
                setResult(RESULT_OK);
                finish();
                break;
        }

    }

    @Override
    public void handlerFailData(String failString, int tag) {
        Toast.makeText(mContext, "登陆失败！", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void handleErrorData(String error, int tag) {
        Toast.makeText(mContext, "错误！", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void releaseCamera() {
        if (mCamera != null) {
            previewing = false;
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

}
