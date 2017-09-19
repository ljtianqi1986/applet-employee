package com.sk.meikelai.utils;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.MemberInfoBean;
import com.sk.meikelai.entity.RegionNumberEditText;
import com.sk.meikelai.entity.SaveCardBean;
import com.sk.meikelai.entity.WorkerInfoBean;
import com.sk.meikelai.view.LoadingDialog;
import com.sk.meikelai.view.MyDialogLoadingFactory;

import org.xutils.common.util.DensityUtil;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

import static com.sk.meikelai.utils.StaticCode.SP_USER_DATA;
import static com.sk.meikelai.utils.StaticCode.SP_VIP_DATA;

/**
 * Created by sk on 2017/6/29.
 */

public class MyUtils {

    //在dialog.show()之后调用
    public static void setDialogWindowAttr(Dialog dlg, Context ctx, int height, int width) {
        if (dlg != null) {
            Window window = dlg.getWindow();
            if (window != null) {
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.gravity = Gravity.CENTER;
                lp.width = DensityUtil.dip2px(width);//宽高可设置具体大小
                lp.height = DensityUtil.dip2px(height);
                dlg.getWindow().setAttributes(lp);
            }
        }
    }

    public static LoadingDialog getLoadingDialog(Context context, boolean isCancel) {
        MyDialogLoadingFactory myLoadingFactory = new MyDialogLoadingFactory();
        LoadingDialog loadingDialog = LoadingDialog.make(context, myLoadingFactory, 150, 150);
        loadingDialog.setCancelable(isCancel);
        return loadingDialog;
    }

    public static String getCode(Context context) {
        WorkerInfoBean bean = JSONObject.parseObject((String) SPUtils.get(context, SP_USER_DATA, ""), WorkerInfoBean.class);
        if (bean != null) {
            return bean.getReturn_data().getUser_code();
        } else {
            return "";
        }
    }

    public static String getName(Context context) {
        WorkerInfoBean bean = JSONObject.parseObject((String) SPUtils.get(context, SP_USER_DATA, ""), WorkerInfoBean.class);
        if (bean != null) {
            return bean.getReturn_data().getLogin_name();
        } else {
            return "";
        }
    }

    public static String getAgent_name(Context context) {
        WorkerInfoBean bean = JSONObject.parseObject((String) SPUtils.get(context, SP_USER_DATA, ""), WorkerInfoBean.class);
        if (bean != null) {
            return bean.getReturn_data().getAgent_name();
        } else {
            return "";
        }
    }

    public static String getShopName(Context context) {
        WorkerInfoBean bean = JSONObject.parseObject((String) SPUtils.get(context, SP_USER_DATA, ""), WorkerInfoBean.class);
        if (bean != null) {
            return bean.getReturn_data().getShop_name();
        } else {
            return "";
        }
    }

    public static String getUrl(Context context) {
        String url = (String) SPUtils.get(context, "head", "");
        if (url != null && url.length() > 0) {
            return url;
        } else {
            return "";
        }
    }

    public static String getCashUserCode(Context context) {
        MemberInfoBean bean = JSONObject.parseObject((String) SPUtils.get(context, SP_VIP_DATA, ""), MemberInfoBean.class);
        if (bean != null) {
            return bean.getReturn_data().getCode();
        } else {
            return "";
        }
    }

    public static String getShopCode(Context context) {
        WorkerInfoBean bean = JSONObject.parseObject((String) SPUtils.get(context, SP_USER_DATA, ""), WorkerInfoBean.class);
        if (bean != null) {
            return bean.getReturn_data().getShop_code();
        } else {
            return "";
        }
    }

    public static String getIdentityCode(Context context) {
        WorkerInfoBean bean = JSONObject.parseObject((String) SPUtils.get(context, SP_USER_DATA, ""), WorkerInfoBean.class);
        if (bean != null) {
            return bean.getReturn_data().getIdentity_code();
        } else {
            return "";
        }
    }


