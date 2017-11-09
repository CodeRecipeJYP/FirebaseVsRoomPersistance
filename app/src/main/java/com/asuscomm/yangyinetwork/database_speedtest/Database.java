package com.asuscomm.yangyinetwork.database_speedtest;

import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by jaeyoung on 09/11/2017.
 */

public interface Database {
    void initialize(Action finished);
    void getList(Consumer<List<String>> finished);
}
