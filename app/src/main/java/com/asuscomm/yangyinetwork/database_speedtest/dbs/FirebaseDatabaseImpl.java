package com.asuscomm.yangyinetwork.database_speedtest.dbs;

import android.os.Handler;
import android.util.Log;

import com.asuscomm.yangyinetwork.database_speedtest.Database;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by jaeyoung on 09/11/2017.
 */

public class FirebaseDatabaseImpl implements Database {
    private static final String TAG = "FirebaseDatabaseImpl";
    private FirebaseDatabase mDbInstance;

    @Override
    public void initialize(Action finished) {
        Log.d(TAG, "initialize: ");

        mDbInstance = FirebaseDatabase.getInstance();

        DatabaseReference myRef = mDbInstance.getReference("messages");
        myRef.removeValue(
                ((databaseError, databaseReference) -> {
                    myRef.push().setValue("Hello, World! #1");
                    myRef.push().setValue("Hello, World! #2");
                    myRef.push().setValue("Hello, World! #3");
                    myRef.push().setValue("Hello, World! #4");
                    myRef.push().setValue("Hello, World! #5");
                    myRef.push().setValue("Hello, World! #6");
                    myRef.push().setValue("Hello, World! #7");
                    myRef.push().setValue("Hello, World! #8");
                    myRef.push().setValue("Hello, World! #9");
                    myRef.push().setValue("Hello, World! #10");
                    try {
                        finished.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
        );
    }

    @Override
    public void getList(Consumer<Object> finished) {
        Log.d(TAG, "getList: ");

        DatabaseReference myRef = mDbInstance.getReference("messages");
        myRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d(TAG, "onDataChange: ");

                        Map<String, String> results = dataSnapshot.getValue(new GenericTypeIndicator<Map<String, String>>() {});
                        try {
                            finished.accept(results);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d(TAG, "onCancelled: ");
                    }
                }
        );
    }
}
