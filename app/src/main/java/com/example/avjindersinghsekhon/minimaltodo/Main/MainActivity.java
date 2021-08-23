package com.example.avjindersinghsekhon.minimaltodo.Main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.avjindersinghsekhon.minimaltodo.About.AboutActivity;
import com.example.avjindersinghsekhon.minimaltodo.AddToDo.AddToDoListActivity;
import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultActivity;
import com.example.avjindersinghsekhon.minimaltodo.R;
import com.example.avjindersinghsekhon.minimaltodo.Settings.SettingsActivity;
import com.example.avjindersinghsekhon.minimaltodo.Utility.ScreenSlidePageAdapter;
import com.example.avjindersinghsekhon.minimaltodo.Utility.StoreRetrieveData;
import com.example.avjindersinghsekhon.minimaltodo.Utility.ToDoItem;
import com.example.avjindersinghsekhon.minimaltodo.Utility.TodoNotificationService;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppDefaultActivity {
    private ViewPager viewPager;
    private TabLayout tabs;
    private ScreenSlidePageAdapter adaptadorViewPager;
    private String titulo = "";
    private ArrayList<ToDoTab<ToDoItem>> arregloDeArreglos;
    private StoreRetrieveData storeRetrieveData;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

       // Intent mainIntent = getIntent();

        adaptadorViewPager = new ScreenSlidePageAdapter(getSupportFragmentManager());
      /*  if(mainIntent.hasExtra(AddToDoListActivity.TODOADAPTER)){
            titulo = mainIntent.getStringExtra(MainFragment.TODOLIST);
            adaptadorViewPager.agregarFragmento(MainFragment.newInstance(),titulo);
        }*/

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                MainFragment fragmentoActual = (MainFragment) adaptadorViewPager.getItem(i);
                fragmentoActual.setmToDoItemsArrayList(arregloDeArreglos.get(i).getItems());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setAdapter(adaptadorViewPager);
        tabs.setupWithViewPager(viewPager);

        storeRetrieveData = new StoreRetrieveData(this, MainFragment.FILENAME);
        arregloDeArreglos = getLocallyStoredData(storeRetrieveData);

        if(arregloDeArreglos.size() == 0)
            cargarListaDefault();
        cargarFragmentos();
        crearTabs();


        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                //fragmentoActual.
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
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    private void cargarFragmentos() {
        int i = 0;
        for(ToDoTab<ToDoItem> lista: arregloDeArreglos){
            adaptadorViewPager.agregarFragmento(MainFragment.newInstance(i), lista.getTitulo());
            i++;
        }
    }

    private void agregarIconoAUltimoTab() {
        TabLayout.Tab tab = tabs.getTabAt(viewPager.getAdapter().getCount() - 1);
        if (tab != null) {
            tab.setIcon(R.drawable.outline_playlist_add_check_white_24dp);
        }
    }
    private void agregarTabsIniciales(ViewPager viewPager) {
        adaptadorViewPager = new ScreenSlidePageAdapter(getSupportFragmentManager());
        adaptadorViewPager.agregarFragmento(MainFragment.newInstance(adaptadorViewPager.getCount()), "Principal");

        viewPager.setAdapter(adaptadorViewPager);
    }
    public Fragment agregarTab(String titulo){
        // el título del tab
        adaptadorViewPager.agregarFragmento(MainFragment.newInstance(), titulo);
        adaptadorViewPager.notifyDataSetChanged();
        agregarIconoAUltimoTab();
        // Enfocar la penúltima 100 milisegundos después
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Seleccionar la penúltima pestaña
                tabs.setScrollPosition(viewPager.getAdapter().getCount() - 2, 0, true);
                viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 2);
            }
        }, 100);
        return MainFragment.newInstance();
    }
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {

            if (requestCode == MainFragment.REQUEST_ID_TODO_LIST) {
                String titulo = data.getStringExtra(MainFragment.TODOLIST);
                arregloDeArreglos.add(new ToDoTab<ToDoItem>(new ArrayList<ToDoItem>(), titulo));

                try {
                    storeRetrieveData.saveArrayToFile(arregloDeArreglos);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                adaptadorViewPager.agregarFragmento(MainFragment.newInstance(arregloDeArreglos.size()-1), titulo);
                crearTabs();
            }

        }
        Fragment fragmentTab = adaptadorViewPager.getItem(tabs.getSelectedTabPosition());
        fragmentTab.onActivityResult(requestCode, resultCode, data);
    }

    private void crearTabs(){
        tabs.removeAllTabs();
        for (String titulo: adaptadorViewPager.getListaDeTitulosDeFragmentos()) {
            tabs.addTab(tabs.newTab().setText(titulo));

        }
    }

    private void cargarListaDefault (){
        arregloDeArreglos.add(new ToDoTab<ToDoItem>(new ArrayList<ToDoItem>(), "Principal"));
    }
    public ArrayList<ToDoTab<ToDoItem>> getLocallyStoredData(StoreRetrieveData storeRetrieveData) {
        ArrayList<ToDoTab<ToDoItem>> arrayItems = null;


        arrayItems = storeRetrieveData.loadArrayFromFile();


        if (arrayItems == null) {
            arrayItems = new ArrayList<ToDoTab<ToDoItem>>();
        }
        return arrayItems;

    }
    @Override
    public void onPause() {
        super.onPause();
        try {
            storeRetrieveData.saveArrayToFile(arregloDeArreglos);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

}


