package com.example.helloworld;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import androidx.test.rule.ActivityTestRule;


@RunWith(AndroidJUnit4.class)

    public class MainActivityTest {
        @Rule
        public ActivityTestRule<SignUpActivity> activityTestRule =
                new ActivityTestRule<>(SignUpActivity.class);

    @Test
    public void hasLogoImage() {
        onView(withId(R.id.logoImage)).check(matches(isDisplayed()));
    }
}