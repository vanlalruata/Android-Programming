package com.mzu.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;
    private String currentNumber = "";
    private String operator = "";
    private double firstOperand = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
    }

    public void onClickNumber(View view) {
        Button button = (Button) view;
        currentNumber += button.getText().toString();
        tvResult.setText(currentNumber);
    }

    public void onClickOperator(View view) {
        Button button = (Button) view;
        if (!currentNumber.isEmpty()) {
            firstOperand = Double.parseDouble(currentNumber);
            operator = button.getText().toString();
            currentNumber = "";
        }
    }

    public void onClickEquals(View view) {
        if (!currentNumber.isEmpty() && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentNumber);
            double result = 0;
            switch (operator) {
                case "+": result = firstOperand + secondOperand; break;
                case "-": result = firstOperand - secondOperand; break;
                case "*": result = firstOperand * secondOperand; break;
                case "/": result = firstOperand / secondOperand; break;
            }
            tvResult.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
            operator = "";
        }
    }

    public void onClickClear(View view) {
        currentNumber = "";
        operator = "";
        firstOperand = 0;
        tvResult.setText("0");
    }
}
