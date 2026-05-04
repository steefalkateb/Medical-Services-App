package com.example.alwadi.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alwadi.MyApplication;
import com.example.alwadi.R;

public class CustomAdapter_Offer_Home extends ArrayAdapter<String> {


    private final Activity context;
//    private final String[] web;
//    private final String[] imageId;


    private final String[] name;
    private final String[] url;
    private ImageView profileImageView;

    private final String[] desc1;


        public CustomAdapter_Offer_Home(Activity context, String[] name, String[] url, String[] desc1) {
//    public CustomAdapter_Offer_Home(Activity context, String[] web, String[] imageId) {
        super(context, R.layout.card_offer_home, name);


        this.context = context;
        this.name = name;
        this.url = url;
        this.desc1 = desc1;
//        super(context, R.layout.card_offer_home, web);
//        this.context = context;
//        this.web = web;
//        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.card_offer_home, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.name);
        profileImageView = (ImageView) rowView.findViewById(R.id.img_card);

        txtTitle.setText(name[position]);
        Glide.with(getContext()).load(url[position]).into(profileImageView);

//        nametext.setText(name[position]);
////        desc1text.setText(desc1[position]);
//
//        profileImageView = (ImageView) rowView.findViewById(R.id.imgvw);
//
//        Glide.with(getContext()).load(url[position]).into(profileImageView);


//        TextView txtTitle = (TextView) rowView.findViewById(R.id.textViewCard);
//        profileImageView = (ImageView) rowView.findViewById(R.id.profileImageView);

//        txtTitle.setText(web[position]);
//        Glide.with(getContext()).load(imageId[position]).into(profileImageView);
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("88888888888", MyApplication.getjsonobjoffer.Android_Name[position]);
                Intent intent = new Intent(getContext(), Offer_Details.class);

                intent.putExtra("name",MyApplication.getjsonobjoffer.Android_Name[position]);
                intent.putExtra("img",MyApplication.getjsonobjoffer.Image_Url[position]);
                intent.putExtra("desc1",MyApplication.getjsonobjoffer.Android_desc1[position]);

                context.startActivity(intent);
            }
        });
        return rowView;
    }


}