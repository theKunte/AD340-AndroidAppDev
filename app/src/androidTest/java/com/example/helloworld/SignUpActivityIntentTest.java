package com.example.helloworld;

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
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;


@RunWith(AndroidJUnit4.class)

public class SignUpActivityIntentTest {
    @Rule
    public ActivityTestRule<SignUpActivity> activityTestRule =
            new ActivityTestRule<>(SignUpActivity.class);

    @Test
    public void ValidateIntent() throws InterruptedException {
        Thread.sleep(2000);

        onView(withId(R.id.name)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_NAME), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.occupation)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_OCCUPATION), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_DESCRIPTION), closeSoftKeyboard());
        closeSoftKeyboard();
        onView(withId(R.id.dateOfBirth))
                .perform(scrollTo(), click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                2000, 11, 31));

        Thread.sleep(2000);

        onView(withText("OK")).perform(click());

        try {
            Intents.init();

            onView(withId(R.id.signUpButton)).perform(scrollTo(), click());

            intended(AllOf.allOf(
                    hasComponent(TabSwitcherActivity.class.getName()),
                    hasExtraWithKey(Constants.KEY_NAME),
                    hasExtraWithKey(Constants.KEY_USERNAME),
                    hasExtraWithKey(Constants.KEY_AGE),
                    hasExtraWithKey(Constants.KEY_OCCUPATION),
                    hasExtraWithKey(Constants.KEY_DESCRIPTION),
                    hasExtraWithKey(Constants.KEY_DateOFBirth)
            ));
        } finally {
            Intents.release();
        }
    }
}



