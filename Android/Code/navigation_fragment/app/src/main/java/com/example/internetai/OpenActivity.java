package com.example.internetai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.eason.navigation_fragment.R;

import java.util.ArrayList;
import java.util.List;

public class OpenActivity extends FragmentActivity {

    private GuideViewPager vPager;
    private List<GuideFragment> list = new ArrayList<GuideFragment>();
    private BaseFragmentAdapter adapter;

    private ImageView[] tips;
    private int currentSelect;

    private Button loginBtn;
    private Button registerBtn;
    private Button thirdAppBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.open_main);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerBtn = (Button) findViewById(R.id.registerBtn);

        loginClickListener();
        registerClickListener();

        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
        tips = new ImageView[3];
        for(int i = 0;i < tips.length; i++) {
            ImageView imageView = new ImageView(this);

            imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));

            if(i == 0) {
                imageView.setBackgroundResource(R.drawable.force);
            }
            else {
                imageView.setBackgroundResource(R.drawable.unforce);
            }

            tips[i] = imageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(30, 30));
            layoutParams.leftMargin = 20;
            layoutParams.rightMargin = 20;
            group.addView(imageView, layoutParams);
        }

        vPager = (GuideViewPager) findViewById(R.id.viewpager_launcher);
        //vPager.setBackground;

        FirstGuideFragment firstGuideFragment = new FirstGuideFragment();
        SecondGuideFragment secondGuideFragment = new SecondGuideFragment();
        ThirdGuideFragment thirdGuideFragment = new ThirdGuideFragment();
        list.add(firstGuideFragment);
        list.add(secondGuideFragment);
        list.add(thirdGuideFragment);

        adapter = new BaseFragmentAdapter(getSupportFragmentManager(), list);
        vPager.setAdapter(adapter);
        vPager.setOffscreenPageLimit(2);
        vPager.setCurrentItem(0);
        vPager.setOnPageChangeListener(changeListener);

    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            setImageBackground(position);
            GuideFragment fragment = list.get(position);

            currentSelect = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private void setImageBackground(int selectItems) {
        for(int i = 0;i < tips.length; i++) {
            if(i == selectItems) {
                tips[i].setBackgroundResource(R.drawable.force);
            }
            else {
                tips[i].setBackgroundResource(R.drawable.unforce);
            }
        }
    }

    private void loginClickListener() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(OpenActivity.this, LoginActivity.class));
                OpenActivity.this.finish();
            }
        });
    }

    private void registerClickListener() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OpenActivity.this, RegisterActivity.class));
                OpenActivity.this.finish();
            }
        });
    }


}
