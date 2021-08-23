package com.example.avjindersinghsekhon.minimaltodo.AddToDo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultActivity;
import com.example.avjindersinghsekhon.minimaltodo.Main.MainFragment;
import com.example.avjindersinghsekhon.minimaltodo.R;
import com.example.avjindersinghsekhon.minimaltodo.Utility.ScreenSlidePageAdapter;

public class AddToDoListActivity extends AppDefaultActivity{
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ScreenSlidePageAdapter adaptadorViewPager;
    public static final String TODOADAPTER = "com.avjindersinghsekhon.com.avjindersinghsekhon.minimaltodo.ScreenSlidePageAdapter";
        @SuppressWarnings("deprecation")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Intent intent = getIntent();


        }

        @Override
        protected int contentViewLayoutRes() {
            return R.layout.activity_add_to_do;
        }

        @NonNull
        @Override
        protected Fragment createInitialFragment() { return AddToDoListFragment.newInstance(adaptadorViewPager);
        }

        @Override
        protected void onResume() {
            super.onResume();
        }

    }

