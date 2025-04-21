package com.example.registration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "students_db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "students";
    public static final String COL_ID = "id";
    public static final String COL_FULLNAME = "fullname";
    public static final String COL_DOB = "dob";
    public static final String COL_GENDER = "gender";
    public static final String COL_FATHER = "father";
    public static final String COL_MOTHER = "mother";
    public static final String COL_PRESENT = "present";
    public static final String COL_CORRESPONDING = "corresponding";
    public static final String COL_CONTACT = "contact";
    public static final String COL_EMAIL = "email";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FULLNAME + " TEXT, " +
                COL_DOB + " TEXT, " +
                COL_GENDER + " TEXT, " +
                COL_FATHER + " TEXT, " +
                COL_MOTHER + " TEXT, " +
                COL_PRESENT + " TEXT, " +
                COL_CORRESPONDING + " TEXT, " +
                COL_CONTACT + " TEXT, " +
                COL_EMAIL + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FULLNAME, student.getFullName());
        values.put(COL_DOB, student.getDob());
        values.put(COL_GENDER, student.getGender());
        values.put(COL_FATHER, student.getFatherName());
        values.put(COL_MOTHER, student.getMotherName());
        values.put(COL_PRESENT, student.getPresentAddress());
        values.put(COL_CORRESPONDING, student.getCorrespondingAddress());
        values.put(COL_CONTACT, student.getContact());
        values.put(COL_EMAIL, student.getEmail());
        return db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, COL_ID + " DESC");
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_FULLNAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_DOB)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_GENDER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_FATHER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_MOTHER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_PRESENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_CORRESPONDING)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_CONTACT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL))
                );
                students.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return students;
    }

    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FULLNAME, student.getFullName());
        values.put(COL_DOB, student.getDob());
        values.put(COL_GENDER, student.getGender());
        values.put(COL_FATHER, student.getFatherName());
        values.put(COL_MOTHER, student.getMotherName());
        values.put(COL_PRESENT, student.getPresentAddress());
        values.put(COL_CORRESPONDING, student.getCorrespondingAddress());
        values.put(COL_CONTACT, student.getContact());
        values.put(COL_EMAIL, student.getEmail());
        return db.update(TABLE_NAME, values, COL_ID + "=?", new String[]{String.valueOf(student.getId())});
    }

    public int deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)});
    }

    public Student getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COL_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Student student = new Student(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_FULLNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_DOB)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_GENDER)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_FATHER)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_MOTHER)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_PRESENT)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_CORRESPONDING)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_CONTACT)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL))
            );
            cursor.close();
            return student;
        }
        return null;
    }
}