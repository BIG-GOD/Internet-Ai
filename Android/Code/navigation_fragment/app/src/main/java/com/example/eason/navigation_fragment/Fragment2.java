package com.example.eason.navigation_fragment;

import android.content.Context;
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
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhkmx.biggod.Https;

/**
 * Created by eason on 2016-05-24.
 */
public class Fragment2 extends Fragment implements View.OnClickListener {
    //定义3个Fragment的对象
    private Fragment1 fg1;
    private static Fragment2 fg2=null;
    private Fragment3 fg3;
    //帧布局对象,就是用来存放Fragment的容器
    private FrameLayout flayout;
    //定义底部导航栏的三个布局
    private RelativeLayout course_layout=null;
    private RelativeLayout found_layout=null;
    private RelativeLayout settings_layout=null;
    private LinearLayout fg2_layout=null;
    //定义底部导航栏中的ImageView与TextView
    private ImageView course_image;
    private ImageView found_image;
    private ImageView settings_image;
    private TextView course_text;
    private TextView settings_text;
    private TextView found_text;

    private ImageButton control;
    //定义要用的颜色值
    private int whirt = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int blue =0xFF0AB2FB;

    private View v=null;
    FragmentManager fManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fg2, container, false);
        control= (ImageButton) view.findViewById(R.id.switch2);
        control.setOnClickListener(this);
        fg2_layout= (LinearLayout) view.findViewById(R.id.fg2);
        v=view.findViewById(R.id.include_);
        fManager = getActivity().getSupportFragmentManager();
        initViews();
        return  view;
    }
    public static Fragment2 getInstance(Context context)
    {
        if (fg2==null)
        {
            fg2=new Fragment2();
        }
        return fg2;

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
            case R.id.switch2:
                onCheckedChanged();
//                System.out.println("fg2 tag:" + control.getTag().toString());
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
                fg1=Fragment1.getInstance(getContext());
                transaction.replace(R.id.content_main, fg1);


                break;

            case 1:
                found_image.setImageResource(R.drawable.ic_tabbar_found_pressed);
                found_text.setTextColor(blue);
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

    public void onCheckedChanged() {
        String url="http://550516c0.nat123.net:26908/";
        Object value=control.getTag();
        int tag=Integer.parseInt(String.valueOf(value));
        if(tag==0)
        {
            String open="infraredctrlon";
            url=url+open;
            //Fragment1.AsynGetHttp(url);
            fg2_layout.setBackgroundColor(Color.YELLOW);
            control.setImageResource(R.drawable.lamp);
            control.setTag(1);
            Toast.makeText(getActivity().getApplicationContext(), "红外按钮开启", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String close="infraredctrloff";
            url=url+close;
            //Fragment1.AsynGetHttp(url);
            fg2_layout.setBackgroundColor(Color.WHITE);
            control.setImageResource(R.drawable.lampoff);
            control.setTag(0);
            Toast.makeText(getActivity().getApplicationContext(),"红外开关关闭",Toast.LENGTH_SHORT).show();
        }
    }

}
