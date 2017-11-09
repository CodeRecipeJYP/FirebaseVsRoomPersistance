package com.asuscomm.yangyinetwork.database_speedtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.asuscomm.yangyinetwork.database_speedtest.dbs.FirebaseDatabase;

import org.reactivestreams.Publisher;

import java.util.Calendar;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FirebaseDatabase mDb;
    private int mCurrentIteration;
    private long mStartAtInMillis;
    private PublishSubject<Boolean> mNotifier = PublishSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int iteration = 10;
        initializeDb(() -> timeCheck(iteration));
    }

    private void timeCheck(final int iteration) {
        Log.d(TAG, "timeCheck: ");

        final Calendar c = Calendar.getInstance();
        mStartAtInMillis = c.getTimeInMillis();

        mCurrentIteration = 1;


        ((Observable<Boolean>) mNotifier).map((onlyTrue) -> (mCurrentIteration > iteration))
                .subscribe(
                        (isFinished) -> {
                            Log.d(TAG, "timeCheck() called with: mCurrentIteration = [" + mCurrentIteration + "]");
                            if (isFinished) {
                                Log.d(TAG, "timeCheck: isfinished");
                                finishedTimeCheck();
                            } else {
                                Log.d(TAG, "timeCheck: not finished");
                                runSingleStatement(() -> mNotifier.onNext(true));
                            }

                            mCurrentIteration++;
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
    }

    private void runSingleStatement(Action finished) {
        Log.d(TAG, "runSingleStatement: ");

        mDb.getList(
                (results) -> finished.run()
        );
    }

    private void initializeDb(Action finished) {
        Log.d(TAG, "initializeDb: ");
        mDb = new FirebaseDatabase();
//        Database mDb = new RoomDatabase();

        mDb.initialize(finished);
    }
}
