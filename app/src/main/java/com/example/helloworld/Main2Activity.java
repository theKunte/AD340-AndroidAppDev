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
    TextView signUpInfo;
    TextView welcomeSecondView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        welcomeSecondView = findViewById(R.id.welcomeSecondView);
        signUpInfo = findViewById(R.id.signUpInfo);

        Button buttonSecondView = findViewById(R.id.buttonSecondView);

        // StringBuilder to display userInfo
        StringBuilder userInfo = new StringBuilder();

        // StringBuilder to display userGreet
        StringBuilder userGreet = new StringBuilder("Thanks for Signing Up! ");

        Bundle bundle = getIntent().getExtras();

        // empty strings and int
        String name = "";
        String username = "";

        // check if bundle is not empty
        if (bundle != null) {
            name = bundle.getString("name");
            username = bundle.getString("username");
        }

        userInfo.append("Your Name: \t" + name).append("\n");
            Log.i(TAG, new StringBuilder().append(name).toString());
        userGreet.append(username);
        // append values
        userInfo.append("Your Username: \t" + username).append("\n");
            Log.i(TAG, new StringBuilder().append(username).toString());



        // shows userInfo and userGreet
        signUpInfo.setText(userInfo);
        welcomeSecondView.setText(userGreet + "!");


        // sign Out Button
        buttonSecondView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
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

