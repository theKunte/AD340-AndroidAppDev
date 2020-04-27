package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private String Tag;
    private TextView textView;
    private Button button;
    private EditText name;
    private EditText email;
    private EditText username;
    private EditText age;
    private ImageButton dateOfBirth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                Bundle bundle = new Bundle();
//                bundle.putString(Constant.KEY_NAME, "Jenny Kunte");
//                bundle.putInt(Constant.KEY_AGE, 30);
//                intent.putExtras(bundle);
//                startActivity(intent);

//                textView = findViewById(R.id.textView);
                button  = findViewById(R.id.signUpButton);
                name = findViewById(R.id.name);
                email =findViewById(R.id.email);
                username = findViewById(R.id.username);
                age = findViewById(R.id.age);
                dateOfBirth = findViewById(R.id.dateOfBirth);
            }
        });


        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if ( validateName(name)&& validateUsername(username)){
                    Intent intent =  new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("name", username.getText().toString());
                    intent.putExtra("email", email.getText().toString());
                    intent.putExtra("username", username.getText().toString() );

                    startActivity(intent);
                    finish();
                }
            }

        });
    }
    //Validate Name
    boolean validateName(EditText name) {
        String signUpFullName = name.getText().toString().trim();
        if (signUpFullName.isEmpty()) {
            name.setError("Hi! enter your Full Name please!");
            return false;
        }
        if (signUpFullName.length() > 100) {
            name.setError("WOW! Your Name is long! Its to long for the app make");
            return false;
        }
        if(!signUpFullName.contains(" ")){
            name.setError("Your fullname should have a space in between");
            return false;
        }
        return true;
    }

    //Validate UserName
    boolean  validateUsername(EditText username) {
        String usernameInput = username.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            username.setError("UserName can't be empty. Please add it");
            return false;
        }
        if (usernameInput.length() > 60) {
            username.setError("Your UserName is to long!");
        }
        return true;
    }





    @Override
    protected void onStart(){
        super.onStart();
        Log.i(Tag, "onStart()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstaneState) {
        super.onRestoreInstanceState(savedInstaneState);
        Log.i(Tag, "onRestoreInstanceState()");

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
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i(Tag, "onSaveInstanceState()");
//        outState.putString(Constant.KEY_NAME, textView.getText().toString());
//        outState.putString(Constant.KEY_TEXTVIEW, textView.getText().toString());
//        outState.putString(Constant.KEY_BUTTON, button.getText().toString());
        // add your data that you want to safe in the second ativity and when you swth layout

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
