package com.example.practical_04;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private Uri imageUri;
//    private Intent intent;

    public void Click(View view)
    {
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://chatgpt.com/"));
        startActivity(intent);
    }

    //send Text
    private EditText et;
    public void Asend(View view ){
        //catch text
        et=findViewById(R.id.EditText1);
        String userInput=et.getText().toString();

        //send Text
        Intent sendIntent=new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,userInput);
        sendIntent.setType("text/plain");
        Intent shareIntent=Intent.createChooser(sendIntent,null);
        startActivity(shareIntent);
    }
}