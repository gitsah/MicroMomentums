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

public class StockListView extends ArrayAdapter<String> {

    private String[] name;
    private String[] symbol;
    private Activity context;

    public StockListView(Activity context, String[] name,String[] symbol) {
        super(context, R.layout.listview_layout, name);

        this.name = name;
        this.symbol = symbol;
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

        viewHolder.tvw1.setText(name[position]);
        viewHolder.tvw2.setText(symbol[position]);
        return r;

    }

    class ViewHolder {
        TextView tvw1;
        TextView tvw2;

        ViewHolder(View v){
            tvw1 = (TextView) v.findViewById(R.id.name);
            tvw2 = (TextView) v.findViewById(R.id.symbol);

        }
    }
}
