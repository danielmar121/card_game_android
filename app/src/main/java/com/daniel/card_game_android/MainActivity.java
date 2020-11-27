package com.daniel.card_game_android;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActivityBase {
    public static final String gander = "GANDER";
    public static final String name = "NAME";

    private final int SECOND = 1000;
    private final int NUMBER_OF_CARDS = 26;
    Deck warDeck;
    String playerGander;
    private int playerScoreA = 0, playerScoreB = 0;
    private String playerName;
    private Timer carousalTimer;
    private MainViewController mainViewController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewController = new MainViewController(this);

        initViews();

        isDoubleBackPressToClose = true;
    }

    private void initViews() {
        initDeck();
        setPlayerGanderAndName();
    }

    public void initDeck() {
        warDeck = new Deck();
        for (int i = 1; i <= NUMBER_OF_CARDS; i++) {
            warDeck.addCard("card_" + i, i);
        }
        warDeck.shuffleCards();
    }

    private void setPlayerGanderAndName() {
        Intent intent = getIntent();
        playerGander = intent.getStringExtra(gander);
        playerName = intent.getStringExtra(name);


        if (playerGander.matches("girl")) {
            int playerGirl = this.getResources().getIdentifier("player_girl", "drawable", this.getPackageName());
            Drawable girlImage = getDrawable(playerGirl);
            mainViewController.setPlayerImage(girlImage);
            mainViewController.setPlayerCardImage(girlImage);
        }
    }

    private void playTurn() {
        Card playerCardA = warDeck.getCard();
        Card playerCardB = warDeck.getCard();
        if (playerCardA != null && playerCardB != null) {
            setNewCardsImage(playerCardA.getImageName(), playerCardB.getImageName());

            setScore(playerCardA, playerCardB);

            setProgress();

            if (warDeck.isEmpty()) {
                displayWinner();
            }
        }


    }

    private void setNewCardsImage(String imageNameA, String imageNameB) {
        int playerDrawableA = this.getResources().getIdentifier(imageNameA, "drawable", this.getPackageName());
        int playerDrawableB = this.getResources().getIdentifier(imageNameB, "drawable", this.getPackageName());

        mainViewController.setPlayerCardImage(getDrawable(playerDrawableA));
        mainViewController.setComputerCardImage(getDrawable(playerDrawableB));
    }

    private void setScore(Card playerCardA, Card playerCardB) {
        if (playerCardA.isStronger(playerCardB)) {
            playerScoreA++;
            mainViewController.setPlayerScore(playerScoreA + "");
        } else {
            playerScoreB++;
            mainViewController.setComputerScore(playerScoreB + "");
        }
    }

    private void setProgress() {
        double sizeOfBar = 100 / (NUMBER_OF_CARDS / 2.0);
        double totalTurns = playerScoreA + playerScoreB;
        int gameProgress = (int) (totalTurns * sizeOfBar);
        mainViewController.setProgressBar(gameProgress);
    }

    private void displayWinner() {
        Intent intent = new Intent(this, WinnerPage.class);
        intent.putExtra(WinnerPage.playerScoreA, playerScoreA);
        intent.putExtra(WinnerPage.playerScoreB, playerScoreB);
        intent.putExtra(MainActivity.gander, playerGander);
        intent.putExtra(name, playerName);
        startActivity(intent);
        finish();
    }

    public void startCounting() {
        Log.d("pttt", "startCounting");
        carousalTimer = new Timer();
        carousalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        playTurn();
                        mainViewController.playSound();
                    }
                });
            }
        }, 0, SECOND);
    }

    private void stopCounting() {
        carousalTimer.cancel();
    }

    @Override
    protected void onStart() {
        Log.d("pttt", "onStart");
        if (carousalTimer != null) {
            startCounting();
            mainViewController.playSound();
        }
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("pttt", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("pttt", "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("pttt", "onStop");
        if (carousalTimer != null) {
            stopCounting();
            mainViewController.stopSound();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("pttt", "onDestroy");
        super.onDestroy();
    }
}