    public static void setEditTextContact(final RegionNumberEditText editText1, final RegionNumberEditText editText2, final double total,
                                          boolean isContact, final List<EditText> allEditTest1, final List<EditText> allEditTest2, final
                                          TextView allMoney, final TextView dkMoney) {
        final DecimalFormat dcmFmt = new DecimalFormat("0.00");
        final SaveCardBean.ReturnDataBean bean = (SaveCardBean.ReturnDataBean) editText1.getTag();
        final double zkdkTotal = ((bean.getBalance() / (float)bean.getDiscount()) * 10 / 100f);
        final SaveCardBean.ReturnDataBean bean1 = (SaveCardBean.ReturnDataBean) editText1.getTag();
        final double bzkdkTotal = (bean1.getBalance() *(float) bean1.getCoefficient()) / 100f;

        if (!isContact) {
            editText1.setRegion(zkdkTotal, 0);
            editText1.setTextWatcher();
            editText2.setRegion(bzkdkTotal, 0);
            editText2.setTextWatcher();
        } else {
            editText1.setRegion(zkdkTotal, 0);
            editText1.setTextWatcher();
            editText2.setRegion(bzkdkTotal, 0);
            editText2.setTextWatcher();
            editText1.setEditChangeListener(new RegionNumberEditText.EditChangeListener() {
                @Override
                public void changeLimit(CharSequence s) {
                    if (s != null && s.length() > 0) {
                        //抵扣
                        double num = Double.parseDouble(s.toString());
                        //edit1实扣
                        double shikou = num * bean.getDiscount() / 10d;
                        //剩余
                        double left = total - shikou;
                        //editText2能抵扣
                        double ed2Dk = (left * bean1.getCoefficient());
                        if (ed2Dk > 0) {
                            editText2.setRegion(ed2Dk, 0);
                        } else {
                            editText2.setRegion(0, 0);
                        }
                        editText2.setTextWatcher();
                    } else {
                        editText2.setRegion(bzkdkTotal, 0);
                        editText2.setTextWatcher();
                    }
                }
            });
            editText1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void afterTextChanged(Editable s) {

                    double zkMoney = 0;
                    double bzkMoney = 0;
                    double zkdkMoney = 0;
                    double bzkdkMoney = 0;
                    for (int i = 0; i < allEditTest1.size(); i++) {
                        EditText editText = allEditTest1.get(i);
                        if (allEditTest1.get(i).getText() == null || allEditTest1.get(i).getText().length() == 0) {
                            zkMoney += 0;
                            zkdkMoney += 0;
                        } else {
                            double nowMoney = Double.parseDouble(editText.getText().toString());
                            zkdkMoney += nowMoney;
                            SaveCardBean.ReturnDataBean bean = (SaveCardBean.ReturnDataBean) editText.getTag();
                            zkMoney += Double.parseDouble(dcmFmt.format(nowMoney * bean.getDiscount() / 10d));
                        }
                    }
                    for (int i = 0; i < allEditTest2.size(); i++) {
                        EditText editText = allEditTest2.get(i);
                        if (allEditTest2.get(i).getText() == null || allEditTest2.get(i).getText().length() == 0) {
                            bzkMoney += 0;
                            bzkdkMoney += 0;
                        } else {
                            double nowMoney = Double.parseDouble(editText.getText().toString());
                            bzkdkMoney += nowMoney;
                            SaveCardBean.ReturnDataBean bean = (SaveCardBean.ReturnDataBean) editText.getTag();
                            bzkMoney += Double.parseDouble(dcmFmt.format(nowMoney / (bean.getCoefficient())));
                        }
                    }
                    allMoney.setText(dcmFmt.format(zkMoney + bzkMoney));
                    dkMoney.setText(dcmFmt.format(zkdkMoney + bzkdkMoney));
                }
            });
            editText2.setEditChangeListener(new RegionNumberEditText.EditChangeListener() {
                @Override
                public void changeLimit(CharSequence s) {
                    if (s != null && s.length() > 0) {
                        //抵扣
                        double num = Double.parseDouble(s.toString());
                        //edit2实扣
                        double shikou = num * bean.getDiscount() / 10d;
                        //剩余
                        double left = total - shikou;
                        //editText1能抵扣
                        int ed1Dk = (int) ((left / bean.getDiscount()) * 10);
                        if (ed1Dk > 0) {
                            editText1.setRegion(ed1Dk, 0);
                        } else {
                            editText1.setRegion(0, 0);
                        }
                        editText1.setTextWatcher();
                    } else {
                        editText1.setRegion(zkdkTotal, 0);
                        editText1.setTextWatcher();
                    }
                }
            });
            editText2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    double zkMoney = 0;
                    double bzkMoney = 0;
                    double zkdkMoney = 0;
                    double bzkdkMoney = 0;
                    for (int i = 0; i < allEditTest1.size(); i++) {
                        EditText editText = allEditTest1.get(i);
                        if (allEditTest1.get(i).getText() == null || allEditTest1.get(i).getText().length() == 0) {
                            zkMoney += 0;
                            zkdkMoney += 0;
                        } else {
                            double nowMoney = Double.parseDouble(editText.getText().toString());
                            zkdkMoney += nowMoney;
                            SaveCardBean.ReturnDataBean bean = (SaveCardBean.ReturnDataBean) editText.getTag();
                            zkMoney += Double.parseDouble(dcmFmt.format(nowMoney * bean.getDiscount() / 10d));
                        }
                    }
                    for (int i = 0; i < allEditTest2.size(); i++) {
                        EditText editText = allEditTest2.get(i);
                        if (allEditTest2.get(i).getText() == null || allEditTest2.get(i).getText().length() == 0) {
                            bzkMoney += 0;
                            bzkdkMoney += 0;
                        } else {
                            double nowMoney = Double.parseDouble(editText.getText().toString());
                            bzkdkMoney += nowMoney;
                            SaveCardBean.ReturnDataBean bean = (SaveCardBean.ReturnDataBean) editText.getTag();
                            bzkMoney += Double.parseDouble(dcmFmt.format(nowMoney / (bean.getCoefficient())));
                        }
                    }
                    allMoney.setText(dcmFmt.format(zkMoney + bzkMoney));
                    dkMoney.setText(dcmFmt.format(zkdkMoney + bzkdkMoney));
                }
            });
        }
    }

    public static void showPopWindow(Context context, String string, View view) {
        com.sk.meikelai.view.BubblePopupWindow bubblePopupWindow = new com.sk.meikelai.view.BubblePopupWindow(context);
        View bubbleView = LayoutInflater.from(context).inflate(R.layout.layout_popup_view, null);
        TextView tvContent = (TextView) bubbleView.findViewById(R.id.tvContent);
        tvContent.setText(string);
        bubblePopupWindow.setBubbleView(bubbleView); // 设置气泡内容
        bubblePopupWindow.show(view, Gravity.BOTTOM); // 显示弹窗
    }

    public static String getRechargeInfo(Context mContext) {
        MemberInfoBean memberInfoBean = JSONObject.parseObject((String) SPUtils.get(mContext, SP_VIP_DATA, ""),
                MemberInfoBean.class);
        StringBuilder rechargeInfo = new StringBuilder();
        if (memberInfoBean != null) {

            if (memberInfoBean.getReturn_data().getRecharge_arr() != null && !memberInfoBean.getReturn_data
                    ().getRecharge_arr().isEmpty()) {
                for (int i = 0; i < memberInfoBean.getReturn_data().getRecharge_arr().size(); i++) {
                    MemberInfoBean.ReturnDataBean.RechargeArrBean bean = memberInfoBean.getReturn_data
                            ().getRecharge_arr().get(i);
                    if (bean.getCard_isEffective().equals("0")) {
                        rechargeInfo.append(bean.getCard_name()).append(":余额").append(bean.getBalance() /
                                100f).append("  ").append("(未生效)");
                    } else {
                        rechargeInfo.append(bean.getCard_name()).append(":余额").append(bean.getBalance() / 100f);
                    }
                    if (i != memberInfoBean.getReturn_data().getRecharge_arr().size() - 1) {
                        rechargeInfo.append("\n\n");
                    }
                }
            }

        }
        return rechargeInfo.toString();
    }

    public static String getCountInfo(Context mContext) {
        MemberInfoBean memberInfoBean = JSONObject.parseObject((String) SPUtils.get(mContext, SP_VIP_DATA, ""), MemberInfoBean.class);
        StringBuilder countInfo = new StringBuilder();
        if (memberInfoBean != null) {

            if (memberInfoBean.getReturn_data().getCount_arr() != null && !memberInfoBean.getReturn_data().getCount_arr().isEmpty()) {
                for (int i = 0; i < memberInfoBean.getReturn_data().getCount_arr().size(); i++) {
                    MemberInfoBean.ReturnDataBean.CountArrBean bean = memberInfoBean.getReturn_data().getCount_arr().get(i);
                    countInfo.append(bean.getCard_name()).append(":剩余").append(bean.getSurplus_times()).append("次").append("\n截止日期到")
                            .append(bean.getEndDate());
                    if (i != memberInfoBean.getReturn_data().getCount_arr().size() - 1) {
                        countInfo.append("\n\n");
                    }

                }
            }

        }
        return countInfo.toString();
    }

    public static String getYearInfo(Context mContext) {
        MemberInfoBean memberInfoBean = JSONObject.parseObject((String) SPUtils.get(mContext, SP_VIP_DATA, ""), MemberInfoBean.class);
        StringBuilder yearInfo = new StringBuilder();
        if (memberInfoBean != null) {

            if (memberInfoBean.getReturn_data().getYear_arr() != null && !memberInfoBean.getReturn_data().getYear_arr().isEmpty()) {
                for (int i = 0; i < memberInfoBean.getReturn_data().getYear_arr().size(); i++) {
                    MemberInfoBean.ReturnDataBean.YearArrBean bean = memberInfoBean.getReturn_data().getYear_arr().get(i);
                    yearInfo.append(bean.getCard_name()).append("\n截至日到").append(bean.getEndDate());
                    if (i != memberInfoBean.getReturn_data().getYear_arr().size() - 1) {
                        yearInfo.append("\n\n");
                    }
                }
            }

        }
        return yearInfo.toString();
    }

    public static int getSaveCount(Context mContext) {
        MemberInfoBean memberInfoBean = JSONObject.parseObject((String) SPUtils.get(mContext, SP_VIP_DATA, ""), MemberInfoBean.class);
        if (memberInfoBean.getReturn_data() != null) {
            return memberInfoBean.getReturn_data().getRecharge_count();
        } else {
            return 0;
        }
    }


    public static File getFileByUri(Uri uri, Context context) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.ImageColumns._ID,
                        MediaStore.Images.ImageColumns.DATA}, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
            // 4.2.2以后
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();

            return new File(path);
        } else {
            Log.i("upLoad", "Uri Scheme:" + uri.getScheme());
        }
        return null;
    }

    /**
     * get App versionCode
     *
     * @param context
     * @return
     */
    public static String getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionCode = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
    /**
     * get App versionCode
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
