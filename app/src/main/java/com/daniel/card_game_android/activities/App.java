package com.daniel.card_game_android.activities;

import android.app.Application;

import com.daniel.card_game_android.services.MySP;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MySP.init(this);
    }
}
