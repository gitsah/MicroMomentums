package com.ad340.micromomentums;

import android.app.FragmentManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    ListView lst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        lst = (ListView) findViewById(R.id.listView);
//
//        FirebaseViewModel viewModel = new FirebaseViewModel();
//        viewModel.getStocks(
//                (ArrayList<Stock> stocks) -> {
//                    //Find isRising for each stock
//                    for(Stock stock : stocks) stock.calcualteRising();
//
//                    //Sort stocks by amount of change descending  //<-- TEMP SHUT OFF SHORT, BRING BACK TO TEST!
//                    stocks.sort(Collections.reverseOrder());
//
//                    //Create arrays from List of Stocks to pass to ListView
//                    String stockSymbols[] = stocks.stream().map(Stock::getSymbol).toArray(String[]::new);
//                    String stockValues[] = stocks.stream().map(Stock::getValue).toArray(String[]::new);
//                    String stockLast5s[] = stocks.stream().map(Stock::getLast5).toArray(String[]::new);
//                    String stockLast10s[] = stocks.stream().map(Stock::getLast10).toArray(String[]::new);
//
//                    StockListView slv = new StockListView(this, stockSymbols, stockValues, stockLast5s, stockLast10s);
//
//
//
//                    lst.setAdapter(slv);
//
//                }
//        );

        //Tab Layout
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up  the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "TAB1");
        adapter.addFragment(new Tab2Fragment(), "TAB2");
        adapter.addFragment(new Tab3Fragment(), "TAB3");

        viewPager.setAdapter(adapter);

    }
}
