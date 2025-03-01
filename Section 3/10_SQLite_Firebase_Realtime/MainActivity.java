package com.mzu.sqlfirebasesync;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText etName, etAge;
    private DatabaseReference firebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        firebaseRef = FirebaseDatabase.getInstance().getReference("students");

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSync = findViewById(R.id.btnSync);

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

        btnSync.setOnClickListener(v -> syncAllDataToFirebase());
    }

    private void syncAllDataToFirebase() {
        Cursor cursor = databaseHelper.getAllStudents();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data to sync!", Toast.LENGTH_SHORT).show();
            return;
        }

        while (cursor.moveToNext()) {
            String id = firebaseRef.push().getKey();
            String name = cursor.getString(1);
            int age = cursor.getInt(2);

            Map<String, Object> student = new HashMap<>();
            student.put("id", id);
            student.put("name", name);
            student.put("age", age);
            firebaseRef.child(id).setValue(student);
        }
        Toast.makeText(this, "All data synced to Firebase!", Toast.LENGTH_SHORT).show();
    }
}
