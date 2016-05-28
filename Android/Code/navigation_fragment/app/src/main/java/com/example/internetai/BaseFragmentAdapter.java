package com.example.internetai;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by joho on 2016/4/24.
 */
public class BaseFragmentAdapter extends FragmentStatePagerAdapter {
    private List<GuideFragment> list;

    public BaseFragmentAdapter(FragmentManager fm, List<GuideFragment> list) {
        super(fm);
        this.list = list;
    }

    public BaseFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return list.get(position);
    }

    @Override
    public int getCount() {

        return list.size();
    }
}
