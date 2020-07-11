package com.example.mhdzam.sellgateproject.Network;

import android.os.Build;

import java.net.URLEncoder;

/**
 * Created by MhdZam on 1/20/2018.
 */

public class WebServiceUrls {
    //auth services
    public static final String SIGNIN = "Login";
    public static final String SIGNOUT = "Logout";
    public static final String SIGNUP = "SignUp";
    public static final String REGISTER = "SignUp";
    public static final String VERIFYACCOUNT = "VerifyAccount";
    public static final String RESETPASSWORD = "signIn";

    //content services
    public static final String ADDPRODUCTTOORDER = "AddProductToOrder";
    public static final String GETPRODUCTDETAILS = "GetProductDetails";
    public static final String GETPRODUCTSBYCATEGORYID = "GetProductsbyCatId";
    public static final String GETALLCATEGORIES = "GetAllCategories";
    public static final String SUBMITORDER = "GetAllCategories";
    public static final String GETMYORDERS = "GetAllCategories";

    //general services
    public static final String GETSETTING = "GetAllCategories";

    public static String getSignInUrl() {
        try {
            return WebConfiguration.getServer() +SIGNIN ;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static final String getsignupUrl()
    {
        try {
            return WebConfiguration.getServer() +SIGNUP ;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static final String getVerifyaccountUrl()
    {
        try{
            return WebConfiguration.getServer() + VERIFYACCOUNT;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "";
        }
    }

    public static final String getProductsByCategoryIDUrl(String username, String authcode, String userlang, int categoryId)
    {
        try{
            return WebConfiguration.getServer() + GETPRODUCTSBYCATEGORYID + "?userName="+ username
                    +"&authCode="+authcode+"&userLang="+userlang+"&catId="+categoryId;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return "";
        }
    }

}
