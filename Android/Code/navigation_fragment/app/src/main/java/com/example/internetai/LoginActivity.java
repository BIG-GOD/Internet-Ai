package com.example.internetai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eason.navigation_fragment.MainActivity;
import com.example.eason.navigation_fragment.R;
import com.example.zhkmx.biggod.Https;


/**
 * Created by joho on 2016/4/25.
 */
public class LoginActivity extends Activity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mLoginBtn;
    private Button mGoBackBtn;
    private Button mGoRegisterBtn;
    private Button mForgetPassword;
    private CheckBox mAutoLogin;
    private String _username, _password;
    private final static int SUCCESS_STATE = 100;
    private final static int FAIL_STATE = 101;
    private final static String SUCCESS_RETURN = "succeed login";
    private final static String FAIL_RETURN = "error username or password";
    private Https https;
    private String url;
    private Handler mhandler;
    private Message msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_activity);


        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mLoginBtn = (Button) findViewById(R.id.login);
        mForgetPassword = (Button) findViewById(R.id.forgetPassword);
        mAutoLogin = (CheckBox) findViewById(R.id.autoLoginCheckBox);
        mGoBackBtn = (Button) findViewById(R.id.logingoback);
        mGoRegisterBtn = (Button) findViewById(R.id.goregister);

        url = null;

        https = new Https();

        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SUCCESS_STATE :
                        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("username",mUsername.getText().toString());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        LoginActivity.this.finish();
                        break;
                    case FAIL_STATE :
                        mUsername.setFocusable(true);
                        mUsername.setFocusableInTouchMode(true);
                        mUsername.requestFocus();
                        mUsername.findFocus();
                        Toast.makeText(LoginActivity.this, "用户名或密码输入错误", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };

        mGoBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, OpenActivity.class));
            }
        });

        mGoRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _username = mUsername.getText().toString();
                _password = mPassword.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        url = "https://120.27.44.239:32001/user/login/" + _username + "&" + _password;
                        final String result = https.GetHttps(url);
                        msg = new Message();
                        if(result.equals(SUCCESS_RETURN)) {
                            msg.what = SUCCESS_STATE;
                        }
                        else {
                            msg.what = FAIL_STATE;
                        }
                       // System.out.println(msg.what);
                        mhandler.sendMessage(msg);
                    }
                }).start();
            }
        });

    }

}
