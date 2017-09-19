package com.sk.meikelai.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;

import com.dyhdyh.widget.loading.LoadingConfig;
import com.dyhdyh.widget.loading.dialog.ILoadingDialog;
import com.dyhdyh.widget.loading.factory.DialogFactory;
import com.sk.meikelai.utils.MyUtils;


/**
 * Created by sk on 2017/6/29.
 */

public class LoadingDialog implements ILoadingDialog {
    private static LoadingDialog LOADINGDIALOG;

    private Dialog mDialog;
    private DialogFactory mFactory;
    private Context context;
    private int height ,width;

    public LoadingDialog(Context context, DialogFactory factory, int height , int width) {
        this.mDialog = factory.onCreateDialog(context);
        this.mFactory = factory;
        this.context = context;
        this.width = width;
        this.height = height;
        int animateStyleId = this.mFactory.getAnimateStyleId();
        if (animateStyleId > 0) {
            this.mDialog.getWindow().setWindowAnimations(animateStyleId);
        }
    }

    @Override
    public void show() {
        if (isValid() && !mDialog.isShowing()) {
            mDialog.show();
            MyUtils.setDialogWindowAttr(mDialog,context,height,width);
        }
    }

    public void cancelDialog() {
        if (isValid() && mDialog.isShowing()) {
            mDialog.cancel();
        }
    }


    @Override
    public Dialog create() {
        return mDialog;
    }

    @Override
    public ILoadingDialog setCancelable(boolean flag) {
        mDialog.setCancelable(flag);
        return this;
    }

    @Override
    public ILoadingDialog setMessage(CharSequence message) {
        mFactory.setMessage(mDialog, message);
        return this;
    }

    private boolean isValid() {
        if (mDialog != null) {
            Context context = mDialog.getContext();
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            }
            if (context instanceof Activity) {
                if (!((Activity) context).isFinishing()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static LoadingDialog make(Context context) {
        return make(context, LoadingConfig.getDialogFactory(),150,300);
    }

    public static LoadingDialog make(Context context, DialogFactory factory, int height, int width) {
        cancel();
        LOADINGDIALOG = new LoadingDialog(context, factory, height, width);
        return LOADINGDIALOG;
    }


    public static void cancel() {
        if (LOADINGDIALOG != null) {
            LOADINGDIALOG.cancelDialog();
            LOADINGDIALOG = null;
        }
    }
}
