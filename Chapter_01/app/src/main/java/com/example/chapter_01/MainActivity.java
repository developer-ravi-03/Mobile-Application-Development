package com.example.chapter_01;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView textt,result;
    EditText n1,n2;
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
        Toast.makeText(this, "Welcome to my first App", Toast.LENGTH_SHORT).show();
        textt=findViewById(R.id.text);
        textt.setText("Good Morning!");

    }
    public void calculate(View view){
        n1=findViewById(R.id.n1);
        String value=n1.getText().toString();
        int num1=Integer.parseInt(value);

        n2=findViewById(R.id.n2);
        String value2=n2.getText().toString();
        int num2=Integer.parseInt(value2);

        result=findViewById(R.id.result);
        result.setText("The Sum is : "+(num1+num2));
    }
}