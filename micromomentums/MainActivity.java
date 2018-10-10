package com.ad340.micromomentums;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lst;
    String[] name = {"NAME","Microsoft:", "Apple:", "Coca-Cola:"};
    String[] symbol = {"SYMBOL","MSFT", "AAPL", "KO"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lst= (ListView) findViewById(R.id.listView);
        StockListView slv = new StockListView(this, name, symbol);
        lst.setAdapter(slv);

    }
}
