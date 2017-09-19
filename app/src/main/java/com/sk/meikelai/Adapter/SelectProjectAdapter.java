package com.sk.meikelai.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.CashCardBean;
import com.sk.meikelai.entity.PeopleBean;
import com.sk.meikelai.entity.ProductListBean;
import com.sk.meikelai.entity.ProjectListBean;
import com.sk.meikelai.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sk on 2017/7/19.
 */

public class SelectProjectAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CashCardBean.ReturnDataBean> onceList;
    private ArrayList<ProjectListBean.ReturnDataBean> projectList;
    private ArrayList<ProductListBean.ReturnDataBean> productList;
    private List<PeopleBean.ReturnDataBean> pepList = new ArrayList<>();

    public SelectProjectAdapter(Context context, ArrayList<CashCardBean.ReturnDataBean> onceList, ArrayList<ProjectListBean.ReturnDataBean> projectList
            , ArrayList<ProductListBean.ReturnDataBean> productList) {
        this.context = context;
        this.onceList = onceList;
        this.projectList = projectList;
        this.productList = productList;
        PeopleBean bean = JSONObject.parseObject((String) SPUtils.get(context, "people", ""), PeopleBean.class);
        pepList = bean.getReturn_data();
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
            return projectList.get(position - onceList.size());
        } else if (position >= (projectList.size() + onceList.size()) && position < (productList.size() + onceList.size() + projectList.size())) {
            return productList.get(position - onceList.size() - projectList.size());
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
            bean = projectList.get(position - onceList.size());
        } else if (position >= (projectList.size() + onceList.size()) && position < (productList.size() + onceList.size() + projectList.size())) {
            bean = productList.get(position - projectList.size() - onceList.size());
        }
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_has_selected, parent, false);
            viewHolder.item_img = (ImageView) convertView.findViewById(R.id.item_img);
            viewHolder.item_add = (ImageView) convertView.findViewById(R.id.item_add);
            viewHolder.item_reduce = (ImageView) convertView.findViewById(R.id.item_reduce);
            viewHolder.item_goods = (TextView) convertView.findViewById(R.id.item_goods);
            viewHolder.item_msg = (TextView) convertView.findViewById(R.id.item_msg);
            viewHolder.item_num = (TextView) convertView.findViewById(R.id.item_num);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.is_discount);
            viewHolder.spinner = (Spinner) convertView.findViewById(R.id.spinner_people);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.spinner.setAdapter(new SpinnerPeopleAdapter(context, pepList));
        if (bean instanceof CashCardBean.ReturnDataBean) {
            CashCardBean.ReturnDataBean entity = (CashCardBean.ReturnDataBean) bean;
            viewHolder.item_msg.setVisibility(View.VISIBLE);
            viewHolder.item_img.setImageResource(R.mipmap.member);
            viewHolder.item_goods.setText(entity.getProject_name());
            viewHolder.item_num.setText(String.valueOf(entity.getCount()));
            viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                    onceList.get(position).setPepCode(pepList.get(p).getUser_code());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            if (entity.getCount() > 0) {
                viewHolder.item_reduce.setVisibility(View.VISIBLE);
                viewHolder.item_add.setVisibility(View.VISIBLE);
            } else {
                viewHolder.item_reduce.setVisibility(View.INVISIBLE);
                viewHolder.item_add.setVisibility(View.VISIBLE);
            }
            viewHolder.item_reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = onceList.get(position).getCount();
                    if (count > 1) {
                        onceList.get(position).setCount(count - 1);
                        notifyDataSetChanged();
                    }
                    if (count == 1) {
                        onceList.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });
            viewHolder.item_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = onceList.get(position).getCount();
                    onceList.get(position).setCount(count + 1);
                    notifyDataSetChanged();
                }
            });
        } else if (bean instanceof ProjectListBean.ReturnDataBean) {
            ProjectListBean.ReturnDataBean entity = (ProjectListBean.ReturnDataBean) bean;
            viewHolder.item_msg.setText(entity.getPrice() / 100f + "元");
            viewHolder.item_msg.setVisibility(View.VISIBLE);
            viewHolder.item_img.setImageResource(R.mipmap.member);
            viewHolder.item_goods.setText(entity.getName());
            viewHolder.item_num.setText(String.valueOf(entity.getCount()));
            viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                    projectList.get(position-onceList.size()).setPepCode(pepList.get(p).getUser_code());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            if (entity.isDiscount()) {
                viewHolder.checkBox.setChecked(false);
            } else {
                viewHolder.checkBox.setChecked(true);
            }
            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalViewHolder.checkBox.isChecked()) {
                        projectList.get(position - onceList.size()).setDiscount(false);
                        notifyDataSetChanged();
                    } else {
                        projectList.get(position - onceList.size()).setDiscount(true);
                        notifyDataSetChanged();
                    }
                }
            });
            if (entity.getCount() > 0) {
                viewHolder.item_reduce.setVisibility(View.VISIBLE);
                viewHolder.item_add.setVisibility(View.VISIBLE);
            } else {
                viewHolder.item_reduce.setVisibility(View.INVISIBLE);
                viewHolder.item_add.setVisibility(View.VISIBLE);
            }
            viewHolder.item_reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = projectList.get(position - onceList.size()).getCount();
                    if (count > 1) {
                        projectList.get(position - onceList.size()).setCount(count - 1);
                        notifyDataSetChanged();
                    }
                    if (count == 1) {
                        projectList.remove(position - onceList.size());
                        notifyDataSetChanged();
                    }
                }
            });
            viewHolder.item_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = projectList.get(position - onceList.size()).getCount();
                    projectList.get(position - onceList.size()).setCount(count + 1);
                    notifyDataSetChanged();
                }
            });
        } else if (bean != null) {
            ProductListBean.ReturnDataBean entity = (ProductListBean.ReturnDataBean) bean;
            viewHolder.item_msg.setText(entity.getPrice() / 100f + "元");
            viewHolder.item_img.setImageResource(R.mipmap.member);
            viewHolder.item_goods.setText(entity.getName());
            viewHolder.item_num.setText(String.valueOf(entity.getCount()));
            viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                    productList.get(position-onceList.size()-projectList.size()).setPepCode(pepList.get(p).getUser_code());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            if (entity.isDiscount()) {
                viewHolder.checkBox.setChecked(false);
            } else {
                viewHolder.checkBox.setChecked(true);
            }
            final ViewHolder finalViewHolder1 = viewHolder;
            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalViewHolder1.checkBox.isChecked()) {
                        productList.get(position - onceList.size() - projectList.size()).setDiscount(false);
                        notifyDataSetChanged();
                    } else {
                        productList.get(position - onceList.size() - projectList.size()).setDiscount(true);
                        notifyDataSetChanged();
                    }
                }
            });
            if (entity.getCount() > 0) {
                viewHolder.item_reduce.setVisibility(View.VISIBLE);
                viewHolder.item_add.setVisibility(View.VISIBLE);
            } else {
                viewHolder.item_reduce.setVisibility(View.INVISIBLE);
                viewHolder.item_add.setVisibility(View.VISIBLE);
            }
            viewHolder.item_reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = productList.get(position - onceList.size() - projectList.size()).getCount();
                    if (count > 1) {
                        productList.get(position - onceList.size() - projectList.size()).setCount(count - 1);
                        notifyDataSetChanged();
                    }
                    if (count == 1) {
                        productList.remove(position - onceList.size() - projectList.size());
                        notifyDataSetChanged();
                    }
                }
            });
            viewHolder.item_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = productList.get(position - onceList.size() - projectList.size()).getCount();
                    productList.get(position - onceList.size() - projectList.size()).setCount(count + 1);
                    notifyDataSetChanged();
                }
            });
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView item_img, item_add, item_reduce;
        TextView item_goods, item_msg, item_num;
        CheckBox checkBox;
        Spinner spinner;
    }

    public ArrayList<CashCardBean.ReturnDataBean> getOnceList() {
        return onceList;
    }

    public ArrayList<ProjectListBean.ReturnDataBean> getProjectList() {
        return projectList;
    }

    public ArrayList<ProductListBean.ReturnDataBean> getProductList() {
        return productList;
    }

}
