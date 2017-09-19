package com.sk.meikelai.activity.mine;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sk on 2017/8/4.
 */

public class PreviewActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.preview_img)
    ImageView previewImg;

    private Uri uri;

    @Override
    protected int getContentView() {
        return R.layout.activity_preview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uri = getIntent().getParcelableExtra("uri");
        Glide.with(mContext).load(uri).into(previewImg);

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
