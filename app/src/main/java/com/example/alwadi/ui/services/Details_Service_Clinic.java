package com.example.alwadi.ui.services;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.alwadi.ui.doctors.Booking_Select_Doctor;
import com.example.alwadi.ui.doctors.Doctor_Customadapter;
import com.example.alwadi.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.alwadi.MyApplication.My_Appointment;


public class Details_Service_Clinic extends AppCompatActivity {
    ListView lv;

    private static final String newurl = "https://kalansarigroup.com/ALWADI_2021/binding_doctor.php";


    Doctor_Customadapter customadapter;


    private Button btn_appointment_booking;
    private TextView name, desc1, desc2, time, price, time_send;
    private ImageView Image_Url;
    private String[] imageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_service_clinic);
        Log.e("Details", "eeee");
//        button_back
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        https://gist.github.com/androidcodehunter/16b83716d80904cfb5913a78adf93bad
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.alwadi), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
///////////////////////////////////////////////////


        this.imageId = imageId;
//        fullscreen_content = (TextView) findViewById(R.id.textView);
//        lv = (ListView) findViewById(R.id.list_slidermenu);

        name = (TextView) findViewById(R.id.name);
        desc1 = (TextView) findViewById(R.id.desc1);
        desc2 = (TextView) findViewById(R.id.desc2);
        desc2.setMovementMethod(new ScrollingMovementMethod());
        time = (TextView) findViewById(R.id.time);
        price = (TextView) findViewById(R.id.price);
        btn_appointment_booking = findViewById(R.id.btn_appointment_booking);

        ImageView image = (ImageView) findViewById(R.id.movieImage);

        Intent myIntent = getIntent();


        String firstKeyName = myIntent.getStringExtra("name");
        String secondKeyName = myIntent.getStringExtra("desc1");
        String therdKeyName = myIntent.getStringExtra("desc2");
        String fourKeyName = myIntent.getStringExtra("time");
        String fiveKeyName = myIntent.getStringExtra("price");


        String Image_Url = myIntent.getStringExtra("Image_Url");



        My_Appointment = fourKeyName;
//        image.setImageBitmap(Bitmap.createScaledBitmap(bitmaps[0], 100, 50, false));


//        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();


        name.setText(firstKeyName);

        desc1.setText(secondKeyName);
        desc2.setText(therdKeyName);
        time.setText(fourKeyName);

        Log.e("time_send", String.valueOf(time_send));
        price.setText(fiveKeyName);

//        image.setImageURI(Uri.parse(Image_Url));

        Picasso.with(Details_Service_Clinic.this)
//        https://futurestud.io/tutorials/picasso-image-resizing-scaling-and-fit
                .load(Image_Url)
                .placeholder(R.drawable.logo_pro)
                .into(image);


        //        Picasso.get().load(Image_Url).into(image);

//        Log.e("nexttttttttttttttt", Image_Url);
///////////////////////////////////////////////////

//        getSupportActionBar().hide(); //<< this

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        https://www.tutorialspoint.com/how-to-handle-a-back-button-in-an-android-activity
//        button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        btn_appointment_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("DDDD", "DDDDD");
//                params
//                Intent myIntent = new Intent(Details_Service_Clinic.this, Appointment_Booking.class);
//                myIntent.putExtra("my_appointment", fourKeyName);
//                startActivity(myIntent);

//                Intent myIntent = new Intent(Details_Service_Clinic.this, Booking_Select_Doctor.class);
//                myIntent.putExtra("Name_of_the_service", firstKeyName);
//                startActivity(myIntent);
                Intent myIntent = new Intent(Details_Service_Clinic.this, Booking_Select_Doctor.class);
                myIntent.putExtra("Name_of_the_service", firstKeyName);
                myIntent.putExtra(My_Appointment, fourKeyName);
                Log.e("fourKeyName", fourKeyName);

                startActivity(myIntent);


//                startActivity(new Intent(Details_Service_Clinic.this, Appointment_Booking.class));

            }
        });

//        GetData();

    }


    private void GetData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, newurl,
                new Response.Listener<String>() {
                    String[] doctor;
                    String[] name;
                    String[] url;
                    String[] desc1;
                    String[] desc2;
                    String[] time;
                    String[] price;
                    String[] type;

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("result");


                            doctor = new String[jsonArray.length()];
                            name = new String[jsonArray.length()];
                            url = new String[jsonArray.length()];
                            desc1 = new String[jsonArray.length()];
                            desc2 = new String[jsonArray.length()];
                            time = new String[jsonArray.length()];
                            price = new String[jsonArray.length()];
                            type = new String[jsonArray.length()];

                            for (int i = 0; i < jsonArray.length(); i++) {

                                ////////////////////////////////////////////////////////////
                                JSONObject object = jsonArray.getJSONObject(i);
                                doctor[i] = object.getString("doctor").trim();
                                name[i] = object.getString("name").trim();
                                url[i] = object.getString("url").trim();
                                desc1[i] = object.getString("desc1").trim();
                                desc2[i] = object.getString("desc2").trim();
                                time[i] = object.getString("time").trim();
                                price[i] = object.getString("price").trim();
                                type[i] = object.getString("type").trim();
                                ///////////////////////////////////////////////////////////

                            }

//                            customadapter = new Binding_Doctor_Customadapter(Details_Service_Clinic.this, doctor, name, url, desc1, desc2, time, price, type);
//                            lv.setAdapter(customadapter);

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
//                params.put("doctor", mdoctor);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Details_Service_Clinic.this);
        requestQueue.add(stringRequest);


    }

}