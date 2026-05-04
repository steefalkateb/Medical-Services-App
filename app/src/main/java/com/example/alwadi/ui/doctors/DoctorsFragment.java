package com.example.alwadi.ui.doctors;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;


import com.example.alwadi.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DoctorsFragment extends Fragment {
    ProgressDialog loading;

//    private static final String newurl = "https://kalansarigroup.com/ALWADI_2021/doctors.php";
    //    http://tutorialscache.com/expandable-listview-android-tutorials/
    public Doctor_Specialty_Getjson getjsonobj;


    //////////////////////////////////////////////////////////////////

    private DoctorsViewModel doctorsViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        loading = ProgressDialog.show(getActivity(), "Loading...", "Please Wait...", true, true);


        doctorsViewModel =
                ViewModelProviders.of(this).get(DoctorsViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_doctors, container, false);
        View root = inflater.inflate(R.layout.fragment_doctors, container, false);

        final BottomNavigationView navigationView;
        navigationView = (BottomNavigationView) getActivity().findViewById(R.id.bottom_navigation);

        CardView cardView1 = root.findViewById(R.id.aa);
        CardView cardView2 = root.findViewById(R.id.bb);
        CardView cardView3 = root.findViewById(R.id.cc);
        CardView cardView4 = root.findViewById(R.id.dd);
        CardView cardView5 = root.findViewById(R.id.ee);
        CardView cardView6 = root.findViewById(R.id.ff);
        CardView cardView7 = root.findViewById(R.id.gg);
        CardView cardView8 = root.findViewById(R.id.hh);
        CardView cardView9 = root.findViewById(R.id.ii);
        CardView cardView10 = root.findViewById(R.id.jj);
        CardView cardView11 = root.findViewById(R.id.kk);
        CardView cardView12 = root.findViewById(R.id.ll);
        CardView cardView13 = root.findViewById(R.id.mm);
        CardView cardView14 = root.findViewById(R.id.nn);
        CardView cardView15 = root.findViewById(R.id.oo);
        CardView cardView16 = root.findViewById(R.id.pp);
        CardView cardView17 = root.findViewById(R.id.qq);
        CardView cardView18 = root.findViewById(R.id.rr);
        CardView cardView19 = root.findViewById(R.id.ss);
        CardView cardView20 = root.findViewById(R.id.tt);
        CardView cardView21 = root.findViewById(R.id.uu);






        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page1", "1");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "القلبية");
                startActivity(intent);

            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page2", "2");
                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "الهضمية");
                startActivity(intent);

//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "الهضمية");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
////                transaction.pop
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page3", "3");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "الجلدية والتجميل");
                startActivity(intent);


//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "الجلدية والتجميل");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page4", "4");
                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "الجراحة التجميلية");
                startActivity(intent);

//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "الجراحة التجميلية");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page5", "5");
                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "الجراحة العامة");
                startActivity(intent);

//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "الجراحة العامة");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page6", "6");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "البولية");
                startActivity(intent);
//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "البولية");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page7", "7");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "التغذية");
                startActivity(intent);
//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "التغذية");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page8", "8");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "الكلية");
                startActivity(intent);

//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "الكلية");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page9", "9");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "الغدد");
                startActivity(intent);

//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "الغدد");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page10", "10");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "النسائية");
                startActivity(intent);

//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "النسائية");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page11", "11");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "الأطفال");
                startActivity(intent);


//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "الأطفال");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page12", "12");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0",  "الجراحة العصبية");
                startActivity(intent);

//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "الجراحة العصبية");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page13", "13");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "العلاج الفيزيائي");
                startActivity(intent);

//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "العلاج الفيزيائي");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page14", "14");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "الداخلية");
                startActivity(intent);

//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "الداخلية");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page15", "15");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "أذن أنف حنجرة");
                startActivity(intent);

//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "أذن أنف حنجرة");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page16", "16");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "الأوعية");
                startActivity(intent);


//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "الأوعية");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page17", "17");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "الأشعة");
                startActivity(intent);

//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "الأشعة");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page18", "18");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "العظمية");
                startActivity(intent);

//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "العظمية");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page19", "19");
                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "الصدرية والتنفسية");
                startActivity(intent);

//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "الصدرية والتنفسية");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page20", "20");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0",  "الألم المزمن");
                startActivity(intent);

//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "الألم المزمن");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });
        cardView21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.getMenu().getItem(2).setChecked(true);
                Log.e("page21", "21");

                Intent intent = new Intent(getActivity(), Doctor_By_Specialty_New.class);
                intent.putExtra("key_0", "تخدير وانعاش");
                startActivity(intent);

//                Fragment someFragment = new Doctor_By_Specialty();
////                params
//                Bundle args = new Bundle();
//                args.putString("key_0", "تخدير وانعاش");
//                someFragment.setArguments(args);
////                params
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_fragment, someFragment); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });


        return root;
    }


}