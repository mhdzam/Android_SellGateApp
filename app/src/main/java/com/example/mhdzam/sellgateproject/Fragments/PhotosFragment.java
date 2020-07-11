package com.example.mhdzam.sellgateproject.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.mhdzam.sellgateproject.Adapters.OrdersAdapter;
import com.example.mhdzam.sellgateproject.Adapters.ProductsAdapter;
import com.example.mhdzam.sellgateproject.DataModel.Order;
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
public class PhotosFragment extends Fragment {

    RecyclerView recyclerView;
    List<Order> orders;
    TextView textView;
    private  RecyclerView.Adapter adapter;
    private static final String TAG =MainActivity.class.getSimpleName();
    public PhotosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_photos, container, false);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.RecyvlerViewOrder);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        textView = (TextView) rootview.findViewById(R.id.orderdatanotfound);
//        RequestData(rootview);

        return rootview;
    }


    private void setadapter()
    {
        adapter = new OrdersAdapter(getContext(),orders);
        recyclerView.setAdapter(adapter);
    }
}
