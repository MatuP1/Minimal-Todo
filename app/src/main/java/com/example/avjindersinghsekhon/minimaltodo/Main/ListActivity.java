package com.example.avjindersinghsekhon.minimaltodo.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.avjindersinghsekhon.minimaltodo.About.AboutActivity;
import com.example.avjindersinghsekhon.minimaltodo.AddToDo.AddToDoListActivity;
import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultActivity;
import com.example.avjindersinghsekhon.minimaltodo.R;
import com.example.avjindersinghsekhon.minimaltodo.Settings.SettingsActivity;
import com.example.avjindersinghsekhon.minimaltodo.Utility.ScreenSlidePageAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.avjindersinghsekhon.minimaltodo.About.AboutActivity;
import com.example.avjindersinghsekhon.minimaltodo.AddToDo.AddToDoListActivity;
import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultActivity;
import com.example.avjindersinghsekhon.minimaltodo.R;
import com.example.avjindersinghsekhon.minimaltodo.Settings.SettingsActivity;
import com.example.avjindersinghsekhon.minimaltodo.Utility.ScreenSlidePageAdapter;
public class ListActivity extends AppDefaultActivity {
        //private ViewPager viewPager;
        //private TabLayout tabLayout;
        private TabLayout tabs;
        private ScreenSlidePageAdapter adaptadorViewPager;
        private String titulo = "";

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //viewPager = (ViewPager) findViewById(R.id.viewPager);
            //tabLayout = (TabLayout) findViewById(R.id.tabLayout);
            tabs = (TabLayout) findViewById(R.id.tabs);
            tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
            tabs.addTab(tabs.newTab().setText("Titulo"));
            Intent mainIntent = getIntent();

            adaptadorViewPager = new ScreenSlidePageAdapter(getSupportFragmentManager());
            if(mainIntent.hasExtra(AddToDoListActivity.TODOADAPTER)){
                titulo = mainIntent.getStringExtra(MainFragment.TODOLIST);
                adaptadorViewPager.agregarFragmento(MainFragment.newInstance(),titulo);
            }


            adaptadorViewPager.agregarFragmento(MainFragment.newInstance(), titulo);
        /*
        viewPager.setAdapter(adaptadorViewPager);
        tabLayout.setupWithViewPager(viewPager);

        //agregarIconoAUltimoTab();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == viewPager.getAdapter().getCount() - 1) {
                   // agregarTab("titulo");
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        */
            //agregarIconoAUltimoTab();
            tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });

            // Connect the tab layout with the view pager. This will
            //   1. Update the tab layout when the view pager is swiped
            //   2. Update the view pager when a tab is selected
            //   3. Set the tab layout's tab names with the view pager's adapter's titles
            //      by calling onPageTitle()
            //tabLayout.setupWithViewPager(mPager);
            final android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
            }
        }/*
    private void agregarIconoAUltimoTab() {
        TabLayout.Tab tab = tabLayout.getTabAt(viewPager.getAdapter().getCount() - 1);
        if (tab != null) {
            tab.setIcon(R.drawable.outline_playlist_add_check_white_24dp);
        }
    }
    private void agregarTabsIniciales(ViewPager viewPager) {
        adaptadorViewPager = new ScreenSlidePageAdapter(getSupportFragmentManager());
        adaptadorViewPager.agregarFragmento(MainFragment.newInstance(adaptadorViewPager), "Principal");

        viewPager.setAdapter(adaptadorViewPager);
    }
    public Fragment agregarTab(String titulo){
        // el título del tab
        adaptadorViewPager.agregarFragmento(MainFragment.newInstance(adaptadorViewPager), titulo);
        adaptadorViewPager.notifyDataSetChanged();
        agregarIconoAUltimoTab();
        // Enfocar la penúltima 100 milisegundos después
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Seleccionar la penúltima pestaña
                tabLayout.setScrollPosition(viewPager.getAdapter().getCount() - 2, 0, true);
                viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 2);
            }
        }, 100);
        return MainFragment.newInstance(adaptadorViewPager);
    }*/
        @Override
        protected int contentViewLayoutRes() {
            return R.layout.activity_main;
        }

        @NonNull
        @Override
        protected Fragment createInitialFragment() {
            return MainFragment.newInstance();
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.aboutMeMenuItem:
                    Intent i = new Intent(this, AboutActivity.class);
                    startActivity(i);
                    return true;
//            case R.id.switch_themes:
//                if(mTheme == R.style.CustomStyle_DarkTheme){
//                    addThemeToSharedPreferences(LIGHTTHEME);
//                }
//                else{
//                    addThemeToSharedPreferences(DARKTHEME);
//                }
//
////                if(mTheme == R.style.CustomStyle_DarkTheme){
////                    mTheme = R.style.CustomStyle_LightTheme;
////                }
////                else{
////                    mTheme = R.style.CustomStyle_DarkTheme;
////                }
//                this.recreate();
//                return true;
                case R.id.preferences:
                    Intent intent = new Intent(this, SettingsActivity.class);
                    startActivity(intent);
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }

}




