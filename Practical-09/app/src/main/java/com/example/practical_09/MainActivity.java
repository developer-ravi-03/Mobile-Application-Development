package com.example.practical_09;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button playBtn, pauseBtn,nextBtn, prevBtn;
    SeekBar volumeBar;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = findViewById(R.id.btnPlay);
        pauseBtn = findViewById(R.id.btnPause);
        volumeBar = findViewById(R.id.volumeSeekBar);
        nextBtn = findViewById(R.id.btnNext);
        prevBtn = findViewById(R.id.btnPrev);

        // Volume Control
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        volumeBar.setMax(maxVolume);
        volumeBar.setProgress(currentVolume);

        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // PLAY
        playBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MusicService.class);
            intent.setAction("PLAY");
            startService(intent);
        });

        // PAUSE
        pauseBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MusicService.class);
            intent.setAction("PAUSE");
            startService(intent);
        });

        // NEXT
        nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MusicService.class);
            intent.setAction("NEXT");
            startService(intent);
        });

        // PREVIOUS
        prevBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MusicService.class);
            intent.setAction("PREVIOUS");
            startService(intent);
        });
    }
}