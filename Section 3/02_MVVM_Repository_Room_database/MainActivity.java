package com.mzu.notesapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mzu.notesapp.adapter.NoteAdapter;
import com.mzu.notesapp.model.Note;
import com.mzu.notesapp.viewmodel.NoteViewModel;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        EditText etNote = findViewById(R.id.etNote);
        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            String text = etNote.getText().toString();
            if (!text.isEmpty()) {
                noteViewModel.insert(new Note(text));
                etNote.setText("");
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        NoteAdapter adapter = new NoteAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        noteViewModel.getAllNotes().observe(this, adapter::setNotes);
    }
}
