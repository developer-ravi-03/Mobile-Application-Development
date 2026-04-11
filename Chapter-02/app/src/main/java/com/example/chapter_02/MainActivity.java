package com.example.chapter_02;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

package com.example.notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {
    Button btnNotify;
    String CHANNEL_ID = "app8_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotify = findViewById(R.id.button);

        // Create Notification Channel (required for Android 8+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "App8 Notification Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager =
                    getSystemService(NotificationManager.class);

            manager.createNotificationChannel(channel);
        }

        btnNotify.setOnClickListener(v -> {

            // Step 1: Create Notification Builder
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this, CHANNEL_ID);

            // Step 2: Set Notification Properties
            builder.setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("App8 Notification")
                    .setContentText("Hello! This is notification from App8")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);

            // Step 3: Attach Action
            Intent intent = new Intent(this, MainActivity.class);

            PendingIntent pendingIntent =
                    PendingIntent.getActivity(
                            this,
                            0,
                            intent,
                            PendingIntent.FLAG_IMMUTABLE
                    );

            builder.setContentIntent(pendingIntent);

            // Step 4: Issue Notification
            NotificationManager manager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            manager.notify(1, builder.build());
        });
    }
}