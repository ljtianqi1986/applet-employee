package com.sk.meikelai.view;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.sk.meikelai.R;

/**
 * Created by lenovo on 2017/7/13.
 */

public class EasyLoadingMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.layout_loading_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
