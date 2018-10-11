package com.ad340.micromomentums;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.function.Consumer;

public class FirebaseViewModel {
    private FirebaseDB dataModel;
    private ArrayList<Stock> stocks;

    public FirebaseViewModel() {
        dataModel = new FirebaseDB();
    }

    public void getStocks(Consumer<ArrayList<Stock>> responseCallback) {
        System.out.println("hit here");
        dataModel.getStocks(
                (DataSnapshot dataSnapshot) -> {
                    ArrayList<Stock> stocks = new ArrayList<>();
                    for (DataSnapshot stockSnapshot : dataSnapshot.getChildren()) {
                        Stock stock = stockSnapshot.getValue(Stock.class);
                        assert stock != null;
                        stocks.add(stock);
                    }
                    responseCallback.accept(stocks);
                },
                (databaseError -> System.out.println("Error reading Stocks: " + databaseError))
        );
    }

    public void clear() {
        dataModel.clear();
    }
}
