package com.example.alwadi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.alwadi.ui.me.MeViewModel;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.alwadi.MyApplication.Profile_email;
import static com.example.alwadi.MyApplication.Profile_id;
import static com.example.alwadi.MyApplication.Profile_name;
import static com.example.alwadi.MyApplication.Profile_photo;
import static com.example.alwadi.MyApplication.sharedPreferences;

public class My_Profile extends AppCompatActivity {
    private MeViewModel meViewModel;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private Button btn_update, btn_img;
    private TextView name;
    private TextView email;
    private TextView password;
    private TextView c_password;
    private String id;

    private ProgressBar loading;
    private ProgressBar loading_photo;

    private static String URL_UPLOAD = "https://kalansarigroup.com/ALWADI_2021/upload.php";
    private static String URL_UPDATE = "https://kalansarigroup.com/ALWADI_2021/updateuser.php";

    private Bitmap bitmap;
    CircleImageView imageView;
//    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        meViewModel = ViewModelProviders.of(this).get(MeViewModel.class);
        setContentView(R.layout.activity_my_profile);


        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.c_password);
        btn_update = findViewById(R.id.btn_update);
        name.setText(Profile_name);
        email.setText(Profile_email);
        loading = findViewById(R.id.loading);
//        loading_photo = root.findViewById(R.id.loading_photo);

        btn_img = findViewById(R.id.btn_img);
        imageView = findViewById(R.id.imageView);
        id = Profile_id;
        String.valueOf(((MyApplication) My_Profile.this.getApplication()).Profile_photo);

        Glide.with(My_Profile.this)
                .load(Profile_photo)
                .placeholder(R.drawable.add_image_user) //this would be your default image (like default profile or logo etc). it would be loaded at initial time and it will replace with your loaded image once glide successfully load image using url.
                .error(R.drawable.image_user)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
//                .centerCrop()
//                .override(800, 400)
                .into(imageView);


//        Picasso.with(My_Profile.this)
//                .load(((MyApplication) My_Profile.this.getApplication()).Profile_photo)
//                .placeholder(R.drawable.image_user)
//                .resize(1000, 500)
//                .centerCrop()
//                .into(imageView);


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().trim().matches(emailPattern)) {

                    String mEmail = email.getText().toString().trim();
                    String mPass = password.getText().toString().trim();


                    if (!mEmail.isEmpty() && !mPass.isEmpty()) {

                        if (!password.getText().toString().equals(c_password.getText().toString())) {
                            Toast.makeText(My_Profile.this, "Check the password", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        saveedit();
                    } else {
                        if (mEmail.isEmpty()) {
                            email.setError("Please insert email");
                        } else if (mPass.isEmpty()) {
                            password.setError("Pleasr insert password");
                        }
                    }
                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(My_Profile.this, "Invalid email address", Toast.LENGTH_SHORT).show();

                    return;
                }
            }
        });


        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseFile();
            }

        });


    }


    private void saveedit() {

//        Log.e("iddddd", String.valueOf(MyApplication.Profile_id));
        loading.setVisibility(View.VISIBLE);
        btn_update.setVisibility(View.GONE);

        final String name = this.name.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();

        Log.e("11111111111", id);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Glide.with(My_Profile.this).load(Profile_photo).into(imageView);

                                Toast.makeText(My_Profile.this, "success!", Toast.LENGTH_SHORT).show();
                                Log.e("iddddd", String.valueOf(Profile_id));
                                loading.setVisibility(View.GONE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                ((MyApplication) My_Profile.this.getApplication()).Save_password = false;

                                editor.clear();
                                editor.commit();


                                Intent intent = new Intent(My_Profile.this, Login_Activity.class);
                                startActivity(intent);

                                Log.e("notifiction_list", "ssssssssssssssssssssssssss");

                                My_Profile.this.finish();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(My_Profile.this,
                                    "Update Error! " + e.toString(), Toast.LENGTH_SHORT)
                                    .show();
                            loading.setVisibility(View.GONE);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override

//            send params
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("id", id);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(My_Profile.this);
        requestQueue.add(stringRequest);
    }


    private void ChooseFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            imageView.setImageURI(filePath);

            Log.e("IIIII", String.valueOf(imageView));


            try {
                bitmap = MediaStore.Images.Media.getBitmap(My_Profile.this.getContentResolver()
                        , filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            UploadPic(id, getStrimgImage(bitmap));
        }
    }


    //    private void UploadPic(final String id, final String photo) {
    private void UploadPic(final String id, final String photo) {

//        loading_photo.setVisibility(View.VISIBLE);

//        ProgressDialog dialog = new ProgressDialog(this);
////        dialog.setMessage("message");
//        dialog.setCancelable(false);
////        dialog.setInverseBackgroundForced(false);
//        dialog.show();


        ProgressDialog progress = new ProgressDialog(this);
//        progress.setTitle("Saving Photo ");
        progress.setMessage("Saving Photo ...");
        progress.setCancelable(true); // disable dismiss by tapping outside of the dialog
        progress.show();

// To dismiss the dialog
        Log.e("AA", "5555555555555555");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPLOAD,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
//                        Log.i("aaaaaaaaaaaaaaaaaaaaaa", response.toString());
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            ((MyApplication) My_Profile.this.getApplication()).Profile_photo = photo;

//                            imageView.setImageBitmap(photo);
//                            Picasso.with(My_Profile.this)
//                                    .load(Profile_photo)
//                                    .placeholder(R.drawable.image_user)
//                                    .into(imageView);
                            if (success.equals("1")) {
                                Toast.makeText(My_Profile.this, "success!", Toast.LENGTH_SHORT).show();
                                Log.e("success", "LLLLLLLLLLLLLLL");
                                Log.e("SSSSSSSSSSS", photo);
                                progress.hide();

//                                loading_photo.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
//                            Toast.makeText(My_Profile.this, "Try Again! " + e.toString(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(My_Profile.this,  "يرجى اختيار صورة أقل من 1MB ", Toast.LENGTH_SHORT).show();

                            Log.e("Again", "NOOOOO");
                            progress.hide();

//                            loading_photo.setVisibility(View.GONE);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(My_Profile.this, "Try Again! " + error.toString(), Toast.LENGTH_SHORT).show();

//                        Toast.makeText(My_Profile.this, "Try Again! " + "يرجى اختيار صورة أقل من 1.MB", Toast.LENGTH_SHORT).show();
                        Toast.makeText(My_Profile.this,  "يرجى اختيار صورة أقل من 1MB ", Toast.LENGTH_SHORT).show();

                        Log.e("NOOOOO", "NOOOOO");
//                        loading_photo.setVisibility(View.GONE);

                    }
                }) {
            @Override

//            send params
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("photo", photo);
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(My_Profile.this);
        requestQueue.add(stringRequest);


    }

    private String getStrimgImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageByArray = byteArrayOutputStream.toByteArray();
        String encodedImage = android.util.Base64.encodeToString(imageByArray, android.util.Base64.DEFAULT);

        Log.e("QQQQ", encodedImage);
        return encodedImage;
    }

}