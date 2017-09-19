package com.sk.meikelai.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sk.meikelai.R;
import com.sk.meikelai.entity.PeopleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sk on 2017/7/19.
 */

public class SpinnerPeopleAdapter extends BaseAdapter {

    private Context context;
    private List<PeopleBean.ReturnDataBean> dataBeanList = new ArrayList<>();

    public SpinnerPeopleAdapter(Context context, List<PeopleBean.ReturnDataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
    }

    @Override
    public int getCount() {
        return dataBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_spinner,parent,false);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if(position == 0){
            holder.tv_name.setTextColor(Color.parseColor("#000000"));
        }
        holder.tv_name.setText(dataBeanList.get(position).getPerson_name());
        return convertView;
    }

    private class ViewHolder{
        TextView tv_name;
    }
}
