package com.sk.meikelai.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;

import com.sk.meikelai.app.MyApplication;

import java.io.DataOutputStream;
import java.io.OutputStream;

/**
 * Created by sk on 2017/6/27.
 */

public class DeviceUtils {

    /**
     * 返回版本名字
     * 对应build.gradle中的versionName
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 返回版本号
     * 对应build.gradle中的versionCode
     *
     * @param context
     * @return
     */
    public static String getVersionCode(Context context) {
        String versionCode = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = String.valueOf(packInfo.versionCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取设备的deviceInfo
     *
     * @param context
     * @return
     */
    public static String getDeviceInfo(Context context) {
        String device_info = "";
        device_info = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return device_info;
    }

    /**
     * 获取设备的ip
     */

    public static String getIp(){
        WifiManager wm=(WifiManager) MyApplication.getInstance().getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        //检查Wifi状态
        if(!wm.isWifiEnabled())
            return "192.168.0.116";
        WifiInfo wi=wm.getConnectionInfo();
        //获取32位整型IP地址
        int ipAdd=wi.getIpAddress();
        //把整型地址转换成“*.*.*.*”地址
        String ip=intToIp(ipAdd);
        return ip;
    }
    private static String intToIp(int i) {
        return (i & 0xFF ) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ( i >> 24 & 0xFF) ;
    }

    /**
     * 判断机器 Android是否已经root，即是否获取root权限
     */
    public static boolean haveRoot() {
        int i = execRootCmdSilent("echo test"); // 通过执行测试命令来检测
        return i != -1;
    }

    private static int execRootCmdSilent(String paramString) {
        try {
            Process localProcess = Runtime.getRuntime().exec("su");
            Object localObject = localProcess.getOutputStream();
            DataOutputStream localDataOutputStream = new DataOutputStream(
                    (OutputStream) localObject);
            String str = String.valueOf(paramString);
            localObject = str + "\n";
            localDataOutputStream.writeBytes((String) localObject);
            localDataOutputStream.flush();
            localDataOutputStream.writeBytes("exit\n");
            localDataOutputStream.flush();
            localProcess.waitFor();
            int result = localProcess.exitValue();
            return (Integer) result;
        } catch (Exception localException) {
            localException.printStackTrace();
            return -1;
        }
    }

}
