package com.ad340.micromomentums;

import android.support.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Stock implements Comparable<Stock>{

    private String symbol;
    private String value;
    private String last5;
    private String last10;
    private String last15;
    private boolean isRising;

    public Stock() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Stock(String symbol, String value, String last5, String last10, String last15) {
        this.symbol = symbol;
        this.value = value;
        this.last5 = last5;
        this.last10 = last10;
        this.last15 = last15;
    }

    public void calcualteRising(){
        this.isRising = (Float.parseFloat(last15) < Float.parseFloat(last10) && Float.parseFloat(last10)
                < Float.parseFloat(last5) && Float.parseFloat(last5) < Float.parseFloat(value));
    }

    @Override
    public int compareTo(@NonNull Stock o) {
        if(this.isRising) {
            if (o.isRising){
                float thisChange = Float.parseFloat(this.value) / Float.parseFloat(this.last15);
                float oChange = Float.parseFloat(o.value) / Float.parseFloat(o.last15);
                if(thisChange == oChange) return 0;
                return (thisChange > oChange) ? 1 : -1;
            }
            else{
                return 1;
            }
        }
        else{
            if(o.isRising){
                return -1;
            }
            else{
                float thisChange = Float.parseFloat(this.value) / Float.parseFloat(this.last15);
                float oChange = Float.parseFloat(o.value) / Float.parseFloat(o.last15);
                if(thisChange == oChange) return 0;
                return (thisChange > oChange) ? 1 : -1;
            }
        }
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

    public String getLast15() {
        return last15;
    }

    public void setLast15(String last15) {
        this.last15 = last15;
    }

    public boolean getIsRising(){
        return isRising;
    }
}

