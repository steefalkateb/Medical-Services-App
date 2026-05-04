package com.example.alwadi.ui.doctors;

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
public class Binding_Doctor_Customadapter extends ArrayAdapter<String> {
    private static final String newurl = "https://kalansarigroup.com/ALWADI_2021/services.php?type";


    private String[] doctor;

    private String[] name;
    private String[] url;

    private String[] desc1;
    private String[] desc2;

    private String[] time;
    private String[] price;

    private String[] type;

    private Bitmap[] bitmaps;
    private Activity context;

    public Binding_Doctor_Customadapter(Activity context, String[] doctor, String[] name, String[] url, String[] desc1, String[] desc2, String[] time, String[] price, String[] type) {
        super(context, R.layout.layout_clinic, desc1);
        this.context = context;
        this.doctor = doctor;

        this.name = name;
        this.url = url;

        this.desc1 = desc1;

        this.desc2 = desc2;

        this.time = time;
        this.price = price;
        this.type = type;



    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_clinic, null, true);

        TextView nametext = (TextView) listViewItem.findViewById(R.id.name);
        TextView desc1text = (TextView) listViewItem.findViewById(R.id.desc1);
        TextView desc2text = (TextView) listViewItem.findViewById(R.id.desc2);
//        TextView timetext = (TextView) listViewItem.findViewById(R.id.desc2);
//        TextView pricetext = (TextView) listViewItem.findViewById(R.id.desc2);

        nametext.setText(name[position]);
//        desc1text.setText(desc1[position]);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imgvw);


        Picasso.with(context)
                .load(url[position])
                .placeholder(R.drawable.logo_pro)
//                .resize(1000, 500)
//                .centerCrop()
                .fit()
                .centerCrop()
                .into(image);
//        Picasso.with(context)
//                .load(url[position])
//                .placeholder(R.drawable.logo_pro)
//                .resize(1000, 500)
//                .centerCrop()
//
//                .into(image);

        return listViewItem;

    }


}
