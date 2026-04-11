package com.example.practical_09;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {

    private MediaPlayer player;

    // 🎵 Song list
    private int[] songs = {
            R.raw.song,
            R.raw.song2,
            R.raw.song3,
            R.raw.song4,
            R.raw.song5,
    };

    private int currentIndex = 0;

//    For Single song
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        player = MediaPlayer.create(this, R.raw.song);
//        player.setLooping(true);
//    }

//    For Multiple song
    @Override
    public void onCreate() {
        super.onCreate();
        playSong(currentIndex);
    }

    private void playSong(int index) {
        if (player != null) {
            player.release();
        }

        player = MediaPlayer.create(this, songs[index]);
        player.setLooping(true);
        player.start();
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//
//        if (intent != null) {
//            String action = intent.getAction();
//
//            if ("PLAY".equals(action)) {
//                if (player != null && !player.isPlaying()) {
//                    player.start();
//                }
//            }
//
//            else if ("PAUSE".equals(action)) {
//                if (player != null && player.isPlaying()) {
//                    player.pause();
//                }
//            }
//        }
//
//        return START_STICKY;
//    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null) {
            String action = intent.getAction();

            switch (action) {

                case "PLAY":
                    if (player != null && !player.isPlaying()) {
                        player.start();
                    }
                    break;

                case "PAUSE":
                    if (player != null && player.isPlaying()) {
                        player.pause();
                    }
                    break;

                case "NEXT":
                    currentIndex = (currentIndex + 1) % songs.length;
                    playSong(currentIndex);
                    break;

                case "PREVIOUS":
                    currentIndex = (currentIndex - 1 + songs.length) % songs.length;
                    playSong(currentIndex);
                    break;
            }
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (player != null) {
            player.release();
            player = null;
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}