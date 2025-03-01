package com.mzu.workmanagerbackup;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class BackupWorker extends Worker {
    private static final String TAG = "BackupWorker";

    public BackupWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        boolean success = databaseHelper.backupDatabase();

        if (success) {
            Log.d(TAG, "Database backup successful!");
            return Result.success();
        } else {
            Log.e(TAG, "Database backup failed!");
            return Result.failure();
        }
    }
}
