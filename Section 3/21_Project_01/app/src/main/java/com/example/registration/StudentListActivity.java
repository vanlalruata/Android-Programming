package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity implements StudentAdapter.OnStudentActionListener {

    RecyclerView recyclerView;
    StudentAdapter adapter;
    DatabaseHelper db;
    ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        db = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadStudents();
    }

    private void loadStudents() {
        students = db.getAllStudents();
        adapter = new StudentAdapter(students, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onEdit(Student student) {
        Intent intent = new Intent(this, EditStudentActivity.class);
        intent.putExtra("student_id", student.getId());
        startActivity(intent);
    }

    @Override
    public void onDelete(Student student) {
        new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Delete this student?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    db.deleteStudent(student.getId());
                    loadStudents();
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStudents();
    }
}