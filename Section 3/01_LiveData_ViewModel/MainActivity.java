package com.mzu.livedataviewmodel;

import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.viewModels;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private CounterViewModel counterViewModel;
    private TextView tvCounter;
    private MaterialButton btnIncrement, btnDecrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterViewModel = new ViewModelProvider(this).get(CounterViewModel.class);

        tvCounter = findViewById(R.id.tvCounter);
        btnIncrement = findViewById(R.id.btnIncrement);
        btnDecrement = findViewById(R.id.btnDecrement);

        // Observe LiveData updates
        counterViewModel.getCounter().observe(this, counter -> tvCounter.setText(String.valueOf(counter)));

        btnIncrement.setOnClickListener(v -> counterViewModel.increment());
        btnDecrement.setOnClickListener(v -> counterViewModel.decrement());
    }
}
