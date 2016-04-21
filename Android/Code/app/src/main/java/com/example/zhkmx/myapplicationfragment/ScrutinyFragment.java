package com.example.zhkmx.myapplicationfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhkmx on 2016/4/21.
 */
public class ScrutinyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview  = inflater.inflate(R.layout.fragment_scrutiny, container, false);
        return rootview;
    }
}
