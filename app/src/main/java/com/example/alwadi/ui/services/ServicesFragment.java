
package com.example.alwadi.ui.services;

import android.content.SharedPreferences;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.alwadi.R;

import com.google.android.material.tabs.TabLayout;


public class ServicesFragment extends Fragment {

    private ViewPager mViewPager;
    private ServicesViewModel servicesViewModel;
    public static SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        servicesViewModel = ViewModelProviders.of(this).get(ServicesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_services, container, false);

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getActivity(), getChildFragmentManager());


        adapter.addFragment(new Clinic_Dermatophytes(), "الجلدية والتجميل");
        adapter.addFragment(new Clinic_Digestive(), "الهضمية");
        adapter.addFragment(new Clinic_Glands_Diabetes(), "أمراض الغدد والداء السكري");
        adapter.addFragment(new Clinic_Thoracic_Respiratory(), "الأمراض الصدرية والتنفسية");
        adapter.addFragment(new Clinic_Cardiovascular_Diseases(), "أمراض القلب والأوعية");
        adapter.addFragment(new Clinic_Plastic_Surgery(), "الجراحة التجميلية");
//        adapter.addFragment(new Clinic_Nose_Ear_Throat(), "أمراض الأذن والأنف والحنجرة");
        adapter.addFragment(new Clinic_Inpatient(), "العيادة الداخلية");
        adapter.addFragment(new Other_Clinics(), "عيادات أخرى");


        mViewPager = (ViewPager) root.findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.tabs);
        mViewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(mViewPager);


        //                params
        Bundle bundle = this.getArguments();
        String value_0 = null;
        if (bundle != null) {
            value_0 = getArguments().getString("key_0");
            Log.e("value_0000000", value_0);
        }

        if (value_0 == "0") {
            Log.e("value_0", value_0);
            tabLayout.getTabAt(0).select();

        } else if (value_0 == "1") {
            Log.e("value_1", value_0);
            tabLayout.getTabAt(1).select();
        } else if (value_0 == "2") {
            Log.e("value_2", value_0);
            tabLayout.getTabAt(2).select();
        } else if (value_0 == "3") {
            Log.e("value_3", value_0);
            tabLayout.getTabAt(3).select();
        } else if (value_0 == "4") {
            Log.e("value_4", value_0);
            tabLayout.getTabAt(4).select();
        } else if (value_0 == "5") {
            Log.e("value_5", value_0);
            tabLayout.getTabAt(5).select();
        } else if (value_0 == "6") {
            Log.e("value_6", value_0);
            tabLayout.getTabAt(6).select();
        } else if (value_0 == "7") {
            Log.e("value_7", value_0);
            tabLayout.getTabAt(7).select();
        } else if (value_0 == "8") {
            Log.e("value_8", value_0);
            tabLayout.getTabAt(7).select();
        } else if (value_0 == "9") {
            Log.e("value_9", value_0);
            tabLayout.getTabAt(7).select();
        } else if (value_0 == "10") {
            Log.e("value_10", value_0);
            tabLayout.getTabAt(7).select();
        } else if (value_0 == "11") {
            Log.e("value_11", value_0);
            tabLayout.getTabAt(7).select();
        } else if (value_0 == "12") {
            Log.e("value_12", value_0);
            tabLayout.getTabAt(7).select();
        } else if (value_0 == "13") {
            Log.e("value_13", value_0);
            tabLayout.getTabAt(7).select();
        } else if (value_0 == "14") {
            Log.e("value_14", value_0);
            tabLayout.getTabAt(7).select();
        } else if (value_0 == "15") {
            Log.e("value_15", value_0);
            tabLayout.getTabAt(7).select();
        } else if (value_0 == "16") {
            Log.e("value_16", value_0);
            tabLayout.getTabAt(7).select();
        } else if (value_0 == "17") {
            Log.e("value_17", value_0);
            tabLayout.getTabAt(7).select();
        } else if (value_0 == "18") {
            Log.e("value_18", value_0);
            tabLayout.getTabAt(7).select();
        } else if (value_0 == "19") {
            Log.e("value_19", value_0);
            tabLayout.getTabAt(7).select();
        }
        return root;
    }

    private void setupViewPager(ViewPager viewPager) {
//        SectionPageAdapter sectionsPagerAdapter = new SectionPageAdapter(this, getSupportFragmentManager());

    }


}
/////////////////////////////////////////////////////////////////////////////
//package com.example.alwadi.ui.services;
//
//import android.content.SharedPreferences;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TabHost;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.Toolbar;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentActivity;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.lifecycle.ViewModelProviders;
//import androidx.viewpager.widget.ViewPager;
//
//import com.example.alwadi.ui.services.Clinic_Cardiovascular_Diseases;
//import com.example.alwadi.ui.services.Clinic_General_Surgery;
//import com.example.alwadi.ui.services.Clinic_Glands_Diabetes;
//import com.example.alwadi.ui.services.Clinic_Nose_Ear_Throat;
//import com.example.alwadi.ui.services.Clinic_Nutrition;
//import com.example.alwadi.ui.services.Clinic_Physical_Treatment;
//import com.example.alwadi.ui.services.Clinic_Plastic_Surgery;
//import com.example.alwadi.ui.services.Clinic_Thoracic_Respiratory;
//import com.example.alwadi.ui.services.Clinic_Urinary_Genital;
//import com.example.alwadi.ui.services.Other_Clinics;
//import com.example.alwadi.R;
//
//import com.example.alwadi.ui.services.Clinic_Digestive;
//import com.example.alwadi.ui.services.Clinic_Dermatophytes;
//import com.google.android.material.tabs.TabLayout;
//
//import static android.content.Context.MODE_PRIVATE;
//
//
//public class ServicesFragment extends Fragment {
//
//
//    private Toolbar toolbar;
//    private TabLayout tabLayout;
//    private ViewPager viewPager;
//
//
//    private FragmentActivity myContext;
//
//    private ServicesViewModel servicesViewModel;
//    public static SharedPreferences sharedPreferences;
//
//
//        public View onCreateView(@NonNull LayoutInflater inflater,
//                                 ViewGroup container, Bundle savedInstanceState) {
//            servicesViewModel =
//                    ViewModelProviders.of(this).get(ServicesViewModel.class);
//            View root = inflater.inflate(R.layout.fragment_services, container, false);
//
//        Log.e("services", "eeee");
//
//
//        sharedPreferences = getActivity().getSharedPreferences("mypref", MODE_PRIVATE);
//
//
//        Fragment someFragment = new Clinic_Dermatophytes();
//
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.main_fragment22, someFragment); // give your fragment container id in first parameter
//        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//        transaction.commit();
//
//
//        toolbar = (Toolbar) root.findViewById(R.id.toolbar);
//
//
////        viewPager = (ViewPager) root.findViewById(R.id.viewpager);
//        tabLayout = (TabLayout) root.findViewById(R.id.tabs);
//
//
////        setupViewPager(viewPager);
////        tabLayout.setupWithViewPager(viewPager);
//
//
//        tabLayout.addTab(tabLayout.newTab().setText("الجلدية والتجميل"));
//        tabLayout.addTab(tabLayout.newTab().setText("الهضمية"));
//        tabLayout.addTab(tabLayout.newTab().setText("أمراض الغدد والداء السكري"));
//        tabLayout.addTab(tabLayout.newTab().setText("الأمراض الصدرية والتنفسية"));
//        tabLayout.addTab(tabLayout.newTab().setText("أمراض القلب والأوعية"));
//        tabLayout.addTab(tabLayout.newTab().setText("الجراحة التجميلية"));
//        tabLayout.addTab(tabLayout.newTab().setText("أمراض الأذن والأنف والحنجرة"));
//        tabLayout.addTab(tabLayout.newTab().setText("عيادات أخرى"));
//
//
////        tabLayout.setScrollX(5);
////        tabLayout.getTabAt(5).select();
//
////        tabLayout.addTab(tabLayout.newTab().setText("الأمراض البولية والتناسلية ومعالجة العقم"));
////        tabLayout.addTab(tabLayout.newTab().setText("الجراحة العامة"));
////        tabLayout.addTab(tabLayout.newTab().setText("العلاج الفيزيائي"));
////        tabLayout.addTab(tabLayout.newTab().setText("التغذية"));
////        tabLayout.addTab(tabLayout.newTab().setText("الأشعة ( ايكو _ أشعة بسيطة _ بانوراما )"));
////        tabLayout.addTab(tabLayout.newTab().setText("النسائية"));
////        tabLayout.addTab(tabLayout.newTab().setText("العظمية"));
////        tabLayout.addTab(tabLayout.newTab().setText("الأطفال"));
////        tabLayout.addTab(tabLayout.newTab().setText("الجراحة العصبية"));
////        tabLayout.addTab(tabLayout.newTab().setText("طب عام اسعاف"));
////        tabLayout.addTab(tabLayout.newTab().setText("جراحة الأوعية الدموية"));
//
//
//////        Pager adapter2 = new Pager(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
//////        viewPager.setAdapter(adapter2);
////
//////            tabLayout.getTabAt(1).select();
//        //                params
//        Bundle bundle = this.getArguments();
//        String value_0 = null;
//        if (bundle != null) {
//            value_0 = getArguments().getString("key_0");
//        }
//
//        if (value_0 == "0") {
//            Log.e("value_0", value_0);
//
//            tabLayout.getTabAt(0).select();
//
//
//
//
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("value_0", value_0);
//            editor.commit();
//
//
//            Fragment someFragment1 = new Clinic_Dermatophytes();
//            FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
//            transaction1.replace(R.id.main_fragment22, someFragment1); // give your fragment container id in first parameter
//            transaction1.addToBackStack(null);  // if written, this transaction will be added to backstack
//            transaction1.commit();
//
//
//            Log.e("editor000", String.valueOf(tabLayout.getWidth()));
//
//        } else if (value_0 == "1") {
//            Log.e("value_0", value_0);
//            tabLayout.getTabAt(1).select();
//
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("value_0", value_0);
//            editor.commit();
//
//
//            Fragment someFragment2 = new Clinic_Digestive();
//            FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
//            transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
//            transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
//            transaction2.commit();
//
//            Log.e("editor111", value_0);
//
//
//        } else if (value_0 == "2") {
//            Log.e("value_0", value_0);
//            tabLayout.getTabAt(2).select();
//
//
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("value_0", value_0);
//            editor.commit();
//
//            Fragment someFragment2 = new Clinic_Glands_Diabetes();
//            FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
//            transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
//            transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
//            transaction2.commit();
//
//            Log.e("editor222", value_0);
//
//
//        } else if (value_0 == "3") {
//            Log.e("value_0", value_0);
//
//            tabLayout.getTabAt(3).select();
//
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("value_0", value_0);
//            editor.commit();
//
//            Fragment someFragment2 = new Clinic_Thoracic_Respiratory();
//            FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
//            transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
//            transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
//            transaction2.commit();
//            Log.e("editor333", value_0);
//
//            super.onResume();
//
//        } else if (value_0 == "4") {
//            Log.e("value_0", value_0);
//            tabLayout.getTabAt(4).select();
//
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("value_0", value_0);
//            editor.commit();
//
//            Fragment someFragment2 = new Clinic_Cardiovascular_Diseases();
//            FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
//            transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
//            transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
//            transaction2.commit();
//            Log.e("editor444", value_0);
//        } else if (value_0 == "5") {
//            Log.e("value_0", value_0);
//            tabLayout.getTabAt(5).select();
//
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("value_0", value_0);
//            editor.commit();
//
//            Fragment someFragment2 = new Clinic_Plastic_Surgery();
//            FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
//            transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
//            transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
//            transaction2.commit();
//            Log.e("editor555", value_0);
//        } else if (value_0 == "6") {
//            Log.e("value_0", value_0);
//
//            tabLayout.getTabAt(6).select();
//
////            tabLayout.scrollTo(5,0);
//
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("value_0", value_0);
//            editor.commit();
//
//            Fragment someFragment2 = new Clinic_Nose_Ear_Throat();
//            FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
//            transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
//            transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
//            transaction2.commit();
//            Log.e("editor666", value_0);
//        }
//        /////////////////////////////////////////////////////
//
//
//        else if (value_0 == "7") {
//            Log.e("value_0", value_0);
//            tabLayout.getTabAt(7).select();
//
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("value_0", value_0);
//            editor.commit();
//
//            Fragment someFragment2 = new Other_Clinics();
//            FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
//            transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
//            transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
//            transaction2.commit();
//            Log.e("editor777", value_0);
//        }
////        else if (value_0 == "8") {
////            Log.e("value_0", value_0);
////            tabLayout.getTabAt(8).select();
////
////            SharedPreferences.Editor editor = sharedPreferences.edit();
////            editor.putString("value_0", value_0);
////            editor.commit();
////
////            Fragment someFragment2 = new Clinic_General_Surgery();
////            FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
////            transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
////            transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
////            transaction2.commit();
////            Log.e("editor888", value_0);
////        } else if (value_0 == "9") {
////            Log.e("value_0", value_0);
////            tabLayout.getTabAt(9).select();
////
////            SharedPreferences.Editor editor = sharedPreferences.edit();
////            editor.putString("value_0", value_0);
////            editor.commit();
////
////            Fragment someFragment2 = new Clinic_Physical_Treatment();
////            FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
////            transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
////            transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
////            transaction2.commit();
////            Log.e("editor999", value_0);
////        } else if (value_0 == "10") {
////            Log.e("value_0", value_0);
////            tabLayout.getTabAt(10).select();
////
////            SharedPreferences.Editor editor = sharedPreferences.edit();
////            editor.putString("value_0", value_0);
////            editor.commit();
////
////            Fragment someFragment2 = new Clinic_Nutrition();
////            FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
////            transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
////            transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
////            transaction2.commit();
////            Log.e("editor التغذية", value_0);
////        }
//
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
////                Toast.makeText(getContext(), tab.getText(), Toast.LENGTH_SHORT).show();
////                viewPager.setCurrentItem(tab.getPosition());
//                Log.e("aaaaaaaaaaaaaaaaaa", String.valueOf(tab.getText()));
//
////                ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
//
//                if (tab.getText() == "الجلدية والتجميل") {
//                    Fragment someFragment = new Clinic_Dermatophytes();
//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.replace(R.id.main_fragment22, someFragment); // give your fragment container id in first parameter
//                    transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                    transaction.commit();
//
//                } else if (tab.getText() == "الهضمية") {
//                    Fragment someFragment2 = new Clinic_Digestive();
//                    FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
//                    transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
//                    transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
//                    transaction2.commit();
//
//
//                } else if (tab.getText() == "أمراض الغدد والداء السكري") {
//                    Fragment someFragment2 = new Clinic_Glands_Diabetes();
//                    FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
//                    transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
//                    transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
//                    transaction2.commit();
//                } else if (tab.getText() == "الأمراض الصدرية والتنفسية") {
//                    Fragment someFragment2 = new Clinic_Thoracic_Respiratory();
//                    FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
//                    transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
//                    transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
//                    transaction2.commit();
//                } else if (tab.getText() == "أمراض القلب والأوعية") {
//                    Fragment someFragment2 = new Clinic_Cardiovascular_Diseases();
//                    FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
//                    transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
//                    transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
//                    transaction2.commit();
//                } else if (tab.getText() == "الجراحة التجميلية") {
//                    Fragment someFragment2 = new Clinic_Plastic_Surgery();
//                    FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
//                    transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
//                    transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
//                    transaction2.commit();
//                } else if (tab.getText() == "أمراض الأذن والأنف والحنجرة") {
//                    Fragment someFragment2 = new Clinic_Nose_Ear_Throat();
//                    FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
//                    transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
//                    transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
//                    transaction2.commit();
//                } else if (tab.getText() == "عيادات أخرى") {
//                    Fragment someFragment2 = new Other_Clinics();
//                    FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
//                    transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
//                    transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
//                    transaction2.commit();
//                }
////                } else if (tab.getText() == "الأمراض البولية والتناسلية ومعالجة العقم") {
////                    Fragment someFragment2 = new Clinic_Urinary_Genital();
////                    FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
////                    transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
////                    transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
////                    transaction2.commit();
////                }else if (tab.getText() == "الجراحة العامة") {
////                    Fragment someFragment2 = new Clinic_General_Surgery();
////                    FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
////                    transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
////                    transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
////                    transaction2.commit();
////                }else if (tab.getText() == "العلاج الفيزيائي") {
////                    Fragment someFragment2 = new Clinic_Physical_Treatment();
////                    FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
////                    transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
////                    transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
////                    transaction2.commit();
////                }else if (tab.getText() == "التغذية") {
////                    Fragment someFragment2 = new Clinic_Nutrition();
////                    FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
////                    transaction2.replace(R.id.main_fragment22, someFragment2); // give your fragment container id in first parameter
////                    transaction2.addToBackStack(null);  // if written, this transaction will be added to backstack
////                    transaction2.commit();
//
//
//            }
//
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
////        servicesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
////            @Override
////            public void onChanged(@Nullable String s) {
////            }
////        });
//        return root;
//    }
//
//
//}