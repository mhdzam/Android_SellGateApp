package com.example.mhdzam.sellgateproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.mhdzam.sellgateproject.DataModel.Product;
import com.example.mhdzam.sellgateproject.Network.VolleySinglton;
import com.example.mhdzam.sellgateproject.Activities.ProductDetailsActivity;
import com.example.mhdzam.sellgateproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Product> productList;
    OnMenuItemClickedListener onMenuItemClickedListener;
int pos;
    private Product album;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, price;
        public ImageView  overflow;
        public NetworkImageView thumbnail;
        public MenuItem favitem;
        List<Product> arrayListString = new ArrayList<>();
        private Context ctx;
        private View productCard;
        public int position;

        public MyViewHolder(View view, List<Product> products, final Context ctx) {
            super(view);

            arrayListString = products;
            this.ctx = ctx;
            title = (TextView) view.findViewById(R.id.title);
            price = (TextView) view.findViewById(R.id.price);
            thumbnail = (NetworkImageView) view.findViewById(R.id.thumbnail);
            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Product str = arrayListString.get(position);
                    Intent intent = new Intent(ctx, ProductDetailsActivity.class);
                    intent.putExtra("itemId", str.getId());
                    ctx.startActivity(intent);
                }
            });
            overflow = (ImageView) view.findViewById(R.id.overflow);
            productCard = view.findViewById(R.id.product_card);
            productCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        int position = getAdapterPosition();
                        Product str = arrayListString.get(position);
                        Intent intent = new Intent(ctx, ProductDetailsActivity.class);
                    intent.putExtra("itemId", str.getId());
                        ctx.startActivity(intent);
                }
            });
        }
   }


    public ProductsAdapter(Context mContext, List<Product> productlist, OnMenuItemClickedListener onMenuItemClickedListener) {
        this.mContext = mContext;
        this.productList = productlist;
        this.onMenuItemClickedListener = onMenuItemClickedListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card, parent, false);

        return new MyViewHolder(itemView,this.productList,this.mContext);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
         final int pos = position;
        album = productList.get(position);
        holder.title.setText(album.getName());
        holder.price.setText(album.getprice() + " $");
        holder.thumbnail.setImageUrl(album.geticon(),VolleySinglton.getInstance(mContext).getImageLoader());

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow, pos);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(final View view, final int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(onMenuItemClickedListener != null){
                    onMenuItemClickedListener.onItemClicked(item,position);
                }

                return false;
            }
        });
        popup.show();
    }


    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {


        public MyMenuItemClickListener() {

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {


//            switch (menuItem.getItemId()) {
//                case R.id.action_add_favourite:
//
//                    Toast.makeText(mContext, "Add to favourite   =  "+pos, Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.action_add_basket:
//
//                    Toast.makeText(mContext, "add to my basket   = " , Toast.LENGTH_SHORT).show();
//                    return true;
//                default:
//            }
            return false;
        }
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    public interface OnMenuItemClickedListener{
        public void onItemClicked(MenuItem menuItem, int position);
    }
}