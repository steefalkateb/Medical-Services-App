package com.example.alwadi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Register_Activity extends AppCompatActivity {
    private EditText name, email, password, c_password, photo;
    private Button btn_regist;
    private ProgressBar loading;
    //    private static  String URL_REGIST = "http://192.168.1.101/register.php";
    private static String URL_REGIST = "https://kalansarigroup.com/ALWADI_2021/register.php";
    private static String URL_check_email = "https://kalansarigroup.com/ALWADI_2021/check_email.php";


    public static SharedPreferences sharedPreferences;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedPreferences = this.getSharedPreferences("mypref", 0);

        loading = findViewById(R.id.loading);
        name = findViewById(R.id.name);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.c_password);
        btn_regist = findViewById(R.id.btn_regist);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);



        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();


                if (email.getText().toString().trim().matches(emailPattern)) {
//                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                    if (!password.getText().toString().equals(c_password.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Check the password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ChakeEmail(mEmail);

                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();

                    return;
                }


            }
        });
    }

    private void ChakeEmail(final String email2) {
//        Log.e("QQQQ", email2);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_check_email,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");


                            if (success.equals("1")) {
                                Log.e("ssssssss", "yes");
                                Toast.makeText(Register_Activity.this, "Email is available. Please enter another email", Toast.LENGTH_SHORT).show();


                            } else {
                                Log.e("ssssssss", "no");
                                Regist();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(Login_Activity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override

//            send params
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email2);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void Regist() {


        loading.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);

        final String name = this.name.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(Register_Activity.this,
                                        "Register Successs!", Toast.LENGTH_SHORT).show();

                                loading.setVisibility(View.GONE);
                                startActivity(new Intent(Register_Activity.this, Login_Activity.class));

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Register_Activity.this,
                                    "Register Error! " + e.toString(), Toast.LENGTH_SHORT)
                                    .show();
                            loading.setVisibility(View.GONE);
//                            btn_regist.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(RegisterActivity.this,
//                                "Register Error! " + error.toString(), Toast.LENGTH_SHORT)
//                                .show();
                        Toast.makeText(getApplicationContext(), "Register Error!", Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
//                        btn_regist.setVisibility(View.VISIBLE);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> Params = new HashMap<>();
                Params.put("name", name);
                Params.put("email", email);
                Params.put("password", password);
                Params.put("photo", "https://kalansarigroup.com/ALWADI_2021/login_virtual_image/logo_login.png");


                return Params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}