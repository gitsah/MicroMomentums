package com.ad340.micromomentums;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.Query;

public class Tab4Fragment extends Fragment {

    private static final String TAG = "Tab4Fragment";
    private ListView list;
    private StockListView slv;
    private ArrayList<Stock> mStocks;

    private boolean symbolAsc = false;
    private boolean valueAsc = false;
    private boolean last5Asc = false;
    private boolean last10Asc = false;
    private boolean percentChangeAsc = false;
    private boolean momentumAsc = false;
    private boolean dailyPercentAsc = false;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab2_fragment,container,false);

        list = (ListView) view.findViewById(R.id.listView);

        Box<TrackedStock> stockBox = ObjectBox.get().boxFor(TrackedStock.class);
        Query<TrackedStock> query = stockBox.query().equal(TrackedStock_.id, 0).build();

        FirebaseViewModel viewModel = new FirebaseViewModel();
        viewModel.getStocks(
                (ArrayList<Stock> stocks) -> {
                    //Find isRising for each stock
                    for(Stock stock : stocks){
                        stock.calcualteRising();
                        List<TrackedStock> matches = query.setParameter(TrackedStock_.id, stock.id).find();
                        if(matches.size() > 0)
                            stock.setTracked(true);
                    }
                    //Sort stocks by amount of change descending
                    stocks.sort(Collections.reverseOrder());
                    ArrayList<Stock> trackedStocks = new ArrayList<>();
                    for(Stock stock : stocks)
                        if(stock.isTracked())
                            trackedStocks.add(stock);

                    mStocks = trackedStocks;
                    Log.d("meme", "mstock in 4 populated");
                    slv = new StockListView(getActivity(), trackedStocks);
                    list.setAdapter(slv);
                }

        );

        ArrayAdapter<Stock> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1
        );

        list.setAdapter(listViewAdapter);

        TextView symbol = view.findViewById(R.id.symbolHead);
        TextView currentHead = view.findViewById(R.id.currentHead);
        TextView last5Head = view.findViewById(R.id.last5Head);
        TextView last10Head = view.findViewById(R.id.last10Head);
        TextView percentage = view.findViewById(R.id.percentage);
        TextView buy = view.findViewById(R.id.buy);
        TextView dailyPercent = view.findViewById(R.id.DailyPercentageHeader);

        symbol.setOnClickListener(v -> {
            Comparator<Stock> symbolComparator = Comparator.comparing(Stock::getSymbol);
            mStocks.sort(symbolComparator);

            if(!symbolAsc)
                Collections.reverse(mStocks);
            symbolAsc = !symbolAsc;

            slv.refreshList(mStocks);
        });
        currentHead.setOnClickListener(v -> {
            Comparator<Stock> valueComparator = Comparator.comparingDouble(s -> Double.parseDouble(s.getValue()));
            mStocks.sort(valueComparator);

            if(!valueAsc)
                Collections.reverse(mStocks);
            valueAsc = !valueAsc;

            slv.refreshList(mStocks);
        });
        last5Head.setOnClickListener(v -> {
            Comparator<Stock> last5Comparator = Comparator.comparingDouble(s -> Double.parseDouble(s.getLast5()));
            mStocks.sort(last5Comparator);

            if(!last5Asc)
                Collections.reverse(mStocks);
            last5Asc = !last5Asc;

            slv.refreshList(mStocks);
        });
        last10Head.setOnClickListener(v -> {
            Comparator<Stock> last10Comparator = Comparator.comparingDouble(s -> Double.parseDouble(s.getLast10()));;
            mStocks.sort(last10Comparator);

            if(!last10Asc)
                Collections.reverse(mStocks);
            last10Asc = !last10Asc;

            slv.refreshList(mStocks);
        });
        percentage.setOnClickListener(v -> {
            Comparator<Stock> percentageComparator = Comparator.comparing(Stock::getPercentChange);
            mStocks.sort(percentageComparator);

            if(!percentChangeAsc)
                Collections.reverse(mStocks);
            percentChangeAsc = !percentChangeAsc;

            slv.refreshList(mStocks);
        });
        buy.setOnClickListener(v -> {
            Collections.sort(mStocks);

            if(!momentumAsc)
                Collections.reverse(mStocks);
            momentumAsc = !momentumAsc;

            slv.refreshList(mStocks);
        });

        dailyPercent.setOnClickListener(v -> {
            Comparator<Stock> percentageDailyComparator = Comparator.comparing(Stock::getDayPercentChange);
            mStocks.sort(percentageDailyComparator);

            if(!dailyPercentAsc)
                Collections.reverse(mStocks);
            dailyPercentAsc = !dailyPercentAsc;

            slv.refreshList(mStocks);
        });

        // Inflate the layout for this fragment
        return view;
    }
}