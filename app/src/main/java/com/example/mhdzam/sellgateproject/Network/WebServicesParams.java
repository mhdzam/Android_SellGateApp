package com.example.mhdzam.sellgateproject.Network;

/**
 * Created by MhdZam on 1/13/2018.
 */

public class WebServicesParams {
    public static final String AUTH_CODE = "\"authCode\":\"";
    public static final String USER_NAME = "\"UserName\":\"";
    public static final String USER_LANGUAGE = "\"userLang\":\"";
    public static final String PRODUCTS_COUNT = "\"productsCounts\":\"";
    public static final String GSM = "\"GSM\":\"";
    public static final String PASSWORD = "\"password\":\"";
    public static final String EMAIL = "\"Email\":\"";
    public static final String FULL_NAME = "\"FullName\":\"";
    public static final String ITEMS_IDS = "\"itemsId\":\"";
    public static final String  CATEGORY_ID = "\"catId\":\"";
    public static final String ITEM_ID = "\"itemId\":\"";
    public static final String VERIFICATION_CODE = "\"v_Code\":\"";

    public static final String Q = "\",";
    public static final String START_P = "{";
    public static final String END_P = "\"}";

    public static String GetSignUpParams(String username, String password, String email, String fullname, String userlang, String gsm) {
        try {

            return START_P + USER_NAME + username + Q + PASSWORD + password + Q + EMAIL + email + Q + FULL_NAME + fullname + Q +USER_LANGUAGE + userlang + Q + GSM + gsm + END_P ;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String GetSignInParams(String username,String password, String userlang) {
        try {

            return START_P + PASSWORD + password + Q + USER_NAME + username + Q + USER_LANGUAGE + userlang + END_P ;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        }

        public static String GetVerifyAccountParams(String username, String vcode, String userlang) {
            try {
                return START_P + USER_NAME + username + Q + USER_LANGUAGE + userlang + Q + VERIFICATION_CODE + vcode + END_P ;
            }catch (Exception ex)
            {
                ex.printStackTrace();
                return "";
            }
        }

        public static String GetSignOutParams(String authcode, String username, String userlang)
        {
            try {

                return START_P + AUTH_CODE + authcode + Q + USER_NAME + username + Q + USER_LANGUAGE + userlang + END_P ;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }

        public static String GetCategoriesParams(String authcode, String username, String userlang)
        {
            try{

                return START_P + USER_NAME + username + Q + AUTH_CODE + authcode + Q + USER_LANGUAGE + userlang + END_P ;
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return "";
            }
        }

        public static String GetProductsByCategoryIdParams(String authcode, String username, String usrlang, String categoryId)
        {
            try{
                return START_P + USER_NAME + username + Q + AUTH_CODE + authcode + Q + USER_LANGUAGE + usrlang + Q + CATEGORY_ID + categoryId + END_P ;
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return "";
            }
        }
        public static String GetProductDetailsParams(String authcode, String username, String userlan, String itemid)
        {
            try{
                return START_P + USER_NAME + username + Q + AUTH_CODE + authcode + Q + ITEM_ID + itemid + END_P ;
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return "";
            }
        }

        public static String GetAddProductToOrderParams(String username, String authcode, String userlang, String itemsids, String productscounts )
        {
            try{
                return START_P + USER_NAME + username + Q + AUTH_CODE + authcode + Q  + USER_LANGUAGE + userlang + Q + PRODUCTS_COUNT + productscounts + Q + ITEMS_IDS + itemsids + END_P ;
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                return "";
            }
        }
}