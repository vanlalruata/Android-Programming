package com.mzu.firestoretosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.ListenerRegistration;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudentDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_FIRESTORE_ID = "firestore_id";

    private FirebaseFirestore firestore;
    private ListenerRegistration listenerRegistration;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        firestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRESTORE_ID + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AGE + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // **Insert Student & Sync with Firestore**
    public void insertStudent(String firestoreId, String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRESTORE_ID, firestoreId);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // **Update Student in SQLite**
    public void updateStudent(String firestoreId, String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        db.update(TABLE_NAME, values, COLUMN_FIRESTORE_ID + "=?", new String[]{firestoreId});
        db.close();
    }

    // **Delete Student from SQLite**
    public void deleteStudent(String firestoreId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_FIRESTORE_ID + "=?", new String[]{firestoreId});
        db.close();
    }

    // **Sync Firestore Changes to SQLite**
    public void startFirestoreSync() {
        listenerRegistration = firestore.collection("students")
                .addSnapshotListener((snapshots, e) -> {
                    if (e != null) {
                        Log.e("FirestoreSync", "Error listening for changes: " + e.getMessage());
                        return;
                    }

                    SQLiteDatabase db = this.getWritableDatabase();
                    if (snapshots != null) {
                        for (QueryDocumentSnapshot doc : snapshots) {
                            String firestoreId = doc.getId();
                            String name = doc.getString("name");
                            Long ageLong = doc.getLong("age");
                            int age = (ageLong != null) ? ageLong.intValue() : 0;

                            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_FIRESTORE_ID + "=?", new String[]{firestoreId});
                            if (cursor.getCount() > 0) {
                                updateStudent(firestoreId, name, age);
                            } else {
                                insertStudent(firestoreId, name, age);
                            }
                            cursor.close();
                        }
                    }
                    db.close();
                });
    }

    // **Stop Firestore Listener**
    public void stopFirestoreSync() {
        if (listenerRegistration != null) {
            listenerRegistration.remove();
        }
    }
}
