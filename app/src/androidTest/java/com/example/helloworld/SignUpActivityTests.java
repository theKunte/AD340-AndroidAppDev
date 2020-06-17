package com.example.helloworld;

import android.os.RemoteException;
import android.widget.DatePicker;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;
import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import androidx.test.rule.ActivityTestRule;


@RunWith(AndroidJUnit4.class)

public class SignUpActivityTests {
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

//    @Test
//    public void usernameTooLong() {
//        onView(withId(R.id.name)).perform(typeText(Constants.TEST_KEY_NAME), closeSoftKeyboard());
//        onView(withId(R.id.email)).perform(typeText(Constants.TEST_KEY_EMAIL), closeSoftKeyboard());
//        onView(withId(R.id.username)).perform(typeText(Constants.TEST_KEY_USERNAME_LONG), closeSoftKeyboard());
//        onView(withId(R.id.signUpButton)).perform(scrollTo()).perform(click());
//        onView(withId(R.id.username)).check(matches(
//                ViewMatchers.hasErrorText(Constants.USERNAME_TO_LONG)));
//    }

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
        onView(withId(R.id.occupation)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_OCCUPATION));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.occupation)).check(matches(withText(Constants.TEST_KEY_OCCUPATION)));
    }

    @Test
    public void hasValidDescription() {
        onView(withId(R.id.description)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.description)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_DESCRIPTION));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.description)).check(matches(withText(Constants.TEST_KEY_DESCRIPTION)));
    }

//    @Test
//    public void hasNoName() throws InterruptedException {
//        Thread.sleep(2000);
//        onView(withId(R.id.name)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_EMPTY), closeSoftKeyboard());
//        closeSoftKeyboard();
//        onView(withId(R.id.signUpButton)).perform(scrollTo()).perform(click());
//        onView(withId(R.id.name)).check(matches(
//                ViewMatchers.hasErrorText(Constants.NAME_ERR)));
//    }
    
    @Test
    public void hasNoSpaceInName() {
        onView(withId(R.id.name)).perform(scrollTo()).perform(typeText(Constants.TEST_NAME_NOSPACE), closeSoftKeyboard());
        onView(withId(R.id.signUpButton)).perform(scrollTo()).perform(click());
        onView(withId(R.id.name)).check(matches(
                ViewMatchers.hasErrorText(Constants.NAME_NO_SPACE_ERR)));
    }

    @Test
    public void nameIsToLong() {
        onView(withId(R.id.name)).perform(typeText(Constants.TEST_NAME_TO_LONG), closeSoftKeyboard());
        onView(withId(R.id.signUpButton)).perform(scrollTo()).perform(click());
        onView(withId(R.id.name)).check(matches(
                ViewMatchers.hasErrorText(Constants.TEST_NAME_TO_LONG_ERR)));
    }

    @Test
    public void hasNoUserName() {
        onView(withId(R.id.name)).perform(typeText(Constants.TEST_KEY_NAME), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(Constants.TEST_KEY_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText(Constants.TEST_KEY_EMPTY), closeSoftKeyboard());
        onView(withId(R.id.signUpButton)).perform(scrollTo()).perform(click());
        onView(withId(R.id.username)).check(matches(
                ViewMatchers.hasErrorText(Constants.USERNAME_ERR)));
    }

    @Test
    public void hasNoEmail() {
        onView(withId(R.id.name)).perform(typeText(Constants.TEST_KEY_NAME), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(Constants.TEST_KEY_EMPTY), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText(Constants.TEST_KEY_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.signUpButton)).perform(scrollTo()).perform(click());
        onView(withId(R.id.email)).check(matches(
                ViewMatchers.hasErrorText(Constants.EMAIL_ERR)));
    }

    @Test
    public void noAgeError() throws InterruptedException {
        Thread.sleep(2000);

        onView(withId(R.id.name)).perform(typeText(Constants.TEST_KEY_NAME), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(Constants.TEST_KEY_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText(Constants.TEST_KEY_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.occupation)).perform(typeText(Constants.TEST_KEY_OCCUPATION), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(typeText(Constants.TEST_KEY_DESCRIPTION), closeSoftKeyboard());
        closeSoftKeyboard();

        onView(withId(R.id.signUpButton)).perform(click());

        onView(withId(R.id.dateOfBirthInfo))
                .check(matches(withText(Constants.AGE_ERR)));
    }

    @Test
    public void validatesAge() throws InterruptedException {
        Thread.sleep(2000);

        onView(withId(R.id.name)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_NAME), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.occupation)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_OCCUPATION), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_DESCRIPTION), closeSoftKeyboard());
        closeSoftKeyboard();
        onView(withId(R.id.dateOfBirth))
            .perform(scrollTo()).perform(scrollTo()).perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                2009, 11, 31));

        Thread.sleep(2000);

        onView(withText("OK")).perform(scrollTo()).perform(scrollTo()).perform(click());
        onView(withId(R.id.dateOfBirthInfo))
                .check(matches(withText(Constants.AGE_TO_YOUNG_ERR)));
    }

    @Test
    public void occupationTooLong() throws InterruptedException {
        onView(withId(R.id.name)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_NAME), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.occupation)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_OCCUPATION_LONG), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_DESCRIPTION), closeSoftKeyboard());
        closeSoftKeyboard();
        onView(withId(R.id.dateOfBirth))
                .perform(scrollTo()).perform(scrollTo()).perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                1998, 11, 31));

        Thread.sleep(2000);

        onView(withText("OK")).perform(scrollTo()).perform(scrollTo()).perform(click());
        onView(withId(R.id.signUpButton)).perform(scrollTo()).perform(click());

        onView(withId(R.id.occupation))
                .check(matches(
                        ViewMatchers.hasErrorText(Constants.OCC_ERR_LONG)));
    }

    @Test
    public void occupationEmpty() throws InterruptedException {
        Thread.sleep(2000);

        onView(withId(R.id.name)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_NAME), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_USERNAME), closeSoftKeyboard());
