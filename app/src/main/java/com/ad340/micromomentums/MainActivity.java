package com.ad340.micromomentums;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Stock> stocks;

    ListView lst;
    String[] name = {"NAME","Microsoft:", "Apple:", "Coca-Cola:"};
    String[] symbol = {"SYMBOL","MSFT", "AAPL", "KO"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lst= (ListView) findViewById(R.id.listView);

        FirebaseViewModel viewModel = new FirebaseViewModel();
        viewModel.getStocks(
                (ArrayList<Stock> stocks) -> {
                    this.stocks = stocks;
                    StockListView slv = new StockListView(this, stocks);
                    lst.setAdapter(slv);
                }
        );

//        StockListView slv = new StockListView(this, stocks);
//        lst.setAdapter(slv);

    }
}
