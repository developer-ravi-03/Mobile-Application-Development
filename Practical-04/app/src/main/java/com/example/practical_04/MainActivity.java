package com.example.practical_04;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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
        if(userInput.isEmpty()){
            Toast.makeText(this, "Please Enter Message", Toast.LENGTH_SHORT).show();
            return;
        }
        //send Text
        Intent sendIntent=new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,userInput);
        sendIntent.setType("text/plain");
        Intent shareIntent=Intent.createChooser(sendIntent,null);
        startActivity(shareIntent);
    }

//    Send Image Logic
//    It is the id of Image not count
    private static final int PICK_IMAGE = 100;
    public void image(View view)
    {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //it Calls Parent class
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {

            Uri imageUri = data.getData();

            if (imageUri == null) {
                Toast.makeText(this, "Image not selected", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, imageUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(intent, "Share Image"));
        }
    }
}