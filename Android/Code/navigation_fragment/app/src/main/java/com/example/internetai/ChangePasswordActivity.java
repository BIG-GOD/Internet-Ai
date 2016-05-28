package com.example.internetai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.eason.navigation_fragment.R;
import com.example.zhkmx.biggod.Https;

import java.util.logging.Handler;

/**
 * Created by joho on 2016/5/26.
 */
public class ChangePasswordActivity extends Activity {


    private Button mChangePassword;
    private EditText mOldPassword;
    private EditText mNewPassword;
    private EditText mConfirmPassowrd;
    private Button mGoBack;

    private String _OldPassword, _NewPassword, _Confirm;
    private String _username;

    private Handler mHandler;
    private Https https;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_layout);

        mChangePassword = (Button) findViewById(R.id.change);
        mOldPassword = (EditText) findViewById(R.id.cp_oldpassword);
        mNewPassword = (EditText) findViewById(R.id.cp_newpassword);
        mConfirmPassowrd = (EditText) findViewById(R.id.cp_confirm);
        mGoBack = (Button) findViewById(R.id.changepasswordgoback);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        _username = bundle.getString("username");

        https = new Https();
        url = null;

        mGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangePasswordActivity.this, LoginActivity.class));
            }
        });

        mChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _OldPassword = mOldPassword.getText().toString();
                _NewPassword = mNewPassword.getText().toString();
                _Confirm = mConfirmPassowrd.getText().toString();

                if(_NewPassword.equals(_Confirm)) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            url = "https://120.27.44.239:32001/user/changepsw/";
                            url +=  _username + "&" + _NewPassword;
                            final String result = https.GetHttps(url);
                        }
                    }).start();
                    Toast.makeText(ChangePasswordActivity.this, "修改成功", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(ChangePasswordActivity.this, "两次输入密码不一致", Toast.LENGTH_LONG).show();
                    mNewPassword.setFocusable(true);
                    mNewPassword.setFocusableInTouchMode(true);
                    mNewPassword.requestFocus();
                    mNewPassword.findFocus();
                }
            }
        });
    }
}
