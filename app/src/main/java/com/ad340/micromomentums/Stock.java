package com.ad340.micromomentums;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Stock {

    public String symbol;
    public String value;
    public String last5;
    public String last10;

    public Stock() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Stock(String symbol, String value, String last5, String last10) {
        this.symbol = symbol;
        this.value = value;
        this.last5 = last5;
        this.last10 = last10;
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

    public String getLast5() {
        return last5;
    }

    public void setLast5(String last5) {
        this.last5 = last5;
    }

    public String getLast10() {
        return last10;
    }

    public void setLast10(String last10) {
        this.last10 = last10;
    }
}

