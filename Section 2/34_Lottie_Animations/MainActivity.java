package com.mzu.lottiedemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private LottieAnimationView lottieAnimation;
    private MaterialButton btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lottieAnimation = findViewById(R.id.lottieAnimation);
        btnPlay = findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(v -> {
            lottieAnimation.playAnimation();
        });
    }
}
