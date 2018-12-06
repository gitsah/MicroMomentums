package com.ad340.micromomentums;

import android.os.SystemClock;
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
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest
{
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

//    @Test
//    public void homeActivityTest() {
//        //Espresso.onView(withId(R.id.hello_world)).check(matches(withText("Hello World!")));
//
//
//        SystemClock.sleep(1800);
//        Stock stock1 = new Stock("xx", "34", "23", "13", "4");
//        Stock stock2 = new Stock("xx", "34", "25", "23", "3");
//        stock1.calcualteRising();
//        stock2.calcualteRising();
//        stock1.compargit eTo(stock2);
//        stock2.compareTo(stock1);
//        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
//        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(1).perform(click());
//        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).perform(click());
//
//
//        //onData(withId(R.id.listView)).atPosition(0).perform(click());
//    }
        @Test
        public void onCreateClickTabs(){

        onView(withText("How It Works")).check(matches(withText("How It Works")));
        onView(withText("How It Works")).perform(click());
        //onView(withId(R.id.container)).perform(swipeLeft());
        onView(withText("Stocks")).check(matches(withText("Stocks")));
        onView(withText("Stocks")).perform(click());

        SystemClock.sleep(1800);

        onView(withText("Symbol")).perform(click()).perform(click());
        onView(withText("AAL")).perform(click()).perform(click());
        onView(withText("Current")).perform(click()).perform(click());
        onView(withText("Last 5")).perform(click()).perform(click());
        onView(withText("last 10")).perform(click()).perform(click());
        onView(withText("last 10")).perform(swipeLeft());
        onView(withText("last 10")).perform(swipeLeft());
        onView(withText("last 10")).perform(swipeLeft());
        onView(withText("Avg % change")).perform(click()).perform(click());
        onView(withText("Avg % change")).perform(swipeLeft());
        onView(withText("Avg % change")).perform(swipeLeft());
        onView(withText("Momentum")).perform(click()).perform(click());
        onView(withText("Momentum")).perform(swipeLeft());
        onView(withText("Momentum")).perform(swipeLeft());
        onView(withText("Day % change")).perform(click()).perform(click());


        Stock stock1 = new Stock("KO", "50", "23", "13", "4", "2018-11-29 16:00:00", "2");
        Stock stock2 = new Stock("VXX", "34", "25", "23", "3", "2018-11-29 16:00:00", "29");
        stock1.calcualteRising();
        stock2.calcualteRising();
        stock1.compareTo(stock2);
        stock2.compareTo(stock1);

        onView(withText("About")).perform(swipeLeft());

        onView(withText("About")).check(matches(withText("About")));
    }

}