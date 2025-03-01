package com.mzu.progressbardemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private MaterialButton btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(v -> new LoadingTask().execute());
    }

    private class LoadingTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            btnStart.setEnabled(false);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000); // Simulate loading
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setVisibility(View.GONE);
            btnStart.setEnabled(true);
            Toast.makeText(MainActivity.this, "Loading Complete!", Toast.LENGTH_SHORT).show();
        }
    }
}
