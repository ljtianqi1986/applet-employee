package com.sk.meikelai.callback;

import com.sk.meikelai.entity.Worker;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */

public interface SelecterWorkerListener {
    void ensureSelect(List<Worker.DataBean> selectData);
}
