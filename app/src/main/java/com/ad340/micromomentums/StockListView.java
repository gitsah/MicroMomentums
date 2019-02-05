package com.ad340.micromomentums;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
        ViewHolder viewHolder;
        if(r == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout,null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }

        else {
            viewHolder = (ViewHolder) r.getTag();
        }
        String cSymbol = stocks.get(position).getSymbol();

        viewHolder.tvw1.setText(cSymbol);
        viewHolder.tvw2.setText(stocks.get(position).getValue());
        viewHolder.tvw3.setText(stocks.get(position).getLast5());
        viewHolder.tvw4.setText(stocks.get(position).getLast10());

        TextView updated = context.findViewById(R.id.lastUpdated);
        updated.setText("Last Updated: " + stocks.get(position).getLastUp());

        r.setOnClickListener(v -> {
            Toast toast = Toast.makeText(getContext(),
                    cSymbol + " last updated: " + stocks.get(position).getLastUp() + " EST",
                    Toast.LENGTH_SHORT);

            // for rounded edges
            //toast.getView().getBackground().setColorFilter(Color.parseColor("#CCCCCC"), PorterDuff.Mode.DARKEN);
            // for block edges
            toast.getView().setBackgroundColor(Color.parseColor("#CCCCCC"));
            toast.show();
        });

        boolean isRising = stocks.get(position).getIsRising();

        double percentChange = stocks.get(position).getPercentChange();
        viewHolder.tvw5.setText(String.valueOf(percentChange));

        if (percentChange < 0) {
            viewHolder.tvw5.setBackgroundColor(Color.parseColor("#990000"));
            viewHolder.tvw5.setShadowLayer(1,1,1, Color.parseColor("#000000"));
        } else if (percentChange > 0) {
            viewHolder.tvw5.setBackgroundColor(Color.parseColor("#6E8771"));
            viewHolder.tvw5.setShadowLayer(1,1,1, Color.parseColor("#000000"));
        }

        if (isRising) {
            viewHolder.iv2.setVisibility(View.GONE);
            viewHolder.iv1.setVisibility(View.VISIBLE);
        }
        else {
            viewHolder.iv1.setVisibility(View.GONE);
            viewHolder.iv2.setVisibility(View.VISIBLE);
        }

        double dayPercentChange = stocks.get(position).getDayPercentChange();
        viewHolder.tvw6.setText(String.valueOf(dayPercentChange));

        if (dayPercentChange < 0) {
            viewHolder.tvw6.setBackgroundColor(Color.parseColor("#990000"));
            viewHolder.tvw6.setShadowLayer(1,1,1, Color.parseColor("#000000"));
        } else if (dayPercentChange > 0) {
            viewHolder.tvw6.setBackgroundColor(Color.parseColor("#6E8771"));
            viewHolder.tvw6.setShadowLayer(1,1,1, Color.parseColor("#000000"));
        }


        return r;
    }

    class ViewHolder {
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        TextView tvw4;
        TextView tvw5;
        TextView tvw6;
        ImageView iv1;
        ImageView iv2;

        ViewHolder(View v){
            tvw1 = (TextView) v.findViewById(R.id.symbol);
            tvw2 = (TextView) v.findViewById(R.id.value);
            tvw3 = (TextView) v.findViewById(R.id.last5);
            tvw4 = (TextView) v.findViewById(R.id.last10);
            tvw5 = (TextView) v.findViewById(R.id.percentChange);
            tvw6 = (TextView) v.findViewById(R.id.DailyPercentChange);
            iv1 = (ImageView)v.findViewById(R.id.momentum_true);
            iv2 = (ImageView) v.findViewById(R.id.momentum_false);
        }
    }

    void refreshList(ArrayList<Stock> stocks){
        this.stocks = stocks;
        notifyDataSetChanged();
    }
}
