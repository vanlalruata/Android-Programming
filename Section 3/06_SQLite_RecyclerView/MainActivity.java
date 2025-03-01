package com.mzu.sqliterecyclerview;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private StudentAdapter adapter;
    private EditText etName, etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Student> studentList = databaseHelper.getAllStudents();
        adapter = new StudentAdapter(studentList);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            String name = etName.getText().toString();
            String ageText = etAge.getText().toString();
            if (!name.isEmpty() && !ageText.isEmpty()) {
                databaseHelper.insertStudent(name, Integer.parseInt(ageText));
                adapter.updateList(databaseHelper.getAllStudents());
                Toast.makeText(this, "Student Added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
