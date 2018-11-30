package com.ad340.micromomentums;

import android.support.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

import java.text.DecimalFormat;

@IgnoreExtraProperties
public class Stock implements Comparable<Stock>{

    private String symbol;
    private String value;
    private String last5;
    private String last10;
    private String last15;
    private String lastUp;
    private String originalValue;
    private boolean isRising;
    private double percentChange;

    public Stock() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Stock(String symbol, String value, String last5, String last10, String last15, String lastUp, String originalValue) {
        this.symbol = symbol;
        this.value = value;
        this.last5 = last5;
        this.last10 = last10;
        this.last15 = last15;
        this.lastUp = lastUp;
        this.originalValue = originalValue;
    }

    public void calcualteRising(){
        //calculate if it's rising
        this.isRising = (Float.parseFloat(last15) < Float.parseFloat(last10) && Float.parseFloat(last10)
                < Float.parseFloat(last5) && Float.parseFloat(last5) < Float.parseFloat(value));

        //calculate percentage changed
        double currentVal = Double.valueOf(value);
        double last5Val = Double.valueOf(last5);
        double last10Val = Double.valueOf(last10);
        double last15Val = Double.valueOf(last15);

        //Difference from last 10 to last 5
        double change10to15 = ((last10Val - last15Val) / last15Val) * 100;

        double change5to10 = ((last5Val - last10Val) / last10Val) * 100;

        double changeCurrentTo5 = ((currentVal - last5Val) / last5Val) * 100;

        double avgPercentChange = (change5to10 + changeCurrentTo5 + change10to15) / 3;

        DecimalFormat df = new DecimalFormat("###.####");

        this.percentChange = Double.valueOf(df.format(avgPercentChange));
    }

    @Override
    public int compareTo(@NonNull Stock o) {
        if(this.isRising) {
            if (o.isRising){
                if(this.percentChange == o.percentChange) return 0;
                return (this.percentChange > o.percentChange) ? 1 : -1;
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
                if(this.percentChange == o.percentChange) return 0;
                return (this.percentChange > o.percentChange) ? 1 : -1;
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

    public String getLastUp() {
        return lastUp;
    }

    public void setLastUp(String lastUp) {
        this.lastUp = lastUp;
    }

    public String getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(String originalValue) {
        this.originalValue = originalValue;
    }

    public boolean getIsRising(){
        return isRising;
    }

    public double getPercentChange(){
        return percentChange;
    }
}
