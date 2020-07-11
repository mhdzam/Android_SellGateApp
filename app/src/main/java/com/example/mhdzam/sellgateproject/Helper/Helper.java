package com.example.mhdzam.sellgateproject.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mhdzam.sellgateproject.Activities.SignUpActivity;
import com.example.mhdzam.sellgateproject.Network.VolleySinglton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by MhdZam on 1/9/2018.
 */

public class Helper {
    private int userLang;
    private String userName;
    private String authCode;
    Context ctx;
    private static String TAG = SignUpActivity.class.getSimpleName();
    String BaseURL = "https://dev-fapi.syriatel.sy/sellgateWS/webresources/generic/";
    public Helper(Context ctx)
    {
        this.ctx=ctx;
        SharedPreferences prefs = ctx.getSharedPreferences("Logs", MODE_PRIVATE);
        userName = prefs.getString("username","");
        authCode = prefs.getString("authCode","");
    }

    public static boolean isAuthUser(JSONObject jsonObject)
    {

        return true;
    }
    public String getProductsURL(int catId)
    {
        return BaseURL+"GetProductsbyCatId?userName="+userName+"&authCode="+authCode+"&userLang="+userLang+"&catId="+catId;
    }
    public String getSignupURL()
    {
        return BaseURL+"SignUp";
    }

    public String getGetProductDetails(int itemID)
    {
        return BaseURL+"GetProductDetails?userName="+userName+"&authCode="+authCode+"&userLang="+userLang+"&ItemId="+itemID;
    }

    public String getSubmitOrderURL()
    {
        return BaseURL+"AddProductToOrder";
    }

    public void sendRequestREG(int itemId)
    {
        Helper helper = new Helper(ctx);
        String SubmiteURL = helper.getSubmitOrderURL();
        HashMap<String,Object> params;
        params= new HashMap<>();
        params.put("userName",this.userName);
        params.put("authCode",this.authCode);
        params.put("UsrLang",String.valueOf(this.userLang));

        String[] ItemID = new String[1];
        String[] ProductCount = new String[1];
        ItemID[0] = String.valueOf(itemId);
        params.put("ItemsId",ItemID);
        ProductCount[0] = "1";
        params.put("ProductsCounts",ProductCount);


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                SubmiteURL, new JSONObject(params), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    String ErrorCode = response.getString("ErrorCode");
                    String ErrorMessage = response.getString("ErrorMessage");
                    if(ErrorCode.equals(String.valueOf(1)))
                    {
                        Toast.makeText(ctx, "Operation Done !",
                                Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(ctx, ErrorMessage,
                                Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ctx,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(ctx,
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        VolleySinglton.getInstance(ctx).addToRequestQueue(jsonObjReq);
    }
}
