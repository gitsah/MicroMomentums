package com.ad340.micromomentums;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest
{
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

        @Test
        public void onCreateClickTabs(){

            onView(withText("How It Works")).check(matches(withText("How It Works")));
            onView(withText("How It Works")).perform(click());
            onView(withText("Stocks")).check(matches(withText("Stocks")));
            onView(withText("Stocks")).perform(click());

            SystemClock.sleep(1800);

            onView(allOf(withText("Symbol"), isDisplayed())).perform(click()).perform(click());
            onView(allOf(withText("AAL"), isDisplayed())).perform(click());
            onView(allOf(withId(R.id.popup_track_switch), isDisplayed())).perform(click());
            Espresso.pressBack();
            onView(allOf(withText("Current"), isDisplayed())).perform(click()).perform(click());
            onView(allOf(withText("Last 5"), isDisplayed())).perform(click()).perform(click());
            onView(allOf(withText("Last 10"), isDisplayed())).perform(click()).perform(click());
            onView(allOf(withText("Last 10"), isDisplayed())).perform(swipeLeft());
            onView(allOf(withText("Last 10"), isDisplayed())).perform(swipeLeft());
            onView(allOf(withText("Last 10"), isDisplayed())).perform(swipeLeft());
            onView(allOf(withText("Avg % change"), isDisplayed())).perform(click()).perform(click());
            onView(allOf(withText("Avg % change"), isDisplayed())).perform(swipeLeft());
            onView(allOf(withText("Avg % change"), isDisplayed())).perform(swipeLeft());
            onView(allOf(withText("Momentum"), isDisplayed())).perform(click()).perform(click());
            onView(allOf(withText("Momentum"), isDisplayed())).perform(swipeLeft());
            onView(allOf(withText("Momentum"), isDisplayed())).perform(swipeLeft());
            onView(allOf(withText("Day % change"), isDisplayed())).perform(click()).perform(click());


            Stock stock1 = new Stock("KO", "50", "23", "13", "4", "2018-11-29 16:00:00", "2");
            Stock stock2 = new Stock("VXX", "34", "25", "23", "3", "2018-11-29 16:00:00", "29");
            stock1.calcualteRising();
            stock2.calcualteRising();
            stock1.compareTo(stock2);
            stock2.compareTo(stock1);

            if(stock2.isTracked())
                stock1.setTracked(true);


            onView(withText("About")).perform(swipeLeft());

            onView(withText("About")).check(matches(withText("About")));
    }

        @Test
        public void onCreateClickTabs2 () {
            onView(withText("How It Works")).check(matches(withText("How It Works")));
            onView(withText("How It Works")).perform(click());
            onView(withText("Tracked")).check(matches(withText("Tracked")));
            onView(withText("Tracked")).perform(click());

            SystemClock.sleep(1800);

            onView(allOf(withText("Symbol"), isDisplayed())).perform(click()).perform(click());
            onView(allOf(withText("Current"), isDisplayed())).perform(click()).perform(click());
            onView(allOf(withText("Last 5"), isDisplayed())).perform(click()).perform(click());
            onView(allOf(withText("Last 10"), isDisplayed())).perform(click()).perform(click());
            onView(allOf(withText("Last 10"), isDisplayed())).perform(swipeLeft());
            onView(allOf(withText("Last 10"), isDisplayed())).perform(swipeLeft());
            onView(allOf(withText("Last 10"), isDisplayed())).perform(swipeLeft());
            onView(allOf(withText("Avg % change"), isDisplayed())).perform(click()).perform(click());
            onView(allOf(withText("Avg % change"), isDisplayed())).perform(swipeLeft());
            onView(allOf(withText("Avg % change"), isDisplayed())).perform(swipeLeft());
            onView(allOf(withText("Momentum"), isDisplayed())).perform(click()).perform(click());
            onView(allOf(withText("Momentum"), isDisplayed())).perform(swipeLeft());
            onView(allOf(withText("Momentum"), isDisplayed())).perform(swipeLeft());
            onView(allOf(withText("Day % change"), isDisplayed())).perform(click()).perform(click());
    }
}