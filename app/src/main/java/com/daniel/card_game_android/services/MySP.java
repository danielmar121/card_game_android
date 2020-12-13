package com.daniel.card_game_android.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.daniel.card_game_android.utils.Constants;

public class MySP {
    private static MySP instance;
    private SharedPreferences prefs;

    private MySP(Context context) {
        prefs = context.getSharedPreferences(Constants.MY_SP, Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new MySP(context.getApplicationContext());
        }
    }

    public static MySP getInstance() {
        return instance;
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String def) {
        return prefs.getString(key, def);
    }

    public void removeKey(String key) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply();
    }
}
