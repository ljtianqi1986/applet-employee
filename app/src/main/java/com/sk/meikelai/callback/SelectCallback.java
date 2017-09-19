package com.sk.meikelai.callback;

import com.sk.meikelai.entity.Worker;
import com.sk.meikelai.entity.WorkerBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */

public interface SelectCallback {

    void changeList(List<WorkerBean.ReturnDataBean> dataBeen);

}
