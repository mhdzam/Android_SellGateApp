package com.example.mhdzam.sellgateproject.Network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.mhdzam.sellgateproject.Helper.LruBitmapCache;


/**
 * Created by MhdZam on 11/27/2017.
 */
public class VolleySinglton {
    private static VolleySinglton INSTANCE;
    private RequestQueue requestQueue;
    private Context context;
    private ImageLoader mImageLoader;

    private VolleySinglton(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
    }
    private VolleySinglton(){
    }

    public static synchronized VolleySinglton getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new VolleySinglton(context);
        }
        return INSTANCE;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


    public ImageLoader getImageLoader() {
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(getRequestQueue(), LruBitmapCache.getInstance());
        }
        return mImageLoader;
    }
        public void cancelPendingRequests(Object tag) {
            if (requestQueue != null) {
                requestQueue.cancelAll(tag);
            }
    }
}
