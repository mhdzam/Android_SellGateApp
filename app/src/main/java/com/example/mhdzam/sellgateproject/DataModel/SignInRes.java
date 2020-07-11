package com.example.mhdzam.sellgateproject.DataModel;

import android.app.backup.FullBackupDataOutput;

/**
 * Created by MhdZam on 1/20/2018.
 */

public class SignInRes {
    private String UserName;
    private String AuthCode;
    private String userLAng;
    private String Email;
    private String Gsm;
    private String Fillname;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGsm() {
        return Gsm;
    }

    public void setGsm(String gsm) {
        Gsm = gsm;
    }

    public String getFillname() {
        return Fillname;
    }

    public void setFillname(String fillname) {
        Fillname = fillname;
    }

    public String getUserName() {
        return UserName;
    }

    public SignInRes(String userName, String authCode, String userLAng) {
        UserName = userName;
        AuthCode = authCode;
        this.userLAng = userLAng;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getAuthCode() {
        return AuthCode;
    }

    public void setAuthCode(String authCode) {
        AuthCode = authCode;
    }

    public String getUserLAng() {
        return userLAng;
    }

    public void setUserLAng(String userLAng) {
        this.userLAng = userLAng;
    }
}
