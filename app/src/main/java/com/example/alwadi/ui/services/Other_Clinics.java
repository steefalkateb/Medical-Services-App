package com.example.alwadi.ui.services;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alwadi.R;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.Context.MODE_PRIVATE;


public class Other_Clinics extends Fragment {
    ListView lv;
    TextView result;


    private static final String newurl = "https://kalansarigroup.com/ALWADI_2021/services.php?type";

    public Other_Clinic_Getjson getjsonobj;
    Clinic_Customadapter customadapter;


    private Button button;
    private TextView fullscreen_content;
    public static SharedPreferences sharedPreferences;


    public static String value_0 = "";


    public Other_Clinics() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.other_clinics, container, false);

        View root = inflater.inflate(R.layout.clinic_listview, container, false);
//        result = (TextView) root.findViewById(R.id.name);

        sharedPreferences = getActivity().getSharedPreferences("mypref", MODE_PRIVATE);
        value_0 = sharedPreferences.getString("value_0", "");
//        fullscreen_content = (TextView) root.findViewById(R.id.fullscreen_content);

        lv = (ListView) root.findViewById(R.id.list_slidermenu);
        getURLs();


//        CustomList_birinci adapter = new CustomList_birinci(getActivity(), web, web2, imageId);
//        lv.setAdapter(adapter);
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


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("value_0", value_0);
        editor.commit();


//        if (value_0 == "0") {
//            Log.e("xxx0", value_0);
////            fullscreen_content.setText("الوجه");
//        } else if (value_0 == "1") {
//            Log.e("xxx1", value_0);
////            fullscreen_content.setText("الحقن");
//        } else if (value_0 == "2") {
//            Log.e("xxx2", value_0);
////            fullscreen_content.setText("العمليات العصبية");
//        } else if (value_0 == "3") {
//            Log.e("xxx3", value_0);
////            fullscreen_content.setText("التقشير الكميائي");
//        }
//
////        params
//        String value_0 = null;
//
//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            value_0 = getArguments().getString("key");
//            Log.e("arg", value_0);
//            fullscreen_content.setText("الوجه");
//        }


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e("dddd", "value_0");
//
////                Intent intent = new Intent(birinci.this.getContext(), ibinci.class);
////                startActivity(intent);
//
//                FragmentManager fm = getFragmentManager();
//                assert fm != null;
//                FragmentTransaction ft = fm.beginTransaction();
//                ServicesFragment llf = new ServicesFragment();
//                ft.replace(R.id.viewpager, llf);
//                ft.commit();
//            }
//        });
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
//                loading = ProgressDialog.show(getActivity(), "Loading Menu", "Please wait...", false, false);
            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
//                loading.dismiss();

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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
                getjsonobj = new Other_Clinic_Getjson(s);
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
//                        Log.e("666666666", json);

                        sb.append(json + "\n");

                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute(newurl + "=" + "other");
    }

    //////////////////////////////////////////////////

}