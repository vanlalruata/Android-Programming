package com.mzu.importcsvsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_CSV_FILE = 1;
    private DatabaseHelper databaseHelper;
    private TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        tvResults = findViewById(R.id.tvResults);
        Button btnImportCSV = findViewById(R.id.btnImportCSV);

        btnImportCSV.setOnClickListener(v -> openFileChooser());
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/csv");
        startActivityForResult(Intent.createChooser(intent, "Select CSV File"), PICK_CSV_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_CSV_FILE && resultCode == RESULT_OK && data != null) {
            Uri fileUri = data.getData();
            if (fileUri != null) {
                importCSV(fileUri);
            }
        }
    }

    private void importCSV(Uri fileUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(fileUri);
            boolean success = databaseHelper.importCSV(inputStream);
            if (success) {
                Toast.makeText(this, "CSV Imported Successfully!", Toast.LENGTH_SHORT).show();
                displayData();
            } else {
                Toast.makeText(this, "Import Failed!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error importing file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void displayData() {
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
    }
}
