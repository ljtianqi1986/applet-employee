package com.sk.meikelai.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.sk.meikelai.R;
import com.sk.meikelai.callback.SelectWorkerCallback;
import com.sk.meikelai.entity.WorkerBean;

import java.util.List;

/**
 * 储值卡item页面
 */

public class SelectWorkerAdapter extends BaseAdapter {


    private Context mContext;
    private List<WorkerBean.ReturnDataBean> lists;
    private LayoutInflater mInflater;
    private SelectWorkerCallback mCallback;

    public SelectWorkerAdapter(Context context, List<WorkerBean.ReturnDataBean> lists, SelectWorkerCallback callback) {
        mContext = context;
        this.lists = lists;
        mCallback = callback;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_select_worker, parent, false);
            holder.mRadioButton = (CheckBox) convertView.findViewById(R.id.item_username);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mRadioButton.setText(lists.get(position).getPerson_name());
        holder.mRadioButton.setChecked(false);

        if (lists.get(position).isSelected()) {
            holder.mRadioButton.setChecked(true);
        } else {
            holder.mRadioButton.setChecked(false);
        }
        final ViewHolder finalHolder = holder;

        finalHolder.mRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!finalHolder.mRadioButton.isChecked()) {
                    finalHolder.mRadioButton.setChecked(false);
                    lists.get(position).setSelected(false);
                } else {
                    finalHolder.mRadioButton.setChecked(true);
                    lists.get(position).setSelected(true);
                }
                mCallback.selectWorker(finalHolder.mRadioButton.isChecked(), lists.get(position));
            }
        });

        return convertView;
    }

    class ViewHolder {
        CheckBox mRadioButton;
    }

}
