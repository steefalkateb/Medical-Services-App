package com.example.alwadi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class SplashScreen_Activity extends AppCompatActivity {
    int SPLASH_TIME = 3000; //This is 3 seconds
    private BroadcastReceiver MyReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (((MyApplication) SplashScreen_Activity.this.getApplication()).haveNetworkConnection()) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do any action here. Now we are moving to next page

                    Intent mySuperIntent = new Intent(SplashScreen_Activity.this, Login_Activity.class);
                    startActivity(mySuperIntent);
                    finish();
                    //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
//                    finish();


                }
            }, SPLASH_TIME);

            Log.e("net", "ok");
        } else {
            // Display message in dialog box if you have not internet connection
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SplashScreen_Activity.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            alertDialogBuilder.setTitle("No Internet Connection");
            alertDialogBuilder.setMessage("You are offline please check your internet connection");
//            Toast.makeText(SplashScreen_Activity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
//            https://abhiandroid.com/ui/alertdialog
            alertDialogBuilder.setIcon(R.drawable.logo_pro);

            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    finish();
                    System.exit(0);
                    recreate();
                    finishAffinity();
                    //Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }


    }
}






