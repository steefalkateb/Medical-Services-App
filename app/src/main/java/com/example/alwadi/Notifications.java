package com.example.alwadi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Notifications extends AppCompatActivity {
    ListView lv;
    TextView result;

    private static final String newurl = "https://kalansarigroup.com/ALWADI_2021/notifications.php";
    public Notifications_Getjson getjsonobj;
    Notifications_Customadapter customadapter;

    public static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.alwadi), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        lv = (ListView) findViewById(R.id.list_slidermenu);
        Log.e("11111111111","..................");
        getURLs();
    }

    ///////////////////////////////////////////////////
    //Get FoodTYpe
    private void getImages() {
        class GetImages extends AsyncTask<Void, Void, Void> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Notifications.this, "Loading ", "Please wait...", false, false);
            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                loading.dismiss();

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        Log.e("lll", "mmm");
//
                        Intent intent = new Intent(Notifications.this, Notifictions_Details.class);
                        intent.putExtra("date_time", String.valueOf(getjsonobj.Android_date_time[i]));
                        intent.putExtra("Image_Url", String.valueOf(getjsonobj.Image_Url[i]));
                        intent.putExtra("desc1", String.valueOf(getjsonobj.Android_desc1[i]));
                        intent.putExtra("desc2", String.valueOf(getjsonobj.Android_desc2[i]));
//
                        startActivity(intent);
//                        Toast.makeText(getActivity(), String.valueOf(i), Toast.LENGTH_SHORT).show();
                    }

                });


                customadapter = new Notifications_Customadapter(Notifications.this,getjsonobj.Android_date_time, getjsonobj.Image_Url,  getjsonobj.Android_desc1, getjsonobj.Android_desc2);
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
                loading = ProgressDialog.show(Notifications.this, "Loading...", "Please Wait...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getjsonobj = new Notifications_Getjson(s);
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
                        Log.e("666666666", json);

                        sb.append(json + "\n");

                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute(newurl);
    }

    //////////////////////////////////////////////////

}