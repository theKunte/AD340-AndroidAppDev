package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();
    public static String POSITION = "POSITION";

    //local variable
    TextView tv_name;
    TextView tv_age;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        // Set up toolbar
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();

        //Fragments
        ProfileFragment profileFragment = new ProfileFragment();
        MatchesFragment matchesFragment = new MatchesFragment();
        SettingsFragment settingsFragment = new SettingsFragment();

        //pass data to fragments
        profileFragment.setArguments(bundle);
        matchesFragment.setArguments(bundle);
        settingsFragment.setArguments(bundle);

        // Set up viewpager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager, profileFragment, matchesFragment, settingsFragment);

        // Set up tabs
        TabLayout tabs = (TabLayout) findViewById(R.id.sliding_tabs);
        tabs.setupWithViewPager(viewPager);

        //  welcomeSecondView = findViewById(R.id.welcomeSecondView);
        tv_name = findViewById(R.id.tv_name);
        tv_age = findViewById(R.id.tv_age);

        // StringBuilder to display SignUp Information
        StringBuilder display_name = new StringBuilder("");
        StringBuilder display_age = new StringBuilder("");
        StringBuilder display_occupation = new StringBuilder("");
        StringBuilder display_description = new StringBuilder("");

        // StringBuilder to display userGreet
        StringBuilder userGreet = new StringBuilder("Thanks for Signing Up! ");

        // empty strings and int
        String name = "";
        String username = "";
        String occupation = "";
        String description = "";

        // check if bundle is not empty
        if (bundle != null) {
            name = bundle.getString("name");
            username = bundle.getString("username");
            occupation = bundle.getString("occupation");
            description = bundle.getString("description");

        }

        // append values
        display_name.append(name).append("\n");
        Log.i(TAG, new StringBuilder().append(username).toString());
        display_occupation.append(occupation).append("\n");
        Log.i(TAG, new StringBuilder().append(name).toString());
        userGreet.append(username);
        // append values
        display_description.append( description).append("\n");
        Log.i(TAG, new StringBuilder().append(description).toString());



        // shows userInfo and userGreet
        tv_name.setText(display_name);
        //  tv_age.setText(display_age);

        // welcomeSecondView.setText(userGreet + "!");




    }

    //Add fragments for each tab
    private void setupViewPager(ViewPager viewPager, ProfileFragment profile, MatchesFragment matches, SettingsFragment settings) {
        Activity2FragmentPagerAdapter adapter = new Activity2FragmentPagerAdapter(getSupportFragmentManager());
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction t = manager.beginTransaction();

        adapter.addFragment(profile, "Profile");
        adapter.addFragment(matches, "Matches");
        adapter.addFragment(settings, "Settings");
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
        outState.putInt(POSITION, tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }
}

