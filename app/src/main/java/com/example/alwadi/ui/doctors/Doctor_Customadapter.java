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
public class Doctor_Customadapter extends ArrayAdapter<String> {

    private String[] specialty;

    private String[] name;
    private String[] url;

    private String[] desc;
    private String[] desc2;

//    private String[] time;
//    private String[] price;
//    private String[] type;

    private Bitmap[] bitmaps;
    private Activity context;

    public Doctor_Customadapter(Activity context, String[] name, String[] url, String[] desc, String[] specialty, String[] desc2) {
//        super(context, R.layout.doctor_by_specialty, desc);
        super(context, R.layout.activity_doctor_by_specialty_new, desc);
        this.context = context;

        this.name = name;
        this.url = url;

        this.desc = desc;
        this.specialty = specialty;

        this.desc2 = desc2;





    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
//        View listViewItem = inflater.inflate(R.layout.doctor_by_specialty, null, true);
        View listViewItem = inflater.inflate(R.layout.activity_doctor_by_specialty_new, null, true);

        TextView nametext = (TextView) listViewItem.findViewById(R.id.name);
        TextView desctext = (TextView) listViewItem.findViewById(R.id.desc);
//        TextView desc2text = (TextView) listViewItem.findViewById(R.id.desc2);
//        TextView specialtytext = (TextView) listViewItem.findViewById(R.id.desc2);

        nametext.setText(name[position]);
        desctext.setText(desc[position]);
//        desc2text.setText(desc2[position]);
//        specialtytext.setText(specialty[position]);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imgvw);


        Picasso.with(context)
                .load(url[position])
                .placeholder(R.drawable.logo_pro)
                .resize(600, 200)
                .centerInside()
                .into(image);


//        Picasso.with(context)
//                .load(url[position])
//                .placeholder(R.drawable.logo_pro)
//                .resize(1000, 500)
//                .centerCrop()
//                .into(image);

        return listViewItem;

    }


}
