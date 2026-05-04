package com.example.alwadi;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.alwadi.MyApplication.Profile_photo;

import static com.example.alwadi.MyApplication.Profile_name;
import static com.example.alwadi.MyApplication.sharedPreferences;

public class MainActivity extends AppCompatActivity {

    //    NAV&MENU
//    https://www.youtube.com/watch?v=FgwkbH8NY_w
    private static final String url_notify = "https://kalansarigroup.com/ALWADI_2021/select_notif.php";


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private TextView name, email;
    private Button btn_profile;
    private ImageView imageview;

    private String aa;
    String[] web = {"عن مركز الوادي الطبي", "فيسبوك", "انستغرام", "الموقع الالكتروني", "اتصل بنا", "موقعنا", "تسجيل الخروج"};

    Integer[] imageId = {
            R.drawable.aboutt,
            R.drawable.facebook,
            R.drawable.insta,
            R.drawable.website,
            R.drawable.callme,
            R.drawable.location,
            R.drawable.sign_out,
    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout app_layer = (LinearLayout) findViewById(R.id.profilelinear);
        app_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("WWWWW", "KKKKK");
                startActivity(new Intent(MainActivity.this, My_Profile.class));
            }
        });
        Button btn_profile = (Button) findViewById(R.id.btn_profile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("WWWWW", "KKKKK");
                startActivity(new Intent(MainActivity.this, My_Profile.class));
            }
        });

        name = (TextView) findViewById(R.id.name);
//        email = (TextView) findViewById(R.id.email);
        imageview = (ImageView) findViewById(R.id.imageView);
        String url = Profile_photo;

//        Log.e("photo", Profile_photo);

        Glide.with(MainActivity.this)
                .load(Profile_photo)
                .placeholder(R.drawable.image_user) //this would be your default image (like default profile or logo etc). it would be loaded at initial time and it will replace with your loaded image once glide successfully load image using url.
                .error(R.drawable.add_image_user)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageview);


//        Glide.with(MainActivity.this) //passing context
//                .load("https://you-ps.ru/uploads/posts/2013-08/1376601606_1273.png") //passing your url to load image.
//                .placeholder(R.drawable.image_user) //this would be your default image (like default profile or logo etc). it would be loaded at initial time and it will replace with your loaded image once glide successfully load image using url.
//                .error(R.drawable.add_image_user)//in case of any glide exception or not able to download then this image will be appear . if you won't mention this error() then nothing to worry placeHolder image would be remain as it is.
//                .diskCacheStrategy(DiskCacheStrategy.ALL) //using to load into cache then second time it will load fast.
//                .fitCenter()//this method help to fit image into center of your ImageView
//                .into(imageview);


        name.setText(Profile_name);

