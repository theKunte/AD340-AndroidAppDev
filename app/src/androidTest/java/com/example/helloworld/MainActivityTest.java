package com.example.helloworld;

import android.os.RemoteException;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
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

    @Test
    public void hasValidUsername() {
        onView(withId(R.id.username)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.username)).perform(typeText(Constants.TEST_KEY_USERNAME));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.username)).check(matches(withText(Constants.TEST_KEY_USERNAME)));
    }

    @Test
    public void hasValidEmail() {
        onView(withId(R.id.email)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.email)).perform(typeText(Constants.TEST_KEY_EMAIL));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.email)).check(matches(withText(Constants.TEST_KEY_EMAIL)));
    }

    @Test
    public void hasValidName() {
        onView(withId(R.id.name)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.name)).perform(typeText(Constants.TEST_KEY_NAME));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.name)).check(matches(withText(Constants.TEST_KEY_NAME)));
    }

    @Test
    public void hasValidOccupation() {
        onView(withId(R.id.occupation)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.occupation)).perform(typeText(Constants.TEST_KEY_OCCUPATION));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.occupation)).check(matches(withText(Constants.TEST_KEY_OCCUPATION)));
    }

    @Test
    public void hasValidDescription() {
        onView(withId(R.id.description)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.description)).perform(typeText(Constants.TEST_KEY_DESCRIPTION));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.description)).check(matches(withText(Constants.TEST_KEY_DESCRIPTION)));
    }

    @Test
    public void hasNoName() {
        onView(withId(R.id.name)).perform(typeText(Constants.TEST_KEY_EMPTY), closeSoftKeyboard());

        onView(withId(R.id.signUpButton)).perform(click());
        onView(withId(R.id.name)).check(matches(
                ViewMatchers.hasErrorText(Constants.NAME_ERR)));
    }

    @Test
    public void hasNoSpaceInName() {
        onView(withId(R.id.name)).perform(typeText(Constants.TEST_NAME_NOSPACE), closeSoftKeyboard());

        onView(withId(R.id.signUpButton)).perform(click());
        onView(withId(R.id.name)).check(matches(
                ViewMatchers.hasErrorText(Constants.NAME_NO_SPACE_ERR)));
    }

    @Test
    public void nameIsToLong() {
        onView(withId(R.id.name)).perform(typeText(Constants.TEST_NAME_TO_LONG), closeSoftKeyboard());

        onView(withId(R.id.signUpButton)).perform(click());
        onView(withId(R.id.name)).check(matches(
                ViewMatchers.hasErrorText(Constants.TEST_NAME_TO_LONG_ERR)));
    }

    @Test
    public void hasNoUserName() {
        onView(withId(R.id.name)).perform(typeText(Constants.TEST_KEY_NAME), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(Constants.TEST_KEY_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText(Constants.TEST_KEY_EMPTY), closeSoftKeyboard());

        onView(withId(R.id.signUpButton)).perform(click());
        onView(withId(R.id.username)).check(matches(
                ViewMatchers.hasErrorText(Constants.USERNAME_ERR)));
    }

    @Test
    public void hasNoEmail() {
        onView(withId(R.id.name)).perform(typeText(Constants.TEST_KEY_NAME), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(Constants.TEST_KEY_EMPTY), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText(Constants.TEST_KEY_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.signUpButton)).perform(click());
        onView(withId(R.id.email)).check(matches(
                ViewMatchers.hasErrorText(Constants.EMAIL_ERR)));
    }

}
