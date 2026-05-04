package com.example.alwadi.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alwadi.R;
import com.squareup.picasso.Picasso;

public class Offer_Details extends AppCompatActivity {
    private TextView name, desc1;
    private ImageView Image_Url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.alwadi), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        name = (TextView) findViewById(R.id.name);
        desc1 = (TextView) findViewById(R.id.desc1);
        ImageView image = (ImageView) findViewById(R.id.movieImage);

        Intent myIntent = getIntent();
        String firstKeyName = myIntent.getStringExtra("name");
        String secondKeyName = myIntent.getStringExtra("desc1");
        String Image_Url = myIntent.getStringExtra("img");


        name.setText(firstKeyName);

        desc1.setText(secondKeyName);

        image.setImageURI(Uri.parse(Image_Url));

        Picasso.with(Offer_Details.this)
//        https://futurestud.io/tutorials/picasso-image-resizing-scaling-and-fit
                .load(Image_Url)
                .placeholder(R.drawable.logo_pro)
                .into(image);
    }
}