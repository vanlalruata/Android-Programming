package com.example.registration;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etFullName, etDob, etFather, etMother, etPresent, etCorresponding, etContact, etEmail;
    RadioGroup rgGender;
    Button btnSubmit, btnView;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        db = new DatabaseHelper(this);

        etFullName = findViewById(R.id.etFullName);
        etDob = findViewById(R.id.etDob);
        etFather = findViewById(R.id.etFather);
        etMother = findViewById(R.id.etMother);
        etPresent = findViewById(R.id.etPresent);
        etCorresponding = findViewById(R.id.etCorresponding);
        etContact = findViewById(R.id.etContact);
        etEmail = findViewById(R.id.etEmail);
        rgGender = findViewById(R.id.rgGender);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnView = findViewById(R.id.btnView);

        etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudentListActivity.class));
            }
        });

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, month1, dayOfMonth) -> {
                    String date = String.format("%02d/%02d/%04d", dayOfMonth, month1 + 1, year1);
                    etDob.setText(date);
                }, year, month, day);
        datePickerDialog.show();
    }

    private void submitForm() {
        String fullName = etFullName.getText().toString().trim();
        String dob = etDob.getText().toString().trim();
        String father = etFather.getText().toString().trim();
        String mother = etMother.getText().toString().trim();
        String present = etPresent.getText().toString().trim();
        String corresponding = etCorresponding.getText().toString().trim();
        String contact = etContact.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        int selectedGenderId = rgGender.getCheckedRadioButtonId();
        String gender = "";
        if (selectedGenderId != -1) {
            RadioButton rb = findViewById(selectedGenderId);
            gender = rb.getText().toString();
        }

        if (TextUtils.isEmpty(fullName)) {
            etFullName.setError("Full Name required");
            return;
        }
        if (TextUtils.isEmpty(dob)) {
            etDob.setError("Date of Birth required");
            return;
        }
        if (TextUtils.isEmpty(gender)) {
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(father)) {
            etFather.setError("Father Name required");
            return;
        }
        if (TextUtils.isEmpty(mother)) {
            etMother.setError("Mother Name required");
            return;
        }
        if (TextUtils.isEmpty(present)) {
            etPresent.setError("Present Address required");
            return;
        }
        if (TextUtils.isEmpty(corresponding)) {
            etCorresponding.setError("Corresponding Address required");
            return;
        }
        if (TextUtils.isEmpty(contact) || contact.length() < 10) {
            etContact.setError("Valid Contact Number required");
            return;
        }
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Valid Email required");
            return;
        }

        Student student = new Student(fullName, dob, gender, father, mother, present, corresponding, contact, email);
        long id = db.insertStudent(student);
        if (id > 0) {
            Toast.makeText(this, "Student Registered!", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        etFullName.setText("");
        etDob.setText("");
        etFather.setText("");
        etMother.setText("");
        etPresent.setText("");
        etCorresponding.setText("");
        etContact.setText("");
        etEmail.setText("");
        rgGender.clearCheck();
    }
}