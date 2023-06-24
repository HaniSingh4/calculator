package com.example.calculaternew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result, solution;
    MaterialButton btnc, btno, btncl;
    MaterialButton btnd, btnm, btna, btns, btne;
    MaterialButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    MaterialButton btnac, btnDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.output);
        solution = findViewById(R.id.solution);
        assignId(btnc, R.id.btn_c);
        assignId(btno, R.id.btn_open);
        assignId(btncl, R.id.btn_close);
        assignId(btnd, R.id.btn_div);
        assignId(btn7, R.id.btn_7);
        assignId(btn8, R.id.btn_8);
        assignId(btn9, R.id.btn_9);
        assignId(btnm, R.id.btn_mul);
        assignId(btna, R.id.btn_add);
        assignId(btns, R.id.btn_sub);
        assignId(btne, R.id.btn_equal);
        assignId(btn1, R.id.btn_1);
        assignId(btn0, R.id.btn_0);
        assignId(btn2, R.id.btn_2);
        assignId(btn3, R.id.btn_3);
        assignId(btn4, R.id.btn_4);
        assignId(btn5, R.id.btn_5);
        assignId(btn6, R.id.btn_6);
        assignId(btnac, R.id.btn_del);
        assignId(btnDot, R.id.btn_l);

    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener((View.OnClickListener) this);
    }


    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution.getText().toString();
        if (buttonText.equals("Ac")) {
            solution.setText("");
            result.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solution.setText(result.getText().toString());
            return;
        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }

        solution.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("error")) {
            result.setText(finalResult);
        }


    }

    String getResult(String r) {
        try {
            Context context = Context.enter();
            Scriptable scriptable = context.initStandardObjects();
            String finalresult = context.evaluateString(scriptable, r, "javascript", 1, null).toString();
            return finalresult;
        } catch (Exception e) {
            return "error";
        }
    }

}
