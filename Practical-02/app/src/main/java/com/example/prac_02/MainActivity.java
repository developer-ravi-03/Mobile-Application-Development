package com.example.prac_02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2;
    Button add, sub, mul, div;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);

        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);

        tv = findViewById(R.id.tv);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int f = Integer.parseInt(et1.getText().toString());
                int s = Integer.parseInt(et2.getText().toString());
                tv.setText("Result=" + (f + s));
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int f = Integer.parseInt(et1.getText().toString());
                int s = Integer.parseInt(et2.getText().toString());
                tv.setText("Result=" + (f - s));
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int f = Integer.parseInt(et1.getText().toString());
                int s = Integer.parseInt(et2.getText().toString());
                tv.setText("Result=" + (f * s));
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int f = Integer.parseInt(et1.getText().toString());
                int s = Integer.parseInt(et2.getText().toString());
                tv.setText("Result=" + (f / s));
            }
        });
    }
}
