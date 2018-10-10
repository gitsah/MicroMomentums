package com.ad340.micromomentums;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;


    public class FirebaseDB {

        private DatabaseReference mDatabase;
        private HashMap<DatabaseReference, ValueEventListener> listeners;

        public FirebaseDB() {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            listeners = new HashMap<>();
        }

        public void getHelloWorld(Consumer<DataSnapshot> dataChangedCallback, Consumer<DatabaseError> dataErrorCallback) {
            // This is where we can construct our path
            DatabaseReference stockRef1 = mDatabase.child("Stock1");
            DatabaseReference stockRef2 = mDatabase.child("Stock2");
            DatabaseReference stockRef3 = mDatabase.child("Stock3");
            ValueEventListener stockListener = new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
//                    dataChangedCallback.accept(dataSnapshot);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
//                    dataErrorCallback.accept(databaseError);
                }
            };
            stockRef1.addValueEventListener(stockListener);
            listeners.put(stockRef1, stockListener);

            stockRef2.addValueEventListener(stockListener);
            listeners.put(stockRef2, stockListener);

            stockRef3.addValueEventListener(stockListener);
            listeners.put(stockRef3, stockListener);
        }

        public void clear() {
            // Clear all the listeners onPause
//            listeners.forEach(Query::removeEventListener);
        }
}
