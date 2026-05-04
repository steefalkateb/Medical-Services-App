package com.example.alwadi.ui.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Clinic_Inpatient_Getjson {
    public static String[] Image_Url;
    //    public static Bitmap[] bitmaps;
    public static String[] Android_Name;
    public static String[] Android_desc1;
    public static String[] Android_desc2;
    public static String[] Android_time;
    public static String[] Android_price;

    public static final String JSON_ARRAY = "result";
    public static final String IMAGEURL = "image";
    public static final String name = "name";
    public static final String desc1 = "desc1";
    public static final String desc2 = "desc2";
    public static final String time = "time";
    public static final String price = "price";

    private String json;
    private JSONArray urls;



    public Clinic_Inpatient_Getjson(String json) {
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getImage(JSONObject jo) {
        URL url = null;
        Bitmap image = null;
        try {
            url = new URL(jo.getString(IMAGEURL));
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void getAllImages() throws JSONException {
        Android_Name = new String[urls.length()];
        Android_desc1 = new String[urls.length()];
        Android_desc2 = new String[urls.length()];
        Android_time = new String[urls.length()];
        Android_price = new String[urls.length()];
        Image_Url = new String[urls.length()];
//        bitmaps = new Bitmap[urls.length()];



        for (int i = 0; i < urls.length(); i++) {

            Android_Name[i] = urls.getJSONObject(i).getString(name);
            Android_desc1[i] = urls.getJSONObject(i).getString(desc1);
            Android_desc2[i] = urls.getJSONObject(i).getString(desc2);
            Android_time[i] = urls.getJSONObject(i).getString(time);
            Android_price[i] = urls.getJSONObject(i).getString(price);
//            Log.e("1111111111111111111",Android_Name[i]);


            Image_Url[i] = urls.getJSONObject(i).getString(IMAGEURL);
//            JSONObject jsonObject = urls.getJSONObject(i);
//
//            bitmaps[i] = getImage(jsonObject);
        }
    }
}



