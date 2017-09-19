package com.sk.meikelai.activity.card;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;
import com.sk.meikelai.callback.ExitCallBack;
import com.sk.meikelai.dialog.ExitDialogFragment;
import com.sk.meikelai.entity.FirstEvent;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.utils.StaticCode;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.sk.meikelai.activity.recharge.ScanCodeActivity.SP_VIP_DATA;

/**
 * Created by sk on 2017/6/5.
 */

public class CardFailActivity extends BaseActivity{


    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.go_card_f)
    TextView mGoCardF;
    @BindView(R.id.quit_account_f)
    TextView mQuitAccountF;

    @Override
    protected int getContentView() {
        return R.layout.fragment_card_fail;
    }

    @OnClick({R.id.back, R.id.go_card_f, R.id.quit_account_f})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.go_card_f:
                EventBus.getDefault().post(new FirstEvent("Goto_Card_Info"));
                finish();
                break;
            case R.id.quit_account_f:
                SPUtils.remove(mContext, StaticCode.SP_VIP_DATA);
                EventBus.getDefault().post(new FirstEvent("Goto_Card"));
                finish();
                break;
        }
    }

}
