package com.mzu.biometricsecuredb;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BiometricEncryptedDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";

    private String databasePassword;

    public DatabaseHelper(Context context, String password) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase.loadLibs(context);
        this.databasePassword = password;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AGE + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // **Open Encrypted Database**
    public SQLiteDatabase getEncryptedDatabase() {
        return getWritableDatabase(databasePassword);
    }

    // **Insert Student**
    public boolean insertStudent(String name, int age) {
        SQLiteDatabase db = getEncryptedDatabase();
        try {
            db.execSQL("INSERT INTO " + TABLE_NAME + " (name, age) VALUES (?, ?)", new Object[]{name, age});
            return true;
        } catch (Exception e) {
            Log.e("DB_ERROR", "Error inserting data: " + e.getMessage());
            return false;
        } finally {
            db.close();
        }
    }

    // **Retrieve All Students**
    public Cursor getAllStudents() {
        SQLiteDatabase db = getEncryptedDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
