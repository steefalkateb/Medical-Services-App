package com.example.alwadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login_Activity extends AppCompatActivity {
    private EditText email, password;
    private Button btn_login;
    private TextView forgetpass;

    private TextView link_regist;
    private ProgressBar loading;
    private CheckBox check_box;


    private static String URL_LOGIN = "https://kalansarigroup.com/ALWADI_2021/login.php";

    public static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = this.getSharedPreferences("mypref", 0);

        Log.e("id", String.valueOf(((MyApplication) Login_Activity.this.getApplication()).Profile_id));


        getSupportActionBar().setDisplayShowTitleEnabled(false);


        if (((MyApplication) Login_Activity.this.getApplication()).Save_password == true) {
            startActivity(new Intent(Login_Activity.this, MainActivity.class));
        }

        loading = findViewById(R.id.loading);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        link_regist = findViewById(R.id.link_regist);
        forgetpass= findViewById(R.id.forgot_password);
        check_box = findViewById(R.id.checkbox_fav);

        TextView textView = (TextView) findViewById(R.id.link_regist);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        TextView password = (TextView) findViewById(R.id.password);
        password.setPaintFlags(password.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = email.getText().toString().trim();
                String mPass = password.getText().toString().trim();


                if (!mEmail.isEmpty() && !mPass.isEmpty()) {
                    Login(mEmail, mPass);
                } else {
                    if (mEmail.isEmpty()) {
                        email.setError("Please insert email");
                    } else if (mPass.isEmpty()) {
                        password.setError("Pleasr insert password");
                    }
                }


            }
        });

        link_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this, Register_Activity.class));

            }
        });

        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this, Forget_Password.class));

            }
        });

    }

    private void Login(final String email, final String password) {


        loading.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Boolean chack_login = false;

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if (success.equals("1")) {
                                chack_login = true;
                                for (int i = 0; i < jsonObject.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();
                                    String id = object.getString("id").trim();
                                    String photo = object.getString("photo").trim();
                                    Log.e("nnnnn3333", email);

                                    Log.e("chack_login", String.valueOf(chack_login));
//                                    Toast.makeText(Login_Activity.this,
//                                            "Successs Login. \nYour Name : " + id + name + "\nYoue Email: " + email, Toast.LENGTH_SHORT)
//                                            .show();
//                                    Toast.makeText(Login_Activity.this, "Successs Login. \n Welcome", Toast.LENGTH_SHORT).show();

                                    if (check_box.isChecked()) {
                                        Log.e("ssssssss", "yes");
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putBoolean("Save_password", true);
                                        editor.commit();
                                        ((MyApplication) Login_Activity.this.getApplication()).Save_password = true;

                                    } else {
                                        Log.e("ssssssss", "no");
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putBoolean("Save_password", false);
                                        editor.commit();
                                        ((MyApplication) Login_Activity.this.getApplication()).Save_password = false;
                                    }

                                    /*Nav Header Main //// Email*/
                                    /*Nav Header Main //// name*/
//                                    save in memory and variable

                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("Profile_name", name);
                                    editor.putString("Profile_email", email);
                                    editor.putString("Profile_id", id);
                                    editor.putString("Profile_photo", photo);


                                    editor.commit();

                                    ((MyApplication) Login_Activity.this.getApplication()).Profile_name = name;
                                    ((MyApplication) Login_Activity.this.getApplication()).Profile_email = email;
                                    ((MyApplication) Login_Activity.this.getApplication()).Profile_id = id;
                                    ((MyApplication) Login_Activity.this.getApplication()).Profile_photo = photo;


                                    Log.e("JJJJJJJJ", id);
                                    startActivity(new Intent(Login_Activity.this, MainActivity.class));

                                    loading.setVisibility(View.GONE);
                                }
                            } else {
                                Log.e("AAAAAAAAAAAA", "SSSSA");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);
                            if (chack_login == false) {
                                Toast.makeText(Login_Activity.this, "اسم المستخدم أو كلمة المرور غير صحيحة", Toast.LENGTH_SHORT).show();

                            }
//                            Toast.makeText(Login_Activity.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);
                        Toast.makeText(Login_Activity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override

//            send params
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}