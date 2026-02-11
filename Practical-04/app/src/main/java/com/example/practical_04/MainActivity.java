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

import java.util.ArrayList;

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

//        allow Choose multiple
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //it Calls Parent class
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {

//            Uri imageUri = data.getData();
            ArrayList<Uri> imageUris = new ArrayList<>();

//            if there is multiple images are choosen then return getClipdata
            if(data.getClipData()!=null){
                int count=data.getClipData().getItemCount();
                for(int i=0;i<count;i++){
                    Uri imageUri=data.getClipData().getItemAt(i).getUri();
                    imageUris.add(imageUri);
                }
            }else if(data.getData()!=null){
//                if selected image is only one
                imageUris.add(data.getData());
            }

//            Checking imageUri is null or Not
            if (imageUris == null) {
                Toast.makeText(this, "Image not selected", Toast.LENGTH_SHORT).show();
                return;
            }

//            Creating intent for multiple Images
            Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            intent.setType("image/*");

            //create bundle
            Bundle bundle=new Bundle();
            bundle.putParcelableArrayList(Intent.EXTRA_STREAM,imageUris);

//            Pass Bundle to the intent
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

//            Start Activity
            startActivity(Intent.createChooser(intent, "Share Images"));
        }
    }
}