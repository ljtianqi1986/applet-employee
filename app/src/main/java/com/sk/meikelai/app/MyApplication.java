package com.sk.meikelai.app;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.basewin.services.ServiceManager;
import com.sk.meikelai.utils.CrashHandler;

import org.xutils.x;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by sk on 2017/6/5.
 */

public class MyApplication extends Application {

    private static MyApplication sInstance;
    private ArrayList<AppCompatActivity> activityList;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        sInstance = this;
        activityList = new ArrayList<>();
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
        getAppInfo();
        if (Build.MANUFACTURER.contains("basewin")) {
            // 初始化service manager
            // init service manager
            ServiceManager.getInstence().init(getApplicationContext());
        }

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }

    public ArrayList<AppCompatActivity> getActivityList() {
        return activityList;
    }

    /**
     * 获取application 单例
     *
     * @return
     */
    public static MyApplication getInstance() {
        return sInstance;
    }

    /**
     * 获取全局的Context
     * 能用全局尽量用全局，防止内存泄漏
     *
     * @return
     */
    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

    public void finishApp() {
        for (AppCompatActivity activity : activityList) {
            activity.finish();
        }
    }

    private String getAppInfo() {
        try {
            String pkName = this.getPackageName();
            String versionName = this.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            int versionCode = this.getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
            Log.v("getAppInfo", pkName);
            return pkName;
        } catch (Exception e) {
        }
        return null;
    }


}
