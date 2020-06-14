package com.example.helloworld;

public class Constants {
    static final String KEY_NAME ="name";
    static final String KEY_EMAIL ="email";
    static final String KEY_USERNAME = "username";
    static final String KEY_AGE = "age";
    static final String KEY_OCCUPATION = "occupation";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_DateOFBirth = "dateOfBirth";
    static final String KEY_EMPTY = "";



    static final String TEST_KEY_NAME = "Jenny Kunte";
    static final String TEST_KEY_EMAIL = "j.kunte@gmx.net";
    static final String TEST_KEY_USERNAME = "JeyKey98";
    static final String TEST_KEY_DateOfBirth = "03/20/1998";
    static final String TEST_KEY_OCCUPATION = "MOBDEV SOFTWARE GURU NINJA ROCKSTAR";
    static final String TEST_KEY_DESCRIPTION = "I love sushi";
    static final String TEST_KEY_EMPTY = "";
    static final String TEST_NAME_NOSPACE = "JennyKunte";
    static final String TEST_NAME_TO_LONG = "Lololololololololololololololololololo Oasasasasasasassassasasass";
    static final String USERNAME_TO_LONG = "Your Username is to long!";


    // Error Text
    static final String NAME_ERR ="Please add your name";
    static final String NAME_NO_SPACE_ERR = "First and Lastname should have a space in between";
    static final String TEST_NAME_TO_LONG_ERR = "WOW! Your Name is long! Its to long for the app make";

    static final String USERNAME_ERR ="Username can't be empty. Please add it";
    static final String EMAIL_ERR ="Forgot to enter Email address!";
    static final String AGE_ERR ="Age can't be empty.";
    static final String AGE_TO_YOUNG_ERR = "You have to be 18 years old!";
    static final String OCC_ERR ="Occupation can't be empty. Please add it";
    static final String DES_ERR ="Description can't be empty. Please add it";


    //default settings
    public static final Integer SETTINGS_DEFAULT_ID = 1;
    public static final Integer SETTINGS_DEFAULT_MinAge = 18;
    public static final Integer SETTINGS_DEFAULT_MaxAge = 120;
    public static final Integer SETTINGS_DEFAULT_ReminderHour = 14;
    public static final Integer SETTINGS_DEFAULT_ReminderMinutes = 0;
    public static final boolean SETTINGS_DEFAULT_PrivateAccount = false;
    public static final Integer SETTINGS_DEFAULT_MaxDistance = 5;
    public static final Integer SETTINGS_DEFAULT_GenderPreference = 0;

    //Limits for Number pickers
    public static final Integer NP_minAgeLimit = 18;
    public static final Integer NP_maxAgeLimit = 120;
    public static final Integer NP_minDistanceLimit = 3;
    public static final Integer NP_maxDistanceLimit = 1000;

}
