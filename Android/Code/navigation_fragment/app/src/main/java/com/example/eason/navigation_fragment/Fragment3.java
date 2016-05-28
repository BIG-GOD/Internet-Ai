package com.example.eason.navigation_fragment;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhkmx.biggod.Https;

/**
 * Created by eason on 2016-05-24.
 */
public class Fragment3 extends Fragment implements View.OnClickListener,SeekBar.OnSeekBarChangeListener,CompoundButton.OnCheckedChangeListener{
    //定义3个Fragment的对象
    private Fragment1 fg1;
    private Fragment2 fg2;
    private  static Fragment3 fg3=null;
    //帧布局对象,就是用来存放Fragment的容器
    private FrameLayout flayout;
    //定义底部导航栏的三个布局
    private RelativeLayout course_layout=null;
    private RelativeLayout found_layout=null;
    private RelativeLayout settings_layout=null;
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
    FragmentManager fManager;

    private SeekBar seekbar;
    private Switch aswitch;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fg3,container,false);
        v=view.findViewById(R.id.include_);
        seekbar= (SeekBar) view.findViewById(R.id.seekBar);
        aswitch= (Switch) view.findViewById(R.id.switch3);
        seekbar.setOnSeekBarChangeListener(this);
        aswitch.setOnCheckedChangeListener(this);
//        control=(Switch)view.findViewById(R.id.switch1);
//        control.setOnCheckedChangeListener(this);
        fManager = getActivity().getSupportFragmentManager();
        initViews();
        return  view;
    }
    public static Fragment3 getInstance(Context context)
    {
        if (fg3==null)
        {
            fg3=new Fragment3();
        }
        return fg3;

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
        //Fragment fragment=null;
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
                fg2=Fragment2.getInstance(getContext());
                transaction.replace(R.id.content_main, fg2);
                break;

            case 2:
                settings_image.setImageResource(R.drawable.ic_tabbar_settings_pressed);
                settings_text.setTextColor(blue);
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

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        int seekProgress = seekBar.getProgress();
        String url="http://550516c0.nat123.net:26908/";
        int parameter=0;
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(seekProgress<13){
            seekBar.setProgress(0);
            parameter=20;
        }else if(seekProgress>=13 && seekProgress<38){
            seekBar.setProgress(25);
            parameter=40;
        }else if(seekProgress>=38 && seekProgress<63){
            seekBar.setProgress(50);
            parameter=60;
        }else if(seekProgress>=63 && seekProgress<88){
            seekBar.setProgress(75);
            parameter=80;
        }else if(seekProgress>=88){
            seekBar.setProgress(100);
            parameter=100;
        }
        editor.putInt("para",seekBar.getProgress());
        String adjust="pwmctrl/adjust/"+String.valueOf(parameter);
        url=url+adjust;
        System.out.println("pwm" + seekbar.getProgress());
        Fragment1.AsynGetHttp(url);


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String url="http://550516c0.nat123.net:26908/";
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("config",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        if(isChecked==true)
        {
            seekbar.setEnabled(true);
            int para=sharedPreferences.getInt("para",20);
            String open="pwmctrl/start/"+String.valueOf(para);
            url=url+open;
            System.out.println("pwm" + seekbar.getProgress());
            Toast.makeText(getActivity().getApplicationContext(), "PWM按钮开启", Toast.LENGTH_SHORT).show();
            System.out.print("url:"+url);
            Fragment1.AsynGetHttp(url);

        }
        else
        {
            seekbar.setEnabled(false);
            String close="pwmctrl/close";
            url=url+close;
            System.out.print("url:"+url);
            Fragment1.AsynGetHttp(url);
            Toast.makeText(getActivity().getApplicationContext(),"PWM开关关闭",Toast.LENGTH_SHORT).show();
        }
    }
}
