package com.example.mhdzam.sellgateproject.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.NetworkImageView;
import com.example.mhdzam.sellgateproject.Network.VolleySinglton;
import com.example.mhdzam.sellgateproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MhdZam on 12/16/2017.
 */

public class ProductDetailsAdapter extends RecyclerView.Adapter<ProductDetailsAdapter.ViewHolder>{


        private List<String> listItem;
        private Context context;

        public ProductDetailsAdapter(List<String> listItem, Context context) {
            this.listItem = listItem;
            this.context = context;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View V = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.imageslistlayout,parent,false);
            return new ViewHolder(V,context,listItem);
        }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String ListItem = listItem.get(position);


        ((ViewHolder)holder).imgView.setImageUrl(ListItem,VolleySinglton.getInstance(context).getImageLoader());
        //holder.imgView.setImageURI(Uri.parse("https://sc01.alicdn.com/kf/HTB1.hG2HVXXXXXWXpXXq6xXFXXXf/221468360/HTB1.hG2HVXXXXXWXpXXq6xXFXXXf.jpg"));
    }



        @Override
        public int getItemCount() {
            return listItem.size();
        }

        public  class  ViewHolder extends RecyclerView.ViewHolder{

            public NetworkImageView imgView;
            List<String> arrayListString = new ArrayList<>();

            Context ctx;
            public ViewHolder(View itemView, Context context, List<String> strings) {
                super(itemView);
                this.arrayListString = strings;
                this.ctx = context;


                imgView = (NetworkImageView) itemView.findViewById(R.id.imageslistng);

            }
        }
    }
