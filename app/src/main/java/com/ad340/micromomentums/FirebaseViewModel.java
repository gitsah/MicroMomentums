package com.ad340.micromomentums;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.function.Consumer;

public class FirebaseViewModel {
    private FirebaseDB dataModel;

    public FirebaseViewModel() {
        dataModel = new FirebaseDB();
    }

    public void getStocks(Consumer<ArrayList<Stock>> responseCallback) {
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
