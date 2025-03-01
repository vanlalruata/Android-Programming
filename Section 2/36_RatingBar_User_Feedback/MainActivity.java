package com.mzu.ratingbardemo;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    private TextView tvFeedback;
    private MaterialButton btnSubmitRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBar = findViewById(R.id.ratingBar);
        tvFeedback = findViewById(R.id.tvFeedback);
        btnSubmitRating = findViewById(R.id.btnSubmitRating);

        btnSubmitRating.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            String feedback;

            if (rating <= 1) {
                feedback = "We're sorry to hear that! 😞";
            } else if (rating <= 3) {
                feedback = "Thanks for your feedback! 😊";
            } else if (rating <= 4) {
                feedback = "Great! We appreciate your support! 👍";
            } else {
                feedback = "Awesome! Thank you so much! 🌟";
            }

            tvFeedback.setText("Feedback: " + feedback);

            Snackbar.make(v, "You rated " + rating + " stars!", Snackbar.LENGTH_LONG).show();
            Toast.makeText(MainActivity.this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
        });
    }
}
