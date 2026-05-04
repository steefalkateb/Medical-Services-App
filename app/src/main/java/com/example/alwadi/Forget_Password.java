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
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

public class Forget_Password extends AppCompatActivity {
    private EditText email, subject, message;
    private Button btn_forget;
    private ProgressBar loading;


    private static String URL_Forget_Password = "https://kalansarigroup.com/ALWADI_2021/forgetpassword.php";
    private String mEmail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
//        https://www.youtube.com/watch?v=ZbosRfH1SnM
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);


        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        email = findViewById(R.id.email);
//        subject = findViewById(R.id.subject);
//        message = findViewById(R.id.message);
        btn_forget = findViewById(R.id.btn);
        loading = findViewById(R.id.loading);

        btn_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senEmail();
            }
        });

    }

    private void senEmail() {
        loading.setVisibility(View.VISIBLE);
        btn_forget.setVisibility(View.GONE);

        mEmail = email.getText().toString();
//        String mSubject = subject.getText().toString();
        String mSubject = "Your Password";
        mPassword = "lolll";


//        String mMessage = String.valueOf(((MyApplication) Forget_Password.this.getApplication()).Save_password);
        Log.e("ddkf", "elfe");
//

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_Forget_Password,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("sendpass");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonObject.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    mEmail = object.getString("email").trim();
                                    mPassword = "Your Password : " + object.getString("password").trim();

                                    String id = object.getString("id").trim();

                                    Log.e("email", mEmail);
                                    Log.e("password", mPassword);

                                    JavaEmailApi JavaEmailApi = new JavaEmailApi(Forget_Password.this, mEmail, mSubject, mPassword);
                                    JavaEmailApi.execute();

                                    startActivity(new Intent(Forget_Password.this, Login_Activity.class));

                                    loading.setVisibility(View.GONE);

                                }


                            } else {
                                Log.e("AAAAAAAAAAAA", "SSSSA");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            btn_forget.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        btn_forget.setVisibility(View.VISIBLE);
                        Toast.makeText(Forget_Password.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
//            send params
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("password", mPassword);
                params.put("email", mEmail);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        /////////////////////////////////////////////////////////////////////////////

    }
}