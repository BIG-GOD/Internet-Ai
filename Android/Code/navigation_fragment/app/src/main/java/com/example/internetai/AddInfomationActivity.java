package com.example.internetai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eason.navigation_fragment.R;
import com.example.zhkmx.biggod.Https;


/**
 * Created by joho on 2016/5/26.
 */
public class AddInfomationActivity extends Activity {

    private TextView mInfoUser;
    private EditText mTelWork;
    private EditText mTelMobile;
    private EditText mEmail;
    private EditText mAddress;
    private Button mXGTelWork;
    private Button mXGTelMobile;
    private Button mXGEmail;
    private Button mXGAddress;
    private LinearLayout mLayoutBottom;

    private Handler mHandler;
    private Https https;
    private String url;
    private String _username, _tel_work, _tel_mobile, _email, _address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_infomation_layout);

        mInfoUser = (TextView) findViewById(R.id.info_user);
        mTelWork = (EditText) findViewById(R.id.tel_work);
        mTelMobile = (EditText) findViewById(R.id.tel_mobile);
        mEmail = (EditText) findViewById(R.id.email_personal);
        mAddress = (EditText) findViewById(R.id.email_work);
        mXGTelWork = (Button) findViewById(R.id.xiugai_tel_work);
        mXGTelMobile = (Button) findViewById(R.id.xiugai_tel_mobile);
        mXGEmail = (Button) findViewById(R.id.xiugai_email_personal);
        mXGAddress = (Button) findViewById(R.id.xiugai_email_work);
        mLayoutBottom = (LinearLayout) findViewById(R.id.infomationBottom);

        mHandler = new Handler();
        https = new Https();
        url = null;

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        _username = bundle.getString("username").toString();

        mInfoUser.setText(_username);

        _email = "null";
        _address = "null";
        _tel_mobile = "null";
        _tel_work = "null";



        mTelWork.setFocusable(false);
        mTelWork.setFocusableInTouchMode(false);
        mTelMobile.setFocusable(false);
        mTelMobile.setFocusableInTouchMode(false);
        mEmail.setFocusable(false);
        mEmail.setFocusableInTouchMode(false);
        mAddress.setFocusable(false);
        mAddress.setFocusableInTouchMode(false);

        mLayoutBottom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mLayoutBottom.setFocusable(true);
                mLayoutBottom.setFocusableInTouchMode(true);
                mLayoutBottom.requestFocus();
                return false;
            }
        });

        mXGTelWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTelWork.setFocusable(true);
                mTelWork.setFocusableInTouchMode(true);
                mTelWork.requestFocus();
                mTelWork.findFocus();
                mTelWork.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            _tel_work = mTelWork.getText().toString();
                            mTelWork.setFocusable(false);
                            mTelWork.setFocusableInTouchMode(false);
                        }
                    }
                });

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        url = "https://120.27.44.239:32001/user/update/";
                        url += _tel_mobile + "&" +_email + "&" + _username + "&" + _tel_work + "&" + _address;
                        String result = https.GetHttps(url);
                    }
                }).start();
            }
        });

        mXGTelMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTelMobile.setFocusable(true);
                mTelMobile.setFocusableInTouchMode(true);
                mTelMobile.requestFocus();
                mTelMobile.findFocus();
                mTelMobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            _tel_mobile = mTelMobile.getText().toString();
                            mTelMobile.setFocusable(false);
                            mTelMobile.setFocusableInTouchMode(false);
                        }
                    }
                });
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        url = "https://120.27.44.239:32001/user/update/";
                        url += _tel_mobile + "&" +_email + "&" + _username + "&" + _tel_work + "&" + _address;
                        String result = https.GetHttps(url);
                    }
                }).start();
            }
        });

        mXGEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEmail.setFocusable(true);
                mEmail.setFocusableInTouchMode(true);
                mEmail.requestFocus();
                mEmail.findFocus();

                mEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            _email = mEmail.getText().toString();
                            mEmail.setFocusable(false);
                            mEmail.setFocusableInTouchMode(false);
                        }
                    }
                });

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        url = "https://120.27.44.239:32001/user/update/";
                        url += _tel_mobile + "&" +_email + "&" + _username + "&" + _tel_work + "&" + _address;
                        String result = https.GetHttps(url);
                    }
                }).start();
            }
        });

        mXGAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAddress.setFocusable(true);
                mAddress.setFocusableInTouchMode(true);
                mAddress.requestFocus();
                mAddress.findFocus();
                mAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            _address = mAddress.getText().toString();
                            mAddress.setFocusable(false);
                            mAddress.setFocusableInTouchMode(false);
                        }
                    }
                });

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        url = "https://120.27.44.239:32001/user/update/";
                        url += _tel_mobile + "&" +_email + "&" + _username + "&" + _tel_work + "&" + _address;
                        String result = https.GetHttps(url);
                    }
                }).start();
            }
        });

    }
}
