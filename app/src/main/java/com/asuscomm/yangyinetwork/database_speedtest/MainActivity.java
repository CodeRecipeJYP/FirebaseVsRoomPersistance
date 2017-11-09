package com.asuscomm.yangyinetwork.database_speedtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.asuscomm.yangyinetwork.database_speedtest.dbs.FirebaseDatabaseImpl;
import com.asuscomm.yangyinetwork.database_speedtest.dbs.FirebaseDatabaseOfflineImpl;
import com.asuscomm.yangyinetwork.database_speedtest.dbs.room.RoomDatabaseImpl;

import java.util.Arrays;
import java.util.Calendar;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Database mDb;
    private int mCurrentIteration;
    private long mStartAtInMillis;
    private PublishSubject<Boolean> mNotifier = PublishSubject.create();
    private int mIteration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIteration = 1000;
        initializeDb(() -> timeCheck(mIteration));
    }

    private void timeCheck(final int iteration) {
        Log.d(TAG, "timeCheck: ");

        final Calendar c = Calendar.getInstance();
        mStartAtInMillis = c.getTimeInMillis();
        mCurrentIteration = 1;


        ((Observable<Boolean>) mNotifier).map((onlyTrue) -> (mCurrentIteration > iteration))
                .subscribe(
                        (isFinished) -> {
                            mCurrentIteration++;
                            if (isFinished) {
                                Log.d(TAG, "timeCheck: isfinished");
                                finishedTimeCheck();
                            } else {
                                Log.d(TAG, "timeCheck() called with: mCurrentIteration = [" + mCurrentIteration + "]");
                                Log.d(TAG, "timeCheck: not finished");
                                runSingleStatement(() -> mNotifier.onNext(true));
                            }

                        }
                );

        mNotifier.onNext(true);


//        finishedTimeCheck();
    }

    private void finishedTimeCheck() {
        Calendar endCalender = Calendar.getInstance();
        long endAtInMillis = endCalender.getTimeInMillis();
        long elapsedTimeInMillis = endAtInMillis - mStartAtInMillis;
        Log.d(TAG, "timeCheck() called with: " +
                "mStartAtInMillis = [" + mStartAtInMillis + "]");
        Log.d(TAG, "timeCheck() called with: " +
                "endAtInMillis = [" + endAtInMillis + "]");
        Log.d(TAG, "timeCheck() called with: " +
                "elapsedTimeInMillis = [" + elapsedTimeInMillis + "]");
        Log.d(TAG, "timeCheck() called with: " +
                "elapsedTimeInMillis/mIteration = [" + elapsedTimeInMillis/ mIteration + "]");
    }

    private void runSingleStatement(Action finished) {
        Log.d(TAG, "runSingleStatement: ");

        mDb.getList(
                (results) -> {
                    Log.d(TAG, "runSingleStatement: results=" + results.toString());
                    finished.run();
                }
        );
    }

    private void initializeDb(Action finished) {
        Log.d(TAG, "initializeDb: ");
//        mDb = new FirebaseDatabaseImpl();
        mDb = new FirebaseDatabaseOfflineImpl();
//        mDb = new RoomDatabaseImpl();

        mDb.initialize(finished);
    }
}
