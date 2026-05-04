package com.example.alwadi;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Shree on 10/25/2016.
 */
public class Notifications_Customadapter extends ArrayAdapter<String> {
    private String[] name;
    private String[] desc1;
    private String[] desc2;

    private String[] date_time;

    private String[] url;
    private Bitmap[] bitmaps;
    private Activity context;

    public Notifications_Customadapter(Activity context, String[] date_time, String[] url, String[] desc1, String[] desc2) {
        super(context, R.layout.notifiction_list, desc1);
        this.context = context;
//        this.bitmaps = bitmaps;

        this.date_time = date_time;
        this.url = url;

        this.desc1 = desc1;
        this.desc2 = desc2;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.notifiction_list, null, true);

        TextView date_timetext = (TextView) listViewItem.findViewById(R.id.date_time);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imgvw);
        TextView desc1text = (TextView) listViewItem.findViewById(R.id.desc1);
        TextView desc2text = (TextView) listViewItem.findViewById(R.id.desc2);


        date_timetext.setText(date_time[position]);
//        Picasso.with(context)
//                .load(url[position])
//                .placeholder(R.drawable.logo_pro)
//                .fit()
//                .centerCrop()
//                .into(image);
//        Picasso.with(context)
//                .load(url[position])
//                .resize(50, 50)
//                .centerCrop()
//                .into(image);

        Picasso.with(context)
                .load(url[position])
                .fit()
                .centerCrop()
                .placeholder(R.drawable.logo_pro)
                .error(R.drawable.logo_pro)
                .into(image);

        desc1text.setText(desc1[position]);

        desc2text.setText(desc2[position]);

//                .resize(1000, 500)
//                .centerCrop()


        return listViewItem;

    }
}
