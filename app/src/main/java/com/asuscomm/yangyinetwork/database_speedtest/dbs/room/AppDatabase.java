package com.asuscomm.yangyinetwork.database_speedtest.dbs.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.asuscomm.yangyinetwork.database_speedtest.GlobalApplication;

/**
 * Created by jaeyoung on 09/11/2017.
 */


@Database(entities = {Data.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance = null;

    public static AppDatabase getInstance() {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(
                    GlobalApplication.getContext(),
                    AppDatabase.class,
                    "mobile_test_android")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return sInstance;
    }
    public abstract StringDao stringDao();
}
