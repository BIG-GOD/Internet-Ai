package com.example.eason.navigation_fragment;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhkmx.biggod.CustomLineChart;
import com.example.zhkmx.biggod.Https;
import com.github.mikephil.charting.charts.LineChart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by eason on 2016-04-22.
 */
public class FragmentDemo3 extends Fragment {
    List<Integer> mDataYs=null;
    NoScrollListView mainlistview;
    SwipeRefreshLayout mSwipeLayout ;
    View root;
    Button btn_syn=null;
    List<String> parent=null;
    List<String> updatetime=null;
    Map<String, Map<String,String>> history=null;
    Map<String,List<String>> map=null;
    NotificationManager notificationManager=null;
    Notification.Builder builder=null;
    TextView firetv=null;
    TextView gastv=null;
    int tag=0;
    Intent mIntent=null;
    Db mydatabase=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root=inflater.inflate(R.layout.fragmentdemo3_main,container,false);
        history=new HashMap<String, Map<String,String>>();
        updatetime=new ArrayList<String>();
        mDataYs=new ArrayList<Integer>();
        mainlistview= (NoScrollListView) root.findViewById(R.id.main_expandablelistview);
        mSwipeLayout = (SwipeRefreshLayout) root.findViewById(R.id.id_swipe_ly);
        builder = new Notification.Builder(getActivity());
        //Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
        mIntent=new Intent(getActivity(),MainActivity.class);
        Bundle activity_bundle=getArguments();
        //设置标志在目标activity中处理事件,1 fire安全co危险 2fire危险co危险 3fire危险co安全 4fire安全co安全
        builder.setSmallIcon(R.drawable.humidity);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.humidity));
        builder.setAutoCancel(true);
        //notification.defaults = Notification.DEFAULT_VIBRATE;// 设置默认震动
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setContentTitle("监控警报");
        //单例模式创建dbhellper唯一实例
        mydatabase=new Db(getActivity(),"my_info.db",null,1);

        if(activity_bundle!=null)
        {
            //设置标志在目标activity中处理事件,1 fire安全co危险 2fire危险co危险 3fire危险co安全 4fire安全co安全
            int signal=activity_bundle.getInt("TTT");
            firetv= (TextView) root.findViewById(R.id.fireText);
            gastv= (TextView) root.findViewById(R.id.gasText);
            if(signal==1)
            {
                firetv.setText("安全");
                firetv.setTextColor(Color.RED);
                gastv.setText("危险");
                gastv.setTextColor(Color.BLUE);

            }
            else if(signal==2)
            {
                firetv.setText("危险");
                firetv.setTextColor(Color.RED);
                gastv.setText("危险");
                gastv.setTextColor(Color.RED);
            }
            else if(signal==3)
            {
                firetv.setText("危险");
                firetv.setTextColor(Color.RED);
                gastv.setText("安全");
                gastv.setTextColor(Color.BLUE);
            }
            else if(signal==4)
            {
                firetv.setText("安全");
                firetv.setTextColor(Color.BLUE);
                gastv.setText("安全");
                gastv.setTextColor(Color.BLUE);
            }
        }
        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();
        mainlistview.setAdapter(new MyAdapter());

        mSwipeLayout .setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scrutinyGetURL("https://120.27.44.239:32001/state/b8:27:eb:ba:d6:b9&1");
                refreshPostURL("https://120.27.44.239:32001/state/history", "user_id=1&device_deviceId=b8:27:eb:ba:d6:b9");
                mSwipeLayout.setRefreshing(false);


            }
        });
        mainlistview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                System.out.println("click the screen!");
                boolean expanded = parent.isGroupExpanded(groupPosition);
                if(!expanded)
                {
                    readSQLite(groupPosition);
                    mainlistview.expandGroup(groupPosition);
                    TextView tv=(TextView)root.findViewById(R.id.current_parameter);
                    tv.setText(FragmentDemo3.this.parent.get(groupPosition));
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
    public void scrutinyGetURL(String url)
    {
        AsyncTask<String, Void, String> execute = new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                Https t = new Https();
                String result = t.GetHttps(params[0]);
                if (result.isEmpty()) {
                    return "no connection";
                }
                return result;
            }
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                String gas=null;
                String fire=null;
                List<String> temp=new ArrayList<String>();
                if (s.equals("no connection"))
                    Toast.makeText(getActivity().getApplicationContext(), "get服务器开小差了。。。", Toast.LENGTH_SHORT).show();
                else {
                    try {
                        System.out.println("jsonobject"+s);
                        JSONObject jsonObject=new JSONObject(s);
                        gas=jsonObject.getString("gas");
                        fire=jsonObject.getString("fire");
                        System.out.println("gas:" + gas+"fire:"+fire);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                super.onPostExecute(s);
                if(Integer.parseInt(fire)==1)
                {
                    notificationManager = (NotificationManager) getActivity().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        builder.setTicker("火警监控情况存在异常！");
                        builder.setContentText("火警监控报警");
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.firebmp);
                        builder.setLargeIcon(bitmap);
                        tag=1;
                        Bundle mbundle=new Bundle();
                        mbundle.putInt("TAG", tag);
                        mIntent.putExtras(mbundle);
                        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 3, mIntent, 0);
                        builder.setContentIntent(pendingIntent);
                        notificationManager.notify(0, builder.build());
                    }
                    firetv= (TextView) root.findViewById(R.id.fireText);
                    firetv.setTextColor(Color.RED);
                    firetv.setText("危险");

                }
                else
                {
                    firetv= (TextView) root.findViewById(R.id.fireText);
                    firetv.setTextColor(Color.BLUE);
                    firetv.setText("安全");
                    tag=4;
                }
                if(Integer.parseInt(gas)==0)
                {
                    notificationManager = (NotificationManager) getActivity().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        builder.setTicker("气体监控情况存在异常！");
                        builder.setContentText("气体监控报警");
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.gas);
                        builder.setLargeIcon(bitmap);
                        tag=3;
                        Bundle mbundle=new Bundle();
                        mbundle.putInt("TAG", tag);
                        mIntent.putExtras(mbundle);
                        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 3, mIntent, 0);
                        builder.setContentIntent(pendingIntent);
                        notificationManager.notify(0, builder.build());
                    }
                    gastv= (TextView) root.findViewById(R.id.gasText);
                    gastv.setTextColor(Color.RED);
                    gastv.setText("危险");
                }
                else
                {
                    gastv= (TextView) root.findViewById(R.id.gasText);
                    gastv.setTextColor(Color.BLUE);
                    gastv.setText("安全");
                    tag=4;
                }
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onCancelled(String s) {
                super.onCancelled(s);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }
        }.execute(url);
    }
    public void refreshPostURL(String url, String data){

        AsyncTask<String, Void, String> execute = new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                Https t = new Https();
                String result = t.PostHttps(params[0], params[1]);
                if (result.isEmpty()) {
                    return "no connection";
                }
                return result;
            }
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                List<String> temp=new ArrayList<String>();
                if (s.equals("no connection"))
                    Toast.makeText(getActivity().getApplicationContext(), "post服务器开小差了。。。", Toast.LENGTH_SHORT).show();
                else {
                    System.out.println("SSSSSSS"+s);
                    try {
                        JSONArray jsonArray = new JSONArray(s);
                        int iSize = jsonArray.length();
                        System.out.println("iSize" + iSize);
                        for (int i = 0; i < iSize; i++) {
                            JSONObject jsonItem = jsonArray.getJSONObject(i);
                            String updateItem = jsonItem.getString("updatetime");
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            SimpleDateFormat sdf = new SimpleDateFormat(" MM月dd日HH:mm ");
                            Date date= null;
                            try {
                                date = df.parse(updateItem);
                                updateItem=sdf.format(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            temp.add(updateItem);
                            Map<String, String> historyItem = new HashMap<String, String>();
                            historyItem.put("temprature", jsonItem.getString("temprature"));
                            historyItem.put("humidity", jsonItem.getString("humidity"));
                            history.put(updateItem, historyItem);
                            //System.out.println("DDDDDD" + history.get(updateItem).toString());
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
//                    Set<String> keyset = history.keySet();
//                    System.out.println("keyset" + keyset);
//                    if(updatetime.size()<1||updatetime==null)
//                    {
//                        System.out.println("updatetime is null");
//                        updatetime.addAll(keyset);
//                        Collections.sort(updatetime);
//                        System.out.println("updatetime" + updatetime);
//                    }
                    System.out.println("history" + history);
                    Collections.sort(temp);
                    updatetime=temp;
                    System.out.println("tempsize"+updatetime.size()+"historysize:"+history.size());
                    //mydatabase=new Db(getActivity(),"parameter.db",null,1);
                    for(int i=0;i<history.size();i++)
                    {
                        String _updatetime=updatetime.get(i);
                        String _humidity=history.get(updatetime.get(i)).get("humidity");
                        String _temprature=history.get(updatetime.get(i)).get("temprature");
                        System.out.println("updatetime)" + _updatetime);
                        if(queryData(_updatetime)==null)
                        {
                            insertData(_updatetime,_temprature,_humidity);
                            System.out.println("insert" + _updatetime);
                        }

                    }
                }

                super.onPostExecute(s);
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onCancelled(String s) {
                super.onCancelled(s);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }
        }.execute(url, data);
    }

    //删除数据库表所有信息
    //db.delete("history", "1", null);
    //向数据库中插入和更新数据
    public void insertData(String updatetime,String temprature,String humidity){

        //获取数据库对象
        SQLiteDatabase db = Db.getInstance(getContext()).getWritableDatabase();
        //if(db.query("history",null,null,null,null,null,null)!=null)
        //使用execSQL方法向表中插入数据
        db.execSQL("insert into history(humidity,temprature,updatetime) values(?,?,?)", new Object[]{humidity, temprature, updatetime});
        //使用insert方法向表中插入数据
        //db.close();
    }

    //查询数据
    public Map<String,Map<String,String>> queryData(String selectionArg){
        Map<String,Map<String,String>> result=new HashMap<String,Map<String,String>>();
        //获得数据库对象
        SQLiteDatabase db = Db.getInstance(getContext()).getReadableDatabase();
        Cursor cursor=null;
        //条件查询参数，可以传入一个String[]数组,这里只查询更新时间
        if(selectionArg=="null")
        {
             cursor= db.query("history", null, null, null, null, null, null);
            System.out.println("cusornull:" + cursor.getCount());
        }
        else
        {
            String[] selectionArgs={selectionArg};
            //查询表中的数据
            //query  (String table, String[] columns, String selection, String[] selectionArgs,String groupBy, String having, String orderBy, String limit)
            cursor = db.query("history", null, "updatetime=?", selectionArgs, null, null, null);
            System.out.println("cusor:" + cursor.getCount());
        }

        if(cursor.getCount()==0)
        {
            System.out.println("cusorCount=0");
            cursor.close();//关闭结果集
            //db.close();//关闭数据库对象
            return null;
        }
        else
        {
            System.out.println("cusorCount:" + cursor.getCount());
        //获取name列的索引
            int tempIndex = cursor.getColumnIndex("temprature");
            int timeIndex=cursor.getColumnIndex("updatetime");
            //获取level列的索引
            int humIndex = cursor.getColumnIndex("humidity");
            for (cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext()) {
                Map<String, String> historyItem = new HashMap<String, String>();
                historyItem.put("temprature",  cursor.getString(tempIndex));
                historyItem.put("humidity", cursor.getString(humIndex));
                result.put(cursor.getString(timeIndex), historyItem);
            }
            cursor.close();//关闭结果集
            //db.close();//关闭数据库对象
            return result;
        }

    }
    //读取数据库中缓存数据
    public void readSQLite(final int GroupPos)
    {
        List<Integer> tempDataYs=new ArrayList<Integer>();
        List<String> tempTime=new ArrayList<String>();
        Set<String> keyset=new HashSet<String>();
        Map<String,Map<String,String>> result=new HashMap<String,Map<String,String>>();
        result=queryData("null");
        System.out.println("readsqliteresult" + result);
        if(result!=null)
        {
            keyset = result.keySet();
            tempTime.addAll(keyset);
            Collections.sort(tempTime);
            System.out.println("timeTime" + tempTime);
            updatetime=tempTime;
            for (int i = 0; i < result.size(); i++) {
                if(GroupPos==0)
                {
                    tempDataYs.add(Integer.parseInt(result.get(updatetime.get(i)).get("temprature")));
                }
                else if(GroupPos==1)
                    tempDataYs.add(Integer.parseInt(result.get(updatetime.get(i)).get("humidity")));
            }
            mDataYs=tempDataYs;
        }
        //展开某个group
        mainlistview.expandGroup(GroupPos);

    }
//    public void readPostURL(String url, String data, final int GroupPos){
//        final int GroupPosition=GroupPos;
//        final String para_type=FragmentDemo3.this.parent.get(GroupPosition);
//        AsyncTask<String, Void, String> execute = new AsyncTask<String, Void, String>() {
//
//            @Override
//            protected String doInBackground(String... params) {
//                Https t = new Https();
//                String result = t.PostHttps(params[0], params[1]);
//                if (result.isEmpty()) {
//                    return "no connection";
//                }
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
//                if (s.equals("no connection"))
//                    Toast.makeText(getActivity().getApplicationContext(), "服务器开小差了。。。", Toast.LENGTH_SHORT).show();
//                else {
//                    try {
//                        JSONArray jsonArray = new JSONArray(s);
//                        int iSize = jsonArray.length();
//                        System.out.println("iSize" + iSize);
//                        for (int i = 0; i < iSize; i++) {
//                            JSONObject jsonItem = jsonArray.getJSONObject(i);
//                            System.out.println("AAAAA" + jsonItem.getString("updatetime").toString());
//                            String updateItem = jsonItem.getString("updatetime");
//
//                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                            SimpleDateFormat sdf = new SimpleDateFormat(" MM月dd日HH:00 ");
//                            Date date= null;
//                            try {
//                                date = df.parse(updateItem);
//                                updateItem=sdf.format(date);
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//                            updatetime.add(updateItem);
//                            Map<String, String> historyItem = new HashMap<String, String>();
//                            historyItem.put("temprature", jsonItem.getString("temprature"));
//                            historyItem.put("humidity", jsonItem.getString("humidity"));
//                            history.put(updateItem, historyItem);
//                            System.out.println("DDDDDD" + history.get(updateItem).toString());
//                        }
//                    } catch (JSONException e) {
//                        throw new RuntimeException(e);
//                    }
//                    //在此处进行处理ui的操作
//                    Collections.sort(updatetime);
//                    for (int i = 0; i < history.size(); i++) {
//                        mDataYs.add(Integer.parseInt(history.get(updatetime.get(i)).get(para_type)));
//                        //System.out.print(mDataYs.get(i));
//                    }
//                    //展开某个group
//                    mainlistview.expandGroup(GroupPosition);
//                    //TextView tv=(TextView)root.findViewById(R.id.current_parameter);
//                    //tv.setText(FragmentDemo3.this.parent.get(GroupPos));
//
//
//                }
//
//
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
//        }.execute(url, data);
//    }
    @Override
    public void onStart() {
        super.onStart();

    }
    public void initData() {
        parent = new ArrayList<String>();
        parent.add("temprature");
        parent.add("humidity");

        map = new HashMap<String,List<String>>();

        List<String> list1 = new ArrayList<String>();
        list1.add("child1-1");
        map.put("temprature", list1);

        List<String> list2 = new ArrayList<String>();
        list2.add("child2-1");
        map.put("humidity", list2);

    }
    class MyAdapter extends BaseExpandableListAdapter{
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            String key = parent.get(groupPosition);
            return (map.get(key).get(childPosition));
        }

        //得到子item的ID
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        //设置子item的组件
        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            //String key = FragmentDemo3.this.parent.get(groupPosition);
            //String info = map.get(key).get(childPosition);
            if (convertView == null) {

                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.child_mian, null);
            }
            LineChart lineChart = (LineChart) convertView
                    .findViewById(R.id.child_chart);
            CustomLineChart customLineChart=new CustomLineChart();
            customLineChart.setChartName("Temprature");
            customLineChart.setData(mDataYs);
            customLineChart.setLimitName("Great");
            customLineChart.setLimitVal(25f);
            customLineChart.setLineName("line");
            customLineChart.setxAxislabel(getXAxisShowLable((ArrayList<String>)updatetime));
            customLineChart.generateChart(lineChart);
            //tv.setText(info);

            return lineChart;
        }
        private ArrayList<String> getXAxisShowLable(ArrayList<String> arrayList) {

            return arrayList;
        }
        //获取当前父item下的子item的个数
        @Override
        public int getChildrenCount(int groupPosition) {
            String key = parent.get(groupPosition);
            int size=map.get(key).size();
            return size;
        }
        //获取当前父item的数据
        @Override
        public Object getGroup(int groupPosition) {
            return parent.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return parent.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
        //设置父item组件
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.layout_parent, null);
            ImageView iv= (ImageView) convertView.findViewById(R.id.group_image);
            TextView tv= (TextView) convertView.findViewById(R.id.label_name);
            tv.setText(FragmentDemo3.this.parent.get(groupPosition));
            TextView current_para= (TextView) convertView.findViewById(R.id.current_parameter);
            //Db mydatabase=new Db(getActivity(),"parameter.db",null,1);
            SQLiteDatabase db = Db.getInstance(getContext()).getReadableDatabase();

            Cursor cursor = db.query("history", null, null, null, null, null, null);

            if(cursor.getCount()!=0)
            {
                int tempIndex=cursor.getColumnIndex("temprature");
                int humIndex=cursor.getColumnIndex("humidity");
                //System.out.print(tempIndex+"tempindex"+humIndex);
                cursor.moveToLast();
                if(groupPosition==0)

                    current_para.setText(cursor.getString(tempIndex)+"℃");
                else if(groupPosition==1)
                    current_para.setText(cursor.getString(humIndex)+"%");
            }
           else
            {
                if(groupPosition==0)

                    current_para.setText("℃");
                else if(groupPosition==1)
                    current_para.setText("%");
            }
            cursor.close();
            if(groupPosition==0)
            {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.temprature);
                iv.setImageBitmap(bitmap);
            }
            else if(groupPosition==1)
            {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.humidity);
                iv.setImageBitmap(bitmap);
            }


            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
