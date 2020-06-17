package com.example.helloworld;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.RemoteException;
import android.widget.DatePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;


@RunWith(AndroidJUnit4.class)

public class TabSwitcherActivityTests {
    @Rule
    public ActivityTestRule<TabSwitcherActivity> activityTestRule =
            new ActivityTestRule<>(TabSwitcherActivity.class);

//    @Test
//    public void hasLogoImage() {
//        onView(withId(R.id.logoImage)).check(matches(isDisplayed()));
//    }

//    @Test
//    public void ProfileFragmentMatchesIntent() {
//        Intent intent = new Intent();;
//        intent.putExtra(Constants.KEY_NAME, Constants.TEST_KEY_NAME);
//        intent.putExtra(Constants.KEY_USERNAME, Constants.TEST_KEY_USERNAME);
//        intent.putExtra(Constants.KEY_AGE, Constants.TEST_KEY_AGE);
//        intent.putExtra(Constants.KEY_OCCUPATION, Constants.TEST_KEY_OCCUPATION);
//        intent.putExtra(Constants.KEY_DESCRIPTION, Constants.TEST_KEY_DESCRIPTION);
//        intent.putExtra(Constants.KEY_DateOFBirth, Constants.TEST_KEY_DateOfBirth);
//
//        Instrumentation.ActivityResult result =
//                new Instrumentation.ActivityResult(Activity.RESULT_OK, intent);
//
//        intending(toPackage("com.example.helloworld.TabSwitcherActivity")).respondWith(result);
//
//
//    }

}



