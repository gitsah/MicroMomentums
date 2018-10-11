package com.ad340.micromomentums;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Stock {

    public String symbol;
    public String value;

    public Stock() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Stock(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

