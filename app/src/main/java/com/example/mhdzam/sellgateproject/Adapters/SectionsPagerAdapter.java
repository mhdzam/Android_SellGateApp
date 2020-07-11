package com.example.mhdzam.sellgateproject.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mhdzam.sellgateproject.Fragments.PlaceholderFragment;

/**
 * Created by MhdZam on 1/23/2018.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position+1);
    }

    @Override
    public int getCount() {
        // Show 7 total pages.
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {


        switch (position) {
            case 0:
                return "CLOTHES";
            case 1:
                return "EQUIPMENTS";
            case 2:
                return "SCREENS";
            case 3:
                return "LAPTOPS";
            case 4:
                return "MOBILES";
            case 5:
                return "BAGS";
            case 6:
                return "BUILDING MATERIALS";
        }
        return null;
    }
}