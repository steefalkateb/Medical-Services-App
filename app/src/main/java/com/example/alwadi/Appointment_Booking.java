package com.example.alwadi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.recyclerview.widget.RecyclerView;

import static com.example.alwadi.MyApplication.Name_of_the_doctor;
import static com.example.alwadi.MyApplication.Profile_email;
import static com.example.alwadi.MyApplication.Name_of_the_service;


import static com.example.alwadi.MyApplication.My_Appointment;


public class Appointment_Booking extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    DatePickerDialog datePickerDialog;
    Calendar calendar;
    ProgressDialog loading;
    //    ArrayList<String> Array_Time_Work = new ArrayList<String>();
    JSONArray Arr_Time_Work = new JSONArray();
    //    String my_appointment = "";
    int Year, Month, Day, Hour, Minute;
    int Time_Work_Resualt;
    private String mSelectedDate, mTime;
    private static String URL_REGIST = "https://kalansarigroup.com/ALWADI_2021/regist_date_time.php";
    private static String URL_GETDATE = "https://kalansarigroup.com/ALWADI_2021/get_time_by_date.php";
    private static String URL_GETDATEANDTIME = "https://kalansarigroup.com/ALWADI_2021/get_date_and_time.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_booking);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.alwadi), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        Intent intent = getIntent();

//        my_appointment = intent.getStringExtra("my_appointment");


//        Log.e("my_appointment", my_appointment);


//    https://www.freakyjolly.com/android-material-datepicker-and-timepicker-by-wdullaer-tutorial-by-example/

        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);
        mSelectedDate = "";
        Time_Work_Resualt = 0;


        recyclerView = findViewById(R.id.rvNumbers);

        Log.e("customer_email", Profile_email);
        Log.e("Name_of_the_serviceeee", Name_of_the_service);
        Log.e("Name_of_the_doctorrrrr", Name_of_the_doctor);


        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("SELECT A DATE");
        final MaterialDatePicker materialDatePicker = builder.build();
        LinearLayout app_layer = (LinearLayout) findViewById(R.id.profilelinear);

