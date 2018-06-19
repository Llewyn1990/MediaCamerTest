package com.plane.llewy.mediacamertest.util;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.Surface;
import android.view.View;

import java.io.File;
import java.io.IOException;

public class PlayVideoThread extends Thread {

    private MediaPlayer mMediaPlayer;
    private String mFilePath;
    private Surface mSurface;
    public PlayVideoThread(MediaPlayer mediaPlayer, String filePath, Surface surface) {
        mMediaPlayer = mediaPlayer;
        mFilePath = filePath;
        mSurface = surface;
    }
    @Override
    public void run() {
        File file = new File(mFilePath);
        if(file.exists()) {
            try {
                mMediaPlayer.setDataSource(file.getAbsolutePath());
                mMediaPlayer.setSurface(mSurface);
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mMediaPlayer.start();
                    }
                });
                mMediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
