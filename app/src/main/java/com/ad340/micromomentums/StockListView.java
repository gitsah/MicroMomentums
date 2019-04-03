package com.ad340.micromomentums;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.Query;

public class StockListView extends ArrayAdapter<Stock> {

    private ArrayList<Stock> stocks;
    private Activity context;
    private Box<TrackedStock> stockBox;
    private Query<TrackedStock> trackedQuery;


    public StockListView(Activity context, ArrayList<Stock> stocks) {
        super(context, R.layout.listview_layout, stocks);


        this.stockBox = ObjectBox.get().boxFor(TrackedStock.class);
        this.trackedQuery = stockBox.query().equal(TrackedStock_.id, 0).build();
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

        r.setOnClickListener(v -> {
            Stock stock = stocks.get(position);

            LayoutInflater layoutInflater = context.getLayoutInflater();
            PopupWindow popupWindow = new PopupWindow(layoutInflater.inflate(R.layout.stock_popup, null), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(context.findViewById(R.id.tab2_fragment), Gravity.NO_GRAVITY, 500, 700);

            TextView stockSymbol = popupWindow.getContentView().findViewById(R.id.popup_stock_symbol);
            TextView currentValue = popupWindow.getContentView().findViewById(R.id.popup_value);
            TextView last5 = popupWindow.getContentView().findViewById(R.id.popup_last5);
            TextView last10 = popupWindow.getContentView().findViewById(R.id.popup_last10);
            TextView lastUpdate = popupWindow.getContentView().findViewById(R.id.popup_last_updated);
            TextView noGraph = popupWindow.getContentView().findViewById(R.id.popup_no_graph);
            GraphView graph = popupWindow.getContentView().findViewById(R.id.daily_graph);
            Switch tracked = popupWindow.getContentView().findViewById(R.id.popup_track_switch);

            List<TrackedStock> matches = trackedQuery.setParameter(TrackedStock_.id, stock.id).find();
            if(matches.size() > 0)
                stock.setTracked(true);
            else
                stock.setTracked(false);

            if(stock.getDayHistory() != null) {
                //create the graph from the list of values
                LinkedList<DataPoint> dataPoints = new LinkedList<>();
                int x = 0;
                for (String value : stock.getDayHistory()) {
                    Double y = Double.parseDouble(value);
                    dataPoints.push(new DataPoint(x, y));
                    x++;
                }
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints.toArray(new DataPoint[dataPoints.size()]));
                graph.addSeries(series);
            }
            else{
                graph.setVisibility(View.GONE);
                noGraph.setVisibility(View.VISIBLE);
            }
            stockSymbol.setText(stock.getSymbol());
            currentValue.setText(stock.getValue());
            last5.setText(stock.getLast5());
            last10.setText(stock.getLast10());
            String lastUpdated = "Last updated: " + stock.getLastUp();
            lastUpdate.setText(lastUpdated);
            tracked.setChecked(stock.isTracked());

            tracked.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if(isChecked){
                    stock.setTracked(true);
                    stockBox.put(new TrackedStock(stock.id));
                }
                else{
                    stock.setTracked(false);
                    stockBox.remove(stock.id);
                }
            });

//            FragmentManager fragmentManager = context.getFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//            StockPopupFragment fragment = new StockPopupFragment();
//            fragment.setStock(stocks.get(position));
//            fragmentTransaction.replace(R.id.container, fragment);
//            fragmentTransaction.commit();

//            Toast toast = Toast.makeText(getContext(),
//                    cSymbol + " last updated: " + stocks.get(position).getLastUp() + " EST",
//                    Toast.LENGTH_SHORT);
//
//            // for rounded edges
//            //toast.getView().getBackground().setColorFilter(Color.parseColor("#CCCCCC"), PorterDuff.Mode.DARKEN);
//            // for block edges
//            toast.getView().setBackgroundColor(Color.parseColor("#CCCCCC"));
//            toast.show();
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
