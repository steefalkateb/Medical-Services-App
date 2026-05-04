package com.example.alwadi.ui.doctors;

import android.app.Activity;
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
public class Binding_Select_Doctor_Customadapter extends ArrayAdapter<String> {


    private String[] doctor;
    private String[] url;

    private Activity context;

    public Binding_Select_Doctor_Customadapter(Activity context, String[] doctor, String[] url) {
//        super(context, R.layout.doctor_by_specialty, doctor);
        super(context, R.layout.activity_doctor_by_specialty_new, doctor);

        this.context = context;
        this.doctor = doctor;
        this.url = url;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
//        View listViewItem = inflater.inflate(R.layout.doctor_by_specialty, null, true);
        View listViewItem = inflater.inflate(R.layout.activity_doctor_by_specialty_new, null, true);

        TextView nametext = (TextView) listViewItem.findViewById(R.id.name);

        nametext.setText(doctor[position]);

        ImageView image = (ImageView) listViewItem.findViewById(R.id.imgvw);


        Picasso.with(context)
                .load(url[position])
                .placeholder(R.drawable.logo_pro)
                .resize(1000, 500)
                .centerInside()
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
