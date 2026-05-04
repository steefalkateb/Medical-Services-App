package com.example.alwadi.ui.doctors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * file:///E:/Display%20data%20with%20image%20from%20mysql%20data%20base%20to%20android%20ListView%20using%20PHP.html
 */
public class Doctor_Specialty_Getjson {
    public static String[] Image_Url;
//    public static Bitmap[] bitmaps;
    public static String[] Android_Name;
    public static String[] Android_desc;
    public static String[] Android_desc2;
    public static String[] Android_specialty;

    public static final String JSON_ARRAY = "result";
    public static final String IMAGEURL = "image";
    public static final String name = "name";
    public static final String desc = "desc";
    public static final String desc2 = "desc2";
    public static final String specialty = "specialty";

    private String json;
    private JSONArray urls;



    public Doctor_Specialty_Getjson(String json) {
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
        Android_desc = new String[urls.length()];
        Android_desc2 = new String[urls.length()];
        Android_specialty = new String[urls.length()];

        Image_Url = new String[urls.length()];
//        bitmaps = new Bitmap[urls.length()];



        for (int i = 0; i < urls.length(); i++) {

            Android_Name[i] = urls.getJSONObject(i).getString(name);
            Android_desc[i] = urls.getJSONObject(i).getString(desc);
            Android_desc2[i] = urls.getJSONObject(i).getString(desc2);
            Android_specialty[i] = urls.getJSONObject(i).getString(specialty);
//            Log.e("1111111111111111111",Android_Name[i]);


            Image_Url[i] = urls.getJSONObject(i).getString(IMAGEURL);
//            JSONObject jsonObject = urls.getJSONObject(i);
//
//            bitmaps[i] = getImage(jsonObject);
        }
    }
}
