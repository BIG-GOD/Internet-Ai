package com.example.internetai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eason.navigation_fragment.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joho on 2016/5/27.
 */
public class EquipmentInfoActivity extends Activity {
    private EditText mDeviceName;
    private EditText mDeviceId;
    private Button mAdd;

    private String _name, _id, _date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipment_info_layout);

        mDeviceId = (EditText) findViewById(R.id.id);
        mDeviceName = (EditText) findViewById(R.id.name);
        mAdd = (Button) findViewById(R.id.adddevice);

        _name = null;
        _id = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date(System.currentTimeMillis());
        _date = format.format(date);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                _name = mDeviceName.getText().toString();
                _id = mDeviceId.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("name", _name);
                intent.putExtra("date", _date);
                intent.putExtra("id", _id);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
