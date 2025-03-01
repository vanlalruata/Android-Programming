package com.mzu.userprofile;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private MaterialButton btnFollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFollow = findViewById(R.id.btnFollow);
        btnFollow.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Followed!", Toast.LENGTH_SHORT).show());
    }
}
