package com.sk.meikelai.xutils;

import org.xutils.HttpManager;
import org.xutils.x;

/**
 * Created by sk on 2017/6/5.
 */
public class HttpUtilsWraped {
    private static HttpManager httpManager = x.http();

    private HttpUtilsWraped(){

    }

    public static HttpManager getInstance(){
        return httpManager;
    }

}
