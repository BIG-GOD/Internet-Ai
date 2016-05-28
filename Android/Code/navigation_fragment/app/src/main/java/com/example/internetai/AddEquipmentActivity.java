package com.example.internetai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.eason.navigation_fragment.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.DeviceBean;

/**
 * Created by joho on 2016/5/26.
 */
public class AddEquipmentActivity extends Activity {

    private Button mAddBtn;
    private ListView mListView;

    private List<Map<String, String>> d;
    final static int GIFT_REQUEST_CODE = 1000;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_equipment_layout);
        mAddBtn = (Button) findViewById(R.id.add);
        mListView = (ListView) findViewById(R.id.listview);
        d = new ArrayList<Map<String, String> >();

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddEquipmentActivity.this, EquipmentInfoActivity.class);
                startActivityForResult(intent, GIFT_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null && requestCode == GIFT_REQUEST_CODE && resultCode == RESULT_OK) {
            DeviceBean now = new DeviceBean();
            now.setDate(data.getStringExtra("date"));
            now.setName(data.getStringExtra("name"));
            now.setId(data.getStringExtra("id"));
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", now.getName());
            map.put("date", now.getDate());
            String[] from = {"name", "date"};
            int[] to = {R.id.devicename, R.id.devicedate};
            d.add(map);
            adapter = new SimpleAdapter(this, d, R.layout.view_layout, from, to);
            mListView.setAdapter(adapter);
        }
    }
}
