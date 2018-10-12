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
    private Activity context;

    public StockListView(Activity context, String[] symbol,String[] value) {
        super(context, R.layout.listview_layout, symbol);

        this.symbol = symbol;
        this.value = value;
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
        return r;

    }

    class ViewHolder {
        TextView tvw1;
        TextView tvw2;

        ViewHolder(View v){
            tvw1 = (TextView) v.findViewById(R.id.symbol);
            tvw2 = (TextView) v.findViewById(R.id.value);

        }
    }
}
