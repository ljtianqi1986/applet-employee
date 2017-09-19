package com.sk.meikelai.activity.mine;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.entity.WorkerInfoBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.view.LoadingDialog;
import com.sk.meikelai.view.StarBar;

import butterknife.BindView;

import static com.sk.meikelai.utils.StaticCode.SP_USER_DATA;


/**
 * 我的满意度
 */

public class ScoreActivity extends BaseActivity implements HandleDataCallBack {
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.star_bar)
    StarBar starBar;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.image_icon)
    ImageView imageIcon;
    LoadingDialog loadingDialog;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_score;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = MyUtils.getLoadingDialog(mContext, false);
        loadingDialog.show();
        WorkerInfoBean bean = JSONObject.parseObject((String) SPUtils.get(mContext, SP_USER_DATA, ""), WorkerInfoBean.class);
        AppApi.getMyScore(bean.getReturn_data().getUser_code(), this, 0);
    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        loadingDialog.cancelDialog();
        JSONObject jsonObject = JSON.parseObject(result);
        float score = jsonObject.getFloat("return_data");
        String remark = "";
        if (score <= 3) {
            remark = "~再接再励~";
            imageIcon.setImageResource(R.mipmap.sad_icon);
        } else if (score > 4) {
            remark = "非常优秀!";
            imageIcon.setImageResource(R.mipmap.happy_icon);
        } else {
            remark = "真棒!";
            imageIcon.setImageResource(R.mipmap.normal_icon);
        }
        tvTip.setText(remark);
        starBar.setStarMark(jsonObject.getFloat("return_data"));
        tvScore.setText(String.valueOf(jsonObject.getFloat("return_data")));
    }

    @Override
    public void handlerFailData(String failString, int tag) {
        loadingDialog.cancelDialog();

    }

    @Override
    public void handleErrorData(String error, int tag) {
        loadingDialog.cancelDialog();
    }
}
