package com.mzu.offlinefirebase;

import android.content.Context;
import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class SyncWorker extends Worker {
    private DatabaseHelper databaseHelper;
    private FirebaseFirestore firestore;

    public SyncWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        databaseHelper = new DatabaseHelper(context);
        firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public Result doWork() {
        Cursor cursor = databaseHelper.getUnsyncedStudents();
        if (cursor.getCount() == 0) {
            return Result.success();
        }

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);

            Map<String, Object> student = new HashMap<>();
            student.put("name", name);
            student.put("age", age);

            firestore.collection("students").add(student)
                    .addOnSuccessListener(documentReference -> databaseHelper.markAsSynced(id));
        }

        return Result.success();
    }
}
