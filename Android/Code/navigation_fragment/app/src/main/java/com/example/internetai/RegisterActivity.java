package com.example.internetai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eason.navigation_fragment.R;
import com.example.zhkmx.biggod.Https;


/**
 * Created by joho on 2016/4/25.
 */
public class RegisterActivity extends Activity {

    private EditText mUsername;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private EditText mMail;
    private Button mRegisterButton;
    private Button mGoBackBtn;
    private Button mGoLoginBtn;

    private final static int SUCCESS_CODE = 100;
    private final static int FAIL_CODE = 101;
    private final static String SUCCESS_RETURN = "succeed";
    private final static String FAIL_RETURN = "username is already exist!";

    private String _username, _password, _confirm, _mail;
    private Https https;
    private String url;
    private Handler mHandler;
    private Message msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register_activity);

        mUsername = (EditText) findViewById(R.id.reg_username);
        mPassword = (EditText) findViewById(R.id.reg_password);
        mConfirmPassword = (EditText) findViewById(R.id.reg_confirm);
        mMail = (EditText) findViewById(R.id.reg_mail);

        mRegisterButton = (Button) findViewById(R.id.register);
        mGoBackBtn = (Button) findViewById(R.id.registergoback);
        mGoLoginBtn = (Button) findViewById(R.id.gologin);

        https = new Https();

        url = null;

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SUCCESS_CODE :
                        Toast.makeText(RegisterActivity.this, "注册成功.", Toast.LENGTH_LONG).show();
                        break;
                    case FAIL_CODE :
                        mUsername.setFocusable(true);
                        mUsername.setFocusableInTouchMode(true);
                        mUsername.requestFocus();
                        mUsername.findFocus();
                        Toast.makeText(RegisterActivity.this, "用户名已存在.", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };

        mGoBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, OpenActivity.class));
                RegisterActivity.this.finish();
            }
        });

        mGoLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                RegisterActivity.this.finish();
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _username = mUsername.getText().toString();
                _password = mPassword.getText().toString();
                _confirm = mConfirmPassword.getText().toString();
                _mail = mMail.getText().toString();
                if(_password.equals(_confirm)) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final String data = "username=" + _username + "&user_password=" + _password + "&user_email=" + _mail;
                            url = "https://120.27.44.239:32001/user/register";
                            String result = https.PostHttps(url, data);
                            msg = new Message();
                            if(result.equals(SUCCESS_RETURN)) {
                                msg.what = SUCCESS_CODE;
                            }
                            else {
                                msg.what = FAIL_CODE;
                            }
                            mHandler.sendMessage(msg);
                        }
                    }).start();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "密码输入不一致.", Toast.LENGTH_LONG).show();
                    mConfirmPassword.setFocusable(true);
                    mConfirmPassword.setFocusableInTouchMode(true);
                    mConfirmPassword.requestFocus();
                    mConfirmPassword.findFocus();
                }
            }
        });

    }
}
