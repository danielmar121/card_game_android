package com.daniel.card_game_android;

import android.content.Intent;
public class Player {
    private String playerImage;
    private int playerScore;
    private String playerGender;
    private String playerName;
    private double playerLongitude;
    private double playerLatitude;
    public static final String gender = "GENDER";
    public static final String name = "NAME";

    public Player(){

    }

    public String getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(String playerImage) {
        this.playerImage = playerImage;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public String getPlayerGender() {
        return playerGender;
    }

    public void setPlayerGender(Intent intent) {
        this.playerGender = intent.getStringExtra(gender);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(Intent intent) {
        this.playerName = intent.getStringExtra(name);
    }

    public double getPlayerLongitude() {
        return playerLongitude;
    }

    public void setPlayerLongitude(double playerLongitude) {
        this.playerLongitude = playerLongitude;
    }

    public double getPlayerLatitude() {
        return playerLatitude;
    }

    public void setPlayerLatitude(double playerLatitude) {
        this.playerLatitude = playerLatitude;
    }
}
