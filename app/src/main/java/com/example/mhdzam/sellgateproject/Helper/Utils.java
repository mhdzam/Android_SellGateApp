package com.example.mhdzam.sellgateproject.Helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.example.mhdzam.sellgateproject.Data.SharedPreferencesManager;
import com.example.mhdzam.sellgateproject.DataModel.UserInfo;
import com.example.mhdzam.sellgateproject.R;

/**
 * Created by MhdZam on 1/16/2018.
 */

public class Utils {

    public static void showDialog(Activity activity, String errorMessage) {
        new AlertDialog.Builder(activity)
                .setMessage(errorMessage)
                .setPositiveButton(activity.getString(R.string.dialog_error_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
    public static UserInfo getUserInfo(Context context)
    {

        return  new UserInfo(context);

    }

    public static void showDialog(Context activity, String errorMessage) {
        new AlertDialog.Builder(activity)
                .setMessage(errorMessage)
                .setPositiveButton(activity.getString(R.string.dialog_error_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    public static void showDialog(Activity activity, int errorId) {
        showDialog(activity, activity.getString(errorId));
    }

    public static void showDialog(Context activity, int errorId) {
        if(activity != null)
            showDialog(activity, activity.getString(errorId));
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showToast(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    public static void showProgress(ProgressDialog dialog) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.setCancelable(false);
            dialog.setMessage("Loading Data ...");
            dialog.show();
        }
    }
    public static void dismissProgress(ProgressDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static float getStatusBarHeight(Context context) {
        float result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimension(resourceId);//.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static boolean isWifi(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
        int dataMobile;
        dataMobile = activeNetwork.getType();
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWifi.isConnected() && dataMobile != 0) {
            return true;
        } else {
            return false;
        }
    }
}
