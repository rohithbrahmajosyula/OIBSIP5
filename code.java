package com.example.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewInput;
    private String input = "";
    private String operator = "";
    private double firstNumber = Double.NaN;
    private double secondNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewInput = findViewById(R.id.textViewInput);

        int[] numberButtonIDs = new int[]{
                R.id.button0, R.id.button1, R.id.button2,
                R.id.button3, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8,
                R.id.button9
        };

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                input += button.getText().toString();
                textViewInput.setText(input);
            }
        };

        for (int id : numberButtonIDs) {
            findViewById(id).setOnClickListener(numberClickListener);
        }

        findViewById(R.id.buttonAdd).setOnClickListener(operatorClickListener);
        findViewById(R.id.buttonSubtract).setOnClickListener(operatorClickListener);
        findViewById(R.id.buttonMultiply).setOnClickListener(operatorClickListener);
        findViewById(R.id.buttonDivide).setOnClickListener(operatorClickListener);
        findViewById(R.id.buttonEquals).setOnClickListener(equalsClickListener);
        findViewById(R.id.buttonClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = "";
                operator = "";
                firstNumber = Double.NaN;
                textViewInput.setText("");
            }
        });
    }

    private View.OnClickListener operatorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            operator = button.getText().toString();
            if (!Double.isNaN(firstNumber)) {
                calculate();
            } else {
                firstNumber = Double.parseDouble(input);
            }
            input = "";
        }
    };

    private View.OnClickListener equalsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!input.isEmpty() && !Double.isNaN(firstNumber)) {
                secondNumber = Double.parseDouble(input);
                calculate();
                input = String.valueOf(firstNumber);
                textViewInput.setText(input);
                firstNumber = Double.NaN;
                operator = "";
            }
        }
    };

    private void calculate() {
        switch (operator) {
            case "+":
                firstNumber = firstNumber + secondNumber;
                break;
            case "-":
                firstNumber = firstNumber - secondNumber;
                break;
            case "*":
                firstNumber = firstNumber * secondNumber;
                break;
            case "/":
                firstNumber = firstNumber / secondNumber;
                break;
        }
    }
}
