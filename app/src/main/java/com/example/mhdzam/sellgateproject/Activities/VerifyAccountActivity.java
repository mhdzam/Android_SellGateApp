package com.example.mhdzam.sellgateproject.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.mhdzam.sellgateproject.Data.SharedPreferencesManager;
import com.example.mhdzam.sellgateproject.DataModel.SignInRes;
import com.example.mhdzam.sellgateproject.DataModel.WebServiceResponse;
import com.example.mhdzam.sellgateproject.Helper.Helper;
import com.example.mhdzam.sellgateproject.Helper.Utils;
import com.example.mhdzam.sellgateproject.Interfaces.resultInterface;
import com.example.mhdzam.sellgateproject.Network.DataLoader;
import com.example.mhdzam.sellgateproject.Network.JSONParser;
import com.example.mhdzam.sellgateproject.Network.WebServiceUrls;
import com.example.mhdzam.sellgateproject.Network.WebServicesParams;
import com.example.mhdzam.sellgateproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class VerifyAccountActivity extends AppCompatActivity {

    private static final String TAG = TabActivity.class.getSimpleName();
    EditText username;
    EditText verificationcode;
    Button submit;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_account);
        this.setTitle("Verification Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String _username = extras.getString(SharedPreferencesManager.USERNAME,"");
            username.setText(_username);

        }else
        {
            Toast.makeText(getApplicationContext(),"Error occurred ... please try again later", Toast.LENGTH_SHORT).show();
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ValidateInput(username.getText().toString(),verificationcode.getText().toString()))
                {
                    try {
                        VerifyAccount();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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

   private void init()
   {
       username = (EditText) findViewById(R.id.usernameinput);
       verificationcode = (EditText) findViewById(R.id.code_input);
       submit = (Button) findViewById(R.id.submitCode);
   }

   private boolean ValidateInput(String username, String vcode)
   {
       if(username != null && vcode != null && vcode.length() == 4)
       {
           return true;
       }
       return false;
   }
   private void VerifyAccount() throws JSONException {

       String body= WebServicesParams.GetVerifyAccountParams(username.getText().toString(),verificationcode.getText().toString(),"1");
       String url = WebServiceUrls.getVerifyaccountUrl();
       progressDialog = new ProgressDialog(this);

       DataLoader.loadJsonDataPostWithProgress(this, url, new resultInterface() {
           @Override
           public void OnSuccess(JSONObject Data) {

               WebServiceResponse webServiceResponse = JSONParser.json2WebServiceResponse(Data);
               if (webServiceResponse != null) {
                   Intent intent = new Intent(VerifyAccountActivity.this, LoginActivity.class);
                   startActivity(intent);
                   finish();
                   Utils.showToast(VerifyAccountActivity.this, webServiceResponse.getMessage());
               }


           }

           @Override
           public void OnError(int errorCode, String errorMessage) {
               Intent intent = new Intent(VerifyAccountActivity.this, LoginActivity.class);
               startActivity(intent);
               finish();
               Utils.showToast(VerifyAccountActivity.this, errorMessage);
           }

           @Override
           public void OnFailure(int errId) {
               Utils.showToast(VerifyAccountActivity.this, errId);
           }
       },progressDialog,body,new HashMap<String, String>(), Request.Priority.HIGH, TAG);
   }
}
