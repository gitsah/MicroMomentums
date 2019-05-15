package com.ad340.micromomentums;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.function.Consumer;

    public class FirebaseDB {

        private DatabaseReference mDatabase;
        private HashMap<DatabaseReference, ValueEventListener> listeners;

        public FirebaseDB() {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            listeners = new HashMap<>();
        }

        //Kyle's approach from last quarter, requires api level 24
        public void getStocks(Consumer<DataSnapshot> dataChangedCallback, Consumer<DatabaseError> dataErrorCallback) {
            // This is where we can construct our path
            DatabaseReference stocksRef = mDatabase.child("Stocks");

            ValueEventListener stockListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataChangedCallback.accept(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        dataErrorCallback.accept(databaseError);
                    }
                };

            stocksRef.addValueEventListener(stockListener);
            listeners.put(stocksRef, stockListener);
        }

        public void clear() {
            // Clear all the listeners onPause
            listeners.forEach(Query::removeEventListener);
        }
}
