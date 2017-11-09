package com.asuscomm.yangyinetwork.database_speedtest.dbs.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by jaeyoung on 09/11/2017.
 */
@Dao
public interface StringDao {
    @Query("SELECT * FROM data")
    List<Data> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Data item);

    @Delete
    void delete(Data item);
}
