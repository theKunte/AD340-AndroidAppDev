package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();
    //local variable
    TextView textView;
    private String Tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        textView = findViewById(R.id.textView);
//
//        StringBuilder msg = new StringBuilder("Hello \n");
//        Intent intent = getIntent();
//        Bundle b = intent.getExtras();
//
//        String name = "Example name";
//        int age = 30;
//
//        if(b!= null){
//            if (b.containsKey(Constant.KEY_NAME)){
//                name = b.getString(Constant.KEY_NAME);
//            }
//        }
//
//        if (b.containsKey(Constant.KEY_AGE)){
//                age = b.getInt(Constant.KEY_AGE);
//            }
        }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(Tag, "onRestart()");
    }

    @Override

    protected void onStart(){
        super.onStart();
        Log.i(Tag, "onStart()");
    }

    @Override

    protected void onResume(){
        super.onResume();
        Log.i(Tag, "onResume()");
    }

    @Override

    protected void onPause(){
        super.onPause();
        Log.i(Tag, "onPause()");
    }

    @Override

    protected void onStop(){
        super.onStop();
        Log.i(Tag, "onStop()");
    }

    @Override

    protected void onDestroy(){
        super.onDestroy();
        Log.i(Tag, "onDestroy()");
    }
}

