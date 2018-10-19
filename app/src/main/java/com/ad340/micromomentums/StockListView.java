package com.ad340.micromomentums;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class StockListView extends ArrayAdapter<String> {

    private String[] symbol;
    private String[] value;
    private String[] last5;
    private String[] last10;
    private Activity context;

    public StockListView(Activity context, String[] symbol, String[] value, String[] last5, String[] last10) {
        super(context, R.layout.listview_layout, symbol);

        this.symbol = symbol;
        this.value = value;
        this.last5 = last5;
        this.last10 = last10;
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

        viewHolder.tvw1.setText(symbol[position]);
        viewHolder.tvw2.setText(value[position]);
        viewHolder.tvw3.setText(last5[position]);
        viewHolder.tvw4.setText(last10[position]);
        return r;

    }

    class ViewHolder {
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        TextView tvw4;

        ViewHolder(View v){
            tvw1 = (TextView) v.findViewById(R.id.symbol);
            tvw2 = (TextView) v.findViewById(R.id.value);
            tvw3 = (TextView) v.findViewById(R.id.last5);
            tvw4 = (TextView) v.findViewById(R.id.last10);
        }
    }
}
