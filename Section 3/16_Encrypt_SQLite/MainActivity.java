package com.mzu.securedb;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText etName, etAge;
    private TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        tvResults = findViewById(R.id.tvResults);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnView = findViewById(R.id.btnView);

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String ageText = etAge.getText().toString();
            if (name.isEmpty() || ageText.isEmpty()) {
                Toast.makeText(MainActivity.this, "Enter all details", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageText);
            boolean success = databaseHelper.insertStudent(name, age);
            if (success) {
                Toast.makeText(MainActivity.this, "Student Added Securely!", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etAge.setText("");
            }
        });

        btnView.setOnClickListener(v -> {
            Cursor cursor = databaseHelper.getAllStudents();
            StringBuilder result = new StringBuilder();
            if (cursor.getCount() == 0) {
                result.append("No students found.");
            } else {
                while (cursor.moveToNext()) {
                    result.append("ID: ").append(cursor.getInt(0))
                          .append(", Name: ").append(cursor.getString(1))
                          .append(", Age: ").append(cursor.getInt(2))
                          .append("\n");
                }
            }
            tvResults.setText(result.toString());
            cursor.close();
        });
    }
}
