package com.ad340.micromomentums;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lst= (ListView) findViewById(R.id.listView);

        FirebaseViewModel viewModel = new FirebaseViewModel();
        viewModel.getStocks(
                (ArrayList<Stock> stocks) -> {
                    //Find isRising for each stock
                    for(Stock stock : stocks) stock.calcualteRising();
                    //Sort stocks by amount of change descending
                    stocks.sort(Collections.reverseOrder());

                    //Create arrays from List of Stocks to pass to ListView
                    String stockSymbols[] = stocks.stream().map(Stock::getSymbol).toArray(String[]::new);
                    String stockValues[] = stocks.stream().map(Stock::getValue).toArray(String[]::new);
                    String stockLast5s[] = stocks.stream().map(Stock::getLast5).toArray(String[]::new);
                    String stockLast10s[] = stocks.stream().map(Stock::getLast10).toArray(String[]::new);

                    StockListView slv = new StockListView(this, stockSymbols, stockValues, stockLast5s, stockLast10s);
                    lst.setAdapter(slv);
                }
        );
    }
}
