package com.daniel.card_game_android;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound {
    private MediaPlayer mp;

    public Sound(Context context, int soundId) {
        setSound(context, soundId);
    }

    public void setSound(Context context, int soundId) {
        mp = MediaPlayer.create(context, soundId);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                mp = null;
            }
        });
    }

    public void playSound() {
        if (!mp.isPlaying()) {
            mp.start();
        }
    }

    public void stopSound() {
        mp.stop();
    }
}
