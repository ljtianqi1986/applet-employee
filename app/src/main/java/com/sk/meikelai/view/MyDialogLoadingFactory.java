package com.sk.meikelai.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.dyhdyh.widget.loading.factory.DialogFactory;
import com.sk.meikelai.R;
import com.zyao89.view.zloading.ZLoadingView;


/**
 * Created by sk on 2017/6/28.
 */

public class MyDialogLoadingFactory implements DialogFactory {

    @Override
    public Dialog onCreateDialog(Context context) {
        Dialog dialog = new Dialog(context,R.style.dialog_theme);
        View view = LayoutInflater.from(context).inflate(R.layout.my_loading_factory,null);
        ZLoadingView zLoadingView = (ZLoadingView) view.findViewById(R.id.loadingView_1);
        dialog.setContentView(view);
        return dialog;
    }

    @Override
    public void setMessage(Dialog dialog, CharSequence message) {

    }

    @Override
    public int getAnimateStyleId() {
        return 0;
    }

}
