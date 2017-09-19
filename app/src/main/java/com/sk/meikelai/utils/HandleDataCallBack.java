package com.sk.meikelai.utils;

/**
 * Created by sk on 2017/6/28.
 */

public interface HandleDataCallBack {

    //成功回调
    void handlerSuccessData(String result,int tag);

    //失败回掉
    void handlerFailData(String failString,int tag);

    //错误回调
    void handleErrorData(String error,int tag);

}
