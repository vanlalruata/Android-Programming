package com.mzu.offlinefirebase;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText etName, etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

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
                Toast.makeText(MainActivity.this, "Student Added Offline!", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etAge.setText("");
            }
        });

        btnSync.setOnClickListener(v -> scheduleSyncTask());
    }

    private void scheduleSyncTask() {
        PeriodicWorkRequest syncWorkRequest =
                new PeriodicWorkRequest.Builder(SyncWorker.class, 1, TimeUnit.HOURS)
                        .build();
        WorkManager.getInstance(this).enqueue(syncWorkRequest);
        Toast.makeText(this, "Auto Sync Scheduled!", Toast.LENGTH_LONG).show();
    }
}
