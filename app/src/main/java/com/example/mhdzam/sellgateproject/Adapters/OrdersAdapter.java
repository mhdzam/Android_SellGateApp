package com.example.mhdzam.sellgateproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormatSymbols;
import android.icu.text.RelativeDateTimeFormatter;
import android.icu.text.SimpleDateFormat;
import android.support.v7.widget.CardView;
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
import com.example.mhdzam.sellgateproject.Activities.ProductDetailsActivity;
import com.example.mhdzam.sellgateproject.DataModel.Order;
import com.example.mhdzam.sellgateproject.DataModel.Product;
import com.example.mhdzam.sellgateproject.Network.VolleySinglton;
import com.example.mhdzam.sellgateproject.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.mhdzam.sellgateproject.R.id.thumbnail;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    private Context mContext;
    private List<Order> orederlist;
    OnMenuItemClickedListener onMenuItemClickedListener;
int pos;
    private Order album;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView year,month,day,status,orderid, price;
        CardView cardView;
        List<Order> arrayListString = new ArrayList<>();
        private Context ctx;
        private View productCard;
        public int position;

        public MyViewHolder(View view, List<Order> orders, final Context ctx) {
            super(view);

            arrayListString = orders;
            this.ctx = ctx;
            orderid = (TextView) view.findViewById(R.id.orderid);
            status = (TextView) view.findViewById(R.id.orderstatus);
            price = (TextView) view.findViewById(R.id.totalprice);
            month = (TextView) view.findViewById(R.id.ordermonth);
            day = (TextView) view.findViewById(R.id.orderday);
            year = (TextView) view.findViewById(R.id.orderyear);
            cardView = (CardView) view.findViewById(R.id.order_card_view);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Order str = arrayListString.get(position);
                    Intent intent = new Intent(ctx, ProductDetailsActivity.class);
                    intent.putExtra("itemId", str.getId());
                    ctx.startActivity(intent);
                }
            });

        }
   }


    public OrdersAdapter(Context mContext, List<Order> orderList/* , OnMenuItemClickedListener onMenuItemClickedListener*/) {
        this.mContext = mContext;
        this.orederlist = orderList;
//        this.onMenuItemClickedListener = onMenuItemClickedListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card, parent, false);

        return new MyViewHolder(itemView,this.orederlist,this.mContext);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
         final int pos = position;
        album = orederlist.get(position);
        holder.status.setText(album.getStatus());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date parse = new Date();
        try {
             parse = sdf.parse(album.getSubmitDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        int date = calendar.get(Calendar.DATE);
        DateFormatSymbols symbols = new DateFormatSymbols();
        String[] Months = symbols.getMonths();
        int day = calendar.get(Calendar.DAY_OF_MONTH)+1;
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);

        holder.year.setText(year);
        holder.day.setText(day);
        holder.month.setText(Months[month]);
        holder.price.setText(album.getTotalPrice() + " $");
        holder.status.setText(album.getStatus());
        holder.orderid.setText(album.getId());

    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
//    private void showPopupMenu(final View view, final int position) {
//        // inflate menu
//        PopupMenu popup = new PopupMenu(mContext, view);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.menu_album, popup.getMenu());
//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if(onMenuItemClickedListener != null){
//                    onMenuItemClickedListener.onItemClicked(item,position);
//                }
//
//                return false;
//            }
//        });
//        popup.show();
//    }


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
        return orederlist.size();
    }


    public interface OnMenuItemClickedListener{
        public void onItemClicked(MenuItem menuItem, int position);
    }
}