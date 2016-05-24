package agora.biggod.com.rcvphone;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.bairuitech.anychat.AnyChatBaseEvent;
import com.bairuitech.anychat.AnyChatCoreSDK;

import businesscenter.BussinessCenter;
import util.BaseMethod;
import util.ConfigEntity;
import util.ConfigService;

public class Launcher extends AppCompatActivity implements AnyChatBaseEvent {

    private Button configBtn;
    private Button loginBtn;
    private CheckBox mCheckRemember;
    private ConfigEntity configEntity;
    private EditText mEditAccount;
    private ProgressDialog mProgressLogin;
    private Dialog dialog;
    private AnyChatCoreSDK anychat;
    private boolean bNeedRelease = false;
    private String mAppKey;
    private String strUserName;
    private RadioButton btn1;
    private String UserName = "Door001";

    private static final String url = "demo.anychat.cn";
    private static final int REQUEST_SIGNED = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        initSdk();
        intParams();
    }


    protected void intParams() {
        configEntity = ConfigService.LoadConfig(this);
        BussinessCenter.getBussinessCenter();
    }
    private void initSdk() {
        if (anychat == null) {
            anychat = new AnyChatCoreSDK();
            anychat.SetBaseEvent((AnyChatBaseEvent) this);
            anychat.InitSDK(android.os.Build.VERSION.SDK_INT, 0);
            bNeedRelease = true;
            anychat.Connect(url,8906);
            anychat.Login(UserName,"123");
        }
    }

    @Override
    public void OnAnyChatConnectMessage(boolean bSuccess) {
        if (!bSuccess) {
            BaseMethod.showToast(getString(R.string.server_connect_error), this);
//            mProgressLogin.dismiss();
        } else {
        }
    }

    @Override
    public void OnAnyChatLoginMessage(int dwUserId, int dwErrorCode) {
        if (dwErrorCode == 0) {
            BussinessCenter.selfUserId = dwUserId;
            BussinessCenter.selfUserName = UserName;
            Intent intent=new Intent();
            intent.setClass(this, HallActivity.class);
            this.startActivity(intent);
        } else if (dwErrorCode == 200) {
            BaseMethod.showToast(
                    getString(R.string.str_lggin_failed), this);
        }
//        mProgressLogin.dismiss();
    }

    @Override
    public void OnAnyChatEnterRoomMessage(int dwRoomId, int dwErrorCode) {

    }

    @Override
    public void OnAnyChatOnlineUserMessage(int dwUserNum, int dwRoomId) {

    }

    @Override
    public void OnAnyChatUserAtRoomMessage(int dwUserId, boolean bEnter) {

    }

    @Override
    public void OnAnyChatLinkCloseMessage(int dwErrorCode) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bNeedRelease) {
            anychat.Logout();
            anychat.Release();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        BussinessCenter.getBussinessCenter().realseData();
    }
}
