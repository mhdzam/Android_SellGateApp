package com.example.mhdzam.sellgateproject.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mhdzam.sellgateproject.Adapters.ProductDetailsAdapter;
import com.example.mhdzam.sellgateproject.Helper.Helper;
import com.example.mhdzam.sellgateproject.Network.VolleySinglton;
import com.example.mhdzam.sellgateproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    Context context;
    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapter;
     List<String> ListItem;

    TextView Name;
    TextView Price;
    TextView Category;
    TextView DescContent;
    Button add_to_order_btn;
    Button add_to_fav_btn;
    private com.example.mhdzam.sellgateproject.DataModel.ProductDetails productDetails;
    String URL;
    NetworkImageView Icon;
    NetworkImageView Poster;
    int value=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productDetails = new com.example.mhdzam.sellgateproject.DataModel.ProductDetails();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getInt("itemId");
            URL = new Helper(this).getGetProductDetails(value);
            loadrecyvlerviewdata();
        }else
        {
            Toast.makeText(getApplicationContext(),"General Error ... please try again later", Toast.LENGTH_SHORT).show();
        }

        recyclerView = (RecyclerView) findViewById(R.id.RecyvlerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));


        // control tools on the layout page
        Icon = (NetworkImageView) findViewById(R.id.networkImageView);
        Name = (TextView) findViewById(R.id.productname);
        DescContent = (TextView) findViewById(R.id.desc_viewtxt);
        Price = (TextView) findViewById(R.id.tetviewvalueofprice);
        Category = (TextView) findViewById(R.id.textviewvalueofcategory);

        //the buttons on the layout page
        add_to_fav_btn = (Button) findViewById(R.id.favbtn);
        add_to_order_btn = (Button) findViewById(R.id.ordrbtn);

        add_to_order_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new Helper(ProductDetailsActivity.this).sendRequestREG(value);

                Toast.makeText(getApplicationContext(),"add to order", Toast.LENGTH_SHORT).show();
            }
        });
        add_to_fav_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"add to fav", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void loadrecyvlerviewdata(){
        final ProgressDialog progressDialog = new ProgressDialog(ProductDetailsActivity.this);
        progressDialog.setMessage("Loading data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if(Integer.parseInt(jsonObject.getString("ErrorCode")) <0)
                    {
                        Toast.makeText(getApplicationContext(),jsonObject.getString("ErrorMessage"),Toast.LENGTH_LONG).show();
                    }
                    JSONObject jo = jsonObject.getJSONObject("ReturnedData");
                    JSONArray jsonArray = jo.getJSONArray("Images");
//                    JSONObject o = jsonArray.getJSONObject(i);

                    productDetails.setDESC(jo.getString("DESC"));
                    productDetails.setAddedDate(jo.getString("addedDate"));
                    productDetails.setID(jo.getString("ID"));
                    productDetails.setName(jo.getString("Name"));
                    productDetails.setPrice(jo.getString("Price"));
                    List<String> imgs= new ArrayList<>();
                    productDetails.setImages(jo.getString("Images").split(","));


                    setProductViewData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(),volleyError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void setProductViewData(){

        Icon.setImageUrl("https://cdn2.iconfinder.com/data/icons/picons-essentials/71/mobile-512.png",VolleySinglton.getInstance(context).getImageLoader());
ArrayList<String> urls = new ArrayList<>();
        if(productDetails.getImages().length == 0)
        {
            urls.add("https://cdn2.iconfinder.com/data/icons/picons-essentials/71/mobile-512.png");
        }
        else {
            for (String url : productDetails.getImages()
                    ) {
                urls.add(url.replace("\\", "").replace("\"", "").replace("]", "").replace("[", ""));
            }
        }
        Name.setText(productDetails.getName());
        Price.setText(productDetails.getPrice() + " $");
        Category.setText(productDetails.getAddedDate());
        DescContent.setText(productDetails.getDESC());
        ArrayList<String> imgs = (urls);
        adapter = new ProductDetailsAdapter(imgs,this);
        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

    }
}