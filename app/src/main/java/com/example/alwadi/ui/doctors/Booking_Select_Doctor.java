package com.example.alwadi.ui.doctors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alwadi.Appointment_Booking;
import com.example.alwadi.R;

import static com.example.alwadi.MyApplication.My_Appointment;
import static com.example.alwadi.MyApplication.Name_of_the_doctor;
import static com.example.alwadi.MyApplication.Name_of_the_service;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Booking_Select_Doctor extends AppCompatActivity {


    ListView lv;

    String[] doctor2;
    String[] url2;

    public static SharedPreferences sharedPreferences;

    private static final String newurl = "https://kalansarigroup.com/ALWADI_2021/binding_doctor.php";
    Binding_Select_Doctor_Customadapter customadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_select_doctor);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.alwadi), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        lv = (ListView) findViewById(R.id.list_slidermenu);
        Intent intent = getIntent();

        Name_of_the_service = intent.getStringExtra("Name_of_the_service");

        Log.e("Name_of_the_service", Name_of_the_service);
        GetData();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Name_of_the_doctor = String.valueOf(doctor2[i]);
                Log.e("Name_of_the_doctor", Name_of_the_doctor);
                Log.e("My_Appointment", My_Appointment);


                Intent intent = new Intent(Booking_Select_Doctor.this, Appointment_Booking.class);
//                Log.e("ddd", String.valueOf(doctor2[i]));


                startActivity(intent);

            }

        });

    }

    private void GetData() {
        Log.e("HH", "GGG");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, newurl,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("result");


                            doctor2 = new String[jsonArray.length()];
                            url2 = new String[jsonArray.length()];

                            for (int i = 0; i < jsonArray.length(); i++) {
                                ////////////////////////////////////////////////////////////
                                JSONObject object = jsonArray.getJSONObject(i);
                                doctor2[i] = object.getString("name").trim();

//                                Log.e("SS", doctor2[i]);
                                url2[i] = object.getString("image").trim();
                                ///////////////////////////////////////////////////////////
                            }

                            customadapter = new Binding_Select_Doctor_Customadapter(Booking_Select_Doctor.this, doctor2, url2);
                            lv.setAdapter(customadapter);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("services", Name_of_the_service);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Booking_Select_Doctor.this);
        requestQueue.add(stringRequest);


    }
}