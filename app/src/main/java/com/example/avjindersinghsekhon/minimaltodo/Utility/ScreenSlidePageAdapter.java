package com.example.avjindersinghsekhon.minimaltodo.Utility;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.avjindersinghsekhon.minimaltodo.Main.MainFragment;

public class ScreenSlidePageAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private final int NUM_PAGES = 4;
    public ScreenSlidePageAdapter(FragmentManager fm, Context c) {
        super(fm);
        mContext = c;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

    @Override
    public Fragment getItem(int position) {
        return new MainFragment();
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
