package com.ad340.micromomentums;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";
    private ViewPager mViewPager;
    ListView lst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObjectBox.init(this);
        setContentView(R.layout.activity_main);

        // Set up  the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "How It Works");
        adapter.addFragment(new Tab2Fragment(), "Stocks");
        adapter.addFragment(new Tab4Fragment(), "Tracked");
        adapter.addFragment(new Tab3Fragment(), "About");

        viewPager.setAdapter(adapter);

    }
}