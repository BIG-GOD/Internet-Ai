package com.example.eason.navigation_fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhkmx.biggod.Https;

/**
 * Created by eason on 2016-05-24.
 */
public class Fragment1 extends Fragment implements View.OnClickListener{
    private ImageButton control;

    //定义3个Fragment的对象
    private static Fragment1 fg1=null;
    private Fragment2 fg2;
    private Fragment3 fg3;
    //帧布局对象,就是用来存放Fragment的容器
    private FrameLayout flayout;
    //定义底部导航栏的三个布局
    private RelativeLayout course_layout=null;
    private RelativeLayout found_layout=null;
    private RelativeLayout settings_layout=null;
    //定义fragment控件
    private LinearLayout fg1_layout=null;
    //定义底部导航栏中的ImageView与TextView
    private ImageView course_image;
    private ImageView found_image;
    private ImageView settings_image;
    private TextView course_text;
    private TextView settings_text;
    private TextView found_text;
    //定义要用的颜色值
    private int whirt = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int blue =0xFF0AB2FB;

    private View v=null;
    //定义FragmentManager对象
    FragmentManager fManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fg1, container, false);
        v=view.findViewById(R.id.include_);
        fg1_layout= (LinearLayout) view.findViewById(R.id.fg1);
        control=(ImageButton)view.findViewById(R.id.switch1);
        control.setOnClickListener(this);
        fManager = getActivity().getSupportFragmentManager();
        initViews();
        return  view;
    }

    public static Fragment1 getInstance(Context context)
    {
        if (fg1==null)
        {
            fg1=new Fragment1();
        }
        return fg1;

    }
    //完成组件的初始化
    public void initViews()
    {

        course_image = (ImageView) v.findViewById(R.id.course_image);
        found_image = (ImageView) v.findViewById(R.id.found_image);
        settings_image = (ImageView) v.findViewById(R.id.setting_image);
        course_text = (TextView) v.findViewById(R.id.course_text);
        found_text = (TextView) v.findViewById(R.id.found_text);
        settings_text = (TextView) v.findViewById(R.id.setting_text);
        course_layout = (RelativeLayout) v.findViewById(R.id.course_layout);
        found_layout = (RelativeLayout) v.findViewById(R.id.found_layout);
        settings_layout = (RelativeLayout) v.findViewById(R.id.setting_layout);

        course_layout.setOnClickListener(this);
        found_layout.setOnClickListener(this);
        settings_layout.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch1:
                onCheckedChanged();
                break;
            case R.id.course_layout:
                setChioceItem(0);
                break;
            case R.id.found_layout:
                setChioceItem(1);
                break;
            case R.id.setting_layout:
                setChioceItem(2);
                break;
            default:
                break;
        }
    }
    //定义一个选中一个item后的处理
    public void setChioceItem(int index)
    {
        //重置选项+隐藏所有Fragment
        FragmentTransaction transaction = fManager.beginTransaction();
        clearChioce();
        //hideFragments(transaction);
        switch (index) {
            case 0:
                course_image.setImageResource(R.drawable.ic_tabbar_course_pressed);
                course_text.setTextColor(blue);
                break;

            case 1:
                found_image.setImageResource(R.drawable.ic_tabbar_found_pressed);
                found_text.setTextColor(blue);
                fg2=Fragment2.getInstance(getContext());
                transaction.replace(R.id.content_main, fg2);

                break;

            case 2:
                settings_image.setImageResource(R.drawable.ic_tabbar_settings_pressed);
                settings_text.setTextColor(blue);
                fg3=Fragment3.getInstance(getContext());
                transaction.replace(R.id.content_main, fg3);

                break;
        }
        transaction.commit();
    }
    //隐藏所有的Fragment,避免fragment混乱
    private void hideFragments(FragmentTransaction transaction) {
        if (fg1 != null) {
            transaction.hide(fg1);
        }
        if (fg2 != null) {
            transaction.hide(fg2);
        }
        if (fg3 != null) {
            transaction.hide(fg3);
        }
    }
    //定义一个重置所有选项的方法
    public void clearChioce()
    {
        course_image.setImageResource(R.drawable.ic_tabbar_course_normal);
        course_layout.setBackgroundColor(whirt);
        course_text.setTextColor(gray);
        found_image.setImageResource(R.drawable.ic_tabbar_found_normal);
        found_layout.setBackgroundColor(whirt);
        found_text.setTextColor(gray);
        settings_image.setImageResource(R.drawable.ic_tabbar_settings_normal);
        settings_layout.setBackgroundColor(whirt);
        settings_text.setTextColor(gray);
    }

//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        String url="http://550516c0.nat123.net:26908/";
//        if(isChecked==true)
//        {
//            String open="lightctrlon";
//            url=url+open;
//            AsynGetHttp(url);
//            fg1_layout.setBackgroundColor(Color.YELLOW);
//            Toast.makeText(getActivity().getApplicationContext(), "控制按钮开启", Toast.LENGTH_SHORT).show();
//
//        }
//        else
//        {
//            String close="lightctrloff";
//            url=url+close;
//            AsynGetHttp(url);
//            fg1_layout.setBackgroundColor(Color.WHITE);
//            Toast.makeText(getActivity().getApplicationContext(),"控制开关关闭",Toast.LENGTH_SHORT).show();
//        }
//    }
public void onCheckedChanged() {
    String url="http://550516c0.nat123.net:26908/";
    Object value=control.getTag();
    int tag=Integer.parseInt(String.valueOf(value));
    if(tag==0)
    {
        String open="lightctrlon";
        url=url + open;
        AsynGetHttp(url);
        fg1_layout.setBackgroundColor(Color.YELLOW);
        control.setImageResource(R.drawable.lamp);
        control.setTag(1);
        Toast.makeText(getActivity().getApplicationContext(), "控制按钮开启", Toast.LENGTH_SHORT).show();

    }
    else
    {
        String close="lightctrloff";
        url=url + close;
        AsynGetHttp(url);
        fg1_layout.setBackgroundColor(Color.WHITE);
        control.setImageResource(R.drawable.lampoff);
        control.setTag(0);
        Toast.makeText(getActivity().getApplicationContext(),"控制开关关闭",Toast.LENGTH_SHORT).show();
    }
}
public static void AsynGetHttp(String url)
{
    AsyncTask<String,Void,String> execute=new AsyncTask<String, Void, String>(){
        @Override
        protected String doInBackground(String... params) {
            String result=HttpUtil.httpRequestGet(params[0]);
            if(result.isEmpty())
            {
                return "no connection";
            }
            return result;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String s) {
            System.out.println("control:"+s);
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }.execute(url);
}
}
