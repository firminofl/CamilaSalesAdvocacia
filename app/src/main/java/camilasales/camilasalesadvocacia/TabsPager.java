package camilasales.camilasalesadvocacia;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import camilasales.camilasalesadvocacia.fragments.FisicaFragment;
import camilasales.camilasalesadvocacia.fragments.JuridicaFragment;
import camilasales.camilasalesadvocacia.fragments.ProcessoFragment;

/**
 * Created by Filipe Firmino on 21/01/2018.
 */

public class TabsPager extends FragmentPagerAdapter {

    //Titulos das tabs que irão na activity
    //String[] titles = new String[]{"Fisica","Jurídica","Processo"};

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    //Construtor da classe TabsPager recebendo como parametro o Fragment referente a uma das tabs
    public TabsPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
