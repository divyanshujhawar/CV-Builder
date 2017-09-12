package com.example.android.cvbuilderapp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Divyanshu on 03-02-2017.
 */

public class RegisterRequest extends StringRequest {


    private static final String REGISTER_REQUEST_URL = "http://cafeorder.pe.hu/register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String email, String password, String age, Response.Listener<String> listener){
        super(Request.Method.POST,REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("email",email);
        params.put("password",password);
        params.put("age",age + "    ");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
