package com.mzu.shimmerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.shimmer.ShimmerFrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ShimmerFrameLayout shimmerLayout;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shimmerLayout = findViewById(R.id.shimmerLayout);
        tvData = findViewById(R.id.tvData);

        new Handler().postDelayed(() -> {
            shimmerLayout.stopShimmer();
            shimmerLayout.setVisibility(View.GONE);
            tvData.setVisibility(View.VISIBLE);
            tvData.setText("Data Loaded Successfully!");
        }, 3000); // Simulates loading for 3 seconds
    }
}
