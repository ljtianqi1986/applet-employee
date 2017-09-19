package com.sk.meikelai.activity.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.sk.meikelai.R;
import com.sk.meikelai.app.MyApplication;
import com.sk.meikelai.manager.SystemBarTintManager;
import com.sk.meikelai.utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by sk on 2016/12/30.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG = "BaseActivity";
    protected Context mContext;
    protected Unbinder unbinder;


    protected abstract int getContentView();

    private SystemBarTintManager tintManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        mContext = this;
        unbinder = ButterKnife.bind(this);
        MyApplication myApplication = MyApplication.getInstance();
        myApplication.getActivityList().add(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }


        tintManager = new SystemBarTintManager(this);

        tintManager.setStatusBarTintEnabled(true);

        tintManager.setStatusBarTintResource(R.color.status_color);

        if (UIUtils.isHasVirtualButton(this)) {
            tintManager = new SystemBarTintManager(this);
            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setNavigationBarTintResource(R.color.navigationBar_color);
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        System.out.print("fuck you");
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 设置系统栏颜色
     *
     * @param color 颜色
     */
    public void setStatusBarTintResource(int color) {
        tintManager.setStatusBarTintResource(color);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        MyApplication myApplication = MyApplication.getInstance();
        myApplication.getActivityList().remove(this);
        super.onDestroy();
    }
}

