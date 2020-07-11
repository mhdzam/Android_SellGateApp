package com.example.mhdzam.sellgateproject.Activities;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mhdzam.sellgateproject.Data.SharedPreferencesManager;
import com.example.mhdzam.sellgateproject.DataModel.SignInRes;
import com.example.mhdzam.sellgateproject.Helper.Utils;
import com.example.mhdzam.sellgateproject.Interfaces.resultInterface;
import com.example.mhdzam.sellgateproject.Network.DataLoader;
import com.example.mhdzam.sellgateproject.Network.JSONParser;
import com.example.mhdzam.sellgateproject.Network.VolleySinglton;
import com.example.mhdzam.sellgateproject.Network.WebServiceUrls;
import com.example.mhdzam.sellgateproject.Network.WebServicesParams;
import com.example.mhdzam.sellgateproject.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {
    private Map<String, String> params;

    // json array response url
    private String urlJsonArry = "https://api.androidhive.info/volley/person_array.json";

    private static String TAG = TabActivity.class.getSimpleName();

    TextView username;
    TextView password;
    Button btnMakeObjectRequest;
    Button TVMakeObjectRequest;
    TextView txtverifyaccount;
    TextView txtforgetpassword;
    SignInRes mSignInResult;
    String _Password;
    String _Username;
    ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (TextView) findViewById(R.id.username_input);
        password = (TextView) findViewById(R.id.password_input);
        btnMakeObjectRequest = (Button) findViewById(R.id.login_btn);
        TVMakeObjectRequest = (Button) findViewById(R.id.reg_btn);
        txtverifyaccount = (TextView) findViewById(R.id.verifyaccount);
        txtforgetpassword = (TextView) findViewById(R.id.forgetpassword);
        mProgress = new ProgressDialog(this);

txtverifyaccount.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        verifyaccount();
    }
});
        txtforgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetpassword();
            }
        });
//        pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
        TVMakeObjectRequest.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       signup();
                                                   }
                                               });

                btnMakeObjectRequest.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // making json object request
                        _Password = password.getText().toString();
                        _Username = username.getText().toString();
                        try {
                            SignIn();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    /**
     * Method to make json object request where json response starts wtih {
     * */


    private void SignIn() throws JSONException {

        String validationResult = validateInput(_Username, _Password);
        if (validationResult != null) {
            Utils.showToast(this, validationResult);
        }
        else
        {
//            _Username = URLEncoder.encode(_Username, "UTF-8");
//            _Password = URLEncoder.encode(_Password, "UTF-8");
            String body= WebServicesParams.GetSignInParams(_Username,_Password,"1");
            String url = WebServiceUrls.getSignInUrl();

            DataLoader.loadJsonDataPostWithProgress(this, url, new resultInterface() {
                @Override
                public void OnSuccess(JSONObject Data) {
                    mSignInResult = JSONParser.json2SignInRes(Data);
                    if (mSignInResult != null) {

                        SharedPreferencesManager.saveToPreferences(getApplicationContext(), null,
                                SharedPreferencesManager.USERNAME, mSignInResult.getUserName());

                        SharedPreferencesManager.saveToPreferences(getApplicationContext(), null,
                                SharedPreferencesManager.AUTHCODE, mSignInResult.getAuthCode());

                        SharedPreferencesManager.saveToPreferences(getApplicationContext(), null,
                                SharedPreferencesManager.ISLOGGEDIN, "1");

//                        SharedPreferencesManager.saveToPreferences(getApplicationContext(), null,
//                                SharedPreferencesManager.EMAIL, mSignInResult.getAuthCode());
                    }
                    Intent intent = new Intent(LoginActivity.this, TabActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void OnError(int errorCode, String errorMessage) {
                    if (String.valueOf(errorCode).equals("-112")) {
                        Intent intent = new Intent(LoginActivity.this, VerifyAccountActivity.class);
                        intent.putExtra(SharedPreferencesManager.USERNAME, _Username);
                        Utils.showToast(LoginActivity.this, errorMessage);
                        startActivity(intent);
                       // finish();
                    } else {
                        Utils.showToast(LoginActivity.this, errorMessage);
                    }
                }

                @Override
                public void OnFailure(int errId) {
                    Utils.showToast(LoginActivity.this, errId);
                }
            },mProgress,body,new HashMap<String, String>(),  Request.Priority.HIGH, TAG);
        }
    }
    private void signup()
    {
        Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(i);
      //  finish();
    }

    private void forgetpassword()
    {
        Intent i = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
        startActivity(i);
       // finish();
    }
    private void verifyaccount()
    {
        Intent i = new Intent(LoginActivity.this,VerifyAccountActivity.class);
        startActivity(i);
    }
//    private void showpDialog() {
//        if (!pDialog.isShowing())
//            pDialog.show();
//    }
//
//    private void hidepDialog() {
//        if (pDialog.isShowing())
//            pDialog.dismiss();
//    }
    private String validateInput(String phoneNumber, String password) {
        if (password.length() == 0) {
            return getString(R.string.error_empty_input_field);
        }
        // Check for phone number length which must be more than 9 chars
        if (password.length() < 1) {
            return getString(R.string.error_invalid_password);
        }
        return null;
    }

}