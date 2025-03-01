package com.mzu.sqlitetocsv;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_STORAGE_PERMISSION = 100;
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
        Button btnExport = findViewById(R.id.btnExport);

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

        btnExport.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                exportData();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
            }
        });
    }

    private void exportData() {
        boolean success = databaseHelper.exportToCSV(this);
        if (success) {
            Toast.makeText(this, "Exported to Downloads/StudentData.csv", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Export Failed!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                exportData();
            } else {
                Toast.makeText(this, "Storage Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
