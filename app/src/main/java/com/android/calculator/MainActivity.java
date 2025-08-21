package com.android.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.imageview.ShapeableImageView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    

    String expFormat = "";
    private Button numberOne,numberTwo,numberThree,numberFour,numberFive,numberSix,numberSeven,
            numberEight,numberNine,numberZero, point, minor, plus, multiplicate, divide,equal,erase;
    private ShapeableImageView backspace;
    private TextView expression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initComp();
        numberZero.setOnClickListener(this);
        numberOne.setOnClickListener(this);
        numberTwo.setOnClickListener(this);
        numberThree.setOnClickListener(this);
        numberFour.setOnClickListener(this);
        numberFive.setOnClickListener(this);
        numberSix.setOnClickListener(this);
        numberSeven.setOnClickListener(this);
        numberEight.setOnClickListener(this);
        numberNine.setOnClickListener(this);
        point.setOnClickListener(this);
        plus.setOnClickListener(this);
        divide.setOnClickListener(this);
        multiplicate.setOnClickListener(this);
        minor.setOnClickListener(this);


        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.setText("");
                expFormat = "";
            }
        });
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView exp = findViewById(R.id.txt_Expression);
                String string = exp.getText().toString();
                if (!string.isEmpty()){
                    int expNum = string.length()-1;
                    String txtExp = string.substring(0,expNum);
                    exp.setText(txtExp);
                    expFormat = txtExp;
                }
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = expression.getText().toString();
                if (text.isEmpty())return;
                String textLast = text.substring(text.length()-1);
                String [] symbols = new String[]{"\u002B", "\u2212", "\u00D7","\u00F7"};
                for(String symbol : symbols){
                    if(symbol.equals(textLast))return;
                }
                for(char c : text.toCharArray()){
                    if (c == '\u00F7'){
                        text = text.replace('\u00F7', '/');
                    }
                    else if(c =='\u00D7'){
                        text = text.replace('\u00D7', '*');
                    }
                    else if(c == '\u2212'){
                        text = text.replace('\u2212', '-');
                    }
                }
                Expression express = new ExpressionBuilder(text).build();
                double res = express.evaluate();
                String result = String.valueOf(res);
                String toRound = (result.substring(result.length()-1));
                if(toRound.equals("0")){
                    Long longResult = (long) res;
                    expression.setText((CharSequence)String.valueOf(longResult));
                    expFormat = String.valueOf(expression.getText());
                }
                else{
                expression.setText((CharSequence)String.valueOf(res));
                expFormat = String.valueOf(expression.getText());
                    }
                }
        });
    }

    private void initComp(){
        numberOne = findViewById(R.id.number_one);
        numberTwo= findViewById(R.id.number_two);
        numberThree = findViewById(R.id.number_three);
        numberFour = findViewById(R.id.number_four);
        numberFive = findViewById(R.id.number_five);
        numberSix = findViewById(R.id.number_six);
        numberSeven = findViewById(R.id.number_seven);
        numberEight = findViewById(R.id.number_eight);
        numberNine = findViewById(R.id.number_nine);
        numberZero = findViewById(R.id.number_zero);
        minor = findViewById(R.id.bt_minor);
        multiplicate = findViewById(R.id.bt_multiplicate);
        divide = findViewById(R.id.bt_divide);
        point = findViewById(R.id.bt_point);
        erase = findViewById(R.id.bt_erase);
        equal = findViewById(R.id.bt_equal);
        backspace = findViewById(R.id.bt_back);
        plus = findViewById(R.id.bt_plus);
        expression = findViewById(R.id.txt_Expression);

    }
    private String formatExp(String string){
        expFormat+=string;
        int len = expFormat.length();
        if(len == 1){
            switch(expFormat){
                case"+":
                    expFormat ="";
                case"*":
                    expFormat="";
                case"/":
                    expFormat="";
            }
        }
        expression.setText(expFormat);
        if(len>1){
            String [] symbols = new String[]{"\u002B", "\u2212", "\u00D7","\u00F7"};
            String subExp = expFormat.substring(len-2, len-1);
            String subExpLast = expFormat.substring(len-1, len);
            for(String symbol : symbols){
                if(symbol.equals(subExpLast)){
                    for(String sym : symbols){
                        if(sym.equals(subExp)){
                            expFormat=expFormat.substring(0,len-2);
                            expFormat=expFormat+subExpLast;
                        }
                    }
                }
            }
        }
        expression.setText(expFormat);
        return expFormat;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.number_zero:
                formatExp("0" );
                break;
            case R.id.number_one:
                formatExp("1" );
                break;
            case R.id.number_two:
                formatExp("2");
                break;
            case R.id.number_three:
                formatExp("3");
                break;
            case R.id.number_four:
                formatExp("4");
                break;
            case R.id.number_five:
                formatExp("5");
                break;
            case R.id.number_six:
                formatExp("6");
                break;
            case R.id.number_seven:
                formatExp("7");
                break;
            case R.id.number_eight:
                formatExp("8");
                break;
            case R.id.number_nine:
                formatExp("9");
                break;
            case R.id.bt_point:
                formatExp(".");
                break;
            case R.id.bt_divide:
                formatExp("\u00F7");
                break;
            case R.id.bt_minor:
                formatExp("\u2212");
                break;
            case R.id.bt_plus:
                formatExp("\u002B") ;
                break;
            case R.id.bt_multiplicate:
                formatExp("\u00D7");
                break;


        }
    }
}