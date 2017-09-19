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
import com.sk.meikelai.entity.AchievementBean;
import com.sk.meikelai.utils.AppApi;
import com.sk.meikelai.utils.HandleDataCallBack;
import com.sk.meikelai.utils.MyUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by sk on 2017/7/10.
 */

public class TodayAchievementFragment extends BaseFragment implements OnChartValueSelectedListener, HandleDataCallBack {
    @BindView(R.id.mPieChart)
    PieChart mPieChart;
    @BindView(R.id.open_card_money)
    TextView openCardMoney;
    @BindView(R.id.recharge_money)
    TextView rechargeMoney;
    @BindView(R.id.stored_money)
    TextView storedMoney;
    @BindView(R.id.once_money)
    TextView onceMoney;
    @BindView(R.id.year_card_money)
    TextView yearCardMoney;
    @BindView(R.id.product_money)
    TextView productMoney;


    @Override
    protected int getContentView() {
        return R.layout.fragment_achievement_cash;
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
        mPieChart.setCenterText("今日业绩");

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

        AppApi.getAchievementStatistics(MyUtils.getCode(mContext), "1", this, 0);

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void handlerSuccessData(String result, int tag) {
        AchievementBean bean = JSONObject.parseObject(result,AchievementBean.class);
        setMoneyData(bean);
        int total = bean.getReturn_data_cikcs()+bean.getReturn_data_kkyj()+bean.getReturn_data_czyj()+bean.getReturn_data_nkcs()+bean.getReturn_data_xfyj()+bean.getReturn_data_cpxf();
        int i = total / 100;
        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        if(bean.getReturn_data_ciksgf()>0){
            entries.add(new PieEntry(bean.getReturn_data_cikcs()/i,"次卡业绩"));
            colors.add(ColorTemplate.rgb("#7ed6ff"));
        }
        if (bean.getReturn_data_kktc() > 0) {
            entries.add(new PieEntry(bean.getReturn_data_kkyj()/i,"开卡业绩"));
            colors.add(ColorTemplate.rgb("#ff777a"));
        }
        if(bean.getReturn_data_cztc()>0){
            entries.add(new PieEntry(bean.getReturn_data_czyj()/i,"充值业绩"));
            colors.add(ColorTemplate.rgb("#ffd972"));
        }
        if(bean.getReturn_data_nksgf()>0){
            entries.add(new PieEntry(bean.getReturn_data_nkcs()/i,"年卡业绩"));
            colors.add(ColorTemplate.rgb("#9fffb1"));
        }
        if(bean.getReturn_data_xftc()>0){
            entries.add(new PieEntry(bean.getReturn_data_xfyj()/i,"项目业绩"));
            colors.add(ColorTemplate.rgb("#b197fc"));
        }
        if(bean.getReturn_data_cptc()>0){
            entries.add(new PieEntry(bean.getReturn_data_cpxf()/i,"产品销售"));
            colors.add(ColorTemplate.rgb("#c5c5c5"));
        }
        setData(entries,colors);

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

    //设置数据
    private void setData(ArrayList<PieEntry> entries ,ArrayList<Integer> colors) {
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

    //设置数据
    private void setMoneyData(AchievementBean bean) {
        openCardMoney.setText(String.valueOf(bean.getReturn_data_kkyj()/100f));
        rechargeMoney.setText(String.valueOf(bean.getReturn_data_czyj()/100f));
        onceMoney.setText(String.valueOf(bean.getReturn_data_cikcs()/100f));
        yearCardMoney.setText(String.valueOf(bean.getReturn_data_nkcs()/100f));
        productMoney.setText(String.valueOf(bean.getReturn_data_cpxf()/100f));
        storedMoney.setText(String.valueOf(bean.getReturn_data_xfyj()/100f));
    }

    @Override
    public void handlerFailData(String failString, int tag) {

    }

    @Override
    public void handleErrorData(String error, int tag) {

    }
}
