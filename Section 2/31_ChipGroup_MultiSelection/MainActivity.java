package com.mzu.chipgroupdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ChipGroup chipGroup;
    private TextView tvSelected;
    private MaterialButton btnShowSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chipGroup = findViewById(R.id.chipGroup);
        tvSelected = findViewById(R.id.tvSelected);
        btnShowSelection = findViewById(R.id.btnShowSelection);

        btnShowSelection.setOnClickListener(v -> {
            List<String> selectedLanguages = new ArrayList<>();
            for (int i = 0; i < chipGroup.getChildCount(); i++) {
                Chip chip = (Chip) chipGroup.getChildAt(i);
                if (chip.isChecked()) {
                    selectedLanguages.add(chip.getText().toString());
                }
            }
            tvSelected.setText("Selected Languages: " + String.join(", ", selectedLanguages));
        });
    }
}
