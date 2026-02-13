package com.example.chapter_01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    Button mul;
    EditText num;
    TextView result2;
    public void calcualte(View view){
        num=findViewById(R.id.num);
        String value=num.getText().toString();
        int number=Integer.parseInt(value);
//        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
        result2=findViewById(R.id.result2);
        StringBuilder table=new StringBuilder();
        for (int i=1;i<=10;i++){
            table.append(number);
            table.append(" x ");
            table.append(i);
            table.append(" = ");
            table.append(number*i);
            table.append("\n");
        }
        result2.setText(table);
    }
}