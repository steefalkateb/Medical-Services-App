package com.example.alwadi.ui.doctors;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alwadi.R;
import com.example.alwadi.ui.services.Details_Service_Clinic;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Details_Doctors extends AppCompatActivity {
    ListView lv;

    String[] doctor2;
    String[] name2;
    String[] url2;
    String[] desc12;
    String[] desc22;
    String[] time2;
    String[] price2;
    String[] type2;
    String mdoctor;

    private TextView name, desc, desc2, specialty;
    private static final String newurl = "https://kalansarigroup.com/ALWADI_2021/binding_services.php";
    private ImageView Image_Url;

    private String[] imageId;


    Doctor_Customadapter customadapter;
    Binding_Doctor_Customadapter customadapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newwww_details_doctors);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        https://gist.github.com/androidcodehunter/16b83716d80904cfb5913a78adf93bad

/////////////////////////////////////////////////
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.alwadi), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
///////////////////////////////////////////////////
        this.imageId = imageId;

        name = (TextView) findViewById(R.id.name);
        desc = (TextView) findViewById(R.id.desc);
        desc2 = (TextView) findViewById(R.id.desc2);
//        specialty = (TextView) findViewById(R.id.specialty);
        ImageView image = (ImageView) findViewById(R.id.imgvw);

        lv = (ListView) findViewById(R.id.list_slidermenu);

        lv.setVerticalScrollBarEnabled(false);


        Intent myIntent = getIntent();
        String firstKeyName = myIntent.getStringExtra("name");
        String secondKeyName = myIntent.getStringExtra("desc");
        String therdKeyName = myIntent.getStringExtra("desc2");
//        String fourKeyName = myIntent.getStringExtra("specialty");

        String Image_Url = myIntent.getStringExtra("Image_Url");

        name.setText(firstKeyName);
        desc.setText(secondKeyName);
        desc2.setText(therdKeyName);
//        specialty.setText(fourKeyName);

//        image.setImageURI(Uri.parse(Image_Url));

        Picasso.with(Details_Doctors.this)
//        https://futurestud.io/tutorials/picasso-image-resizing-scaling-and-fit
                .load(Image_Url)
                .placeholder(R.drawable.logo_ionic)
                .into(image);

        GetData();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("dd", name2[i]);
                Intent intent = new Intent(Details_Doctors.this, Details_Service_Clinic.class);
//
                intent.putExtra("name", name2[i]);
                intent.putExtra("desc1", desc12[i]);
                intent.putExtra("desc2", desc22[i]);
                intent.putExtra("Image_Url", url2[i]);
                intent.putExtra("time", time2[i]);
                intent.putExtra("price", price2[i]);
//
                startActivity(intent);

            }

        });

        lv.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    private void GetData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, newurl,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("result");


                            doctor2 = new String[jsonArray.length()];
                            name2 = new String[jsonArray.length()];
                            url2 = new String[jsonArray.length()];
                            desc12 = new String[jsonArray.length()];
                            desc22 = new String[jsonArray.length()];
                            time2 = new String[jsonArray.length()];
                            price2 = new String[jsonArray.length()];
                            type2 = new String[jsonArray.length()];


                            for (int i = 0; i < jsonArray.length(); i++) {

                                ////////////////////////////////////////////////////////////
                                JSONObject object = jsonArray.getJSONObject(i);
                                doctor2[i] = object.getString("doctor").trim();
                                name2[i] = object.getString("name").trim();
                                url2[i] = object.getString("image").trim();
                                desc12[i] = object.getString("desc1").trim();
                                desc22[i] = object.getString("desc2").trim();
                                time2[i] = object.getString("time").trim();
                                price2[i] = object.getString("price").trim();
                                type2[i] = object.getString("type").trim();
                                ///////////////////////////////////////////////////////////
                                Log.e("HH", String.valueOf(object));
                            }

                            customadapter2 = new Binding_Doctor_Customadapter(Details_Doctors.this, doctor2, name2, url2, desc12, desc22, time2, price2, type2);
                            lv.setAdapter(customadapter2);

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
//                params.put("date", mSelectedDate);
                params.put("doctor", String.valueOf(name.getText()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Details_Doctors.this);
        requestQueue.add(stringRequest);


    }


}