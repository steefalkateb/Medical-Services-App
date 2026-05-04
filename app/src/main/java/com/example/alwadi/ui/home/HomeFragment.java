package com.example.alwadi.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alwadi.Appointment_Booking;
import com.example.alwadi.Login_Activity;
import com.example.alwadi.MyApplication;
import com.example.alwadi.R;
import com.example.alwadi.SplashScreen_Activity;
import com.example.alwadi.ui.services.ServicesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import link.fls.swipestack.SwipeStack;

public class HomeFragment extends Fragment implements SwipeStack.SwipeStackListener {
    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    private HomeViewModel homeViewModel;

    private SwipeStack mSwipeStack;

    private ArrayList<String> mData;

    private CustomAdapter_Offer_Home mAdapter2;

    private SwipeStackAdapter mAdapter;


    String[] name = {"العرض لسا مستمر لنهاية الشهر فقط", "وداعاً لمناطق تراكم الدهون", "تقنية الفراكشنال أزالة آثار حب الشباب", "حان الوقت لاستعادة النضارة", "ديرما بن هو العلاج النهائي"};

    String[] url = {"https://scontent.flca1-1.fna.fbcdn.net/v/t1.0-9/131425670_861160031284920_2201336965448832015_n.jpg?_nc_cat=105&ccb=2&_nc_sid=8bfeb9&_nc_ohc=kVlyqMf3eUgAX8eiTTJ&_nc_ht=scontent.flca1-1.fna&oh=6f41b8f96d12a26cee5c1c91dfd0c6ac&oe=600206F2",
            "https://scontent.flca1-1.fna.fbcdn.net/v/t1.0-9/131275900_860491618018428_6344499351822566676_n.jpg?_nc_cat=105&ccb=2&_nc_sid=8bfeb9&_nc_ohc=rSl0FvfsaPUAX_Y_ikE&_nc_ht=scontent.flca1-1.fna&oh=4fab78e42091d2abe8ed3c3f6f032f2c&oe=600087CF",
            "https://scontent.flca1-1.fna.fbcdn.net/v/t1.0-9/130279554_856284958439094_4159048713326604010_n.jpg?_nc_cat=105&ccb=2&_nc_sid=8bfeb9&_nc_ohc=xaFKTbXZlP4AX_5TstA&_nc_ht=scontent.flca1-1.fna&oh=a12cca452a14ba8cddc8bcd10bf592a6&oe=5FFF7972",
            "https://scontent.flca1-1.fna.fbcdn.net/v/t1.0-9/129961705_853549495379307_1143787178249799260_n.jpg?_nc_cat=105&ccb=2&_nc_sid=8bfeb9&_nc_ohc=imLju1J5AXYAX-VYdXo&_nc_ht=scontent.flca1-1.fna&oh=7f2a521ef4ae5ad5b99ff4c901205d79&oe=6001369C",
            "https://scontent.flca1-2.fna.fbcdn.net/v/t1.0-9/128421800_849938945740362_3811808902965415959_n.jpg?_nc_cat=102&ccb=2&_nc_sid=8bfeb9&_nc_ohc=96StHTOBfaYAX-n0rCU&_nc_ht=scontent.flca1-2.fna&oh=388531028f6c53da1df238f4bfd09d53&oe=60021861"};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /////////////////////////////////////////////////////////
//        Log.e("ppp", Profile_photo);
//        Log.e("home", "eeee");




        autoswipeLeft();

        RelativeLayout relativeclic1 = root.findViewById(R.id.bb);
        RelativeLayout relativeclic2 = root.findViewById(R.id.cc);
        RelativeLayout relativeclic3 = root.findViewById(R.id.dd);
        RelativeLayout relativeclic4 = root.findViewById(R.id.ee);
        RelativeLayout relativeclic5 = root.findViewById(R.id.ff);
        RelativeLayout relativeclic6 = root.findViewById(R.id.gg);
        RelativeLayout relativeclic7 = root.findViewById(R.id.hh);
        //////////////////////////عيادات أخرى/////////////////////
        RelativeLayout relativeclic8 = root.findViewById(R.id.ii);
        RelativeLayout relativeclic9 = root.findViewById(R.id.jj);
        RelativeLayout relativeclic10 = root.findViewById(R.id.kk);
        RelativeLayout relativeclic11 = root.findViewById(R.id.ll);
        RelativeLayout relativeclic12 = root.findViewById(R.id.mm);
        RelativeLayout relativeclic13 = root.findViewById(R.id.nn);
        RelativeLayout relativeclic14 = root.findViewById(R.id.oo);
        RelativeLayout relativeclic15 = root.findViewById(R.id.pp);
        RelativeLayout relativeclic16 = root.findViewById(R.id.qq);
        RelativeLayout relativeclic17 = root.findViewById(R.id.rr);
        RelativeLayout relativeclic18 = root.findViewById(R.id.ss);
        RelativeLayout relativeclic19 = root.findViewById(R.id.tt);
        RelativeLayout relativeclic20 = root.findViewById(R.id.uu);

