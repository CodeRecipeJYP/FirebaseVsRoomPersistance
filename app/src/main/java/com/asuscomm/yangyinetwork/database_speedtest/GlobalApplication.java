package com.asuscomm.yangyinetwork.database_speedtest;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by jaeyoung on 09/11/2017.
 */

public class GlobalApplication extends Application {
    private static WeakReference<Context> sContext;


    @Override
    public void onCreate() {
        super.onCreate();
        sContext = new WeakReference<Context>(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        sContext = null;
    }

    public static Context getContext() {
        if (sContext == null) {
            return null;
        } else {
            return sContext.get();
        }
    }
}
