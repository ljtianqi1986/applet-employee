package com.sk.meikelai.utils;

import android.content.Context;
import android.content.Intent;

import com.sk.meikelai.view.CommonActivity;
import com.sk.meikelai.view.CommonFragmentInfo;


public class UIHelper {

//
//    //跳转到注册界面
//    public static void showWorkerLogin(Context context) {
//        showCommonActivity(context, CommonFragmentInfo.WORKERLOGIN);
//    }
//    //跳转到开卡页面
//    public static void showOpenCardInfo(Context context) {
//        showCommonActivity(context, CommonFragmentInfo.OPENCARDINFO);
//    }
    public static void showConfirm(Context context) {
//        showCommonActivity(context, CommonFragmentInfo.CONFIRM);
    }

    //跳转到今日现金交接申报界面
    public static void showHandOver(Context context) {
        showCommonActivity(context, CommonFragmentInfo.HANDOVER);
    }
//    //跳转到今日实收
//    public static void showTodayCash(Context context) {
//        showCommonActivity(context, CommonFragmentInfo.TODAYCASH);
//    }
//    //跳转到今日业绩
//    public static void showTodayAchievement(Context context) {
//        showCommonActivity(context, CommonFragmentInfo.TODAYACHIEVEMENT);
//    }
    //跳转到今日预约
    public static void showTodayAppointment(Context context) {
        showCommonActivity(context, CommonFragmentInfo.TODAYAPPOINTMENT);
    }


    private static void showCommonActivity(Context context, CommonFragmentInfo commonFragmentInfo) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CommonActivity.BUNDLE_KEY_FRAGMENT, commonFragmentInfo.getmKey());
        context.startActivity(intent);
    }
}
