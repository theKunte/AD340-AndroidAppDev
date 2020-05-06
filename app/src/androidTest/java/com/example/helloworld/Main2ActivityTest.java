package com.example.helloworld;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)

public class Main2ActivityTest {
    @Rule
    public ActivityScenarioRule<Main2Activity> activityScenarioRule
            = new ActivityScenarioRule<>(Main2Activity.class);

    @Test
    public void hasTextOnScreen(){
        onView(withId(R.id.buttonSecondView))
            .check(matches(isDisplayed()));
    }

}
