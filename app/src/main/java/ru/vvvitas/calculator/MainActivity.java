package ru.vvvitas.calculator;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int FONT_SIZE = 42;
    private static final ArrayList<String > buttons = new ArrayList<>(Arrays.asList("+", "-", "X", ":", "%"
                                                                                  , "7", "8", "9", "<-", "C"
                                                                                  , "4", "5", "6", ".", "/-/"
                                                                                  , "1", "2", "3", "0", "="));
    TextView textView;
    LinearLayout container;
    static float arg = 0;
    static char op='.';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,1);
        container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);

        textView = new TextView (this);
        textView.setId (View.generateViewId());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, FONT_SIZE);
        textView.setTextColor(android.graphics.Color.GREEN);
        textView.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
        textView.setText("0");
        textView.setLayoutParams(layoutParams);
        container.addView(textView);

        Iterator<String> iterator = buttons.iterator();
        for(int i=1; i<5; i++){
            LinearLayout horizontalLayout = new LinearLayout(this);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
            horizontalLayout.setLayoutParams(layoutParams);
            for (int j = 1; j<=5; j++){
                Button btn = new Button(this);
                btn.setTextSize(FONT_SIZE);
                btn.setPadding(0,0,0,0);
                btn.setText(iterator.next());
                btn.setLayoutParams(layoutParams);
                btn.setOnClickListener(MainActivity.this);
                horizontalLayout.addView(btn);
            }
            container.addView(horizontalLayout);
        }
        setContentView(container);
        if(savedInstanceState != null){
            arg = savedInstanceState.getFloat("arg");
            op = savedInstanceState.getChar("op");
            textView.setText(savedInstanceState.getString("indicator"));
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("arg", arg);
        outState.putChar("op", op);
        outState.putString("indicator", (String)textView.getText());
    }

    @Override
    public void onClick(View view) {

        textView.setText(Calculator.calc(((Button)view).getText().toString(), textView.getText().toString()));

    }

}