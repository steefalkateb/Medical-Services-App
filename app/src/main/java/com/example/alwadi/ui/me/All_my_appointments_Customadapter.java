package com.example.alwadi.ui.me;

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

public class All_my_appointments_Customadapter extends ArrayAdapter<String> {
    private String[] time;
    private String[] date;

    private String[] service;
    private String[] doctor;


    private Activity context;

        public All_my_appointments_Customadapter(Activity context, String[] time, String[] date, String[] service, String[] doctor) {
        super(context, R.layout.layout_all_my_appointments, date);
        this.context = context;
        this.time = time;
        this.date = date;
        this.service = service;
        this.doctor = doctor;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_all_my_appointments, null, true);

        TextView timetext = (TextView) listViewItem.findViewById(R.id.time);
        TextView datetext = (TextView) listViewItem.findViewById(R.id.date);
        TextView servicetext = (TextView) listViewItem.findViewById(R.id.service);
        TextView doctortext = (TextView) listViewItem.findViewById(R.id.doctor);


        timetext.setText(time[position]);
        datetext.setText(date[position]);
        servicetext.setText(service[position]);
        doctortext.setText(doctor[position]);


        return listViewItem;

    }
}
