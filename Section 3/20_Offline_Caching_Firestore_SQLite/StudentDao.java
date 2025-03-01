package com.mzu.firestorecache;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudent(Student student);

    @Query("SELECT * FROM students")
    List<Student> getAllStudents();

    @Query("DELETE FROM students WHERE firestoreId = :firestoreId")
    void deleteStudent(String firestoreId);
}
