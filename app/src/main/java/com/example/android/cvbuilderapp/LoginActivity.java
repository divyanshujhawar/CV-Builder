package com.example.android.cvbuilderapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.suitebuilder.TestMethod;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.name;

public class LoginActivity extends AppCompatActivity {

    EditText etLogInPassword;
    EditText etLogInEmail;
    Button btUserSignIn;
    TextView tvForgotPassword;
    Button btSignUpPage;
    String name;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogInPassword = (EditText)findViewById(R.id.etLogInPassword);
        etLogInEmail = (EditText)findViewById(R.id.etLogInEmail);

        btUserSignIn = (Button)findViewById(R.id.btUserSignIn);
        btUserSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
        btSignUpPage = (Button)findViewById(R.id.btSignUpPage);
        btSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });

        tvForgotPassword = (TextView)findViewById(R.id.tvForgotPassword);
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        progressDialog = new ProgressDialog(this);
    }
    private void userLogin(){
        String email = etLogInEmail.getText().toString().trim();
        String password = etLogInPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please enter your email address",Toast.LENGTH_SHORT).show();
            //Stopping the function execution further
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Please enter your password",Toast.LENGTH_SHORT).show();
            //Stopping the function execution further
            return;
        }

        //all good till now
        //we'll show a progressbar
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        final Response.Listener<String> responseListener = new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        progressDialog.dismiss();
                        String name = jsonResponse.getString("name");
                        String email = jsonResponse.getString("email");


                        Intent intent = new Intent(LoginActivity.this,NavDrawer.class);
                        intent.putExtra("name",name);
                        intent.putExtra("email",email);


                        startActivity(intent);
                        finish();
                    }

                    else{
                        progressDialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("Log In Failed").setNegativeButton("Retry",null).create().show();
                    }

                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        };

        LoginRequest loginRequest = new LoginRequest(name, email,password,responseListener);
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(loginRequest);
    }



    @Override
    public void onBackPressed(){
        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
    }
}