        /////////////////////////////////////////////////////////

        final BottomNavigationView navigationView;
        navigationView = (BottomNavigationView) getActivity().findViewById(R.id.bottom_navigation);

        relativeclic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                mSwipeStack.swipeTopViewToLeft();


                navigationView.getMenu().getItem(1).setChecked(true);

                Log.e("page1", "1");


                Fragment someFragment = new ServicesFragment();
//                params
                Bundle args = new Bundle();
                args.putString("key_0", "0");
                someFragment.setArguments(args);
//                params
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

            }
        });
        relativeclic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page2", "2");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "1");
                someFragment.setArguments(args);
//                params


                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page3", "3");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "2");
                someFragment.setArguments(args);
//                params


                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page4", "4");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "3");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page5", "5");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "4");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page6", "6");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "5");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page7", "7");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "6");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page8", "8");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "7");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page9", "9");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "7");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page10", "10");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "7");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page11", "11");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "7");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page11", "11");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "7");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page11", "11");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "7");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page11", "11");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "7");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page11", "11");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "7");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page11", "11");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "7");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page11", "11");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "7");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page11", "11");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "7");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page11", "11");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "7");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        relativeclic20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(1).setChecked(true);
                Log.e("page11", "11");

                Fragment someFragment = new ServicesFragment();

//                params
                Bundle args = new Bundle();
                args.putString("key_0", "7");
                someFragment.setArguments(args);
//                params

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
//        final TextView textView = root.findViewById(R.id.text_home);

//        mSwipeView = (SwipePlaceHolderView) root.findViewById(R.id.swipeView);

        mSwipeStack = (SwipeStack) root.findViewById(R.id.swipeStack);

        CardView cardView = (CardView) root.findViewById(R.id.card_view);

        mData = new ArrayList<>();
        mAdapter = new SwipeStackAdapter(mData);
        mSwipeStack.setAdapter(mAdapter);
        mSwipeStack.setListener(this);


//        mAdapter2 = new CustomAdapter_Offer_Home(getActivity(), name, url);
        mAdapter2 = new CustomAdapter_Offer_Home(getActivity(), MyApplication.getjsonobjoffer.Android_Name, MyApplication.getjsonobjoffer.Image_Url, MyApplication.getjsonobjoffer.Android_desc1);
        mSwipeStack.setAdapter(mAdapter2);
        fillWithTestData();

///////////////////////////////////////////////////////

//        Log.e("222", String.valueOf(MyApplication.getjsonobjoffer.Android_Name.length));

//        Log.e("222", "222");


        return root;
    }


    //////////////////////////////////////////////////

    private void fillWithTestData() {
        for (int x = 0; x < MyApplication.getjsonobjoffer.Android_Name.length; x++) {
            mData.add(getString(R.string.dummy_text) + " " + (x + 1));

        }

    }

    @Override
    public void onViewSwipedToLeft(int position) {


        String swipedElement = mAdapter.getItem(position);

//        Log.e("qqqqqqqqqqqq", String.valueOf(position));
    }

    @Override
    public void onViewSwipedToRight(int position) {
        String swipedElement = mAdapter.getItem(position);
//        Log.e("aaaaaaaaaaaaaaaaaaaa", String.valueOf(position));
    }


    public void autoswipeLeft() {

        final int intervalTime = 5000; // 5 sec
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Display Data here
                mSwipeStack.swipeTopViewToLeft();
//                Log.e("DDD", "Left");
                autoswipeRight();

            }
        }, intervalTime);

    }


    public void autoswipeRight() {

        final int intervalTime = 3000; // 3 sec
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Display Data here
                mSwipeStack.swipeTopViewToRight();
//                Log.e("DDD", "Right");
                autoswipeLeft();
            }
        }, intervalTime);

    }


//    }

    @Override
    public void onStackEmpty() {

        mSwipeStack.resetStack();
    }

    public class SwipeStackAdapter extends BaseAdapter {

        private List<String> mData;

        public SwipeStackAdapter(List<String> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {

            return mData.size();
        }

        @Override
        public String getItem(int position) {
//            Log.e("wwwwwwwwwwwww", MyApplication.getjsonobjoffer.Android_Name[position]);
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            return convertView;
        }


    }


}