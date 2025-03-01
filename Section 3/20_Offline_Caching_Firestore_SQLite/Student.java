package com.mzu.firestorecache;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String firestoreId;
    private String name;
    private int age;

    public Student(String firestoreId, String name, int age) {
        this.firestoreId = firestoreId;
        this.name = name;
        this.age = age;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFirestoreId() { return firestoreId; }
    public String getName() { return name; }
    public int getAge() { return age; }
}
