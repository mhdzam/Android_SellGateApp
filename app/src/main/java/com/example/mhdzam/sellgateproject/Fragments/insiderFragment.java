package com.example.mhdzam.sellgateproject.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class insiderFragment extends Fragment {

    private static final String TAG =MainActivity.class.getSimpleName();
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapter;
    TextView textView;
    List<Product> products;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public insiderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_insider, container, false);

        View rootView = inflater.inflate(R.layout.fragment_insider, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyvlerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        textView = (TextView) rootView.findViewById(R.id.datanotfound);

//        rootView = inflater.inflate(R.layout.fragment_placeholder, container, false);
//        ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.vp_my_account_pager);
////        recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyvlerView);
////        recyclerView.setHasFixedSize(true);
////        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
////        recyclerView.setLayoutManager(mLayoutManager);
////        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabsplacholder);
//        tabLayout.setupWithViewPager(mViewPager);
//
//        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
//
//        mViewPager.setAdapter(mSectionsPagerAdapter);




        RequestData(rootView);


        return rootView;
    }
    private void RequestData(final View rootview)
    {
        int categoryId = getArguments().getInt(ARG_SECTION_NUMBER);
        products = new ArrayList<>();
        String url = WebServiceUrls.getProductsByCategoryIDUrl(Utils.getUserInfo(getContext()).getUsername(),
                Utils.getUserInfo(getContext()).getAuthcode(),Utils.getUserInfo(getContext()).getUserlang(),categoryId);
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Data Loading ...");
        DataLoader.loadJsonDataGetWithProgress(getContext(), url, new resultInterface() {
            @Override
            public void OnSuccess(JSONObject Data) {
                products = JSONParser.json2listproducts(Data);
                // adapter.notifyDataSetChanged();
                setadapter();
                if(products.size()==0)
                    textView.setVisibility(rootview.VISIBLE);
            }

            @Override
            public void OnError(int errorCode, String errorMessage) {
                Utils.showToast(getContext(), errorMessage);
            }

            @Override
            public void OnFailure(int errId) {
                Utils.showToast(getContext(), errId);
            }
        },progressDialog, Request.Priority.HIGH, TAG);

    }
    private void setadapter()
    {
        adapter = new ProductsAdapter(getContext(),products, new ProductsAdapter.OnMenuItemClickedListener() {
            @Override
            public void onItemClicked(MenuItem mi, int position) {

                switch (mi.getItemId()) {
                    case R.id.action_add_favourite:

                        Toast.makeText(getContext(), "Add to favourite   =  "+position, Toast.LENGTH_SHORT).show();

                    case R.id.action_add_basket:

                        new Helper(getContext()).sendRequestREG(products.get(position).getId());

                    default:
                        // Toast.makeText(getContext(), "General Error Occured", Toast.LENGTH_SHORT).show();
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

}
