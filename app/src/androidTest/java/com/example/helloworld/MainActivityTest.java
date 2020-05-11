package com.example.helloworld;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.content.Intent;
import android.net.Uri;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;


@RunWith(AndroidJUnit4.class)

    public class MainActivityTest {
        @Rule
        public ActivityTestRule<MainActivity> activityTestRule =
                new ActivityTestRule<>(MainActivity.class);

    @Test
    public void hasTextOnScreen(){
        onView(withId(R.id.title))
                .check(matches(withText(R.string.assignmentThree)));

    }
    @Test
    public void validatesEmptyName() {
        onView(withId(R.id.name)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.signUpButton)).perform(scrollTo(),(click()));

        MainActivity activity = activityTestRule.getActivity();
        onView(withText("Hi! enter your Full Name please!")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }
    
}