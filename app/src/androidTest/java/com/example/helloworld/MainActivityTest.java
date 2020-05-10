package com.example.helloworld;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;



@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> ac
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void hasTextOnScreen(){
        onView(withId(R.id.title))
                .check(matches(withText(R.string.AssignmentTwo)));
    }
    @Test
    public void hasValidFullname() {
        onView(withId(R.id.name)).perform(typeText(Constants.TEST_KEY_NAME), closeSoftKeyboard());
        onView(withId(R.id.name)).check(matches(withText(Constants.TEST_KEY_NAME)));
    }
    @Test
    public void hasValidEmail() {
        onView(withId(R.id.email)).perform(typeText(Constants.TEST_KEY_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.email)).check(matches(withText(Constants.TEST_KEY_EMAIL)));
    }
    @Test
    public void hasNoEmail() {
        onView(withId(R.id.email)).perform(clearText());
        closeSoftKeyboard();
        onView(withId(R.id.signUpButton)).perform(click());
    }
//    @Test
//    public void hasValidUsername() {
//        onView(withId(R.id.username)).perform(typeText(Constants.TEST_KEY_USERNAME), closeSoftKeyboard());
//        onView(withId(R.id.username)).check(matches(withText(Constants.TEST_KEY_USERNAME)));
//    }
    @Test
    public void hasSignUpButton() {
        onView(withId(R.id.signUpButton)).check(matches(withText(R.string.SignUpButton)));
    }
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