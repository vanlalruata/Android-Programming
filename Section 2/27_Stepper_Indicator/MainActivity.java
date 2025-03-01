package com.mzu.stepperdemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.github.badoualy.stepperindicator.StepperIndicator;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        StepperIndicator stepper = findViewById(R.id.stepper);
        stepper.attachTo(viewPager);
    }
}
