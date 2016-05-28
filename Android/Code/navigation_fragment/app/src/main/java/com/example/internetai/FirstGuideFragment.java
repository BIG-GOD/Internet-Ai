package com.example.internetai;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.eason.navigation_fragment.R;

/**
 * Created by joho on 2016/4/24.
 */
public class FirstGuideFragment extends GuideFragment {

    private ImageView backgroundImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.first_guide_fragment, null);

        backgroundImage = (ImageView) rootView.findViewById(R.id.firstImage);

        backgroundImage.setBackgroundResource(R.mipmap.back4);

        return rootView;
    }


}
