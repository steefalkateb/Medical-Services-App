package com.example.alwadi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alwadi.ui.services.Details_Service_Clinic;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notifictions_Details extends AppCompatActivity {
    private TextView  desc1, desc2,date_time;

    private String[] imageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifictions_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.alwadi), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        this.imageId = imageId;
        date_time = (TextView) findViewById(R.id.date_time);
        ImageView image = (ImageView) findViewById(R.id.movieImage);


        desc1 = (TextView) findViewById(R.id.desc1);
        desc2 = (TextView) findViewById(R.id.desc2);



        Intent myIntent = getIntent();
        String fourKeyName = myIntent.getStringExtra("date_time");



        String Image_Url = myIntent.getStringExtra("Image_Url");

        String secondKeyName = myIntent.getStringExtra("desc1");
        String therdKeyName = myIntent.getStringExtra("desc2");



        date_time.setText(fourKeyName);

        Picasso.with(Notifictions_Details.this)
//        https://futurestud.io/tutorials/picasso-image-resizing-scaling-and-fit
                .load(Image_Url)
                .placeholder(R.drawable.logo_pro)
                .into(image);

        desc1.setText(secondKeyName);
        desc2.setText(therdKeyName);

//        image.setImageURI(Uri.parse(Image_Url));




    }
}