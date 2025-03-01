package com.mzu.dialogdemo;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private MaterialButton btnShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowDialog = findViewById(R.id.btnShowDialog);
        btnShowDialog.setOnClickListener(view -> showDialog());
    }

    private void showDialog() {
        new AlertDialog.Builder(this)
            .setTitle("Confirm Action")
            .setMessage("Are you sure you want to proceed?")
            .setPositiveButton("Yes", (dialog, which) -> dialog.dismiss())
            .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
            .show();
    }
}
