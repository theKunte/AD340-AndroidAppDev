package com.example.helloworld;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class SignUpActivity extends AppCompatActivity {

    private String TAG;
    private TextView dateOfBirthInfo;
    private EditText name;
    private EditText email;
    private EditText username;
    private EditText description;
    private EditText occupation;
    private int age;
    private boolean isOfAge;

    DatePickerDialog.OnDateSetListener setListener;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        dateOfBirthInfo = findViewById(R.id.dateOfBirthInfo);
        description = findViewById(R.id.description);
        occupation = findViewById(R.id.occupation);
        isOfAge = false;

        Button signUpButton = findViewById(R.id.signUpButton);
        ImageButton dateOfBirth = findViewById(R.id.dateOfBirth);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, TabSwitcherActivity.class);

                if (validateName(name) && validateUsername(username) && validateEmail(email)  && isOfAge) {
                    intent.putExtra(Constants.KEY_NAME, name.getText().toString());
                    intent.putExtra(Constants.KEY_USERNAME, username.getText().toString());
                    intent.putExtra(Constants.KEY_AGE, Integer.toString(age));
                    intent.putExtra(Constants.KEY_OCCUPATION, occupation.getText().toString());
                    intent.putExtra(Constants.KEY_DESCRIPTION, description.getText().toString());
                    intent.putExtra(Constants.KEY_DateOFBirth, dateOfBirthInfo.getText().toString());


                    startActivity(intent);
                }
            }
        });

        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUpActivity.this, setListener, year, month, day);
                Objects.requireNonNull(datePickerDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, day);

                @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat( "MM-dd-yyyy");
                dateOfBirthInfo.setText(format.format(cal.getTime()));

                isOfAge = ageRestrictionCalculator(cal.getTimeInMillis());
            }
        };
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
            name.setError("First and Lastname should have a space in between");
            return false;
        }
        return true;
    }

    // Calculate Age
    boolean ageRestrictionCalculator(long date) {
        Calendar dateOfBirthCal = Calendar.getInstance();
        dateOfBirthCal.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();

        age = today.get(Calendar.YEAR) - dateOfBirthCal.get(Calendar.YEAR);
        String dateOfBirthInfoInput = dateOfBirthInfo.getText().toString().trim();

        if (dateOfBirthInfoInput.isEmpty()) {
            dateOfBirthInfo.setError("Field can't be empty.");
            return false;
        }
        if (age < 18) {
            dateOfBirthInfo.setError("You have to be 18 years old!");
            return false;
        }
        else {
            dateOfBirthInfo.setError(null);
            return true;
        }
    }

    //Validate UserName
    boolean  validateUsername(EditText username) {
        String usernameInput = username.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            username.setError("Username can't be empty. Please add it");
            return false;
        }
        if (usernameInput.length() > 60) {
            username.setError("Your Username is to long!");
        }
        return true;
    }

    boolean validateEmail(EditText email){
        String emailInfo = email.getText().toString().trim();
        if(emailInfo.isEmpty()){
            email.setError("Forgot to enter Email address!");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInfo).matches()) {
            email.setError("Invalid Email address!");
            return false;
        }
        return true;
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG, "onStart()");
        name.setText("");
        email.setText("");
        username.setText("");
        dateOfBirthInfo.setText("");
        occupation.setText("");
        description.setText("");

        name.requestFocus();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState.containsKey(Constants.KEY_NAME)) {
            name.setText((String) savedInstanceState.get(Constants.KEY_NAME));
        }
        if(savedInstanceState.containsKey(Constants.KEY_EMAIL)) {
            email.setText((String) savedInstanceState.get(Constants.KEY_EMAIL));
        }
        if(savedInstanceState.containsKey(Constants.KEY_USERNAME)) {
            username.setText( (String) savedInstanceState.get(Constants.KEY_USERNAME));
        }
        if(savedInstanceState.containsKey(Constants.KEY_OCCUPATION)) {
            occupation.setText( (String) savedInstanceState.get(Constants.KEY_OCCUPATION));
        }
        if(savedInstanceState.containsKey(Constants.KEY_DESCRIPTION)) {
            description.setText( (String) savedInstanceState.get(Constants.KEY_DESCRIPTION));
        }
        if(savedInstanceState.containsKey(Constants.KEY_DateOFBirth)) {
            dateOfBirthInfo.setText((String) savedInstanceState.get(Constants.KEY_DateOFBirth));
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(Constants.KEY_NAME, name.getText().toString());
        outState.putString(Constants.KEY_EMAIL, email.getText().toString());
        outState.putString(Constants.KEY_USERNAME, username.getText().toString());
        outState.putString(Constants.KEY_AGE, Integer.toString(age));
        outState.putString(Constants.KEY_DateOFBirth, dateOfBirthInfo.getText().toString());
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
