package com.mzu.firestorecache;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private FirestoreSyncManager firestoreSyncManager;
    private StudentDao studentDao;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firestoreSyncManager = new FirestoreSyncManager(this);
        studentDao = StudentDatabase.getInstance(this).studentDao();
        tvResults = findViewById(R.id.tvResults);
        Button btnSync = findViewById(R.id.btnSync);

        btnSync.setOnClickListener(v -> {
            firestoreSyncManager.startFirestoreSync();
            displayLocalData();
        });
    }

    private void displayLocalData() {
        executorService.execute(() -> {
            List<Student> students = studentDao.getAllStudents();
            StringBuilder result = new StringBuilder();
            for (Student student : students) {
                result.append("ID: ").append(student.getFirestoreId())
                      .append(", Name: ").append(student.getName())
                      .append(", Age: ").append(student.getAge())
                      .append("\n");
            }

            runOnUiThread(() -> tvResults.setText(result.toString()));
        });
    }
}