//        app_layer.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                datePickerDialog = DatePickerDialog.newInstance(Appointment_Booking.this, Year, Month, Day);
////                datePickerDialog.setAccentColor(000000);
//
//                String white = "#9c4889";
//                int whiteInt = Color.parseColor(white);
//
//                datePickerDialog.setAccentColor(whiteInt);
//
//                datePickerDialog.setThemeDark(true);
//                datePickerDialog.showYearPickerFirst(false);
//                datePickerDialog.setTitle("Date Picker");
//                // Setting Min Date to today date
//                Calendar min_date_c = Calendar.getInstance();
//                datePickerDialog.setMinDate(min_date_c);
//                // Setting Max Date to next 2 years
//                Calendar max_date_c = Calendar.getInstance();
//                max_date_c.set(Calendar.MONTH, Month + 3);
//                datePickerDialog.setMaxDate(max_date_c);
//                //Disable all SUNDAYS and SATURDAYS between Min and Max Dates
//                for (Calendar loopdate = min_date_c; min_date_c.before(max_date_c); min_date_c.add(Calendar.DATE, 1), loopdate = min_date_c) {
//                    int dayOfWeek = loopdate.get(Calendar.DAY_OF_WEEK);
////                    if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
//                    if (dayOfWeek == Calendar.FRIDAY) {
//                        Calendar[] disabledDays = new Calendar[1];
//                        disabledDays[0] = loopdate;
//                        datePickerDialog.setDisabledDays(disabledDays);
//                    }
//                }
//                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                    @Override
//                    public void onCancel(DialogInterface dialogInterface) {
////                        Toast.makeText(Appointment_Booking.this, "Datepicker Canceled", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                datePickerDialog.show(getSupportFragmentManager(), "DatePickerDialog");
//            }
//
//            private void datePickerDialog(Appointment_Booking appointment_booking, int my_dialog_theme) {
//            }
//        });
        SelectDate();
    }


    public void SelectDate() {
        datePickerDialog = DatePickerDialog.newInstance(Appointment_Booking.this, Year, Month, Day);
//                datePickerDialog.setAccentColor(000000);

        String white = "#9c4889";
        int whiteInt = Color.parseColor(white);

        datePickerDialog.setAccentColor(whiteInt);

        datePickerDialog.setThemeDark(true);
        datePickerDialog.showYearPickerFirst(false);
        datePickerDialog.setTitle("Date Picker");
        // Setting Min Date to today date
        Calendar min_date_c = Calendar.getInstance();
        datePickerDialog.setMinDate(min_date_c);
        // Setting Max Date to next 2 years
        Calendar max_date_c = Calendar.getInstance();
        max_date_c.set(Calendar.MONTH, Month + 3);
        datePickerDialog.setMaxDate(max_date_c);


        //Disable all SUNDAYS and SATURDAYS between Min and Max Dates
        for (Calendar loopdate = min_date_c; min_date_c.before(max_date_c); min_date_c.add(Calendar.DATE, 1), loopdate = min_date_c) {
            int dayOfWeek = loopdate.get(Calendar.DAY_OF_WEEK);
//                    if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
//            if (dayOfWeek == Calendar.FRIDAY) {
            if (dayOfWeek == Calendar.SUNDAY) {

                Calendar[] disabledDays = new Calendar[1];
                disabledDays[0] = loopdate;
                datePickerDialog.setDisabledDays(disabledDays);
            }
        }
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
//                        Toast.makeText(Appointment_Booking.this, "Datepicker Canceled", Toast.LENGTH_SHORT).show();
            }
        });
        datePickerDialog.show(getSupportFragmentManager(), "DatePickerDialog");
    }

    public void Fill_Time(JSONArray arr) {
        Log.e("Fill_Time.arr", String.valueOf(arr));
//        Save_Appointment(arr);
//        for (int q = 0; q < arr.length(); q++) {
//            try {
//                JSONObject object = arr.getJSONObject(q);
//                String time = object.getString("time").trim();
//                Log.e("dddd", time);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
        /////////////////////////////////////////////time
        ArrayList<String> data = new ArrayList<String>();

        int m;
        int x = 9;//ساعات العمل
        int y = 10;//بدء العمل
        String s = null;
        Boolean check = false;


        for (int i = 0; i < x; i++) {

            m = i + y;

            if (m > 12) {
                m = m - 12;
//                Log.e("mmm", String.valueOf(m));
            }


//            for (int l = 0; l < 6; l++) {
            for (int l = 0; l < 3; l++) {

                //                s = m + ":" + (l * 10);
                s = m + ":" + (l * 20);
                if (l == 0) {
                    s = s + 0;
                }
                Log.e("sss", s);
//                        https://stackoverflow.com/questions/15039519/how-to-dynamically-add-elements-to-string-array
                for (int j = 0; j < arr.length(); j++) {
                    try {
                        JSONObject object = arr.getJSONObject(j);
                        String time = object.getString("time").trim();
//                        Log.e("nnnn", s);
                        if (s.equals(time)) {
                            check = true;
                            Log.e("check", String.valueOf(check));
//                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (!check) {
                    data.add(s);
                }
                check = false;

            }
        }

//        Log.e("sss", String.valueOf(data));


        // set up the RecyclerView
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new MyRecyclerViewAdapter(this, data);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        //////////////////////////////////
    }


    @Override
    public void onDateSet(DatePickerDialog view, int Year, int Month, int Day) {

        String date = Year + "-" + (Month + 1) + "-" + Day;
        loading = ProgressDialog.show(Appointment_Booking.this, "", "Please wait...", false, false);

//        Toast.makeText(Appointment_Booking.this, date, Toast.LENGTH_LONG).show();
        mSelectedDate = date;

        TextView text_datepicker = (TextView) findViewById(R.id.text_datepicker);
        text_datepicker.setText(date);
        GetDate();

    }

    @Override
    public void onItemClick(View view, int position) {
        Log.e("TAG", "You clicked number " + adapter.getItem(position) + ", which is at cell position " + position);

        mTime = adapter.getItem(position);
        adapter = new MyRecyclerViewAdapter(mSelectedDate);
        Log.e("sssss", My_Appointment);
        //////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////
//        Time_Work_Resualt = Integer.parseInt(My_Appointment) / 10;
        Time_Work_Resualt = Integer.parseInt(My_Appointment) / 20;

//        Time_Work_Resualt = Time_Work/ 10;

        AsyncCallWS task = new AsyncCallWS();
        task.execute();
    }

    private class AsyncCallWS extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            Log.e("TAG", "doInBackground");
            try {
                for (int i = 1; i <= Time_Work_Resualt; i++) {
                    GetDateandtime(mTime);
//                    mTime = adapter.getItem(Integer.parseInt(params[0]) + i);
                    ////////////////////////////////////
                    SimpleDateFormat df = new SimpleDateFormat("H:mm");
                    Date d = null;
                    try {
                        d = df.parse(mTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d);
//                    cal.add(Calendar.MINUTE, 10);
                    cal.add(Calendar.MINUTE, 10);

                    mTime = df.format(cal.getTime());
                    ///////////////////////////////////
                    Thread.sleep(2000);
                    Log.e("TAG", "55555555555555555555555555");
                }
            } catch (Exception ex) {
                Log.e("TAG", "erorrrrrrrrrrrrrr");
            }
            return "finish";
        }

        private void GetDateandtime(String mm) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GETDATEANDTIME,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("result");
//                            Log.e("aaaaaaaaaaaa", String.valueOf(jsonArray));
                                if (jsonArray.length() == 0) {
                                    Arr_Time_Work.put(mm);
                                    Log.e("cccccccc1", mm);
                                    loading = ProgressDialog.show(Appointment_Booking.this, "", "Please wait...", false, false);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();

//                                Toast.makeText(Appointment_Booking.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(Appointment_Booking.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("date", mSelectedDate);
                    params.put("time", mm);
//                    params.put("customer_name", customer_name);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Appointment_Booking.this);
            requestQueue.add(stringRequest);
        }


        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e("TAG", "onPostExecute");
            Log.e("Array_Time_Work", String.valueOf(Arr_Time_Work));


            if (Arr_Time_Work.length() != Time_Work_Resualt) {
                Toast.makeText(Appointment_Booking.this, "لايمكنك الحجز يرجى المحاولة مرة أخرى", Toast.LENGTH_SHORT).show();
                finish();

                Arr_Time_Work = new JSONArray();
                Time_Work_Resualt = 0;
                Log.e("www", Arr_Time_Work.toString());
            } else {

                AlertDialog alertDialog = new AlertDialog.Builder(Appointment_Booking.this)
//set icon
//                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setIcon(R.drawable.logo_pro)

//set title
//                        .setTitle("حجز موعد")

//set message
                        .setMessage("هل أنت متأكد من حجز الموعد الساعة " + String.valueOf(Arr_Time_Work))
//set positive button
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what would happen when positive button is clicked
                                loading = ProgressDialog.show(Appointment_Booking.this, "Saving ...", "Please wait...", false, false);

                                Save_Appointment();

//                                            finish();
                            }
                        })
