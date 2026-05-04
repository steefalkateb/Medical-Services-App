package com.example.alwadi.ui.doctors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.alwadi.R;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Doctor_By_Specialty_New extends AppCompatActivity {


    Doctor_Customadapter customadapter;
    public Doctor_Specialty_Getjson getjsonobj;
    String value_0 = null;

    ListView lv;
    CardView cardView;

//    private static final String newurl = "https://kalansarigroup.com/ALWADI_2021/doctors.php";


    private static final String newurl = "https://kalansarigroup.com/ALWADI_2021/doctors.php?specialty";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_doctor_by_specialty_new);
        setContentView(R.layout.booking_select_doctor);

        //        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        https://gist.github.com/androidcodehunter/16b83716d80904cfb5913a78adf93bad
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.alwadi), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        //////////////////////////////////////////////////
        lv = (ListView) findViewById(R.id.list_slidermenu);

        Log.e("222222", "222222222");
//                params
//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            value_0 = getArguments().getString("key_0");
//            Log.e("value_0", value_0);

        Intent intent = getIntent();
         value_0 = intent.getStringExtra("key_0");
        Log.e("value_000000000", value_0);

        getURLs();
    }

    //////////////////////////////////////////////////


    ///////////////////////////////////////////////////
    //Get FoodTYpe
    private void getImages() {
        class GetImages extends AsyncTask<Void, Void, Void> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
//                loading = ProgressDialog.show(getActivity(), "Loading ", "Please wait...", false, false);
                loading = ProgressDialog.show(Doctor_By_Specialty_New.this, "Loading...", "Please Wait...", false, false);

            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                loading.dismiss();


                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.e("lll", "mmm");

                        Intent intent = new Intent(Doctor_By_Specialty_New.this, Details_Doctors.class);
//
                        intent.putExtra("name", String.valueOf(getjsonobj.Android_Name[i]));
                        intent.putExtra("Image_Url", String.valueOf(getjsonobj.Image_Url[i]));

                        intent.putExtra("desc", String.valueOf(getjsonobj.Android_desc[i]));

                        intent.putExtra("specialty", String.valueOf(getjsonobj.Android_specialty[i]));
                        intent.putExtra("desc2", String.valueOf(getjsonobj.Android_desc2[i]));


//                        intent.putExtra("nameeee", String.valueOf(getjsonobj.Android_Name[i]));
//
                        startActivity(intent);

//                        Toast.makeText(getActivity(), String.valueOf(i), Toast.LENGTH_SHORT).show();


                    }

                });


                customadapter = new Doctor_Customadapter(Doctor_By_Specialty_New.this, getjsonobj.Android_Name, getjsonobj.Image_Url, getjsonobj.Android_desc, getjsonobj.Android_specialty, getjsonobj.Android_desc2);
                lv.setAdapter(customadapter);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getjsonobj.getAllImages();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        GetImages getImages = new GetImages();
        getImages.execute();
    }

    private void getURLs() {
        class GetURLs extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Doctor_By_Specialty_New.this, "Loading...", "Please Wait...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getjsonobj = new Doctor_Specialty_Getjson(s);
                getImages();
            }

            @Override
            protected String doInBackground(String... strings) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
//                        Log.e("666666666", json);

                        sb.append(json + "\n");

                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetURLs gu = new GetURLs();
        Log.e("ddddddddddddddddddddddd",value_0);
        gu.execute(newurl + "=" + value_0);
    }

    //////////////////////////////////////////////////
}
