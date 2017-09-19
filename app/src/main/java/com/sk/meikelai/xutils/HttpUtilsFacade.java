package com.sk.meikelai.xutils;

import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;

/**
 * Created by sk on 2017/6/5.
 */

public class HttpUtilsFacade implements HttpManager {


    @Override
    public <T> Callback.Cancelable request(HttpMethod method, RequestParams entity, Callback.CommonCallback<T> callback) {
        if (null == entity) entity = new RequestParams();
        entity.setConnectTimeout(20000);
        return HttpUtilsWraped.getInstance().request(method, entity, callback);
    }

    @Override
    public <T> Callback.Cancelable get(RequestParams entity, Callback.CommonCallback<T> callback) {
        return HttpUtilsWraped.getInstance().get(entity, callback);
    }

    @Override
    public <T> Callback.Cancelable post(RequestParams entity, Callback.CommonCallback<T> callback) {
        entity.setConnectTimeout(20000);
        return HttpUtilsWraped.getInstance().post(entity, callback);
    }


    @Override
    public <T> T getSync(RequestParams entity, Class<T> resultType) throws Throwable {
        return HttpUtilsWraped.getInstance().getSync(entity, resultType);
    }

    @Override
    public <T> T postSync(RequestParams entity, Class<T> resultType) throws Throwable {
        return HttpUtilsWraped.getInstance().postSync(entity, resultType);
    }

    @Override
    public <T> T requestSync(HttpMethod method, RequestParams entity, Class<T> resultType) throws Throwable {
        return HttpUtilsWraped.getInstance().requestSync(method, entity, resultType);
    }

    @Override
    public <T> T requestSync(HttpMethod method, RequestParams entity, Callback.TypedCallback<T> callback) throws Throwable {
        return HttpUtilsWraped.getInstance().requestSync(method, entity, callback);
    }

}
