package com.example.android.cvbuilderapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import static com.example.android.cvbuilderapp.R.id.btSignInPage;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFirstName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etMobileNumber;
    public EditText etAge;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        progressDialog = new ProgressDialog(this);

        etFirstName = (EditText)findViewById(R.id.etName);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etConfirmPassword = (EditText)findViewById(R.id.etConfirmPassword);
        etMobileNumber = (EditText)findViewById(R.id.etMobileNumber);
        Button btCreateAccount = (Button) findViewById(R.id.btCreateAccount);
        btCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RegisterActivity.this,SampleActivity.class));

                //registerUser();
            }
        });

        Button btSignInPage = (Button) findViewById(R.id.btSignInPage);
        btSignInPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });

    }
    private void registerUser(){

        String name = etFirstName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirm_password = etConfirmPassword.getText().toString().trim();
        String age = etAge.getText().toString().trim();
        int passwordLength = password.length();


        if(TextUtils.isEmpty(name)){
            //First name is empty
            Toast.makeText(this,"Please enter your first name",Toast.LENGTH_SHORT).show();
            //Stopping the function execution further
            return;
        }

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
        if(passwordLength < 8){
            Toast.makeText(this,"The password should be atleast 8 characters long",Toast.LENGTH_SHORT).show();
        }

        if(passwordLength > 15){
            Toast.makeText(this,"The password should be below 16 characters",Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(confirm_password)){
            //Confirm password field is empty
            Toast.makeText(this,"Please enter your password again",Toast.LENGTH_SHORT).show();
            //Stopping the function execution further
            return;
        }
        if(TextUtils.isEmpty(age)){
            //password is empty
            Toast.makeText(this,"Please enter your age",Toast.LENGTH_SHORT).show();
            //Stopping the function execution further
            return;
        }

        if(etPassword.getText().toString().equals(etConfirmPassword.getText().toString())){
            //Do nothing
        }
        else{
            //pasword does not match
            Toast.makeText(this,"The passwords do not match, please try again",Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        //all good till now
        //we'll show a progressbar
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        Response.Listener<String> responseListener = new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if(success){
                        progressDialog.dismiss();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        finish();
                    }
                    else{
                        progressDialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("Registration Failed").setNegativeButton("Retry",null).create().show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegisterRequest registerRequest = new RegisterRequest(name,email,password,age, responseListener);
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        Log.i("request1","request1");
        queue.add(registerRequest);
    }


    /*@Override
    public void onClick(View view){
        if(view == btCreateAccount){
            registerUser();
        }
        if(view == btSignInPage){
            //Intent to login page
            startActivity(new Intent(this,LoginActivity.class));
        }
    }*/
    @Override
    public void onBackPressed(){
        startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
    }

}
