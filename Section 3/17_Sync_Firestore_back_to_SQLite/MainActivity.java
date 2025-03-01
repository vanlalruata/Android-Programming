package com.mzu.firestoretosqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        tvResults = findViewById(R.id.tvResults);
        Button btnStartSync = findViewById(R.id.btnStartSync);

        btnStartSync.setOnClickListener(v -> {
            databaseHelper.startFirestoreSync();
            displayData();
        });
    }

    private void displayData() {
        Cursor cursor = databaseHelper.getReadableDatabase().rawQuery("SELECT * FROM students", null);
        StringBuilder result = new StringBuilder();
        if (cursor.getCount() == 0) {
            result.append("No students found.");
        } else {
            while (cursor.moveToNext()) {
                result.append("ID: ").append(cursor.getInt(0))
                      .append(", Name: ").append(cursor.getString(2))
                      .append(", Age: ").append(cursor.getInt(3))
                      .append("\n");
            }
        }
        tvResults.setText(result.toString());
        cursor.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.stopFirestoreSync();
    }
}
