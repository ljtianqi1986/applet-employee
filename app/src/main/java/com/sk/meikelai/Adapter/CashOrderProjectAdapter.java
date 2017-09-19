package com.sk.meikelai.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sk.meikelai.R;
import com.sk.meikelai.entity.CashCardBean;
import com.sk.meikelai.entity.ProductListBean;
import com.sk.meikelai.entity.ProjectListBean;

import java.util.ArrayList;

/**
 * Created by sk on 2017/7/19.
 */
public class CashOrderProjectAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<CashCardBean.ReturnDataBean> onceList;
    private ArrayList<ProjectListBean.ReturnDataBean> projectList;
    private ArrayList<ProductListBean.ReturnDataBean> productList;

    public CashOrderProjectAdapter(Context context, ArrayList<CashCardBean.ReturnDataBean> onceList, ArrayList<ProjectListBean.ReturnDataBean> projectList
            , ArrayList<ProductListBean.ReturnDataBean> productList) {
        this.context = context;
        this.onceList = onceList;
        this.projectList = projectList;
        this.productList = productList;

    }


    @Override
    public int getCount() {
        return onceList.size() + projectList.size() + productList.size();
    }

    @Override
    public Object getItem(int position) {
        if (position >= 0 && position < onceList.size()) {
            return onceList.get(position);
        } else if (position >= onceList.size() && position < (projectList.size() + onceList.size())) {
            return projectList.get(position-onceList.size());
        } else if (position >= (projectList.size() + onceList.size()) && position < (productList.size() + onceList.size() + projectList.size())) {
            return productList.get(position-onceList.size()-projectList.size());
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        Object bean = null;
        if (position >= 0 && position < onceList.size()) {
            bean = onceList.get(position);
        } else if (position >= onceList.size() && position < (projectList.size() + onceList.size())) {
            bean = projectList.get(position-onceList.size());
        } else if (position >= (projectList.size() + onceList.size()) && position < (productList.size() + onceList.size() + projectList.size())) {
            bean = productList.get(position-onceList.size()-projectList.size());
        }
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pay_select_card, parent, false);
            viewHolder.item_img = (ImageView) convertView.findViewById(R.id.item_img);
            viewHolder.card_type = (ImageView) convertView.findViewById(R.id.card_type);
            viewHolder.item_goods = (TextView) convertView.findViewById(R.id.item_goods);
            viewHolder.item_price = (TextView) convertView.findViewById(R.id.item_price);
            viewHolder.item_money = (TextView) convertView.findViewById(R.id.item_money);
            viewHolder.is_discount = (TextView) convertView.findViewById(R.id.is_discount);
            viewHolder.item_num = (TextView) convertView.findViewById(R.id.item_num);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (bean instanceof CashCardBean.ReturnDataBean) {
            CashCardBean.ReturnDataBean entity = (CashCardBean.ReturnDataBean) bean;
            viewHolder.item_img.setImageResource(R.mipmap.member);
            viewHolder.item_goods.setText(entity.getProject_name());
            viewHolder.item_price.setText("单价:"+entity.getProject_price()/100f);
            viewHolder.item_money.setText(String.valueOf(entity.getProject_price()/100f));
            viewHolder.item_num.setText("数量:"+1);
            viewHolder.card_type.setVisibility(View.VISIBLE);

            if(entity.getCardType() == 0){
                viewHolder.card_type.setBackgroundResource(R.mipmap.cash_once);
            }else {
                viewHolder.card_type.setBackgroundResource(R.mipmap.cash_year);
            }
            viewHolder.is_discount.setVisibility(View.GONE);
        } else if (bean instanceof ProjectListBean.ReturnDataBean) {
            ProjectListBean.ReturnDataBean entity = (ProjectListBean.ReturnDataBean) bean;
            viewHolder.item_img.setImageResource(R.mipmap.project);
            viewHolder.item_goods.setText(entity.getName());
            viewHolder.item_price.setText("单价:"+entity.getPrice()/100f);
            viewHolder.item_money.setText(String.valueOf(entity.getActual_price()/100f));
            viewHolder.item_num.setText("数量:"+1);
            viewHolder.card_type.setVisibility(View.INVISIBLE);
            if(entity.isDiscount()){
                viewHolder.is_discount.setVisibility(View.INVISIBLE);
            }else {
                viewHolder.is_discount.setVisibility(View.VISIBLE);
            }
        } else if (bean != null) {
            ProductListBean.ReturnDataBean entity = (ProductListBean.ReturnDataBean) bean;
            viewHolder.item_img.setImageResource(R.mipmap.goods);
            viewHolder.item_goods.setText(entity.getName());
            viewHolder.item_price.setText("单价:"+entity.getPrice()/100f);
            viewHolder.item_money.setText(String.valueOf(entity.getActual_price()/100f));
            viewHolder.item_num.setText("数量:"+1);
            viewHolder.card_type.setVisibility(View.INVISIBLE);
            if(entity.isDiscount()){
                viewHolder.is_discount.setVisibility(View.INVISIBLE);
            }else {
                viewHolder.is_discount.setVisibility(View.VISIBLE);
            }
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView item_img, card_type;
        TextView item_goods, item_price,item_money, item_num,is_discount;
    }

}
