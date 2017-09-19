package com.sk.meikelai.dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.sk.meikelai.Adapter.SelectWorkerAdapter;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.card.ConfirmActivity;
import com.sk.meikelai.activity.main.MainActivity;
import com.sk.meikelai.activity.recharge.RechargeCardFragment;
import com.sk.meikelai.callback.SelectWorkerCallback;
import com.sk.meikelai.callback.SureSelectedCallBack;
import com.sk.meikelai.entity.WorkerBean;
import com.sk.meikelai.utils.SPUtils;

import org.xutils.common.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class DialogSelectWorkerFragment extends DialogFragment implements SelectWorkerCallback {
    public static final String TAG = DialogSelectWorkerFragment.class.getSimpleName();
    public static final String DEFAULT_VALUE_TAG = "default_tag_value";
    @BindView(R.id.cancel)
    TextView mCancel;
    @BindView(R.id.sure)
    TextView mSure;
    @BindView(R.id.girdView)
    GridView mRcContent;
    Unbinder unbinder;
    SelectWorkerAdapter mAdapter;
    String name = "";
    private SureSelectedCallBack sureSelectedCallBack;

    public void setSureSelectedCallBack(SureSelectedCallBack sureSelectedCallBack) {
        this.sureSelectedCallBack = sureSelectedCallBack;
    }

    private List<WorkerBean.ReturnDataBean> dataList = new ArrayList<>();

    //选中的技师
    private List<WorkerBean.ReturnDataBean> selectDataList = new ArrayList<>();


    public static DialogSelectWorkerFragment newInstance(int defaultValue) {
        Bundle args = new Bundle();
        DialogSelectWorkerFragment fragment = new DialogSelectWorkerFragment();
        args.putInt(DEFAULT_VALUE_TAG, defaultValue);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int style = DialogFragment.STYLE_NO_TITLE, theme = 0;
        setStyle(style, theme);
        initWorkerList();
        Bundle data = getArguments();//获得从activity中传递过来的值
        selectDataList.clear();
        data.getString("name");
        name = data.getString("name");
        if (name != null) {
            if (name.equals("confirm")) {
                ConfirmActivity confirmActivity = (ConfirmActivity) getActivity();
                selectDataList.addAll(confirmActivity.getSelectWorkList());
            } else {
                MainActivity mainActivity = (MainActivity) getActivity();
                RechargeCardFragment rechargeCardFragment = new RechargeCardFragment();
                RechargeCardFragment fragment = (RechargeCardFragment) mainActivity.getSupportFragmentManager().findFragmentByTag
                        (rechargeCardFragment.getClass().getSimpleName());
                selectDataList.addAll(fragment.getSelectWorkList());
            }
        }
        Log.v("DialogSelect",selectDataList.size()+"");
        //找出选中的技师
        for (int i = 0; i < selectDataList.size(); i++) {
            for (int k = 0; k < dataList.size(); k++) {
                if(selectDataList.get(i).getUser_code().equals(dataList.get(k).getUser_code())){
                    dataList.get(k).setSelected(true);
                }
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dialog_worker, container, false);

        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mAdapter = new SelectWorkerAdapter(getActivity(), dataList, DialogSelectWorkerFragment.this);
        mRcContent.setAdapter(mAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, DensityUtil.dip2px(300));
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        getDialog().getWindow().setAttributes(layoutParams);
    }

    @OnClick({R.id.cancel, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.sure:
                sureSelectedCallBack.sureSelect(selectDataList);
                dismiss();
                break;
        }
    }

    private void initWorkerList() {
        WorkerBean bean = JSONObject.parseObject((String) SPUtils.get(getActivity(), "people", ""), WorkerBean.class);
        if (bean.getReturn_data() == null) {
            return;
        } else {
            List<WorkerBean.ReturnDataBean> dataBeanList = bean.getReturn_data();
            dataList.addAll(dataBeanList);
        }
    }

    @Override
    public void selectWorker(boolean isSelected, WorkerBean.ReturnDataBean dataBeen) {
        Log.v("selectWorker",dataBeen.toString());
        if(isSelected){
            selectDataList.add(dataBeen);
        }else {
            Integer p = null;
            for(int i = 0 ;i <selectDataList.size() ;i++){
                if(dataBeen.getUser_code().equals(selectDataList.get(i).getUser_code())){
                    p = i;
                    break;
                }
            }
            if(p!=null){
                selectDataList.remove((int)p);
            }
        }
    }
}
