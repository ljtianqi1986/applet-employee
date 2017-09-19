package com.sk.meikelai.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sk.meikelai.Adapter.RechargeSelectCardAdapter;
import com.sk.meikelai.R;

import org.xutils.common.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 选择升级到卡的类型
 */
public class DialogCardFragment extends DialogFragment implements  BaseQuickAdapter
        .RequestLoadMoreListener {


    public static final String TAG = DialogCardFragment.class.getSimpleName();

    List<String> items = new ArrayList<>();

    String selected = "1";

    int position = 0;

    String mTitle;
    @BindView(R.id.cancel)
    TextView mCancel;
    @BindView(R.id.rc_content)
    RecyclerView mRcContent;
    Unbinder unbinder;
    RechargeSelectCardAdapter mAdapter;
    public DialogCardFragment() {
    }

    public static DialogCardFragment newInstance(ArrayList<String> list) {
        DialogCardFragment fragment = new DialogCardFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("list",list);
//        bundle.putString("list", JSON.toJSONString(list));
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            items.clear();
            items = arguments.getStringArrayList("list");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dialog_card, container, false);

        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }


    private void initView() {
        mRcContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RechargeSelectCardAdapter(R.layout.item_select_card, items);
        mRcContent.setAdapter(mAdapter);
        mRcContent.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                selected= String.valueOf(position);
                mAdapter.notifyDataSetChanged();
                mOnCardSetListener.onCardSet(selected);
                dismiss();
            }
         });
    }
    @Override
    public void onResume() {
        super.onResume();
    }




    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, DensityUtil.dip2px(300));
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ffffff")));
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        getDialog().getWindow().setAttributes(layoutParams);
    }

    @OnClick({R.id.cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //确定按钮回调接口
    private OnCardSetListener mOnCardSetListener;

    public void setOnCardSetListener(OnCardSetListener mOnCardSetListener) {
        this.mOnCardSetListener = mOnCardSetListener;
    }

    @Override
    public void onLoadMoreRequested() {

    }

    public interface OnCardSetListener {
        void onCardSet(String card);
    }
}
