package com.example.avjindersinghsekhon.minimaltodo.Utility;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.avjindersinghsekhon.minimaltodo.Main.MainFragment;
import com.example.avjindersinghsekhon.minimaltodo.R;

import java.util.ArrayList;
import java.util.List;

public class ScreenSlidePageAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    private final int NUM_PAGES = 4;
    private final List<Fragment> listaDeFragmentos = new ArrayList<>();
    private final List<String> listaDeTitulosDeFragmentos = new ArrayList<>();

    public ScreenSlidePageAdapter(FragmentManager fm) {
        super(fm);

    }
    public ScreenSlidePageAdapter(FragmentManager fm, Context c) {
        super(fm);
        mContext = c;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position >= getCount() - 1) return null;
        return listaDeTitulosDeFragmentos.get(position);
    }

    @Override
    public Fragment getItem(int position) { return listaDeFragmentos.get(position);  }

    @Override
    public int getCount() {
        return listaDeFragmentos.size();
    }

    public void agregarFragmento(Fragment fragment, String title) {
        listaDeFragmentos.add(fragment);
        listaDeTitulosDeFragmentos.add(title);
        notifyDataSetChanged();
    }

    public List<String> getListaDeTitulosDeFragmentos() {
        return listaDeTitulosDeFragmentos;
    }

}
