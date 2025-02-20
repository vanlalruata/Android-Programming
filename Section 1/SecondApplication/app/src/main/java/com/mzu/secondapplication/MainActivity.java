package com.mzu.secondapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.net.Uri;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText etFullName, etDob, etAddress;
    private RadioGroup radioGenderGroup;
    private Button btnSubmit;


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


        //Open up Calendar
        etDob.setOnClickListener(v -> showDatePicker());


        //Button Action
        btnSubmit.setOnClickListener(v -> sendToWhatsApp());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
           String dob = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
           etDob.setText(dob);

        }, year, month, day);
        datePickerDialog.show();
    }

    private void sendToWhatsApp() {
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