package com.asuscomm.yangyinetwork.database_speedtest.dbs.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by jaeyoung on 09/11/2017.
 */
@Entity()
public class Data {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "value")
    public String value;

    public Data(String value) {
        this.value = value;
}

    @Override
    public String toString() {
        return "Data{" +
                "uid=" + uid +
                ", value='" + value + '\'' +
                '}';
    }
}
