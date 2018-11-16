package com.ad340.micromomentums;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class Tab2Fragment extends Fragment {

    private static final String TAG = "Tab2Fragment";


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab2_fragment,container,false);

        ListView list = (ListView) view.findViewById(R.id.listView);

        FirebaseViewModel viewModel = new FirebaseViewModel();
        viewModel.getStocks(
                (ArrayList<Stock> stocks) -> {
                    //Find isRising for each stock
                    for(Stock stock : stocks) stock.calcualteRising();
                    //Sort stocks by amount of change descending
                    stocks.sort(Collections.reverseOrder());

//                    String stockSymbols[] = stocks.stream().map(Stock::getSymbol).toArray(String[]::new);
                    StockListView slv = new StockListView(getActivity(), stocks);
                    list.setAdapter(slv);
                }
        );

        ArrayAdapter<Stock> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1
        );

        list.setAdapter(listViewAdapter);

        // Inflate the layout for this fragment
        return view;
    }
}
