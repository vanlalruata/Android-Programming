package com.mzu.calculator;

import android.os.Bundle;

import androidx.cardview.widget.CardView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView answerTextView,equationTextView;

    String answerStr="0",equationStr="",operand2Str="";

    boolean isOperandThere=true;

    boolean isOperator=false;

    char operator;

    double operand1,operand2;

    boolean isFirstOperation=true;

    DecimalFormat decimalFormat;

    boolean isEqualPressed=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        answerTextView=(TextView) findViewById(R.id.answerTextView);
        equationTextView=(TextView) findViewById(R.id.equationTextView);

        decimalFormat=new DecimalFormat("0.00");

        answerTextView.setText(answerStr);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void performOperation(){
        switch (operator){
            case '+':
                answerStr=decimalFormat.format(operand1+operand2);
                answerTextView.setText(answerStr);
                isFirstOperation=false;
                break;

            case '-':
                answerStr=decimalFormat.format(operand1-operand2);
                answerTextView.setText(answerStr);
                isFirstOperation=false;
                break;
            case 'x':
                answerStr=decimalFormat.format(operand1*operand2);
                answerTextView.setText(answerStr);
                isFirstOperation=false;
                break;
            case '/':
                if(operand1!=0){
                    answerStr=decimalFormat.format(operand1/operand2);
                    answerTextView.setText(answerStr);
                    isFirstOperation=false;
                }else{
                    answerStr="0";
                    equationStr="";
                    operand2Str="";
                    isOperandThere=false;
                    isOperator=false;
                    isFirstOperation=true;
                    equationTextView.setText(equationStr);
                    answerTextView.setText(answerStr);
                }

                break;

            case '%':
                answerStr=decimalFormat.format(operand1%operand2);
                answerTextView.setText(answerStr);
                isFirstOperation=false;
                break;

            default:

        }

    }


    private void setOperator(char operatorValue){
        if(isOperandThere && !isEqualPressed){
            isOperandThere=false;
            operator=operatorValue;

            if(isFirstOperation){
                operand1=Double.parseDouble(equationStr);
            }else{
                operand1=Double.parseDouble(answerStr);
            }


            equationStr=equationStr+String.valueOf(operator);
            equationTextView.setText(equationStr);
            operand2Str="";

        }
    }



    private void setNumber(String value){


        try{
            if(isEqualPressed){
                isEqualPressed=false;
                operand1=0.0;
                answerStr="0";
                equationStr="";
                operand2Str="";
                isOperandThere=false;
                isOperator=false;
                isFirstOperation=true;
                equationTextView.setText(equationStr);
                answerTextView.setText(answerStr);
                equationTextView.setVisibility(View.VISIBLE);
            }

            operand2=Double.parseDouble(operand2Str+value);
            operand2Str=operand2Str+value;
            equationStr=equationStr+value;
            equationTextView.setText(equationStr);

            isOperandThere=true;


            performOperation();
        }catch (Exception e){

        }


    }



    public void Number0(View view){


        setNumber("0");



    }

    public void Number1(View view){
        setNumber("1");
    }

    public void Number2(View view){
        setNumber("2");
    }

    public void Number3(View view){
        setNumber("3");
    }

    public void Number4(View view){
        setNumber("4");
    }

    public void Number5(View view){
        setNumber("5");
    }

    public void Number6(View view){
        setNumber("6");
    }

    public void Number7(View view){
        setNumber("7");
    }

    public void Number8(View view){
        setNumber("8");
    }

    public void Number9(View view){
        setNumber("9");
    }

    public void ClearButton(View view){
        operand1=0.0;
        answerStr="0";
        equationStr="";
        operand2Str="";
        isOperandThere=false;
        isOperator=false;
        isFirstOperation=true;
        equationTextView.setText(equationStr);
        answerTextView.setText(answerStr);
        operator='+';
    }

    public void DivideButton(View view){
        setOperator('/');
    }

    public void MultiplicationButton(View view){
        setOperator('x');
    }


    public void MinusButton(View view){
        setOperator('-');
    }


    public void AddButton(View view){
        setOperator('+');
    }

    public void ModButton(View view){
        setOperator('%');
    }

    public void DotButton(View view){
        setNumber(".");
    }

    public void EqualButton(View view){
        equationTextView.setVisibility(View.GONE);
        isEqualPressed=true;
    }
}