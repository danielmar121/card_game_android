package com.daniel.card_game_android;

public class Player {
    private String playerImage;
    private String playerName;
    private int playerScore;
    private double playerLongitude;
    private double playerLatitude;

    public Player() {
    }

    public Player(String playerImage, int playerScore, String playerName, double playerLongitude, double playerLatitude) {
        this.playerImage = playerImage;
        this.playerScore = playerScore;
        this.playerName = playerName;
        this.playerLongitude = playerLongitude;
        this.playerLatitude = playerLatitude;
    }

    public String getPlayerImage() {
        return playerImage;
    }

    public Player setPlayerImage(String playerImage) {
        this.playerImage = playerImage;
        return this;

    }

    public int getPlayerScore() {
        return playerScore;
    }

    public Player setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
        return this;

    }

    public String getPlayerName() {
        return playerName;
    }

    public Player setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public double getPlayerLongitude() {
        return playerLongitude;
    }

    public Player setPlayerLongitude(double playerLongitude) {
        this.playerLongitude = playerLongitude;
        return this;

    }

    public double getPlayerLatitude() {
        return playerLatitude;
    }

    public Player setPlayerLatitude(double playerLatitude) {
        this.playerLatitude = playerLatitude;
        return this;

    }

    public void addScore() {
        playerScore++;
    }
}
