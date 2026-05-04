package com.example.alwadi.ui.services;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.alwadi.R;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


//https://www.youtube.com/watch?v=n4BnLL0Y5Ww
public class Clinic_Dermatophytes extends Fragment {
    ListView lv;
    TextView result;

private static final String newurl = "https://kalansarigroup.com/ALWADI_2021/services.php?type";

    public Clinic_Dermatophytes_Getjson getjsonobj;
    Clinic_Customadapter customadapter;


    private Button button;
    private TextView fullscreen_content;
    public static SharedPreferences sharedPreferences;


    public static String value_0 = "";


    public Clinic_Dermatophytes() {
//        // Gerekli Boşluklar
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.clinic_listview, container, false);
//        result = (TextView) root.findViewById(R.id.name);
//        sharedPreferences = getActivity().getSharedPreferences("mypref", MODE_PRIVATE);
//        value_0 = sharedPreferences.getString("value_0", "");

        lv = (ListView) root.findViewById(R.id.list_slidermenu);
        Log.e("11111111111","..................");
        getURLs();


        return root;
    }


    ///////////////////////////////////////////////////
    //Get FoodTYpe
    private void getImages() {
        class GetImages extends AsyncTask<Void, Void, Void> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
//                loading = ProgressDialog.show(getActivity(), "Loading ", "Please wait...", false, false);
            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
//                loading.dismiss();

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.e("lll", "mmm");

                        Intent intent = new Intent(getActivity(), Details_Service_Clinic.class);

                        intent.putExtra("name", String.valueOf(getjsonobj.Android_Name[i]));
                        intent.putExtra("desc1", String.valueOf(getjsonobj.Android_desc1[i]));
                        intent.putExtra("desc2", String.valueOf(getjsonobj.Android_desc2[i]));
                        intent.putExtra("Image_Url", String.valueOf(getjsonobj.Image_Url[i]));
                        intent.putExtra("time", String.valueOf(getjsonobj.Android_time[i]));
                        intent.putExtra("price", String.valueOf(getjsonobj.Android_price[i]));

                        startActivity(intent);
//                        Toast.makeText(getActivity(), String.valueOf(i), Toast.LENGTH_SHORT).show();
                    }

                });


                customadapter = new Clinic_Customadapter(getActivity(), getjsonobj.Android_Name, getjsonobj.Android_desc1, getjsonobj.Android_desc2, getjsonobj.Image_Url, getjsonobj.Android_time, getjsonobj.Android_price);
                lv.setAdapter(customadapter);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getjsonobj.getAllImages();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        GetImages getImages = new GetImages();
        getImages.execute();
    }

    private void getURLs() {
        class GetURLs extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Loading...", "Please Wait...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getjsonobj = new Clinic_Dermatophytes_Getjson(s);
                getImages();
            }

            @Override
            protected String doInBackground(String... strings) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        Log.e("666666666", json);

                        sb.append(json + "\n");

                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute(newurl + "=" + "dermatophytes");
    }

    //////////////////////////////////////////////////

//    public void openDetails_Service() {
//        Intent intent = new Intent(getActivity(), Details_Service.class);
//        startActivity(intent);
//    }
}