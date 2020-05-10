package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();
    //local variable
    TextView tv_name;
    TextView tv_age;
    TextView tv_occupation;
    TextView tv_description;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

      //  welcomeSecondView = findViewById(R.id.welcomeSecondView);
        tv_name = findViewById(R.id.tv_name);
        tv_age = findViewById(R.id.tv_age);
        tv_occupation = findViewById(R.id.tv_occupation);
        tv_description = findViewById(R.id.tv_description);

        Button buttonSecondView = findViewById(R.id.buttonSecondView);

        // StringBuilder to display SignUp Information
        StringBuilder display_name = new StringBuilder("");
        StringBuilder display_age = new StringBuilder("");
        StringBuilder display_occupation = new StringBuilder("");
        StringBuilder display_description = new StringBuilder("");

        // StringBuilder to display userGreet
        StringBuilder userGreet = new StringBuilder("Thanks for Signing Up! ");

        Bundle bundle = getIntent().getExtras();

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
        tv_occupation.setText(display_occupation);
        tv_description.setText(display_description);
       // welcomeSecondView.setText(userGreet + "!");


        // sign Out Button
        buttonSecondView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                finish();
            }
        });
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
}

