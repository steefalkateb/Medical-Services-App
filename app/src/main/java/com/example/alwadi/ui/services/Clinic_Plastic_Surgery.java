package com.example.alwadi.ui.services;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alwadi.R;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.Context.MODE_PRIVATE;


public class Clinic_Plastic_Surgery extends Fragment {
    ListView lv;
    TextView result;

private static final String newurl = "https://kalansarigroup.com/ALWADI_2021/services.php?type";

    public Clinic_Plastic_Surgery_Getjson getjsonobj;

    Clinic_Customadapter customadapter;

    private TextView fullscreen_content;
    public static SharedPreferences sharedPreferences;
    public static String value_0 = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.clinic_listview, container, false);

        sharedPreferences = getActivity().getSharedPreferences("mypref", MODE_PRIVATE);
        value_0 = sharedPreferences.getString("value_0", "");
//        fullscreen_content = (TextView) root.findViewById(R.id.fullscreen_content);

        lv = (ListView) root.findViewById(R.id.list_slidermenu);
        getURLs();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if (i == 0) {
//                    openDetails_Service();
                } else {
//                    Toast.makeText(getActivity() , String.valueOf(i), Toast.LENGTH_SHORT).show();

//                    Toast.makeText(MainActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
//                    bottomNavigationView.getMenu().getItem(1).setChecked(true);
                }

            }

        });

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

                        Intent intent = new Intent(getActivity(), Details_Service_Clinic.class);

//                        intent.putExtra("firstKeyName", String.valueOf(Get2qqq.Android_Name[i]));
//                        intent.putExtra("secondKeyName", String.valueOf(Get2qqq.Android_Details[i]));
//                        intent.putExtra("therdKeyName", String.valueOf(Get2qqq.Android_Addrees[i]));
//                        intent.putExtra("Image_Url", String.valueOf(Get2qqq.Image_Url[i]));
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


//                customadapter = new Customadapterqqq(getActivity(), getjsonobj.Android_Name, getjsonobj.Android_Addrees, getjsonobj.bitmaps);
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
//                getjsonobj = new Get2qqq(s);
                getjsonobj = new Clinic_Plastic_Surgery_Getjson(s);

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
        gu.execute(newurl + "=" +  "plastic_surgery");
    }
}