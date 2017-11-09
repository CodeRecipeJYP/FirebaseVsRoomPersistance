package com.asuscomm.yangyinetwork.database_speedtest.dbs;

import android.os.Handler;
import android.util.Log;

import com.asuscomm.yangyinetwork.database_speedtest.Database;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by jaeyoung on 09/11/2017.
 */

public class RoomDatabase implements Database {
    private static final String TAG = "RoomDatabase";

    @Override
    public void initialize(Action finished) {
        Log.d(TAG, "initialize: ");

        new Handler().postDelayed(
                () -> {
                    Log.d(TAG, "initialize: finished");

                    try {
                        finished.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, 2000);
    }

    @Override
    public void getList(Consumer<Object> finished) {
        Log.d(TAG, "getList: ");

        new Handler().postDelayed(
                () -> {
                    Log.d(TAG, "getList: delayed");

                    List<String> results = new ArrayList<>();
                    results.add("1");
                    results.add("2");
                    results.add("3");

                    try {
                        finished.accept(results);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, 3000);
    }
}
