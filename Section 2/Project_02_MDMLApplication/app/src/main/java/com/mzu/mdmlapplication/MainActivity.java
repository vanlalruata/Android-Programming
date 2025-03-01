package com.mzu.mdmlapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etFullName, etDob, etAddress;
    private RadioGroup radioGenderGroup;
    private MaterialButton btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etFullName = findViewById(R.id.etFullName);
        etDob = findViewById(R.id.etDob);
        etAddress = findViewById(R.id.etAddress);
        radioGenderGroup = findViewById(R.id.radioGenderGroup);
        btnSubmit = findViewById(R.id.btnSubmit);

        //EvenListener Call
        etDob.setOnClickListener(v -> showDatePicker());
        btnSubmit.setOnClickListener(v -> sendToWhatsApp());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, day) -> {
            String dob = day + "/" + (month + 1) + "/" + year;
            etDob.setText(dob);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private  void sendToWhatsApp() {
        String fullName = etFullName.getText().toString().trim();
        String dob = etDob.getText().toString().trim();
        String address = etAddress.getText().toString().trim();

        int selectedId = radioGenderGroup.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(selectedId);
        String gender = selectedGender != null ? selectedGender.getText().toString() : "Not Selected";

        //Check whether they are empty or not
        if(fullName.isEmpty() || dob.isEmpty() || address.isEmpty() || selectedGender == null) {
            etFullName.setError(fullName.isEmpty() ? "Required" : null);
            etDob.setError(dob.isEmpty() ? "Required" : null);
            etAddress.setError(address.isEmpty() ? "Required" : null);

            return;
        }

        //Message
        String message = "Full Name: " + fullName +
                "\nDate of Birth: " + dob +
                "\nGender: " + gender +
                "\nAddress: " + address;

        //Open WhatsApp and Send
        String url ="https://wa.me/918413111111?text=" + Uri.encode(message);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}