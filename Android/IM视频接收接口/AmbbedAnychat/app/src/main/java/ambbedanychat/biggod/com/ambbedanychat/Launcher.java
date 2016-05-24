package ambbedanychat.biggod.com.ambbedanychat;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;


import com.bairuitech.anychat.AnyChatBaseEvent;
import com.bairuitech.anychat.AnyChatCoreSDK;
import com.bairuitech.anychat.AnyChatDefine;
import com.bairuitech.anychat.AnyChatUserInfoEvent;
import com.bairuitech.anychat.AnyChatVideoCallEvent;

import bgservivce.BackService;
import businesscenter.BussinessCenter;
import util.BaseConst;
import util.BaseMethod;
import util.ConfigEntity;
import util.ConfigService;
import util.DialogFactory;

public class Launcher extends AppCompatActivity implements  AnyChatBaseEvent, AnyChatVideoCallEvent,
        AnyChatUserInfoEvent {
    private UserAdapter mUserAdapter;
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
    private String UserName = "HOME001";

    private static final String url = "demo.anychat.cn";
    private static final int REQUEST_SIGNED = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            ApplyVideoConfig();
        }
    }

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
            anychat.Connect(url, 8906);
            anychat.Login(UserName, "123");
//            anychat.SetBaseEvent(this);
            anychat.SetVideoCallEvent((AnyChatVideoCallEvent) this);
            anychat.SetUserInfoEvent((AnyChatUserInfoEvent) this);
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
            ApplyVideoConfig();

            BussinessCenter.getBussinessCenter().getOnlineFriendDatas();
            startBackServce();
            BussinessCenter.selfUserId = dwUserId;
            BussinessCenter.selfUserName=anychat.GetUserName(dwUserId);
            System.out.println("服务开启成功");
//            Intent intent=new Intent();
//            intent.setClass(this, HallActivity.class);
//            this.startActivity(intent);
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
        if (dwErrorCode == 0) {
            if (dialog != null && dialog.isShowing())
                dialog.dismiss();
            dialog = DialogFactory.getDialog(DialogFactory.DIALOG_NETCLOSE,
                    DialogFactory.DIALOG_NETCLOSE, this);
            dialog.show();
        } else {
            BaseMethod.showToast(this.getString(R.string.str_serverlink_close),
                    this);
            Intent intent = new Intent();
            intent.putExtra("INTENT", BaseConst.AGAIGN_LOGIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setClass(this, LoginActivity.class);
            this.startActivity(intent);
            this.finish();
        }
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
    protected void startBackServce() {
        Intent intent = new Intent(this, BackService.class);
//		intent.setAction(BaseConst.ACTION_BACK_SERVICE);
        this.startService(intent);
    }

    // 根据配置文件配置视频参数
    private void ApplyVideoConfig()
    {
        ConfigEntity configEntity = ConfigService.LoadConfig(this);
        if(configEntity.configMode == 1)		// 自定义视频参数配置
        {
            // 设置本地视频编码的码率（如果码率为0，则表示使用质量优先模式）
            AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_BITRATECTRL, configEntity.videoBitrate);
            if(configEntity.videoBitrate==0)
            {
                // 设置本地视频编码的质量
                AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_QUALITYCTRL, configEntity.videoQuality);
            }
            // 设置本地视频编码的帧率
            AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_FPSCTRL, configEntity.videoFps);
            // 设置本地视频编码的关键帧间隔
            AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_GOPCTRL, configEntity.videoFps*4);
            // 设置本地视频采集分辨率
            AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_WIDTHCTRL, configEntity.resolution_width);
            AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_HEIGHTCTRL, configEntity.resolution_height);
            // 设置视频编码预设参数（值越大，编码质量越高，占用CPU资源也会越高）
            AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_PRESETCTRL, configEntity.videoPreset);
        }
        // 让视频参数生效
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_APPLYPARAM, configEntity.configMode);
        // P2P设置
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_NETWORK_P2PPOLITIC, configEntity.enableP2P);
        // 本地视频Overlay模式设置
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_OVERLAY, configEntity.videoOverlay);
        // 回音消除设置
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_AUDIO_ECHOCTRL, configEntity.enableAEC);
        // 平台硬件编码设置
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_CORESDK_USEHWCODEC, configEntity.useHWCodec);
        // 视频旋转模式设置
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_ROTATECTRL, configEntity.videorotatemode);
        // 本地视频采集偏色修正设置
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_FIXCOLORDEVIA, configEntity.fixcolordeviation);
        // 视频GPU渲染设置
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_VIDEOSHOW_GPUDIRECTRENDER, configEntity.videoShowGPURender);
        // 本地视频自动旋转设置
        AnyChatCoreSDK.SetSDKOptionInt(AnyChatDefine.BRAC_SO_LOCALVIDEO_AUTOROTATION, configEntity.videoAutoRotation);
    }

    @Override
    public void OnAnyChatUserInfoUpdate(int dwUserId, int dwType) {
        // TODO Auto-generated method stub
        // 同步完成服务器中的所有好友数据，可以在此时获取数据
        if (dwUserId == 0 && dwType == 0) {
            BussinessCenter.getBussinessCenter().getOnlineFriendDatas();
            if (mUserAdapter != null)
                mUserAdapter.notifyDataSetChanged();
            Log.i("ANYCHAT", "OnAnyChatUserInfoUpdate");
        }
    }

    @Override
    public void OnAnyChatFriendStatus(int dwUserId, int dwStatus) {
//        BussinessCenter.getBussinessCenter().onUserOnlineStatusNotify(dwUserId,
//                dwStatus);
//        if (mUserAdapter != null)
//            mUserAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnAnyChatVideoCallEvent(int dwEventType, int dwUserId, int dwErrorCode, int dwFlags, int dwParam, String userStr) {
        switch (dwEventType) {

            case AnyChatDefine.BRAC_VIDEOCALL_EVENT_REQUEST:
                System.out.println("请求BRAC_VIDEOCALL_EVENT_REQUEST");
                BussinessCenter.getBussinessCenter().onVideoCallRequest(dwUserId, dwFlags, dwParam, userStr,this);

                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                dialog = DialogFactory.getDialog(DialogFactory.DIALOGID_REQUEST,
                        dwUserId, this);
                dialog.show();
                break;
            case AnyChatDefine.BRAC_VIDEOCALL_EVENT_REPLY:
                BussinessCenter.getBussinessCenter().onVideoCallReply(
                        dwUserId, dwErrorCode, dwFlags, dwParam, userStr);
                if (dwErrorCode == AnyChatDefine.BRAC_ERRORCODE_SUCCESS) {
                    dialog = DialogFactory.getDialog(
                            DialogFactory.DIALOGID_CALLING, dwUserId,
                            Launcher.this);
                    dialog.show();

                } else {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
                break;
            case AnyChatDefine.BRAC_VIDEOCALL_EVENT_START:
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                BussinessCenter.getBussinessCenter().onVideoCallStart(
                        dwUserId, dwFlags, dwParam, userStr);
                break;
            case AnyChatDefine.BRAC_VIDEOCALL_EVENT_FINISH:
                BussinessCenter.getBussinessCenter().onVideoCallEnd(dwUserId,
                        dwFlags, dwParam, userStr);
                break;
        }
    }
}
