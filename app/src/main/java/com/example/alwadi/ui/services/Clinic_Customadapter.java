package com.example.alwadi.ui.services;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alwadi.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Shree on 10/25/2016.
 */
public class Clinic_Customadapter extends ArrayAdapter<String> {
    private String[] name;
    private String[] desc1;
    private String[] desc2;

    private String[] time;
    private String[] price;

    private String[] url;
    private Bitmap[] bitmaps;
    private Activity context;

    public Clinic_Customadapter(Activity context, String[] name, String[] desc1, String[] desc2, String[] url, String[] time, String[] price) {
        super(context, R.layout.layout_clinic, desc1);
        this.context = context;
        this.url = url;
//        this.bitmaps = bitmaps;

        this.name = name;
        this.desc1 = desc1;
        this.desc2 = desc2;

        this.time = time;
        this.price = price;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_clinic, null, true);

        TextView nametext = (TextView) listViewItem.findViewById(R.id.name);
//        TextView desc1text = (TextView) listViewItem.findViewById(R.id.desc1);
//        TextView desc2text = (TextView) listViewItem.findViewById(R.id.desc2);
//        TextView timetext = (TextView) listViewItem.findViewById(R.id.desc2);
        TextView pricetext = (TextView) listViewItem.findViewById(R.id.price);

        nametext.setText(name[position]);
        pricetext.setText(price[position]);
//        desc2text.setText(desc2[position]);
//        timetext.setText(time[position]);
//        pricetext.setText(price[position]);


        ImageView image = (ImageView) listViewItem.findViewById(R.id.imgvw);

        Picasso.with(context)
                .load(url[position])
                .placeholder(R.drawable.logo_pro)
//                .resize(1000, 500)
//                .centerCrop()
                .fit()
                .centerCrop()
                .into(image);


        return listViewItem;

    }
}
