package com.mzu.dialogdemo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton btnShowDialog = findViewById(R.id.btnShowDialog);
        btnShowDialog.setOnClickListener(v -> showDialog());
    }

    private void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);

        ImageView imageView = dialog.findViewById(R.id.dialogImage);
        imageView.setImageResource(R.drawable.sample_image);

        dialog.show();
    }
}
