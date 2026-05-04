package com.example.alwadi.ui.me;

import android.app.ProgressDialog;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.alwadi.R;
import com.example.alwadi.ui.doctors.DoctorsViewModel;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.alwadi.MyApplication.Profile_email;

public class MeFragment extends Fragment {
    //    All_my_appointments
    private static String URL_GETALLMYAPPINTEMENTS = "https://kalansarigroup.com/ALWADI_2021/all_my_appointments.php?customer_email";

    public All_my_appointments_Getjson getjsonobj;
    All_my_appointments_Customadapter customadapter;
    private com.example.alwadi.ui.me.MeViewModel MeViewModel;

    ListView lv;
    TextView result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getURLs();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        MeViewModel = ViewModelProviders.of(this).get(MeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_me, container, false);

        result = (TextView) root.findViewById(R.id.time);
        lv = (ListView) root.findViewById(R.id.list_slidermenu);
        return root;

    }

    /////////////////////////////////////////////////
    //Get FoodTYpe
    private void getImages() {
        class GetImages extends AsyncTask<Void, Void, Void> {
            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Loading ...", "Please wait...", false, false);
            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                loading.dismiss();


                customadapter = new All_my_appointments_Customadapter(getActivity(), getjsonobj.Android_Time, getjsonobj.Android_Date, getjsonobj.Android_Service, getjsonobj.Android_Doctor);
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
                loading = ProgressDialog.show(getActivity(), "Loading ...", "Please Wait...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getjsonobj = new All_my_appointments_Getjson(s);
                getImages();
                Log.e("GGGG", "gggg");
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
        gu.execute(URL_GETALLMYAPPINTEMENTS + "=" + Profile_email);
        Log.e("WW", URL_GETALLMYAPPINTEMENTS);
    }

}
