package com.sk.meikelai.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dyhdyh.widget.loading.factory.LoadingFactory;
import com.sk.meikelai.R;


/**
 * Created by sk on 2017/6/28.
 */

public class MyLoadingFactory implements LoadingFactory {
    @Override
    public View onCreateView(ViewGroup parent) {
        View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_loading_factory,parent,false);
        return loadingView;
    }
}
