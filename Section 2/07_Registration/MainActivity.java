package com.mzu.registrationform;

import android.os.Bundle;
import android.view.View;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText etFullName, etEmail;
    private MaterialButton btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            String name = etFullName.getText().toString();
            String email = etEmail.getText().toString();

            if (name.isEmpty() || email.isEmpty()) {
                Snackbar.make(v, "Please enter all details!", Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(v, "Registered Successfully!\nName: " + name + "\nEmail: " + email, Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
