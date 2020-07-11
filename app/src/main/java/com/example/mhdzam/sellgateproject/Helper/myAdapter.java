package com.example.mhdzam.sellgateproject.Helper;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.example.mhdzam.sellgateproject.Activities.ProductDetailsActivity;
import com.example.mhdzam.sellgateproject.Network.VolleySinglton;
import com.example.mhdzam.sellgateproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by MhdZam on 12/13/2017.
 */

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private List<listItem> listItem;
    private Context context;
    private Random mRandom = new Random();

    public myAdapter(List<com.example.mhdzam.sellgateproject.Helper.listItem> listItem, Context context) {
        this.listItem = listItem;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View V = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(V,context,listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        listItem ListItem = listItem.get(position);
       // holder.imgView.getLayoutParams().height = getRandomIntInRange(250,75);
        holder.textViewHead.setText(ListItem.getHead());
        holder.textViewDesc.setText(ListItem.getDesc());
        ((ViewHolder)holder).imgView.setImageUrl(ListItem.getImgURL(), VolleySinglton.getInstance(context).getImageLoader());
        }
    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textViewHead;
        public TextView textViewDesc;
        public NetworkImageView imgView;

        List<listItem> arrayListString = new ArrayList<listItem>();

        Context ctx;
        public ViewHolder(View itemView, Context context, List<listItem> strings) {
            super(itemView);
            this.arrayListString = strings;
            this.ctx= context;

            itemView.setOnClickListener(this);
//            textViewHead = (TextView) itemView.findViewById(R.id.textView);
//            textViewDesc = (TextView) itemView.findViewById(R.id.vewdesc);
//            imgView = (NetworkImageView) itemView.findViewById(R.id.networkimgview);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            listItem str = this.arrayListString.get(position);
            Intent intent = new Intent(ctx,ProductDetailsActivity.class);
            this.ctx.startActivity(intent);
        }
    }
}