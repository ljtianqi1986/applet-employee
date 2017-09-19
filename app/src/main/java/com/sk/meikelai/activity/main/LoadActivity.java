package com.sk.meikelai.activity.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.cash.FullActivity;
import com.sk.meikelai.activity.mine.WorkerLoginActivity;
import com.sk.meikelai.dialog.UpDateDialogFragment;
import com.sk.meikelai.entity.WorkerInfoBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.DeviceUtils;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.NetUtil;
import com.sk.meikelai.utils.SPUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;

import static com.sk.meikelai.utils.StaticCode.SP_USER_DATA;

/**
 * Created by sk on 2017/6/27.
 */

public class LoadActivity extends FullActivity implements HandleDataCallBack {
    @BindView(R.id.proLoad)
    ProgressBar proLoad;
    @BindView(R.id.tv_alert)
    TextView tvAlert;
    UpDateDialogFragment upDateDialogFragment;
    //设备信息
    String device_info = "";
    String device_ip = "";
    String device_ver = "";
    private static final String BASE_URL = "https://mj.pay17.cn/mj/mj_app.apk";
    private String path = "";

    @Override
    protected int getContentView() {
        return R.layout.activity_load;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucent(this);
        path = getSDPath() + "/meikelai/";
        device_info = DeviceUtils.getDeviceInfo(mContext);
        device_ip = DeviceUtils.getIp();
        device_ver = DeviceUtils.getVersionCode(mContext);
        if (DeviceUtils.haveRoot()) {
            AlertDialog dialog = new AlertDialog.Builder(LoadActivity.this)
                    .setTitle("安全提示")
                    .setMessage("检测到该设备已经ROOT,存在安全隐患,确定继续使用吗?")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            doLoad();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
        } else {
            doLoad();
        }
    }

    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
            return sdDir.toString();
        } else {
            return "";
        }

    }

    private void doLoad() {
        // TODO Auto-generated method stub
        tvAlert.setText("正在检测网络!");
        if (NetUtil.isNetWorkConection(LoadActivity.this)) {
            tvAlert.setText("正在连接服务器...");
            update();
        } else {
            Toast.makeText(mContext, "无法连接到网络,请检查网络设置!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkIsAutoLogin() {
        int is_auto_login = (int) SPUtils.get(mContext, "is_auto_login", 0);
        if (is_auto_login == 1) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void handlerSuccessData(String result, int tag) {
        if (tag == 0) {
            SPUtils.put(mContext, "people", result);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (checkIsAutoLogin()) {
                        WorkerInfoBean workerInfoBean = JSON.parseObject((String) SPUtils.get(mContext, SP_USER_DATA, ""), WorkerInfoBean
                                .class);
                        if (workerInfoBean != null) {
                            AppApi.autogin(workerInfoBean.getReturn_data().getLogin_name(), workerInfoBean.getReturn_data().getPwd(),
                                    device_ver, device_info, device_ip, LoadActivity.this, 1);
                        } else {
                            Intent intent = new Intent(mContext, WorkerLoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Intent intent = new Intent(mContext, WorkerLoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 800);
        } else {
            SPUtils.put(mContext, SP_USER_DATA, result);
            WorkerInfoBean bean = JSON.parseObject(result, WorkerInfoBean.class);
            SPUtils.put(mContext, "head", bean.getReturn_data().getPic_url());

            Toast.makeText(mContext, "自动登陆成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void handlerFailData(String failString, int tag) {
        Intent intent = new Intent(mContext, WorkerLoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void handleErrorData(String error, int tag) {
        Intent intent = new Intent(mContext, WorkerLoginActivity.class);
        startActivity(intent);
        finish();
    }

    public static void setTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public void update() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getAreaText();
            }
        }).start();
    }

    private boolean getAreaText() {
        String urlStr = "https://mj.pay17.cn/mj/app_update.txt";
        //long a = System.currentTimeMillis();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(60 * 1000);
            conn.setReadTimeout(60 * 1000);
            // 取得inputStream，并进行读取
            InputStream input = conn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String line = null;
            final StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    JSONObject jsonObject = JSON.parseObject(sb.toString());
                    String updateCode = jsonObject.getString("version_code");
                    String versionCode = MyUtils.getVersionCode(LoadActivity.this);
                    if (Integer.valueOf(updateCode) > Integer.valueOf(versionCode)) {
                        File dir = new File(path);
                        boolean isCreate = false;
                        if (!dir.exists()) {
                            isCreate = dir.mkdirs();
                        } else {
                            isCreate = true;
                        }
                        if (isCreate) {
                            upDateDialogFragment = new UpDateDialogFragment();
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.add(upDateDialogFragment, this.getClass().getSimpleName());
                            ft.commitAllowingStateLoss();//注意这里使用commitAllowingStateLoss()
                            downloadFile(BASE_URL, path);
                        }
                    } else {
                        LoadUI();
                    }
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private void downloadFile(final String url, String path) {
        RequestParams requestParams = new RequestParams(url);
        requestParams.setSaveFilePath(path + "mj_app.apk");
        x.http().get(requestParams, new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {
            }

            @Override
            public void onStarted() {
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                int now = (int) (current * 100 / total);
                upDateDialogFragment.setProcess(now + "%");
            }

            @Override
            public void onSuccess(File result) {
                //apk下载完成后，调用系统的安装方法
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
                LoadActivity.this.startActivity(intent);
                android.os.Process.killProcess(android.os.Process.myPid());
                upDateDialogFragment.dismissAllowingStateLoss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                Log.v("onError", ex.toString() + ex.getMessage() + "error");
                Toast.makeText(LoadActivity.this, ex.toString() + ex.getMessage() + "error", Toast.LENGTH_SHORT).show();
                upDateDialogFragment.dismissAllowingStateLoss();
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }


    private void LoadUI() {
        if (SPUtils.contains(mContext, SP_USER_DATA)) {
            AppApi.getWorkerList(MyUtils.getShopCode(mContext), LoadActivity.this, 0);
        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (checkIsAutoLogin()) {
                        WorkerInfoBean workerInfoBean = JSON.parseObject((String) SPUtils.get(mContext, SP_USER_DATA, ""),
                                WorkerInfoBean.class);
                        if (workerInfoBean != null) {
                            AppApi.autogin(workerInfoBean.getReturn_data().getLogin_name(), workerInfoBean.getReturn_data().getPwd(),
                                    device_ver, device_info, device_ip, LoadActivity.this, 1);
                        } else {
                            Intent intent = new Intent(mContext, WorkerLoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Intent intent = new Intent(mContext, WorkerLoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 1000);
        }
    }
}
