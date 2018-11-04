package com.ad340.micromomentums;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest
{
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void homeActivityTest() {
        //Espresso.onView(withId(R.id.hello_world)).check(matches(withText("Hello World!")));


//        SystemClock.sleep(1800);
//        Stock stock1 = new Stock("xx", "34", "23", "13", "4");
//        Stock stock2 = new Stock("xx", "34", "25", "23", "3");
//        stock1.calcualteRising();
//        stock2.calcualteRising();
//        stock1.compareTo(stock2);
//        stock2.compareTo(stock1);
//        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
//        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(1).perform(click());
//        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).perform(click());


        //onData(withId(R.id.listView)).atPosition(0).perform(click());
    }
        @Test
        public void onCreateClickTabs(){

        onView(withText("How It Works")).check(matches(withText("How It Works")));

        onView(withId(R.id.container)).perform(swipeLeft());
        onView(withText("Stocks")).check(matches(withText("Stocks")));

        SystemClock.sleep(1800);
        Stock stock1 = new Stock("xx", "34", "23", "13", "4");
        Stock stock2 = new Stock("xx", "34", "25", "23", "3");
        stock1.calcualteRising();
        stock2.calcualteRising();
        stock1.compareTo(stock2);
        stock2.compareTo(stock1);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(1).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).perform(click());

        onView(withId(R.id.container)).perform(swipeLeft());
        onView(withText("About")).check(matches(withText("About")));
    }

}