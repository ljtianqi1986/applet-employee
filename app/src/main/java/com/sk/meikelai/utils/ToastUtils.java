package com.sk.meikelai.utils;


import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

public class ToastUtils {
    public static void toastError(int code, Bundle bundle, Context context) {
        String value = bundle.getString(StaticCode.RETURN_INFO);
        if (code == StaticCode.SERVER_DATA_ERROR) {
            Toast.makeText(context, value, Toast.LENGTH_LONG).show();
        } else {
            JSONObject jsonObject = JSONObject.parseObject(value);
            String msg = jsonObject.getString(StaticCode.ERROR_INFO);
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }
    }

    protected static Toast toast   = null;
    private static String oldMsg;
    private static long oneTime = 0;
    private static long twoTime = 0;

    public static void showToast(Context context, String s){
        if(toast==null){
            toast =Toast.makeText(context, s, Toast.LENGTH_SHORT);
            toast.show();
            oneTime=System.currentTimeMillis();
        }else{
            twoTime=System.currentTimeMillis();
            if(s.equals(oldMsg)){
                if(twoTime-oneTime>Toast.LENGTH_SHORT){
                    toast.show();
                }
            }else{
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime=twoTime;
    }

    public static void showToast(Context context, int resId){
        showToast(context, context.getString(resId));
    }
}