//set negative button
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what should happen when negative button is clicked
//                                Toast.makeText(getApplicationContext(), "Nothing Happened", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        })
                        .show();
            }
        }
    }

    public void Save_Appointment() {
        //        ArrayList<String> Array_Time_Work = new ArrayList<String>();
//        JSONArray lolo = new JSONArray();
//        lolo.put("2:00");
//        lolo.put("10:10");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            loading.dismiss();

                            Toast.makeText(Appointment_Booking.this, "تم حجز الموعد", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(Appointment_Booking.this, MainActivity.class));


//                            if (success.equals("1")) {
//                                Toast.makeText(Appointment_Booking.this, "Saved222", Toast.LENGTH_SHORT).show();
//                            }

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
                params.put("time", Arr_Time_Work.toString());
                params.put("date", mSelectedDate);
                params.put("customer_email", Profile_email);
//
                params.put("service", Name_of_the_service);
                params.put("doctor", Name_of_the_doctor);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void GetDate() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GETDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("result");

                            Log.e("getdatearray.lenght", String.valueOf(jsonArray.length()));
                            Log.e("getdatearray", String.valueOf(jsonArray));
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject object = jsonArray.getJSONObject(i);
//                                String time = object.getString("time").trim();
//                                Log.e("dddd", time);}

                            Fill_Time(jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Appointment_Booking.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Appointment_Booking.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();


                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("date", mSelectedDate);
//                params.put("doctor", mdoctor);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}