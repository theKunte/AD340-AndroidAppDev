package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.util.Log;


import com.example.helloworld.viewmodel.FirebaseMatchesViewModel;
import com.google.android.material.tabs.TabLayout;

public class TabSwitcherActivity extends AppCompatActivity {

    private FragmentManager manager;
    private static final String TAG = TabSwitcherActivity.class.getSimpleName();

    //local variable
    TabLayout tabLayout;
    ViewPager viewPager;
    FirebaseMatchesViewModel matchesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        matchesViewModel = new FirebaseMatchesViewModel();

        Bundle bundle = getIntent().getExtras();

        // empty strings and int
        String name = "";
        String username = "";
        String occupation = "";
        String description = "";
        String age = "";

        // check if bundle is not empty
        if (bundle != null) {
//            name = bundle.getString("name");
//            username = bundle.getString("username");
//            occupation = bundle.getString("occupation");
//            description = bundle.getString("description");
//            age = bundle.getString("age");

            //Init Fragments
            ProfileFragment profileFragment = new ProfileFragment();
            MatchesFragment matchesFragment = new MatchesFragment();
            SettingsFragment settingsFragment = new SettingsFragment();

            //Pass bundle data to be used inside fragments
            profileFragment.setArguments(bundle);
            matchesFragment.setArguments(bundle);
            settingsFragment.setArguments(bundle);

            // Set up viewpager
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager, profileFragment, matchesFragment, settingsFragment);

            // Set up tabs
            TabLayout tabs = (TabLayout) findViewById(R.id.sliding_tabs);
            tabs.setupWithViewPager(viewPager);

            // StringBuilder to display userGreet
            StringBuilder userGreet = new StringBuilder(getString(R.string.signUpMessage));

        }
    }

    //Add fragments for each tab
    private void setupViewPager(ViewPager viewPager, ProfileFragment profile, MatchesFragment matches, SettingsFragment settings) {
        ProfileFragmentPagerAdapter adapter = new ProfileFragmentPagerAdapter(getSupportFragmentManager());
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction t = manager.beginTransaction();

        adapter.addFragment(profile, getString(R.string.pofileFragment));
        adapter.addFragment(matches, getString(R.string.matchesFragment));
        adapter.addFragment(settings, getString(R.string.settingsFragment));
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart()");
    }

    @Override

    protected void onStart(){
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override

    protected void onResume(){
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override

    protected void onPause(){
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override

    protected void onStop(){
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override

    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(getString(R.string.position), tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(getString(R.string.position)));
    }
}

