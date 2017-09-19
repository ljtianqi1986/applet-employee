package com.sk.meikelai.activity.mine;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.activity.main.LoadActivity;
import com.sk.meikelai.dialog.UpDateDialogFragment;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.ToastUtils;

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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/4.
 */

public class UpdateActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.un_update_new)
    TextView mUnUpdateNew;
    @BindView(R.id.un_update)
    LinearLayout mUnUpdate;
    @BindView(R.id.update_now)
    TextView mUpdateBefore;
    @BindView(R.id.update_new)
    TextView mUpdateNew;
    @BindView(R.id.update)
    LinearLayout mUpdate;
    @BindView(R.id.update_msg)
    TextView mUpdateMsg;
    @BindView(R.id.btn_update)
    TextView mBtnUpdate;
    private UpDateDialogFragment upDateDialogFragment;
    private static final String BASE_URL = "https://mj.pay17.cn/mj/mj_app.apk";
    private static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "mj_app.apk";

    @Override
    protected int getContentView() {
        return R.layout.activity_update;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getAreaText();
            }
        }).start();
    }

    //获取地区txt文本
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
            UpdateActivity.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    JSONObject jsonObject = JSON.parseObject(sb.toString());
                    String updateCode = jsonObject.getString("version_code");
                    String updateName = jsonObject.getString("version_name");
                    String updateMsg = jsonObject.getString("version_info");
                    updateMsg = updateMsg.replace('|', '\n');
                    String versionCode = MyUtils.getVersionCode(UpdateActivity.this);
                    String versionName = MyUtils.getVersionName(UpdateActivity.this);
                    mUpdateBefore.setText("当前版本号：V" + versionName);

                    if (Integer.valueOf(updateCode) > Integer.valueOf(versionCode)) {
                        mUnUpdate.setVisibility(View.GONE);
                        mUpdate.setVisibility(View.VISIBLE);
                        mUpdateNew.setText("发现新版本：V" + updateName);
                        mUpdateMsg.setText(updateMsg);
                    } else {
                        mUnUpdate.setVisibility(View.VISIBLE);
                        mUpdate.setVisibility(View.GONE);
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

    @OnClick({R.id.back, R.id.btn_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_update:
                upDateDialogFragment = new UpDateDialogFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(upDateDialogFragment, this.getClass().getSimpleName());
                ft.commitAllowingStateLoss();//注意这里使用commitAllowingStateLoss()
                downloadFile(BASE_URL, BASE_PATH);
                break;
        }
    }

    private void downloadFile(final String url, String path) {
        RequestParams requestParams = new RequestParams(url);
        requestParams.setSaveFilePath(path);
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
                UpdateActivity.this.startActivity(intent);
                android.os.Process.killProcess(android.os.Process.myPid());
                upDateDialogFragment.dismissAllowingStateLoss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                Toast.makeText(UpdateActivity.this, "下载失败，请检查网络和SD卡", Toast.LENGTH_SHORT).show();
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
}
