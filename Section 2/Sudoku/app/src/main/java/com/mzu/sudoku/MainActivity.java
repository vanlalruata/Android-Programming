package com.mzu.sudoku;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private SudokuBoardView sudokuBoard;
    private SudokuGenerator generator = new SudokuGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        sudokuBoard = findViewById(R.id.sudokuBoard);
        setupNumberButtons();
        setupDifficultySpinner();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setupNumberButtons() {
        for (int i = 1; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("btn_" + i, "id", getPackageName());
            Button btn = findViewById(buttonId);
            final int number = i;
            btn.setOnClickListener(v -> sudokuBoard.setNumber(number));
        }

        findViewById(R.id.btn_clear).setOnClickListener(v -> sudokuBoard.setNumber(0));
    }

    private void setupDifficultySpinner() {
        Spinner spinner = findViewById(R.id.difficultySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] levels = getResources().getStringArray(R.array.difficulty_levels);
                generateNewPuzzle(levels[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void generateNewPuzzle(String difficulty) {
        int emptyCells;
        switch (difficulty) {
            case "Easy": emptyCells = 30; break;
            case "Medium": emptyCells = 40; break;
            case "Hard": emptyCells = 50; break;
            case "Expert": emptyCells = 60; break;
            default: emptyCells = 30;
        }

        SudokuGenerator.SudokuPuzzle puzzle = generator.generate(emptyCells);
        sudokuBoard.setPuzzle(puzzle.numbers, puzzle.fixed);
    }
}