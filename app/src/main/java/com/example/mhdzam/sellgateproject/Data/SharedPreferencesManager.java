package com.example.mhdzam.sellgateproject.Data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by MhdZam on 1/11/2018.
 */

public class SharedPreferencesManager {
    public static final String PREF_GID = "PREF_GID";
    SharedPreferences.Editor editor;
    SharedPreferences pref;
    public static final String DEFAULT_FILE_NAME = "mainFile";
    public static final String PREF_USER_ID = "authservice.user_id";
    public static final String PREF_FULL_NAME = "authservice.full_name";
    public static final String USERNAME = "authservice.username";
    public static final String AUTHCODE = "authservice.authcode";
    public static final String GSM = "authservice.gsm";
    public static final String EMAIL = "authservice.email";
    public static final String USERLANGUAGE = "authservice.userlanguage";
    public static final String ISLOGGEDIN = "authservice.isloggedin";


    //functions
    public static void saveToPreferences(Context context, String prefFileName, String preferenceName, String preferenceValue) {
        if (prefFileName == null) {
            prefFileName = DEFAULT_FILE_NAME;
        }
        if (context != null){


            SharedPreferences sharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(preferenceName, preferenceValue);
            editor.commit();
        }
    }

    public static String readFromPreferences(Context context, String prefFileName, String preferenceName, String defaultValue) {
        if (prefFileName == null) {
            prefFileName = DEFAULT_FILE_NAME;
        }
        SharedPreferences sharedPreferences;
        try {
            sharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);

        } catch (Exception ex) {

            sharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(preferenceName, defaultValue);
    }


}
