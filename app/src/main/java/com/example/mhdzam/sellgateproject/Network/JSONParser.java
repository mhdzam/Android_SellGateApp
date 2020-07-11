package com.example.mhdzam.sellgateproject.Network;

/**
 * Created by MhdZam on 11/28/2017.
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.mhdzam.sellgateproject.DataModel.NewUser;
import com.example.mhdzam.sellgateproject.DataModel.Product;
import com.example.mhdzam.sellgateproject.DataModel.SignInRes;
import com.example.mhdzam.sellgateproject.DataModel.WebServiceResponse;
import com.example.mhdzam.sellgateproject.DataModel.WebServiceResponsePost;

public class JSONParser {
    final String TAG = "JSONParser.java";
    static InputStream inputStream = null;
    static JSONObject jObj = null;
    static JSONArray jArr = null;
    static String json = "";

    StringBuilder result;
    HttpURLConnection connection;


    public static WebServiceResponse json2WebServiceResponse(JSONObject root) {
        try {
            int code = 1;
            try {
                code = Integer.valueOf(root.getString("ErrorCode"));
            } catch (JSONException e) {
            }

            String message = "";
            try {
                message = root.getString("ErrorMessage");
            } catch (JSONException e) {
            }

            JSONObject data = null;
            try {
                data = root.getJSONObject("ReturnedData");
            } catch (JSONException ex) {
            }

            String itemsCount="";
            try{
                itemsCount = root.getString("TotalCount");
            }catch (Exception ex)
            {

            }
            return new WebServiceResponse(code, message, data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static WebServiceResponsePost json2WebServiceResponseProductsArray(JSONObject root)
    {
        try {
            String code = "";
            try {
                code = (root.getString("ErrorCode"));
            } catch (JSONException e) {
            }

            String message = "";
            try {
                message = root.getString("ErrorMessage");
            } catch (JSONException e) {
            }

            JSONObject data = null;
            try {
                data = root.getJSONObject("ReturnedData");

            } catch (JSONException ex) {
            }

            return new WebServiceResponsePost(code, message, data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static WebServiceResponsePost json2WebServiceResponsePost(JSONObject root) {
        try {
            String code = "";
            try {
                code = (root.getString("ErrorCode"));
            } catch (JSONException e) {
            }

            String message = "";
            try {
                message = root.getString("ErrorMessage");
            } catch (JSONException e) {
            }

            JSONObject data = null;
            try {
                data = root.getJSONObject("ReturnedData");
            } catch (JSONException ex) {
            }

            return new WebServiceResponsePost(code, message, data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static SignInRes json2SignInRes(JSONObject data) {

            String userlang = "";
            String username = "";
            String authcode = "";

            JSONObject ReturnedData = data;

            try {
                username = ReturnedData.getString("UserName");
            } catch (Exception e) {
            }

            try {
                authcode = ReturnedData.getString("AuthCode");
            } catch (Exception e) {
            }

            try {
                userlang =String.valueOf(ReturnedData.getInt("userlang"));
            } catch (Exception e) {
            }

                return new SignInRes(username, authcode, userlang);


    }

     public static NewUser json2NewUser(JSONObject Data)
     {
        try{
            String ID="";
            String GSM="";
            String UserName="";
            String FullName="";
            String Email="";
            String CreateDate="";

            JSONObject ReturnedData = Data;

            try {
                ID = ReturnedData.getString("ID");
            } catch (Exception e) {
            }
            try {
                UserName = ReturnedData.getString("UserName");
            } catch (Exception e) {
            }
            try {
                GSM = ReturnedData.getString("GSM");
            } catch (Exception e) {
            }
            try {
                FullName = ReturnedData.getString("FullName");
            } catch (Exception e) {
            }
            try {
                Email = ReturnedData.getString("Email");
            } catch (Exception e) {
            }
            try {
                CreateDate = ReturnedData.getString("CreateDate");
            } catch (Exception e) {
            }
            //String ID, String GSM, String userName, String fullName, String email, String createDate
            return new NewUser(ID,GSM,UserName,FullName,Email,CreateDate);
        } catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
     }

     public static List<Product> json2listproducts(JSONObject Data)
     {
         List<Product> products = new ArrayList<>();
         try {
             String ID = "";
             String Name = "";
             String Icon = "";
             int Price = 0;

            // List<Product> products = new ArrayList<>();

             JSONArray jsonArray = Data.getJSONArray("items");

             for(int i=0;i<jsonArray.length();i++) {

                 try {
                     ID = jsonArray.getJSONObject(i).getString("ID");
                 } catch (Exception e) {
                 }
                 try {
                     Name = jsonArray.getJSONObject(i).getString("Name");
                 } catch (Exception e) {
                 }
                 try {
                     Icon = jsonArray.getJSONObject(i).getString("IconURL");
                 } catch (Exception e) {
                 }
                 try {
                     Price = i*100+(i/7);
                 } catch (Exception e) {
                 }
                 products.add(new Product(Name,Price,Icon,Integer.parseInt(ID)));
             }

         }catch (Exception ex)
         {
             ex.printStackTrace();
             return null;
         }
return products;

     }
    public JSONObject makeHttpRequest(String url, String method, HashMap<String, String> params) {
        String charset = "UTF-8";
        StringBuilder sbParams;
        sbParams = new StringBuilder();
        String inputLine="";
        JSONObject jObj = null;
        int i = 0;

        for (String key : params.keySet()) {
            try {
                if (i != 0) {
                    sbParams.append("&");
                }
                sbParams.append(key).append("=").append(URLEncoder.encode(params.get(key), charset));
            } catch(UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }

        if(method.equals("POST")){
            // Send values
            try {
                URL urlObj;
                urlObj = new URL(url);

                connection = (HttpURLConnection) urlObj.openConnection();

                connection.setRequestMethod("POST");
                connection.setRequestProperty("Authorization","Basic MDk5MEQ1RDItRTg2NS00ODA4LUFCOUYtMDY2NkY4RkE5MTRDOk15U3lyaUB0M2xAcHBQQCQkdzByZA==");
                //connection.setRequestProperty("Accept-Charset", charset);
                connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setDoOutput(true);


                Log.d(TAG, "Connection estabilished");

                String paramsString;
                paramsString = sbParams.toString();

                //DataOutputStream wr;
                BufferedOutputStream outputPost = new BufferedOutputStream(connection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputPost, "UTF-8"));

                writer.write(paramsString);
                writer.flush();
                writer.close();
                outputPost.close();

                connection.connect();

                int status = connection.getResponseCode();

                InputStreamReader streamReader;
                if (status > 200) {
                    streamReader = new InputStreamReader(connection.getErrorStream());
                }
                else {
                    streamReader = new InputStreamReader(connection.getInputStream());
                }

                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();

                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }

                reader.close();
                streamReader.close();

                //JSONObject jsonResponse=new JSONObject(stringBuilder.toString());
                Log.d(TAG, "Data sent & connection closed");
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else if(method.equals("GET")) {
            // TODO: 21.02.16 Dopisac obsluge GET
        }

        try {
            // Receive response
            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            Log.d(TAG, "result: "+result.toString());
        } catch(IOException e){
            e.printStackTrace();
        }

        connection.disconnect();

        // Try parse the string to JSONObject

        try {
            jObj = new JSONObject(result.toString());
        } catch(JSONException e) {
            e.printStackTrace();
        }

        return jObj;
    }
}