package com.example.zhkmx.biggod;

import android.graphics.Color;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhkmx on 2016/4/23.
 */
public class CustomLineChart {
    private String chartName;
    private float limitVal;//限制线值
    private String limitName;//限制线名称
    private String lineName;//设置曲线脚注
    private ArrayList<String> xAxislabel;//横坐标标签
    private List<Integer> data;//图表数据源

    public CustomLineChart(){

    }

    /**
     * 构造函数
     * 图表名称，限制线名称，曲线名称，限制线值，数据源，横坐标
     * @param chartName
     * @param limitName
     * @param lineName
     * @param limitVal
     * @param data
     * @param xAxislabel
     */
    public CustomLineChart(String chartName,
                           String limitName,
                           String lineName,
                           float limitVal,
                           List<Integer> data,
                           ArrayList<String> xAxislabel){
        this.chartName = chartName;
        this.limitName = limitName;
        this.lineName = lineName;
        this.limitVal = limitVal;
        this.data = data;
        this.xAxislabel = xAxislabel;
    }

    /**
     * 构造图表
     * 参数传入LineChart实例
     * LineChart chart = (LineChart)view.findViewById(R.id.chart);
     * @param chart
     */
    public void generateChart (LineChart chart) {
        ArrayList<ILineDataSet> allLinesList = new ArrayList<ILineDataSet>();
        ArrayList<Entry> entryList = new ArrayList<Entry>();

        for (int i = 0; i<getData().size();i++){
            entryList.add(new Entry(getData().get(i),i));
        }
        LineDataSet dataSet = new LineDataSet(entryList,getLineName());
        dataSet.setLineWidth(2.5f);
//      dataSet.setCircleSize(3.5f);
        dataSet.setHighLightColor(Color.BLUE);
        dataSet.setDrawValues(false);
        dataSet.setValueTextColor(Color.GREEN);
        dataSet.setValueTextSize(19f);
        allLinesList.add(dataSet);
        setLineChart(chart);
        LineData mChartData = new LineData(getxAxislabel(),allLinesList);
        chart.setData((LineData) mChartData);
        chart.animateX(1500);

    }


    /**
     * 设置折现图的样式
     * 私有方法
     * @param chart
     */
    private void setLineChart(LineChart chart) {
        String name = getChartName();
        float limiteVal = getLimitVal();
        String limitName = getLimitName();

        chart.setDescription(name);
        chart.setDrawGridBackground(false);//设置网格背景
        chart.setScaleEnabled(true);//设置缩放
        chart.setDoubleTapToZoomEnabled(true);//设置双击进行缩放

        LimitLine ylimitLine = new LimitLine(limiteVal,limitName);
        ylimitLine.setTextSize(10f);
        ylimitLine.setTextColor(Color.RED);
        ylimitLine.setLineColor(Color.RED);
        // 获得左侧侧坐标轴
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.addLimitLine(ylimitLine);

        //设置X轴
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        //设置右侧坐标轴
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawAxisLine(false);//右侧坐标轴线
        rightAxis.setDrawLabels(false);//右侧坐标轴数组Lable

    }


    /**************setter and getter***********************/
    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }


    public ArrayList<String> getxAxislabel() {
        return xAxislabel;
    }

    public void setxAxislabel(ArrayList<String> xAxislabel) {
        this.xAxislabel = xAxislabel;
    }

    public float getLimitVal() {
        return limitVal;
    }

    public void setLimitVal(float limitVal) {
        this.limitVal = limitVal;
    }



    public String getLimitName() {
        return limitName;
    }

    public void setLimitName(String limitName) {
        this.limitName = limitName;
    }

    /**
     * 设置图表标题
     * @param chartName
     */
    public void setChartName(String chartName){
        this.chartName = chartName;
    }

    /**
     * 获取图表标题
     * @return
     */
    public String getChartName(){
        return chartName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }
/**************setter and getter***********************/

}
