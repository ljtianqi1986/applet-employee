package com.sk.meikelai.utils;


import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class MobileUtils {

    public static boolean isPhoneNumberEmpty(Context context, String phoneNumber, String toastStr) {
        if (TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(context, toastStr, Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }

    //判断是否是手机号码
    public static boolean isMobileNumber(Context context, String phoneNumber, String toastStr) {
        if(phoneNumber==null)
        {
            Toast.makeText(context,"手机号码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(phoneNumber.startsWith("1") && phoneNumber.trim().length() == 11){
            return true;
        }else {
            Toast.makeText(context,"手机号码格式不规范!",Toast.LENGTH_SHORT).show();
            return false;
        }
    }



    /**
     * 手机卡号隐藏中间
     */
    public static String hideMobileNumMid(String phone) {
        if (!phone.equals(null)) {
            // 括号表示组，被替换的部分$n表示第n组的内容
            // 正则表达式中，替换字符串，括号的意思是分组，在replace()方法中，
            // 参数二中可以使用$n(n为数字)来依次引用模式串中用括号定义的字串。
            // "(\d{3})\d{4}(\d{4})", "$1****$2"的这个意思就是用括号，
            // 分为(前3个数字)中间4个数字(最后4个数字)替换为(第一组数值，保持不变$1)(中间为*)(第二组数值，保持不变$2)
            return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        } else {
            return "";
        }
    }
}
