package com.example.pract_01;

import android.os.Bundle;
import android.util.Log;
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

        Log.i("message", "i am in onCreate");
        Toast.makeText(getApplicationContext(),
                "I am in onCreate",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("message", "i am in onStart");
        Toast.makeText(getApplicationContext(),
                "I am in onStart",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("message", "i am in onResume");
        Toast.makeText(getApplicationContext(),
                "I am in onResume",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("message", "i am in onPause");
        Toast.makeText(getApplicationContext(),
                "I am in onPause",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("message", "i am in onStop");
        Toast.makeText(getApplicationContext(),
                "I am in onStop",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("message", "i am in onDestroy");
        Toast.makeText(getApplicationContext(),
                "I am in onDestroy",
                Toast.LENGTH_SHORT).show();
    }
}