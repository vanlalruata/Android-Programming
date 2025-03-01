package com.mzu.ftssearch;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudentDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";

    private static final String FTS_TABLE_NAME = "students_fts";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // **Create Regular Table**
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AGE + " INTEGER)";
        db.execSQL(CREATE_TABLE);

        // **Create FTS Table**
        String CREATE_FTS_TABLE = "CREATE VIRTUAL TABLE " + FTS_TABLE_NAME +
                " USING fts5(" + COLUMN_NAME + ")";
        db.execSQL(CREATE_FTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FTS_TABLE_NAME);
        onCreate(db);
    }

    // **Insert Student into Both Tables**
    public boolean insertStudent(String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_NAME + " (name, age) VALUES (?, ?)", new Object[]{name, age});
        db.execSQL("INSERT INTO " + FTS_TABLE_NAME + " (name) VALUES (?)", new Object[]{name});
        db.close();
        return true;
    }

    // **Search Students Using FTS**
    public List<String> searchStudents(String query) {
        List<String> resultList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM " + FTS_TABLE_NAME + " WHERE name MATCH ?", new String[]{query + "*"});
        while (cursor.moveToNext()) {
            resultList.add(cursor.getString(0));
        }
        cursor.close();
        return resultList;
    }
}
