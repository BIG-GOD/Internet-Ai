package com.example.eason.navigation_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.internetai.AddEquipmentActivity;
import com.example.internetai.AddInfomationActivity;
import com.example.internetai.ChangePasswordActivity;

/**
 * Created by eason on 2016-05-27.
 */
public class Setting_fragment extends Fragment {
    private Button mChangePassword;
    private Button mAddInfomation;
    private Button mAddEquipment;

    private String _username;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.setting_layout,container,false);
        mChangePassword = (Button) root.findViewById(R.id.changepassword);
        mAddInfomation = (Button) root.findViewById(R.id.addinfomation);
        mAddEquipment = (Button) root.findViewById(R.id.addequipment);

        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        _username = bundle.getString("username");


        mChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                intent.putExtra("username", _username);
                startActivity(intent);
            }
        });

        mAddInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AddInfomationActivity.class);
                intent.putExtra("username", _username);
                startActivity(intent);
            }
        });

        mAddEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AddEquipmentActivity.class);
                intent.putExtra("username", _username);
                startActivity(intent);
            }
        });
        return root;
    }
}
