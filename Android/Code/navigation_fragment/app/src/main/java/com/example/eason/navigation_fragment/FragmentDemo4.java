package com.example.eason.navigation_fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eason on 2016-04-23.
 */
public class FragmentDemo4 extends Fragment {
    private int[] mDataYs = new int[]{90, 50, 60, 30, 40, 80, 70, 20, 60, 10, 50, 70, 80, 90};
    public LineChart lineChart;
    public List<String> x=new ArrayList<String>();
    public ArrayList<Entry> y=new ArrayList<Entry>();
    public ArrayList<ILineDataSet> lineDataSets=new ArrayList<ILineDataSet>();
    public LineData lineData=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragmentdemo4_main,container,false);
        lineChart= (LineChart) root.findViewById(R.id.chart);
        LineData lineData=getLineData();
        lineChart.setData(lineData);
        showChart();
        return root;
    }

    private ArrayList<String> getXAxisShowLable() {
        ArrayList<String> m = new ArrayList<String>();
        m.add("Jan");
        m.add("Feb");
        m.add("Mar");
        m.add("Apr");
        m.add("May");
        m.add("Jun");
        m.add("Jul");
        m.add("Aug");
        m.add("Sep");
        m.add("Okt");
        m.add("Nov");
        m.add("Dec");
        return m;
    }

    public LineData getLineData()
    {
        ArrayList<ILineDataSet> allLinesList = new ArrayList<ILineDataSet>();
        ArrayList<Entry> entryArrayList=new ArrayList<Entry>();
        for (int i=0;i<8;i++)
        {
            entryArrayList.add(new Entry(mDataYs[i],i));
        }
        ILineDataSet lineDataSet=new LineDataSet(entryArrayList,"dataLine");//数据集合
        allLinesList.add(lineDataSet);
        LineData mChartdata=new LineData(getXAxisShowLable(),allLinesList);
        return  mChartdata;
    }
    public void showChart()
    {
        lineChart.setDescription("my first linechart");
        lineChart.setBackgroundColor(Color.GRAY);
        lineChart.setDescriptionColor(Color.BLUE);
        lineChart.setNoDataTextDescription("sb zhkmxx930 sb zhaozihe");
        XAxis xAxis=lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setEnabled(true);


    }

}
