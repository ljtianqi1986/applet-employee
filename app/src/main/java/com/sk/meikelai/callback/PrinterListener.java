package com.sk.meikelai.callback;

import android.util.Log;

import com.basewin.aidl.OnPrinterListener;

/**
 * Created by sk on 2017/8/9.
 */

public class PrinterListener implements OnPrinterListener {



    @Override
    public void onStart() {
        // TODO 打印开始
        // Print start
        Log.d("print", "打印开始");
    }

    @Override
    public void onFinish() {
        // TODO 打印结束
        // End of the print
        Log.d("print", "打印结束");
    }

    @Override
    public void onError(int errorCode, String detail) {
        Log.d("print", "打印出错");
    }
}
