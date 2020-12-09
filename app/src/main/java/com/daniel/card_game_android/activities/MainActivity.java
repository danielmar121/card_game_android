package com.daniel.card_game_android.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.daniel.card_game_android.R;
import com.daniel.card_game_android.objects.Card;
import com.daniel.card_game_android.objects.Deck;
import com.daniel.card_game_android.objects.Player;
import com.daniel.card_game_android.services.MainViewController;
import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

import static com.daniel.card_game_android.utils.Constants.BOY_CARD;
import static com.daniel.card_game_android.utils.Constants.COMPUTER_CARD;
import static com.daniel.card_game_android.utils.Constants.COMPUTER_NAME;
import static com.daniel.card_game_android.utils.Constants.GIRL_CARD;

public class MainActivity extends ActivityBase {
    public static final String PLAYER_GENDER = "PLAYER_GENDER";
    public static final String PLAYER_NAME = "PLAYER_NAME";

    private final int SECOND = 1000;
    private final int NUMBER_OF_CARDS = 26;
    Deck warDeck;
    private Timer carousalTimer;
    private MainViewController mainViewController;
    public Player playerA;
    public Player playerB;

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
        setPlayers();
    }

    public void initDeck() {
        warDeck = new Deck();
        for (int i = 1; i <= NUMBER_OF_CARDS; i++) {
            warDeck.addCard("card_" + i, i);
        }
        warDeck.shuffleCards();
    }

    private void setPlayers() {
        Intent intent = getIntent();
        String playerGander = intent.getStringExtra(PLAYER_GENDER);
        String playerName = intent.getStringExtra(PLAYER_NAME);

        playerA = new Player().setPlayerName(playerName).setPlayerScore(0);
        playerB = new Player(COMPUTER_CARD, 0, COMPUTER_NAME, -0.142368, 51.501156);

        if (playerGander.matches("girl")) {
            playerA.setPlayerImage(GIRL_CARD);
        } else {
            playerA.setPlayerImage(BOY_CARD);
        }

        int playerImageId = this.getResources().getIdentifier(playerA.getPlayerImage(), "drawable", this.getPackageName());
        Drawable playerImage = getDrawable(playerImageId);
        mainViewController.setPlayerImage(playerImage);
        mainViewController.setPlayerCardImage(playerImage);
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
            playerA.addScore();
            mainViewController.setPlayerScore(playerA.getPlayerScore() + "");
        } else {
            playerB.addScore();
            mainViewController.setComputerScore(playerB.getPlayerScore() + "");
        }
    }

    private void setProgress() {
        double sizeOfBar = 100 / (NUMBER_OF_CARDS / 2.0);
        double totalTurns = playerA.getPlayerScore() + playerB.getPlayerScore();
        int gameProgress = (int) (totalTurns * sizeOfBar);
        mainViewController.setProgressBar(gameProgress);
    }

    private void displayWinner() {
        Gson gson = new Gson();
        Intent intent = new Intent(this, WinnerPage.class);

        String playerJsonA = gson.toJson(playerA);
        String playerJsonB = gson.toJson(playerB);
        intent.putExtra(WinnerPage.PLAYER_A, playerJsonA);
        intent.putExtra(WinnerPage.PLAYER_B, playerJsonB);

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