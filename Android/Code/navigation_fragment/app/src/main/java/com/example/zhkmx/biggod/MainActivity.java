//package com.example.zhkmx.biggod;
//
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.View;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import network.Https;
//
//public class MainActivity extends AppCompatActivity implements ServiceConnection {
//
//
//    TextView tvResult = null;
////    EditText etData = (EditText)findViewById(R.id.etDataMain);
////    MyService.Binder binder = null;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        Button btnBindService = (Button)findViewById(R.id.btnBindServiceMain);
//        Button btnUnbindService = (Button)findViewById(R.id.btnUnbindServiceMain);
//        Button btnSyncData = (Button)findViewById(R.id.btnSyncData);
//        Button btnHttps = (Button)findViewById(R.id.btnHttps);
//        tvResult = (TextView)findViewById(R.id.tvResult);
//
//        btnHttps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////              readGetURL("https://120.27.44.239:32001/state/40060114013633661548&1");
//                readPostURL("https://120.27.44.239:32001/state/history", "user_id=1&device_deviceId=74:d0:2b:05:8d:50");("https://120.27.44.239:32001/state/history", "user_id=1&device_deviceId=74:d0:2b:05:8d:50");
//            }
//        });
//
//        //数据同步
////        btnSyncData.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if(binder != null){
////                    binder.setData("");
//////                    binder.getParameters();
////                }
////            }
////        });
//
////        //绑定服务
////        btnBindService.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                bindService(new Intent(MainActivity.this,MyService.class),MainActivity.this, Context.BIND_AUTO_CREATE);
////            }
////        });
////
////
////        //解绑服务
////        btnUnbindService.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                unbindService(MainActivity.this);
////            }
////        });
////
////
////
////        Button btn = (Button)findViewById(R.id.btnShowDrawer);
////        btn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                startActivity(new Intent(MainActivity.this, Slider.class));
////            }
////        });
////
////        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });
////    }
//
//
//    public void readGetURL(String url){
//        new AsyncTask<String,Void,String>(){
//            @Override
//            protected String doInBackground(String... params) {
//                Https t = new Https();
//                String result = t.GetHttps(params[0]);
//                if(result.isEmpty())
//                {
//                    return "no connection";
//                }
////              System.out.println(result);
////                Log.e("ERROR",result);
//                return result;
//            }
//
//            @Override
//            protected void onPreExecute() {
//
//                super.onPreExecute();
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                if(s.equals("no connection"))
//                    Toast.makeText(getApplicationContext(),"请检查网络连接",Toast.LENGTH_SHORT).show();
//                else {
//                    tvResult.setText(s);
////                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
//                }
//                super.onPostExecute(s);
//            }
//
//            @Override
//            protected void onProgressUpdate(Void... values) {
//                super.onProgressUpdate(values);
//            }
//
//            @Override
//            protected void onCancelled(String s) {
//                super.onCancelled(s);
//            }
//
//            @Override
//            protected void onCancelled() {
//                super.onCancelled();
//            }
//        }.execute(url);
//    }
//
//    public void readPostURL(String url, String data){
//        new AsyncTask<String,Void,String>(){
//
//            @Override
//            protected String doInBackground(String... params) {
//                Https t = new Https();
//                String result = t.PostHttps(params[0],params[1]);
//                if(result.isEmpty())
//                {
//                    return "no connection";
//                }
////              System.out.println(result);
////                Log.e("ERROR",result);
//                return result;
//            }
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                if(s.equals("no connection"))
//                    Toast.makeText(getApplicationContext(),"请检查网络连接",Toast.LENGTH_SHORT).show();
//
//                tvResult.setText(s);
//                super.onPostExecute(s);
//            }
//
//            @Override
//            protected void onProgressUpdate(Void... values) {
//                super.onProgressUpdate(values);
//            }
//
//            @Override
//            protected void onCancelled(String s) {
//                super.onCancelled(s);
//            }
//
//            @Override
//            protected void onCancelled() {
//                super.onCancelled();
//            }
//
//
//        }.execute(url,data);
//    }
//
//
//    @Override
//    public void onServiceConnected(ComponentName name, IBinder service) {
////        binder = (MyService.Binder)service;
//
//    }
//
//    @Override
//    public void onServiceDisconnected(ComponentName name) {
//
//    }
//}