//.
        ///////                     Add right menu                           ////
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        ///////                     Add right menu                           ////

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        onHandleIntent();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);


        navController = Navigation.findNavController(this, R.id.main_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
//menu https://www.youtube.com/watch?v=FgwkbH8NY_w
        drawerLayout = findViewById(R.id.my_drawer);
        navigationView = findViewById(R.id.navigation_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        final ListView lv = (ListView) findViewById(R.id.list_slidermenu);
        CustomList_Menu adapter = new CustomList_Menu(MainActivity.this, web, imageId);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                Toast.makeText(MainActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();

                if (i == 0) {
                    openAbout_us();
                } else if (i == 1) {

                    Uri uri = Uri.parse("https://www.facebook.com/alwadi.medical/");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));

                } else if (i == 2) {
                    Uri uri = Uri.parse("https://www.instagram.com/alwadi_medical/?igshid=e136y3s1017f");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                } else if (i == 3) {
                    Uri uri = Uri.parse("https://weldon-group.com/");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                } else if (i == 4) {

                    Log.e("ee", "ee");
                    Intent callIntent = new Intent(Intent.ACTION_CALL); //use ACTION_CALL class
                    callIntent.setData(Uri.parse("tel:0997442442"));    //this is the phone number calling
                    //check permission
                    //If the device is running Android 6.0 (API level
                    // 23) and the app's targetSdkVersion is 23 or higher,
                    //the system asks the user to grant approval.


                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        //request permission from user if the app hasn't got the required permission
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
                                10);
                        return;
                    } else {     //have got permission
                        try {
                            startActivity(callIntent);  //call activity and make phone call
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
                        }

                    }


                } else if (i == 5) {
//                    String strUri = "http://maps.google.com/maps?q=loc:" + 34.741102 + "," + 36.3147925 + " (" + "Label which you want" + ")";
                    String strUri = "http://maps.google.com/maps?q=loc:" + 34.7551537 + "," + 36.318908 + " (" + "Label which you want" + ")";


//                    https://www.google.com/maps/place/34%C2%B045'18.6%22N+36%C2%B019'08.1%22E/@34.7551537,36.31672,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d34.7551537!4d36.3189087?hl=en

//                    https://www.google.com/maps/place/Alhawash.mall/                          @34.7551016,36.3210264,17z/data=!3m1!4b1!4m5!3m4!1s0x1523b7a666ac426b:0xc2d5c27ca07c5eb9!8m2!3d34.7551016!4d36.3188377
//                                        /34.7551016.3210264

                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));

                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");

                    startActivity(intent);

                } else if (i == 6) {
//                    Toast.makeText(MainActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
//                    bottomNavigationView.getMenu().getItem(1).setChecked(true);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    ((MyApplication) MainActivity.this.getApplication()).Save_password = false;
                    editor.clear();
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, Login_Activity.class);
                    startActivity(intent);
                    Log.e("notifiction_list", "ssssssssssssssssssssssssss");
                    finish();

                }


            }

        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
//
//        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.notices) {
            Intent intent = new Intent(this, Notifications.class);
            startActivity(intent);

            Log.e("ooo", "kkk");

        } else if (toggle.onOptionsItemSelected(item))

            return super.onOptionsItemSelected(item);
        return true;

    }

    public void openAbout_us() {
        Intent intent = new Intent(this, About_us.class);
        startActivity(intent);
    }

    protected void onHandleIntent() {
        Log.e("lolo", "2222222222222222222222222");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_notify,
                new Response.Listener<String>() {
                    //                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonObject.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String desc1 = object.getString("desc1").trim();
                                    String desc2 = object.getString("desc2").trim();
                                    int id = object.getInt("id");


                                    Log.e("iiiiiiiiiiii", desc1);



                                    if (sharedPreferences.getInt("Last_id_notify", 0) < id) {
                                        Log.e("Last_id_notify", String.valueOf(id));

                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putInt("Last_id_notify", id);
                                        Log.e("Last_id_notify", String.valueOf(id));

                                        editor.commit();


//                                        // Creat New Intent
//                                            Intent intent = new Intent(getApplication(), mybroacast.class);
//                                            intent.setAction("com.example.Broadcast");
//                                            intent.putExtra("desc1", desc1);
//                                            intent.putExtra("id", id);
//                                            sendBroadcast(intent);

                                    }

//                                        Toast.makeText(getApplication(), id, Toast.LENGTH_SHORT).show();

                                }


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

//                            Toast.makeText(MainActivity.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {

//                        Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();


                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//                params.put("id", "0");
//                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        //////////////////////////////////// end function
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {
//
//        }


    }
//    public void Chack_Net() {
////        https://stackoverflow.com/questions/35431339/how-to-navigate-to-another-activity-if-network-available
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        android.net.NetworkInfo wifi = cm
//                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        android.net.NetworkInfo datac = cm
//                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//        if ((wifi != null & datac != null)
//                && (wifi.isConnected() | datac.isConnected())) {
//
//            setContentView(R.layout.fragment_home);
//        } else {
//            //no connection
//            Toast toast = Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_LONG);
//            toast.show();
//            finish();
//        }
//    }


    public boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if ((mobile != null && mobile.isConnectedOrConnecting() || wifi != null && wifi.isConnectedOrConnecting())) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public AlertDialog.Builder buildDialog(Context c) {
//        https://www.youtube.com/watch?v=TUKNr5uciBE
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection !");
        builder.setMessage("Pleace Turn On Mobile Data or Wifi. Press Ok to Exit");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        return builder;
    }


}