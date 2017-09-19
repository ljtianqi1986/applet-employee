package com.sk.meikelai.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sk.meikelai.R;
import com.sk.meikelai.entity.CashCardBean;
import com.sk.meikelai.entity.PeopleBean;
import com.sk.meikelai.entity.ProductListBean;
import com.sk.meikelai.entity.ProjectListBean;
import com.sk.meikelai.utils.SPUtils;
import com.sk.meikelai.view.EditTextWithNumer;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.sk.meikelai.R.id.spinner_people;

/**
 * Created by sk on 2017/7/19.
 */

public class SelectedAllProjiectAdapter extends SwipeMenuAdapter<SelectedAllProjiectAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CashCardBean.ReturnDataBean> onceList;
    private ArrayList<ProjectListBean.ReturnDataBean> projectList;
    private ArrayList<ProductListBean.ReturnDataBean> productList;

    private List<PeopleBean.ReturnDataBean> pepList = new ArrayList<>();
     DecimalFormat dcmFmt = new DecimalFormat("0.00");


    public SelectedAllProjiectAdapter(Context context, ArrayList<CashCardBean.ReturnDataBean> onceList, ArrayList<ProjectListBean.ReturnDataBean> projectList
            , ArrayList<ProductListBean.ReturnDataBean> productList) {
        this.context = context;
        this.onceList = onceList;
        this.projectList = projectList;
        this.productList = productList;
        PeopleBean bean = JSONObject.parseObject((String) SPUtils.get(context, "people", ""), PeopleBean.class);
        pepList = bean.getReturn_data();
        PeopleBean.ReturnDataBean pep = new PeopleBean.ReturnDataBean();
        pep.setPerson_name("请选择");
        pep.setUser_code("-1");
        pepList.add(0,pep);
    }


    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_project, parent, false);
    }

    @Override
    public ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new ViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        Object bean = null;
        if (position >= 0 && position < onceList.size()) {
            bean = onceList.get(position);
        } else if (position >= onceList.size() && position < (projectList.size() + onceList.size())) {
            bean = projectList.get(position - onceList.size());
        } else if (position >= (projectList.size() + onceList.size()) && position < (productList.size() + onceList.size() + projectList.size())) {
            bean = productList.get(position - projectList.size() - onceList.size());
        }
        viewHolder.spinner.setAdapter(new SpinnerPeopleAdapter(context, pepList));
        //次年卡
        if (bean instanceof CashCardBean.ReturnDataBean) {
            CashCardBean.ReturnDataBean entity = (CashCardBean.ReturnDataBean) bean;
            viewHolder.item_img.setImageResource(R.mipmap.member);
            viewHolder.item_goods.setText(entity.getProject_name());
            viewHolder.tv_num.setVisibility(View.VISIBLE);
            viewHolder.ll_not_card.setVisibility(View.INVISIBLE);
            viewHolder.tv_num.setText("1次");
            viewHolder.checkBox.setVisibility(View.GONE);
            viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                    onceList.get(position).setPepCode(pepList.get(p).getUser_code());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //记录选中的技师
            if(onceList.get(position).getPepCode()!=null&&!onceList.get(position).getPepCode().equals("-1")){
                int p = 0;
                for(int i = 0 ; i < pepList.size() ; i++){
                    if(onceList.get(position).getPepCode().equals(pepList.get(i).getUser_code())){
                        p = i;
                        break;
                    }
                }
                viewHolder.spinner.setSelection(p);
            }
            //服务项目
        } else if (bean instanceof ProjectListBean.ReturnDataBean) {
            final ProjectListBean.ReturnDataBean entity = (ProjectListBean.ReturnDataBean) bean;
            viewHolder.checkBox.setVisibility(View.VISIBLE);
            if (entity.isDiscount()) {
                viewHolder.checkBox.setChecked(false);
            } else {
                viewHolder.checkBox.setChecked(true);
            }
            viewHolder.item_img.setImageResource(R.mipmap.project);
            viewHolder.item_goods.setText(entity.getName());
            viewHolder.tv_num.setVisibility(View.INVISIBLE);
            viewHolder.ll_not_card.setVisibility(View.VISIBLE);
            viewHolder.ed_price.setVisibility(View.VISIBLE);
            viewHolder.tv_price.setText("单价" + entity.getPrice() / 100f + "元");
            viewHolder.ed_price.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s != null && s.length() > 0) {
                        Float price = Float.parseFloat(s.toString());
                        int surePrice = (int) (price*1000/10);
                        projectList.get(position - onceList.size()).setActual_price(surePrice);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if(entity.getActual_price()>0){
                viewHolder.ed_price.setText(entity.getActual_price() / 100f + "");
            }else {
                viewHolder.ed_price.setText(entity.getPrice() / 100f + "");
            }
            viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                    projectList.get(position - onceList.size()).setPepCode(pepList.get(p).getUser_code());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //记录选中的技师
            if(projectList.get(position - onceList.size()).getPepCode()!=null&&!projectList.get(position - onceList.size()).getPepCode().equals("-1")){
                int p = 0;
                for(int i = 0 ; i < pepList.size() ; i++){
                    if(projectList.get(position - onceList.size()).getPepCode().equals(pepList.get(i).getUser_code())){
                        p = i;
                        break;
                    }
                }
                viewHolder.spinner.setSelection(p);
            }
            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewHolder.checkBox.isChecked()) {
                        projectList.get(position - onceList.size()).setDiscount(false);
                    } else {
                        projectList.get(position - onceList.size()).setDiscount(true);
                    }
                }
            });
            //产品类
        } else if (bean != null) {
            final ProductListBean.ReturnDataBean entity = (ProductListBean.ReturnDataBean) bean;
            viewHolder.checkBox.setVisibility(View.VISIBLE);
            viewHolder.item_img.setImageResource(R.mipmap.goods);
            viewHolder.item_goods.setText(entity.getName());
            viewHolder.tv_num.setVisibility(View.INVISIBLE);
            viewHolder.ll_not_card.setVisibility(View.VISIBLE);
            viewHolder.ed_price.setVisibility(View.VISIBLE);
            viewHolder.tv_price.setText("单价" + entity.getPrice() / 100f + "元");
            viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                    productList.get(position - onceList.size() - projectList.size()).setPepCode(pepList.get(p).getUser_code());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //记录选中的技师
            if(productList.get(position - onceList.size() - projectList.size()).getPepCode()!=null&&!productList.get(position - onceList.size() - projectList.size()).getPepCode().equals("-1")){
                int p = 0;
                for(int i = 0 ; i < pepList.size() ; i++){
                    if(productList.get(position - onceList.size() - projectList.size()).getPepCode().equals(pepList.get(i).getUser_code())){
                        p = i;
                        break;
                    }
                }
                viewHolder.spinner.setSelection(p);
            }

            if (entity.isDiscount()) {
                viewHolder.checkBox.setChecked(false);
            } else {
                viewHolder.checkBox.setChecked(true);
            }
            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewHolder.checkBox.isChecked()) {
                        productList.get(position - onceList.size() - projectList.size()).setDiscount(false);
                    } else {
                        productList.get(position - onceList.size() - projectList.size()).setDiscount(true);
                    }
                }
            });

            viewHolder.ed_price.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s != null && s.length() > 0) {
                        Float price = Float.parseFloat(s.toString());
                        int surePrice = (int) (price*1000/10);
                        productList.get(position - onceList.size() - projectList.size()).setActual_price(surePrice);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if(entity.getActual_price()>0){
                viewHolder.ed_price.setText(entity.getActual_price() / 100f + "");
            }else {
                viewHolder.ed_price.setText(entity.getPrice() / 100f + "");
            }
        }
    }

    @Override
    public int getItemCount() {
        return onceList.size() + projectList.size() + productList.size();
    }

    public class ViewHolder extends BaseViewHolder {

        ImageView item_img;
        TextView item_goods, tv_num, tv_price;
        Spinner spinner;
        CheckBox checkBox;
        EditTextWithNumer ed_price;
        LinearLayout ll_not_card;

        public ViewHolder(View itemView) {
            super(itemView);
            item_img = (ImageView) itemView.findViewById(R.id.item_img);
            item_goods = (TextView) itemView.findViewById(R.id.item_goods);
            tv_num = (TextView) itemView.findViewById(R.id.tv_num);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            spinner = (Spinner) itemView.findViewById(spinner_people);
            checkBox = (CheckBox) itemView.findViewById(R.id.is_discount);
            ed_price = (EditTextWithNumer) itemView.findViewById(R.id.ed_price);
            ll_not_card = (LinearLayout) itemView.findViewById(R.id.ll_not_card);
        }
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
