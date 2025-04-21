package com.example.registration;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EditStudentActivity extends AppCompatActivity {

    EditText etFullName, etDob, etFather, etMother, etPresent, etCorresponding, etContact, etEmail;
    RadioGroup rgGender;
    Button btnUpdate;
    DatabaseHelper db;
    int studentId;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

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
        btnUpdate = findViewById(R.id.btnUpdate);

        studentId = getIntent().getIntExtra("student_id", -1);
        if (studentId != -1) {
            student = db.getStudent(studentId);
            if (student != null) {
                etFullName.setText(student.getFullName());
                etDob.setText(student.getDob());
                etFather.setText(student.getFatherName());
                etMother.setText(student.getMotherName());
                etPresent.setText(student.getPresentAddress());
                etCorresponding.setText(student.getCorrespondingAddress());
                etContact.setText(student.getContact());
                etEmail.setText(student.getEmail());
                if (student.getGender().equalsIgnoreCase("Male")) {
                    rgGender.check(R.id.rbMale);
                } else if (student.getGender().equalsIgnoreCase("Female")) {
                    rgGender.check(R.id.rbFemale);
                } else {
                    rgGender.check(R.id.rbOther);
                }
            }
        }

        etDob.setOnClickListener(v -> showDatePicker());

        btnUpdate.setOnClickListener(v -> updateStudent());
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

    private void updateStudent() {
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

        student.setFullName(fullName);
        student.setDob(dob);
        student.setGender(gender);
        student.setFatherName(father);
        student.setMotherName(mother);
        student.setPresentAddress(present);
        student.setCorrespondingAddress(corresponding);
        student.setContact(contact);
        student.setEmail(email);

        int rows = db.updateStudent(student);
        if (rows > 0) {
            Toast.makeText(this, "Student Updated!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error updating data", Toast.LENGTH_SHORT).show();
        }
    }
}
