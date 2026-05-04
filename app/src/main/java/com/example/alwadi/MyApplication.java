package com.example.alwadi;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.alwadi.ui.home.Getjson_Offer_Home;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class MyApplication extends Application {
    public static boolean internet = false;

    public static String someVariable = "";
    public static String Profile_name = "";
    public static String Profile_email = "";
    public static String Profile_id = "";


    //    in Booking_Select_Doctor
    public static String Name_of_the_service = "";
    public static String Name_of_the_doctor = "";
    //

    //    in Appointment_Booking
    public static String My_Appointment = "";
    //


    public static String Profile_photo = "mmm";

//    public static String Last_id_notify = "";



    public static boolean Save_password = false;
    public static SharedPreferences sharedPreferences;

    public static Getjson_Offer_Home getjsonobjoffer;


    public boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())


                    haveConnectedWifi = true;

            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;

        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = this.getSharedPreferences("mypref", 0);
        Log.e("Start My Applaction", String.valueOf(Save_password));


        if (haveNetworkConnection()) {
            getURLs();
//            // Display message in dialog box if you have internet connection
//
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
//
//            alertDialogBuilder.setTitle("Internet Connection Available");
//            alertDialogBuilder.setMessage("Yes, you are online, internet connection available");
//            Toast.makeText(this, "Internet Connection Available", Toast.LENGTH_LONG).show();
//            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface arg0, int arg1) {
//                    Toast.makeText(MyApplication.this, "Internet Connection Available", Toast.LENGTH_LONG).show();
//                }
//            });
//
//            AlertDialog alertDialog = alertDialogBuilder.create();
//            alertDialog.show();
//
//        } else {
//            // Display message in dialog box if you have not internet connection
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
//            alertDialogBuilder.setTitle("No Internet Connection");
//            alertDialogBuilder.setMessage("You are offline please check your internet connection");
//            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
//            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface arg0, int arg1) {
//                    Toast.makeText(MyApplication.this, "No Internet Connection", Toast.LENGTH_LONG).show();
//                }
//            });
//
//            AlertDialog alertDialog = alertDialogBuilder.create();
//            alertDialog.show();
        }


//        getURLs();

        /*Nav Header Main //// Email*/
        /*Nav Header Main //// name*/

//        internet = Boolean.parseBoolean(sharedPreferences.getString("internet", String.valueOf(false)));


        Profile_name = sharedPreferences.getString("Profile_name", "");
        Profile_email = sharedPreferences.getString("Profile_email", "");
        Profile_id = sharedPreferences.getString("Profile_id", "");
        Save_password = sharedPreferences.getBoolean("Save_password", false);

        Profile_photo = sharedPreferences.getString("Profile_photo", "");


        Name_of_the_service = sharedPreferences.getString("Name_of_the_service", "");
        Name_of_the_doctor = sharedPreferences.getString("Name_of_the_doctor", "");

        My_Appointment = sharedPreferences.getString("My_Appointment", "");

//        Last_id_notify = sharedPreferences.getString("Last_id_notify", "");

    }


    public final String getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }


    private void getURLs() {
        class GetURLs extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                getjsonobjoffer = new Getjson_Offer_Home(s);
                try {
                    getjsonobjoffer.getAllImages();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                        Log.e("get all offers  sssssss", json);

                        sb.append(json + "\n");

                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute("http://192.168.1.103/offer_page.php");
    }

}