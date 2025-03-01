package com.mzu.biometricsecuredb;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private TextView tvResults;
    private static final String DATABASE_PASSWORD = "SecureBiometricKey!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResults = findViewById(R.id.tvResults);
        Button btnAuthenticate = findViewById(R.id.btnAuthenticate);

        btnAuthenticate.setOnClickListener(v -> authenticateUser());
    }

    private void authenticateUser() {
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                showBiometricPrompt();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                tvResults.setText("Biometric authentication is not available.");
                break;
        }
    }

    private void showBiometricPrompt() {
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                accessDatabase();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                tvResults.setText("Authentication failed. Try again.");
            }
        });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Secure Database Access")
                .setSubtitle("Use your fingerprint or face to access the database")
                .setDeviceCredentialAllowed(true)  // Allow PIN as fallback
                .build();

        biometricPrompt.authenticate(promptInfo);
    }

    private void accessDatabase() {
        databaseHelper = new DatabaseHelper(this, DATABASE_PASSWORD);
        Cursor cursor = databaseHelper.getAllStudents();
        StringBuilder result = new StringBuilder();
        if (cursor.getCount() == 0) {
            result.append("No students found.");
        } else {
            while (cursor.moveToNext()) {
                result.append("ID: ").append(cursor.getInt(0))
                      .append(", Name: ").append(cursor.getString(1))
                      .append(", Age: ").append(cursor.getInt(2))
                      .append("\n");
            }
        }
        tvResults.setText(result.toString());
        cursor.close();
    }
}
