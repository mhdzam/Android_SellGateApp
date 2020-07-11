package com.example.mhdzam.sellgateproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mhdzam.sellgateproject.R;

public class ForgetPasswordActivity extends AppCompatActivity {

    TextView resetpass;
    EditText username;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.setTitle("Forget Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        resetpass = (TextView) findViewById(R.id.resetpassbtn);
        username = (EditText) findViewById(R.id.username_forgetpass);
        submit = (Button) findViewById(R.id.submitforgetpassword);
    }

    private boolean validateinput(String userName)
    {
        if(userName == null)
        {
            return false;
        }
        return true;
    }

    private void sendforgetpasswordRequest()
    {
        if(validateinput(username.getText().toString()))
        {

        }
    }
}
