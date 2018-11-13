package com.ad340.micromomentums;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StockListView extends ArrayAdapter<Stock> {

    private ArrayList<Stock> stocks;
    private Activity context;

    public StockListView(Activity context, ArrayList<Stock> stocks) {
        super(context, R.layout.listview_layout, stocks);

        this.stocks = stocks;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r = convertView;
        ViewHolder viewHolder = null;
        if(r == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout,null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }

        else {
            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.tvw1.setText(stocks.get(position).getSymbol());
        viewHolder.tvw2.setText(stocks.get(position).getValue());
        viewHolder.tvw3.setText(stocks.get(position).getLast5());
        viewHolder.tvw4.setText(stocks.get(position).getLast10());

        boolean isRising = stocks.get(position).getIsRising();

        double percentChange = percentChange(stocks.get(position).getValue(),stocks.get(position).getLast5(),stocks.get(position).getLast10());
        viewHolder.tvw5.setText(String.valueOf(percentChange));

        if (percentChange < 0) {
            viewHolder.tvw5.setBackgroundColor(Color.parseColor("#CC0000"));
            viewHolder.tvw5.setShadowLayer(1,1,1, Color.parseColor("#000000"));

        } else if (percentChange > 0) {
            viewHolder.tvw5.setBackgroundColor(Color.parseColor("#5FD25F"));
            viewHolder.tvw5.setShadowLayer(1,1,1, Color.parseColor("#000000"));
        }

        if (isRising) {
            viewHolder.iv2.setVisibility(convertView.GONE);
            viewHolder.iv1.setVisibility(convertView.VISIBLE);
        }
        else {
            viewHolder.iv1.setVisibility(convertView.GONE);
            viewHolder.iv2.setVisibility(convertView.VISIBLE);
        }

        // uncomment out to see the result of isRising
        //viewHolder.tvX.setText(String.valueOf(isRising));
        return r;

    }

    class ViewHolder {
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        TextView tvw4;
        TextView tvw5;
        ImageView iv1;
        ImageView iv2;
        TextView tvX;

        ViewHolder(View v){
            tvw1 = (TextView) v.findViewById(R.id.symbol);
            tvw2 = (TextView) v.findViewById(R.id.value);
            tvw3 = (TextView) v.findViewById(R.id.last5);
            tvw4 = (TextView) v.findViewById(R.id.last10);
            tvw5 = (TextView) v.findViewById(R.id.percentChange);
            iv1  = (ImageView)v.findViewById(R.id.momentum_true);
            iv2 = (ImageView) v.findViewById(R.id.momentum_false);
            tvX = (TextView) v.findViewById(R.id.momentum);

        }
    }

    /**
     * Take a percent change between each interval of 5 minutes and average them.
     * So the final percent displayed is the average change for each interval.
     * @param current
     * @param last5
     * @param last10
     * @return
     */
    private double percentChange(String current, String last5, String last10){
        // convert the values from Strings to doubles
        double currentVal = Double.valueOf(current);
        double last5Val = Double.valueOf(last5);
        double last10Val = Double.valueOf(last10);

        // Difference from last 10 to last 5
        double change5to10 = ((last5Val - last10Val) / last10Val) * 100;
        double changeCurrentTo5 = ((currentVal - last5Val) / last5Val) * 100;
        double avgPercentChange = (change5to10 + changeCurrentTo5) / 2;

        // this returns a string
        DecimalFormat df = new DecimalFormat("###.####");

        return Double.valueOf(df.format(avgPercentChange));


    }
}
