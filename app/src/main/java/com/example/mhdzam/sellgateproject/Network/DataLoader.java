package com.example.mhdzam.sellgateproject.Network;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mhdzam.sellgateproject.DataModel.WebServiceResponse;
import com.example.mhdzam.sellgateproject.DataModel.WebServiceResponsePost;
import com.example.mhdzam.sellgateproject.Helper.Utils;
import com.example.mhdzam.sellgateproject.Interfaces.resultInterface;
import com.example.mhdzam.sellgateproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import static com.example.mhdzam.sellgateproject.Network.JSONParser.json2WebServiceResponse;

/**
 * Created by MhdZam on 1/16/2018.
 */

public class DataLoader {

    private static final int NUM_RETRIES = 0;
    private static final int TIMEOUT_MS = 30000; // 30 second
    private static  Context CONTEXT;

    public static void loadJsonDataGet(final Context activity, String url, final resultInterface listener,
                                       final Request.Priority priority, String tag) {
//        if (CommunityApplication.DEBUG_MODE) {
//            Log.d("wiso", url);
//        }

        //HttpsTrustManager.allowAllSSL();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, (JSONObject) null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (listener != null) {
                          WebServiceResponse webServiceResponse = json2WebServiceResponse(response);
                            if (webServiceResponse != null) {
                                if (webServiceResponse.getCode() < 1) {
                                    listener.OnError(webServiceResponse.getCode(), webServiceResponse.getMessage());
                                } else if (webServiceResponse.getCode() > 1) {
                                    Utils.showToast(activity.getApplicationContext(), webServiceResponse.getMessage());
                                    listener.OnSuccess(webServiceResponse.getData());
                                } else {
                                    listener.OnSuccess(webServiceResponse.getData());
                                }
                            } else {
                                Utils.showDialog(activity, R.string.error_parse);
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (listener != null) {
                    int errorId = R.string.error_connection;
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        errorId = R.string.error_connection;
                    } else if (error instanceof AuthFailureError) {
                        errorId = R.string.error_connection;
                    } else if (error instanceof ServerError) {
                        errorId = R.string.error_server;
                    } else if (error instanceof NetworkError) {
                        errorId = R.string.error_connection;
                    } else if (error instanceof ParseError) {
                        errorId = R.string.error_parse;
                    }
                    listener.OnFailure(errorId);
                }
            }
        }) {
            @Override
            public Priority getPriority() {
                return priority;
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT_MS,
                NUM_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add request to request queue
        VolleySinglton.getInstance(activity.getApplicationContext()).addToRequestQueue(jsonObjReq);
    }
    public static void loadJsonDataGetWithProgress(final Context activity, String url, final resultInterface listener,
                                                   final ProgressDialog progress, final Request.Priority priority, final String tag) {

        Utils.showProgress(progress);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, (JSONObject) null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Utils.dismissProgress(progress);
                        if (listener != null) {
                            WebServiceResponse webServiceResponse = JSONParser.json2WebServiceResponse(response);
                            if (webServiceResponse != null) {
                                if (webServiceResponse.getCode() < 1) {
                                    listener.OnError(webServiceResponse.getCode(), webServiceResponse.getMessage());
                                } else if (webServiceResponse.getCode() > 1) {
                                    Utils.showToast(activity.getApplicationContext(), webServiceResponse.getMessage());
                                    listener.OnSuccess(webServiceResponse.getData());
                                } else {
                                    listener.OnSuccess(webServiceResponse.getData());
                                }
                            } else {
                                Utils.showDialog(activity, R.string.error_parse);
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Utils.dismissProgress(progress);
                if (listener != null) {
                    int errorId = R.string.error_connection;
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        errorId = R.string.error_connection;
                    } else if (error instanceof AuthFailureError) {
                        errorId = R.string.error_connection;
                    } else if (error instanceof ServerError) {
                        errorId = R.string.error_server;
                    } else if (error instanceof NetworkError) {
                        errorId = R.string.error_connection;
                    } else if (error instanceof ParseError) {
                        errorId = R.string.error_parse;
                    }
                    listener.OnFailure(errorId);
                }
            }
        }) {
            @Override
            public Priority getPriority() {
                return priority;
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT_MS,
                NUM_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add request to request queue
        VolleySinglton.getInstance(activity).addToRequestQueue(jsonObjReq);
        progress.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                VolleySinglton.getInstance(activity).cancelPendingRequests(tag);
            }
        });
    }

    public static void loadJsonDataPostWithProgress(final Activity activity,  String url, final resultInterface listener,
                                         final ProgressDialog progress, final String body, final Map<String, String> headers, final Request.Priority priority, String tag) throws JSONException {
        Utils.showProgress(progress);

        JsonObjectRequest JsonObjRequest = new JsonObjectRequest(Request.Method.POST,url,new JSONObject(body), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Utils.dismissProgress(progress);
                if (listener != null) {
                    WebServiceResponsePost webServiceResponse = JSONParser.json2WebServiceResponsePost(response);
                    if (webServiceResponse != null) {

                        if (!String.valueOf(webServiceResponse.getCode()).equals("1")) {
                            listener.OnError(Integer.parseInt(webServiceResponse.getCode()), webServiceResponse.getErrorMessage());
                        } else if (String.valueOf(webServiceResponse.getCode()).equals("1")) {
                            listener.OnSuccess(webServiceResponse.getData());
                        }
                    } else {
                        Utils.showDialog(activity, R.string.error_parse);
                    }
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.dismissProgress(progress);
                if (listener != null) {
                    int errorId = R.string.error_connection;
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        errorId = R.string.error_connection;
                    } else if (error instanceof AuthFailureError) {
                        errorId = R.string.error_connection;
                    } else if (error instanceof ServerError) {
                        errorId = R.string.error_server;
                    } else if (error instanceof NetworkError) {
                        errorId = R.string.error_connection;
                    } else if (error instanceof ParseError) {
                        errorId = R.string.error_parse;
                    }
                    listener.OnFailure(errorId);
                }
            }
        }) {

//            @Override
//            public byte[] getBody() {
//                try {
//                    return body == null ? null : body.getBytes("utf-8");
//                } catch (UnsupportedEncodingException uee) {
//                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
//                            body, "utf-8");
//                    return null;
//                }
//            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                return headers;
            }


            @Override
            public Priority getPriority() {
                return priority;
            }
        };
        JsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT_MS,
                NUM_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add request to request queue
        VolleySinglton.getInstance(activity).addToRequestQueue(JsonObjRequest);
    }

}
