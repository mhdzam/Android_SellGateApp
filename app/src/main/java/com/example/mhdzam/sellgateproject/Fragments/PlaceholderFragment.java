package com.example.mhdzam.sellgateproject.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.mhdzam.sellgateproject.Activities.MainActivity;
import com.example.mhdzam.sellgateproject.Activities.TabActivity;
import com.example.mhdzam.sellgateproject.Adapters.ProductsAdapter;
import com.example.mhdzam.sellgateproject.Adapters.SectionsPagerAdapter;
import com.example.mhdzam.sellgateproject.DataModel.Product;
import com.example.mhdzam.sellgateproject.Helper.Helper;
import com.example.mhdzam.sellgateproject.Helper.Utils;
import com.example.mhdzam.sellgateproject.Interfaces.resultInterface;
import com.example.mhdzam.sellgateproject.Network.DataLoader;
import com.example.mhdzam.sellgateproject.Network.JSONParser;
import com.example.mhdzam.sellgateproject.Network.WebServiceUrls;
import com.example.mhdzam.sellgateproject.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderFragment extends Fragment {

    private List<Product> ListItem;

    private ProgressDialog progressDialog;

    private static final String TAG =MainActivity.class.getSimpleName();
    private static final String ARG_SECTION_NUMBER = "section_number";

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static insiderFragment newInstance(int sectionNumber) {

        insiderFragment fragment = new insiderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_placeholder, container, false);

        ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.vp_my_account_pager);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabsplacholder);
        tabLayout.setupWithViewPager(mViewPager);

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        mViewPager.setAdapter(mSectionsPagerAdapter);


        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.vp_my_account_pager, newInstance(0), TAG);
        fragmentTransaction.commitAllowingStateLoss();


        return rootView;
    }

}

