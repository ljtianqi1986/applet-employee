package com.sk.meikelai.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;

import butterknife.OnClick;

/**
 * 今日现金交接申报
 */

public class HistoryActivity extends BaseActivity implements View.OnClickListener{
    @Override
    protected int getContentView() {
        return R.layout.activity_history;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitleBar();

    }
    protected void initTitleBar() {
        TextView back = (TextView) findViewById(R.id.title_bar_back);
        TextView title = (TextView) findViewById(R.id.title_bar_title);
        title.setText(getResources().getString(R.string.mine_today_handover));
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.title_bar_back://点击返回
                finish();
                break;
        }
    }

}
