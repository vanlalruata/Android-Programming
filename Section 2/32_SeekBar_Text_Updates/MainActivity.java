package com.mzu.seekbardemo;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView tvProgress;
    private MaterialButton btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        tvProgress = findViewById(R.id.tvProgress);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Update text as SeekBar moves
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvProgress.setText("Progress: " + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Display SeekBar value when button is clicked
        btnSubmit.setOnClickListener(v -> 
            Toast.makeText(MainActivity.this, "Selected Value: " + seekBar.getProgress() + "%", Toast.LENGTH_SHORT).show()
        );
    }
}
