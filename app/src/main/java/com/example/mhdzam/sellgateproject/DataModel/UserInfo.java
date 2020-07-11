package com.example.mhdzam.sellgateproject.DataModel;

import android.content.Context;

import com.example.mhdzam.sellgateproject.Data.SharedPreferencesManager;

import java.util.Locale;

/**
 * Created by MhdZam on 1/21/2018.
 */

public class UserInfo {
    public String Username;
    public String FullName;
    public String Gsm;
    public String Userlang;
    public String Email;
    public String Authcode;
    Context context;

    public UserInfo(Context context) {
        this.context = context;
    }

    public String getUsername() {
        return SharedPreferencesManager.readFromPreferences(context,null,SharedPreferencesManager.USERNAME,"");
    }

    public String getFirstname() {
        return SharedPreferencesManager.readFromPreferences(context,null,SharedPreferencesManager.PREF_FULL_NAME,"");
    }

    public String getGsm() {
        return SharedPreferencesManager.readFromPreferences(context,null,SharedPreferencesManager.GSM,"");
    }

    public String getUserlang() {
        String userlang = SharedPreferencesManager.readFromPreferences(context,null,SharedPreferencesManager.USERLANGUAGE,"");
        if(userlang.isEmpty() || userlang == null)
            userlang = Locale.getDefault().getLanguage().toLowerCase() == "en" ? "1":"0";
        return userlang;
    }

    public String getEmail() {
        return SharedPreferencesManager.readFromPreferences(context,null,SharedPreferencesManager.EMAIL,"");
    }

    public String getAuthcode() {
        return SharedPreferencesManager.readFromPreferences(context,null,SharedPreferencesManager.AUTHCODE,"");
    }
}
