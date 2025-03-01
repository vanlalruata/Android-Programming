package com.mzu.firestorecache;

import android.content.Context;
import android.util.Log;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirestoreSyncManager {
    private final FirebaseFirestore firestore;
    private final StudentDao studentDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public FirestoreSyncManager(Context context) {
        firestore = FirebaseFirestore.getInstance();
        studentDao = StudentDatabase.getInstance(context).studentDao();
    }

    public void startFirestoreSync() {
        firestore.collection("students").addSnapshotListener((snapshots, e) -> {
            if (e != null) {
                Log.e("FirestoreSync", "Error fetching Firestore data: " + e.getMessage());
                return;
            }

            if (snapshots != null) {
                executorService.execute(() -> {
                    for (QueryDocumentSnapshot doc : snapshots) {
                        String firestoreId = doc.getId();
                        String name = doc.getString("name");
                        int age = doc.getLong("age").intValue();

                        Student student = new Student(firestoreId, name, age);
                        studentDao.insertStudent(student);
                    }
                });
            }
        });
    }
}
