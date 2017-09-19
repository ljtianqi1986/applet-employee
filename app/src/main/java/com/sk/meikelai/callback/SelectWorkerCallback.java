package com.sk.meikelai.callback;

import com.sk.meikelai.entity.WorkerBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */

public interface SelectWorkerCallback {

    void selectWorker(boolean isSelected, WorkerBean.ReturnDataBean dataBeen);

}
