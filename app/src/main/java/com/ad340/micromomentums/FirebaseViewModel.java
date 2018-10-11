package com.ad340.micromomentums;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class FirebaseViewModel extends ViewModel {
    private FirebaseDB dataModel;

    private MutableLiveData<List<Stock>> stocks;
    public LiveData<List<Stock>> getStocks() {
        if (stocks == null) {
            stocks = new MutableLiveData<>();
            loadStocks();
        }
        return stocks;
    }

    private void loadStocks() {
        dataModel.getStocks();
    }
}
