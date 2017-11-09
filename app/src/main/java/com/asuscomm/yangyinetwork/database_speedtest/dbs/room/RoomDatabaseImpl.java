package com.asuscomm.yangyinetwork.database_speedtest.dbs.room;

import android.arch.persistence.room.Room;
import android.os.Handler;
import android.util.Log;

import com.asuscomm.yangyinetwork.database_speedtest.Database;
import com.asuscomm.yangyinetwork.database_speedtest.GlobalApplication;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by jaeyoung on 09/11/2017.
 */

public class RoomDatabaseImpl implements Database {
    private static final String TAG = "RoomDatabaseImpl";
    private AppDatabase mAppDatabase;
    private StringDao mStringDao;

    @Override
    public void initialize(Action finished) {
        Log.d(TAG, "initialize: ");


        mAppDatabase = Room.databaseBuilder(
                GlobalApplication.getContext(),
                AppDatabase.class,
                "mobile_test_android")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        mStringDao = mAppDatabase.stringDao();
        List<Data> all = mStringDao.getAll();

        for (Data item :
                all) {
            mStringDao.delete(item);
        }

        mStringDao.insert(new Data("Hello, World! #1"));
        mStringDao.insert(new Data("Hello, World! #2"));
        mStringDao.insert(new Data("Hello, World! #3"));
        mStringDao.insert(new Data("Hello, World! #4"));
        mStringDao.insert(new Data("Hello, World! #5"));
        mStringDao.insert(new Data("Hello, World! #6"));
        mStringDao.insert(new Data("Hello, World! #7"));
        mStringDao.insert(new Data("Hello, World! #8"));
        mStringDao.insert(new Data("Hello, World! #9"));
        mStringDao.insert(new Data("Hello, World! #10"));

        try {
            finished.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getList(Consumer<Object> finished) {
        Log.d(TAG, "getList: ");

        try {
            finished.accept(mStringDao.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
