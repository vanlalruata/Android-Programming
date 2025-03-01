package com.mzu.ftssearch;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText etName, etAge, etSearch;
    private TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etSearch = findViewById(R.id.etSearch);
        tvResults = findViewById(R.id.tvResults);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSearch = findViewById(R.id.btnSearch);

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
                Toast.makeText(MainActivity.this, "Student Added!", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etAge.setText("");
            }
        });

        btnSearch.setOnClickListener(v -> {
            String query = etSearch.getText().toString();
            List<String> results = databaseHelper.searchStudents(query);
            if (results.isEmpty()) {
                tvResults.setText("No matches found.");
            } else {
                StringBuilder builder = new StringBuilder();
                for (String name : results) {
                    builder.append(name).append("\n");
                }
                tvResults.setText(builder.toString());
            }
        });
    }
}
