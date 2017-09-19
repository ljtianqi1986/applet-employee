package com.sk.meikelai.utils;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.sk.meikelai.app.MyApplication;
import com.sk.meikelai.xutils.HttpUtilsFacade;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    public static String TEST_HOST = "https://mj.pay17.cn/mj/";

//    public static String TEST_HOST = "http://182.254.215.179/mkl/";

//    public static String TEST_HOST = "http://192.168.0.116:8001/mkl/";

//    public static String TEST_HOST = "http://192.168.0.119:8080/mkl/";

//    private static String TEST_HOST = "http://182.254.215.179/mkl/";

//    public static String TEST_HOST = "http://192.168.23.1:8080/mkl/";

//    private static String TEST_HOST = "http://182.254.215.179/mkl/";

//    private static String TEST_HOST = "http://192.168.0.134:8080/mkl/";

//    private static String TEST_HOST = "http://182.254.215.179/mkl/";

//    private static String TEST_HOST = "http://192.168.0.134:8080/mkl/";

//    public static String TEST_HOST = "http://192.168.0.116:8001/mj/";

//    private static String HOST = "http://mkl.wcwifi.cn/mkl/";

//    public static String TEST_HOST = "http://192.168.0.119:8080/mkl/";

    public static void postData(String urlStr, final Map<String, Object> args, final HandleDataCallBack callBack, final int tag) {
        String url = TEST_HOST + urlStr;
        RequestParams params = new RequestParams(url);
        for (Object o : args.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            String val = entry.getValue().toString();
            String key = entry.getKey().toString();
            params.addBodyParameter(key, val);
        }
        Log.v("getData", params.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v("onSuccess", result);
                JSONObject jsonObject = JSON.parseObject(result);
                int code = jsonObject.getIntValue(StaticCode.RETURN_CODE);
                if (code == StaticCode.SUCCESS_REQUEST) {
                    callBack.handlerSuccessData(result, tag);
                } else {
                    callBack.handlerFailData(result, tag);
                    Toast.makeText(MyApplication.getAppContext(), jsonObject.getString(StaticCode.RETURN_INFO), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.v("onError", ex.getMessage());
                callBack.handleErrorData(ex.getMessage(), tag);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    public static void postServer(String urlStr, final Map<String, Object> args, final HandleDataCallBack callBack, final int tag) {
        String url = TEST_HOST + urlStr;
        RequestParams params = new RequestParams(url);
        Long nowTime = System.currentTimeMillis() / 1000;
        final Map<String, Object> map = new HashMap<>();
        map.put("Reqtime", String.valueOf(nowTime));
        map.put("Apiversion", "1.0");
        map.put("Phoneclass", "2");
        map.put("Version", "1.0");
        String sessionId;
        sessionId = "";
        map.put("Sessionid", sessionId);
        map.put("Appid", "1585");
        if (args != null) {
            for (Object o : args.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object val = entry.getValue().toString();
                Object key = entry.getKey();
                String enCodeValue = EncodeUtils.encodeURIComponent(val.toString());
                params.addBodyParameter(key.toString(), val.toString());
                map.put(key.toString(), enCodeValue);
            }
        }

        Object[] key_arr = map.keySet().toArray();
        StringBuffer buffer = new StringBuffer();
        Arrays.sort(key_arr);
        for (Object key : key_arr) {
            Object value = map.get(key);
            buffer.append(key).append("=" + value).append("&");
        }
        String sb = buffer.substring(0, buffer.length() - 1);
        String result = sb + "b._*cGfd|Csxbz.,>?";
        String md5First = MD5Util.MD5(result).toLowerCase();
        String md5Final = MD5Util.MD5(md5First);
        params.addHeader("Reqtime", String.valueOf(nowTime));
        params.addHeader("Apiversion", "1.0");
        params.addHeader("Phoneclass", "2");
        params.addHeader("Version", "1.0");
        params.addHeader("Sessionid", sessionId);
        params.addHeader("Sign", md5Final);
        params.addHeader("Appid", "1585");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v("success", result);
                JSONObject jsonObject = JSON.parseObject(result);
                int code = jsonObject.getIntValue(StaticCode.RETURN_CODE);
                if (code == StaticCode.SUCCESS_REQUEST) {
                    callBack.handlerSuccessData(result, tag);
                } else {
                    callBack.handlerFailData(result, tag);
                    Toast.makeText(MyApplication.getAppContext(), jsonObject.getString(StaticCode.RETURN_INFO), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.v("onError", ex.getMessage());
                callBack.handleErrorData(ex.getMessage(), tag);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }
        });
    }

    static void getData(String urlStr, final Map<String, Object> args, final HandleDataCallBack callBack, final int tag) {
        String url = TEST_HOST + urlStr;
        RequestParams params = new RequestParams(url);
        for (Object o : args.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            String val = entry.getValue().toString();
            String key = entry.getKey().toString();
            params.addBodyParameter(key, val);
        }
        Log.v("getData", params.toString());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v("onSuccess", result);
                JSONObject jsonObject = JSON.parseObject(result);
                int code = jsonObject.getIntValue(StaticCode.RETURN_CODE);
                if (code == StaticCode.SUCCESS_REQUEST) {
                    callBack.handlerSuccessData(result, tag);
                } else {
                    callBack.handlerFailData(result, tag);
                    Toast.makeText(MyApplication.getAppContext(), jsonObject.getString(StaticCode.RETURN_INFO), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.v("onError", ex.toString());
                callBack.handleErrorData(ex.toString(), tag);
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });


    }
}
