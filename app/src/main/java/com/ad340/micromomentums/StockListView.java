package com.ad340.micromomentums;

import android.app.Activity;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StockListView extends ArrayAdapter<String> {

    private String[] symbol;
    private String[] value;
    private String[] last5;
    private String[] last10;
    private boolean[] momentum;
    private Activity context;

    public StockListView(Activity context, String[] symbol, String[] value, String[] last5, String[] last10, boolean[] isRising) {
        super(context, R.layout.listview_layout, symbol);

        this.symbol = symbol;
        this.value = value;
        this.last5 = last5;
        this.last10 = last10;
        this.context = context;
        this.momentum = isRising;
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
        viewHolder.iv1.setText(String.valueOf(momentum[position])); // TEST to see if it prints T/F
        return r;

    }

    class ViewHolder {
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        TextView tvw4;
        //ImageView iv1;
        TextView iv1;

        ViewHolder(View v){
            tvw1 = (TextView) v.findViewById(R.id.symbol);
            tvw2 = (TextView) v.findViewById(R.id.value);
            tvw3 = (TextView) v.findViewById(R.id.last5);
            tvw4 = (TextView) v.findViewById(R.id.last10);
            //iv1  = (ImageView)v.findViewById(R.id.momentum);
            iv1 = (TextView) v.findViewById(R.id.momentum);
        }
    }
}
