package com.sk.meikelai.activity.mine.fragment;

import android.graphics.Color;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.sk.meikelai.R;
import com.sk.meikelai.activity.base.BaseFragment;
import com.sk.meikelai.entity.CashBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by sk on 2017/7/10.
 */

public class LastMonthNewRecepitsFragment extends BaseFragment implements OnChartValueSelectedListener, HandleDataCallBack {

    @BindView(R.id.mPieChart)
    PieChart mPieChart;
    @BindView(R.id.recharge_cash)
    TextView rechargeCash;
    @BindView(R.id.open_card_cash)
    TextView openCardCash;
    @BindView(R.id.product_cash)
    TextView productCash;
    @BindView(R.id.project_cash)
    TextView projectCash;

    @Override
    protected int getContentView() {
        return R.layout.fragment_net_recepits;
    }

    @Override
    public void initView() {
        //饼状图
        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5, 10, 5, 5);

        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        mPieChart.setDrawEntryLabels(true);
        mPieChart.setUsePercentValues(true);
        //设置中间文件
        mPieChart.setCenterText("上月实收");

        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);

        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);

        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(true);

        mPieChart.setRotationAngle(0);
        // 触摸旋转
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);

        //变化监听
        mPieChart.setOnChartValueSelectedListener(this);

        AppApi.getCashStatistics(MyUtils.getCode(mContext), "3", this, 0);


    }


    //设置数据
    private void setData(ArrayList<PieEntry> entries, ArrayList<Integer> colors) {
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mPieChart.setData(data);
        mPieChart.highlightValues(null);
        //刷新
        mPieChart.invalidate();
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        CashBean cashBean = JSONObject.parseObject(result, CashBean.class);
        setData(cashBean);
        int total = cashBean.getReturn_data_cpss() + cashBean.getReturn_data_czss() + cashBean.getReturn_data_kkss() + cashBean.getReturn_data_xmss();
        int i = total / 100;
        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        if (cashBean.getReturn_data_czss() > 0) {
            entries.add(new PieEntry(cashBean.getReturn_data_czss() / i, "充值实收"));
            colors.add(ColorTemplate.rgb("#7ed6ff"));
        }
        if (cashBean.getReturn_data_kkss() > 0) {
            entries.add(new PieEntry(cashBean.getReturn_data_kkss() / i, "开卡实收"));
            colors.add(ColorTemplate.rgb("#ff777a"));
        }
        if (cashBean.getReturn_data_cpss() > 0) {
            entries.add(new PieEntry(cashBean.getReturn_data_cpss() / i, "产品实收"));
            colors.add(ColorTemplate.rgb("#ffd972"));
        }
        if (cashBean.getReturn_data_xmss() > 0) {
            entries.add(new PieEntry(cashBean.getReturn_data_xmss() / i, "项目实收"));
            colors.add(ColorTemplate.rgb("#b197fc"));
        }
        setData(entries, colors);

        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // 输入标签样式
        mPieChart.setEntryLabelColor(Color.WHITE);
        mPieChart.setEntryLabelTextSize(12f);


    }

    @Override
    public void handlerFailData(String failString, int tag) {

    }

    @Override
    public void handleErrorData(String error, int tag) {

    }

    private void setData(CashBean cashBean) {
        rechargeCash.setText(String.valueOf(cashBean.getReturn_data_czss() / 100f));
        openCardCash.setText(String.valueOf(cashBean.getReturn_data_kkss() / 100f));
        productCash.setText(String.valueOf(cashBean.getReturn_data_cpss() / 100f));
        projectCash.setText(String.valueOf(cashBean.getReturn_data_xmss() / 100f));
    }

}
