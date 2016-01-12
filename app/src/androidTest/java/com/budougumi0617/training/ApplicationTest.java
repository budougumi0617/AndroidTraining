package com.budougumi0617.training;


import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.MediumTest;
import android.support.test.rule.ActivityTestRule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;
import com.budougumi0617.training.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@MediumTest // Filtering test size
public class ApplicationTest {

    @Rule // Initialize test case
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);


    @Test
    public void existStringHelloWorld() {
        onView(withText("Hello World!")).check(matches(isDisplayed()));
    }
    @Test
    public void changeStringToGoodEvening() {
        onView(withId(R.id.button_change_greet)).perform(ViewActions.click());
        onView(withId(R.id.greet_view)).check(matches(withText(R.string.good_evening)));
    }
    @Test
    public void changeStringToHelloWorld() {
        ViewInteraction button = onView(withId(R.id.button_change_greet));
        button.perform(ViewActions.click());
        button.perform(ViewActions.click());
        onView(withId(R.id.greet_view)).check(matches(withText(R.string.hello_world)));
    }

    @Test
    public void existMyFragment1() {
        ViewInteraction fragment = onView(withId(R.id.my_fragment1));
        fragment.check(matches(isDisplayed()));
    }
    @Test
    public void changeFragmentToMyFragment2() {
        ViewInteraction button = onView(withId(R.id.button_manage_fragment));
        button.perform(ViewActions.click());
        ViewInteraction fragment = onView(withId(R.id.my_fragment2));
        fragment.check(matches(isDisplayed()));
    }
}