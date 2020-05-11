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
        onView(withId(R.id.signUpButton)).perform(click());

        MainActivity activity = activityTestRule.getActivity();
        onView(withText("Hi! enter your Full Name please!")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void testAgeForSignUp() {
        onView(withId(R.id.name)).perform(typeText("Jenny"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("j.kunte@gmx.net"), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText("jennyKey"), closeSoftKeyboard());

        onView(withId(R.id.signUpButton)).perform(click());

        MainActivity activity = activityTestRule.getActivity();
        onView(withText("You have to be 18 years old!")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void validatesEmptyDOB() {
        onView(withId(R.id.name)).perform(typeText("Jenny"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("jennykunte@gmx.com"), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText("JeyKey"), closeSoftKeyboard());
        onView(withId(R.id.occupation)).perform(typeText(String.valueOf("Software Developer")), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(typeText(String.valueOf("I love Suhsi")), closeSoftKeyboard());
        onView(withId(R.id.dateOfBirthInfo)).perform(typeText(String.valueOf("")), closeSoftKeyboard());

        onView(withId(R.id.signUpButton)).perform(click());

        MainActivity activity = activityTestRule.getActivity();
        onView(withText("date of birth is required")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

}