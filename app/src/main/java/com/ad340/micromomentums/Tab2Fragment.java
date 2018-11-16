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
import android.widget.Toast;

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

        TextView symbol = view.findViewById(R.id.symbolHead);
        TextView currentHead = view.findViewById(R.id.currentHead);
        TextView last5Head = view.findViewById(R.id.last5Head);
        TextView last10Head = view.findViewById(R.id.last10Head);
        TextView percentage = view.findViewById(R.id.percentage);
        TextView buy = view.findViewById(R.id.buy);
        
        symbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageToast(v);
            }
        });
        currentHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageToast(v);
            }
        });
        last5Head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageToast(v);
            }
        });
        last10Head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageToast(v);
            }
        });
        percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageToast(v);
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageToast(v);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }


    private void messageToast(View v) {
        Toast.makeText(getContext(), "Symbol", Toast.LENGTH_SHORT).show();
    }


}
