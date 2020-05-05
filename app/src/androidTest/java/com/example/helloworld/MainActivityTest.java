package com.example.helloworld;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void hasTextOnScreen(){
        onView(withId(R.id.title))
                .check(matches(withText(R.string.AssignmentTwo)));
    }
//    @Test
//    public void hasSignUpButton() {
//        onView(withId(R.id.signUpButton)).check(matches(withText(R.string.SignUpButton)));
//    }
//    @Test
//    public void hasNameHintDisplay(){
//        onView(withId(R.id.name)).check(matches(withHint("Full Name")));
//    }
//    @Test
//    public void hasUsernameHintDisplay(){
//        onView(withId(R.id.username)).check(matches(withHint("UserName")));
//    }
//    @Test
//    public void hasEmailHintDisplay(){
//        onView(withId(R.id.email)).check(matches(withHint("Email")));
//    }

}