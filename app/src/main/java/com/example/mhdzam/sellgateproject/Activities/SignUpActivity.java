package com.example.mhdzam.sellgateproject.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mhdzam.sellgateproject.Data.SharedPreferencesManager;
import com.example.mhdzam.sellgateproject.DataModel.NewUser;
import com.example.mhdzam.sellgateproject.Helper.Helper;
import com.example.mhdzam.sellgateproject.Helper.Utils;
import com.example.mhdzam.sellgateproject.Interfaces.resultInterface;
import com.example.mhdzam.sellgateproject.Network.DataLoader;
import com.example.mhdzam.sellgateproject.Network.JSONParser;
import com.example.mhdzam.sellgateproject.Network.VolleySinglton;
import com.example.mhdzam.sellgateproject.Network.WebServiceUrls;
import com.example.mhdzam.sellgateproject.Network.WebServicesParams;
import com.example.mhdzam.sellgateproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {

    Button signupBTN;
    String firstName;
    String LastName;
    String Email;
    String GSM;
    String Password;
    String UserName;
    EditText _Email;
    EditText _FirsName;
    EditText _LastName;
    EditText _UsrName;
    EditText _PassWord;
    EditText _ConforsmPassword;
    EditText _GSM;

    ProgressDialog progressDialog;
    HashMap<String,String> params;
    private static String TAG = SignUpActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog = new ProgressDialog(this);
        init();

        signupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = _FirsName.getText().toString();
                LastName = _LastName.getText().toString();
                Email = _Email.getText().toString();
                GSM = _GSM.getText().toString();
                Password = _PassWord.getText().toString();
                UserName = _UsrName.getText().toString();

                String lang = Locale.getDefault().getLanguage().toLowerCase() == "en" ? "1" : "0";

                String url = WebServiceUrls.getsignupUrl();
                String params = WebServicesParams.GetSignUpParams(UserName, Password, Email, firstName + LastName, lang, GSM);
                try {
                    sendSignUpRequest(url,params);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

    });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean chkconfirmpassword()
    {
        boolean isValid= false;
        if (_ConforsmPassword.getText().toString().equals(_PassWord))
            return true;
        return false;
    }
    private void init()
    {
        signupBTN = (Button) findViewById(R.id.signup_button);
        _Email = (EditText) findViewById(R.id.Email);
        _FirsName = (EditText) findViewById(R.id.First_Name);
        _LastName = (EditText) findViewById(R.id.Last_Name);
        _UsrName = (EditText) findViewById(R.id.User_Name);
        _ConforsmPassword = (EditText) findViewById(R.id.conf);
        _PassWord = (EditText) findViewById(R.id.Password);
        _GSM = (EditText) findViewById(R.id.Phone_Number);
    }
    private void sendRequestREG()
    {
        Helper helper = new Helper(getApplicationContext());
        String SignupURL = helper.getSignupURL();

        params=new HashMap<String,String>();
        params.put("UserName",UserName);
        params.put("Password",Password);
        params.put("UserLang","1");
        params.put("GSM",GSM);
        params.put("Email",Email);
        params.put("FullName",firstName);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                SignupURL, new JSONObject(params), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    String ErrorCode = response.getString("ErrorCode");
                    String ErrorMessage = response.getString("ErrorMessage");
                    if(ErrorCode.equals(String.valueOf(1)))
                    {
                        Toast.makeText(getApplicationContext(), "Operation Done !",
                                Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), ErrorMessage,
                                Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
              //  hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        VolleySinglton.getInstance(SignUpActivity.this).addToRequestQueue(jsonObjReq);
    }

    private void sendSignUpRequest(String url, String params) throws JSONException {

        DataLoader.loadJsonDataPostWithProgress(this, url, new resultInterface() {
            @Override
            public void OnSuccess(JSONObject Data) {
                NewUser newUser = JSONParser.json2NewUser(Data);
                if (newUser != null) {
                    SharedPreferencesManager.saveToPreferences(getApplicationContext(), null,
                            SharedPreferencesManager.USERNAME, newUser.getUserName());

                    SharedPreferencesManager.saveToPreferences(getApplicationContext(), null,
                            SharedPreferencesManager.GSM, newUser.getGSM());

                    SharedPreferencesManager.saveToPreferences(getApplicationContext(), null,
                            SharedPreferencesManager.EMAIL, newUser.getEmail());

                    SharedPreferencesManager.saveToPreferences(getApplicationContext(), null,
                            SharedPreferencesManager.PREF_FULL_NAME, newUser.getFullName());

                    Utils.showToast(SignUpActivity.this, SharedPreferencesManager.readFromPreferences(SignUpActivity.this,null,SharedPreferencesManager.USERNAME,""));
                    Utils.showToast(SignUpActivity.this, SharedPreferencesManager.readFromPreferences(SignUpActivity.this,null,SharedPreferencesManager.GSM,""));
                    Utils.showToast(SignUpActivity.this, SharedPreferencesManager.readFromPreferences(SignUpActivity.this,null,SharedPreferencesManager.USERLANGUAGE,""));
                    Utils.showToast(SignUpActivity.this, SharedPreferencesManager.readFromPreferences(SignUpActivity.this,null,SharedPreferencesManager.EMAIL,""));
                    Intent intent = new Intent(SignUpActivity.this, VerifyAccountActivity.class);
                    intent.putExtra(SharedPreferencesManager.USERNAME, newUser.getUserName());
                    Utils.showToast(SignUpActivity.this, "Operation Done !");
                    startActivity(intent);
                }
//                Intent intent = new Intent(SignUpActivity.this, VerifyAccountActivity.class);
//                startActivity(intent);
//                finish();
            }

            @Override
            public void OnError(int errorCode, String errorMessage) {

            }

            @Override
            public void OnFailure(int errId) {

            }
        }, progressDialog, params, new HashMap<String, String>(), Request.Priority.HIGH, TAG);

    }
}
