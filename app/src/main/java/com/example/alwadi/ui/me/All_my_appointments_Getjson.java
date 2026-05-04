package com.example.alwadi.ui.me;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class All_my_appointments_Getjson {
    public static String[] Android_Time;
    public static String[] Android_Date;
    public static String[] Android_Service;
    public static String[] Android_Doctor;

    public static final String JSON_ARRAY = "result";
    public static final String time = "time";
    public static final String date = "date";
    public static final String service = "service";
    public static final String doctor = "doctor";

    private JSONArray urls;
    private String json;

    public All_my_appointments_Getjson(String json) {


        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(JSON_ARRAY);
            Log.e("LLLMMM", String.valueOf(urls));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getAllImages() throws JSONException {
        Android_Time = new String[urls.length()];
        Android_Date = new String[urls.length()];
        Android_Service = new String[urls.length()];
        Android_Doctor = new String[urls.length()];


        for (int i = 0; i < urls.length(); i++) {

            Android_Time[i] = urls.getJSONObject(i).getString(time);
            Android_Date[i] = urls.getJSONObject(i).getString(date);
            Android_Service[i] = urls.getJSONObject(i).getString(service);
            Android_Doctor[i] = urls.getJSONObject(i).getString(doctor);
            Log.e("aaaaaaaaaaaaaa", Android_Date[i]);
        }
    }

}
