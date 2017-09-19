package com.sk.meikelai.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.os.Environment;
import android.os.Vibrator;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Base
{
	// 获取屏幕的宽度
	public static int getScreenWidth(Context context)
	{
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		return display.getWidth();
	}

	// 获取屏幕的高度
	public static int getScreenHeight(Context context)
	{
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		return display.getHeight();
	}

	public static float getScreenDesign(Context context)
	{
		DisplayMetrics displaymetrics = context.getResources().getDisplayMetrics();
		return displaymetrics.density;
	}
	public static String getFullNumber(double val)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		return nf.format(val);
	}

	public static String getShortDate(String date_time)
	{
		if (date_time.length() > 10)
		{
			date_time = date_time.substring(0, 10);
		}
		return date_time;
	}

	public static void disabledEditText(EditText edt)
	{
		edt.setFocusable(false);
		edt.setEnabled(false);
	}

	/**
	 * 获取系统版本号
	 * 
	 * @Description:
	 * @param context
	 * @param packageInfo
	 * @return
	 * @author GengLong
	 * @date Aug 30, 2011 5:30:38 PM Modification History:
	 * 
	 */
	public static int queryVerCode(Context context, String packageInfo)
	{
		int verCode = -1;
		try
		{
			verCode = context.getPackageManager()
					.getPackageInfo(packageInfo, 0).versionCode;
		} catch (NameNotFoundException e)
		{
			return -1;
		}
		return verCode;
	}

	/**
	 * 判断是否是平板
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isTablet(Context context)
	{
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	public static String queryNowDate()
	{
		Time time = new Time();
		time.setToNow();
		return time.format("%Y-%m-%d");
	}

	public static String queryNowDateTime()
	{
		Time time = new Time();
		time.setToNow();
		return time.format("%Y-%m-%d %H:%M:%S");
	}

	public static String queryVerName(Context context, String packageInfo)
	{
		String verName = "";
		try
		{
			verName = context.getPackageManager()
					.getPackageInfo(packageInfo, 0).versionName;
		} catch (NameNotFoundException e)
		{
			return "";
		}
		return verName;
	}

	/**
	 * 是否数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str)
	{
		if (str.matches("^\\d+$|^\\d+\\.\\d+$|-\\d+$"))
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * 震动
	 * 
	 * @param context
	 */
	public static void Vibrator(Context context)
	{
		Vibrator vibrator = (Vibrator) context
				.getSystemService(context.VIBRATOR_SERVICE);
		long[] pattern = { 0, 100 }; // 停止 开启 停止 开启
		vibrator.vibrate(pattern, -1);
	}

	// public static void onStop()
	// {
	// super.onStop();
	// vibrator.cancel();
	// }
	public static String getRandomCode(int RandomMax)
	{
		Random rand = new Random();
		Date now = new Date();
		return (new StringBuilder(String.valueOf(Long.toString(now.getTime()))))
				.append(Integer.toString(rand.nextInt(RandomMax))).toString();
	}

	public static String getRandomCode()
	{
		return "5"+getRandomCode(1000);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int ConvDipToPx(Context context, float dpValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int ConvPxToDip(Context context, float pxValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 检测内存卡是否存在
	 */
	public static boolean isSdCardIsExsit()
	{
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	public static String encodeMD5(String str)
	{
		StringBuffer buf = new StringBuffer();

		MessageDigest md5 = null;
		try
		{
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e)
		{
		}
		md5.update(str.getBytes());
		byte[] bytes = md5.digest();
		for (int i = 0; i < bytes.length; i++)
		{
			String s = Integer.toHexString(bytes[i] & 0xFF);
			if (s.length() == 1)
			{
				buf.append("0");
			}
			buf.append(s);
		}

		return buf.toString();
	}

	// 将字符串转为时间戳

	public static long getTime(String user_time)
	{
		long re_time = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		Date d;
		try
		{

			d = sdf.parse(user_time);
			re_time = d.getTime();
			// String str = String.valueOf(l);
			// re_time = str.substring(0, 10);

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re_time;
	}

	// 将时间戳转为字符串
	public static String getStrTime(String cc_time)
	{
		String re_StrTime = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		// 例如：cc_time=1291778220
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));

		return re_StrTime;
	}
	
	public static String getLocalIpAddress()  
    {  
       return "127.0.0.1";
    }  
}
