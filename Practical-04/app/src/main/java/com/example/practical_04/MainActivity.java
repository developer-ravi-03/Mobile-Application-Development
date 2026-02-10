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

    //Send Image Logic
    public void image(View view)
    {
        BitmapDrawable drawable = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.profilecard);

        if (drawable == null) {
            Toast.makeText(this, "Image not found", Toast.LENGTH_SHORT).show();
            return;
        }

        Bitmap bitmap = drawable.getBitmap();

        String path = MediaStore.Images.Media.insertImage(
                getContentResolver(),
                bitmap,
                "SharedImage",
                null
        );

        if (path == null) {
            Toast.makeText(this, "Image not shared", Toast.LENGTH_SHORT).show();
            return;
        }

        Uri imageUri = Uri.parse(path);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);

        startActivity(Intent.createChooser(intent, "Share Image"));
    }
}