//        onView(withId(R.id.occupation)).perform(scrollTo()).perform(typeText(Constants.KEY_EMPTY), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_DESCRIPTION), closeSoftKeyboard());
        closeSoftKeyboard();
        onView(withId(R.id.dateOfBirth))
                .perform(scrollTo()).perform(scrollTo()).perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                1998, 11, 31));

        Thread.sleep(2000);

        onView(withText("OK")).perform(scrollTo()).perform(scrollTo()).perform(click());
        onView(withId(R.id.signUpButton)).perform(scrollTo()).perform(click());

        onView(withId(R.id.occupation))
                .check(matches(
                        ViewMatchers.hasErrorText(Constants.OCC_ERR)));
    }

    @Test
    public void descriptionTooLong() throws InterruptedException {

        onView(withId(R.id.name)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_NAME), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.occupation)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_OCCUPATION), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_DESCRIPTION_LONG), closeSoftKeyboard());
        closeSoftKeyboard();
        onView(withId(R.id.dateOfBirth))
                .perform(scrollTo()).perform(scrollTo()).perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                1998, 11, 31));

        Thread.sleep(2000);

        onView(withText("OK")).perform(scrollTo()).perform(scrollTo()).perform(click());
        onView(withId(R.id.signUpButton)).perform(scrollTo()).perform(click());
        onView(withId(R.id.description))
                .check(matches(
                        ViewMatchers.hasErrorText(Constants.DES_ERR_LONG)));
    }

    @Test
    public void descriptionEmpty() throws InterruptedException {
        onView(withId(R.id.name)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_NAME), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.occupation)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_OCCUPATION), closeSoftKeyboard());
//        onView(withId(R.id.description)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_DESCRIPTION), closeSoftKeyboard());
        closeSoftKeyboard();
        onView(withId(R.id.dateOfBirth))
                .perform(scrollTo()).perform(scrollTo()).perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                1998, 11, 31));

        Thread.sleep(2000);

        onView(withText("OK")).perform(scrollTo()).perform(scrollTo()).perform(click());

        onView(withId(R.id.signUpButton)).perform(scrollTo()).perform(click());

        onView(withId(R.id.description))
                .check(matches(
                        ViewMatchers.hasErrorText(Constants.DES_ERR)));
    }

    @Test
    public void signUpLandscapeOrientationAtEnd() throws InterruptedException, RemoteException {
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
                2009, 11, 31));

        Thread.sleep(2000);

        onView(withText("OK")).perform(click());
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.setOrientationLeft();

        onView(withId(R.id.dateOfBirthInfo))
                .check(matches(withText(Constants.AGE_TO_YOUNG_ERR)));
    }

    @Test
    public void validatesIsOfAgeLandscape() throws InterruptedException, RemoteException {
        Thread.sleep(2000);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.setOrientationLeft();
        Thread.sleep(2000);

        onView(withId(R.id.name)).perform(replaceText(Constants.TEST_KEY_NAME), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(replaceText(Constants.TEST_KEY_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(replaceText(Constants.TEST_KEY_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.occupation)).perform(replaceText(Constants.TEST_KEY_OCCUPATION), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(scrollTo()).perform(replaceText(Constants.TEST_KEY_DESCRIPTION), closeSoftKeyboard());
        closeSoftKeyboard();
        onView(withId(R.id.dateOfBirth))
                .perform(scrollTo(), click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                2000, 11, 31));

        Thread.sleep(2000);

        onView(withText("OK")).perform(click());
        onView(withId(R.id.dateOfBirthInfo))
                .check(matches(withText("12-01-2000"))); //date is being calculated wrong
    }

    @Test
    public void validatesIsOfAge() throws InterruptedException {
        Thread.sleep(2000);

        onView(withId(R.id.name)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_NAME), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.occupation)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_OCCUPATION), closeSoftKeyboard());
        onView(withId(R.id.description)).perform(scrollTo()).perform(typeText(Constants.TEST_KEY_DESCRIPTION), closeSoftKeyboard());
        closeSoftKeyboard();
        onView(withId(R.id.dateOfBirth))
                .perform(scrollTo()).perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                2000, 11, 31));

        Thread.sleep(2000);

        onView(withText("OK")).perform(click());
        onView(withId(R.id.dateOfBirthInfo))
                .check(matches(withText("12-01-2000"))); //date is being calculated wrong TODO fix this
    }

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



