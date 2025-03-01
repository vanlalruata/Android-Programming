package com.mzu.sqlitepdfexport;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_STORAGE_PERMISSION = 100;
    private DatabaseHelper databaseHelper;
    private EditText etName, etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnExportPDF = findViewById(R.id.btnExportPDF);

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String ageText = etAge.getText().toString();
            if (name.isEmpty() || ageText.isEmpty()) {
                Toast.makeText(MainActivity.this, "Enter all details", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageText);
            boolean success = databaseHelper.insertStudent(name, age);
            if (success) {
                Toast.makeText(MainActivity.this, "Student Added!", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etAge.setText("");
            }
        });

        btnExportPDF.setOnClickListener(v -> checkPermissionAndGeneratePDF());
    }

    private void checkPermissionAndGeneratePDF() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            generatePDF();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
        }
    }

    private void generatePDF() {
        try {
            File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "StudentReport.pdf");
            PdfWriter writer = new PdfWriter(pdfFile);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Student Report\n\n"));
            List<Student> students = databaseHelper.getAllStudents();
            for (Student student : students) {
                document.add(new Paragraph("ID: " + student.getId() + ", Name: " + student.getName() + ", Age: " + student.getAge()));
            }

            document.close();
            Toast.makeText(this, "PDF Saved in Downloads!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "PDF Export Failed!", Toast.LENGTH_SHORT).show();
        }
    }
}
