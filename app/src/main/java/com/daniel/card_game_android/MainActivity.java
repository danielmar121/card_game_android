package com.daniel.card_game_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final int NUMBER_OF_CARDS = 26;
    Deck warDeck;
    TextView main_LBL_score_player_A, main_LBL_score_player_B;
    ImageView main_IMG_player_A_card, main_IMG_player_B_card;
    ImageView main_BTN_play ;
    private int playerScoreA = 0,  playerScoreB = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();
    }

    private void findViews() {
        main_LBL_score_player_A = findViewById(R.id.main_LBL_score_player_A);
        main_LBL_score_player_B = findViewById(R.id.main_LBL_score_player_B);
        main_IMG_player_A_card = findViewById(R.id.main_IMG_player_A_card);
        main_IMG_player_B_card = findViewById(R.id.main_IMG_player_B_card);
        main_BTN_play = findViewById(R.id.main_BTN_play);
    }

    private void initViews() {
        initDeck();
        main_BTN_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTurn();
            }
        });
    }

    private void initDeck() {
        warDeck = new Deck();
        for (int i = 1; i <= NUMBER_OF_CARDS; i++) {
            warDeck.addCard("card_" + i, i);
        }
        warDeck.shuffleCards();
    }

    private void playTurn() {
        Card playerCardA = warDeck.getCard();
        Card playerCardB = warDeck.getCard();

        setNewCardsImage(playerCardA.getImageName(),playerCardB.getImageName());

        setScore(playerCardA,playerCardB);

        if(warDeck.isEmpty()){
            displayWinner();
        }
    }

    private void setNewCardsImage(String imageNameA, String imageNameB) {
        int playerDrawableA = this.getResources().getIdentifier(imageNameA, "drawable", this.getPackageName());
        int playerDrawableB = this.getResources().getIdentifier(imageNameB, "drawable", this.getPackageName());

        main_IMG_player_A_card.setImageDrawable(getDrawable(playerDrawableA));
        main_IMG_player_B_card.setImageDrawable(getDrawable(playerDrawableB));
    }

    private void setScore(Card playerCardA, Card playerCardB) {
        if(playerCardA.isStronger(playerCardB)){
            playerScoreA++;
            main_LBL_score_player_A.setText(playerScoreA + "");
        }else{
            playerScoreB++;
            main_LBL_score_player_B.setText(playerScoreB + "");
        }
    }

    private void displayWinner() {
        Intent intent = new Intent(this, WinnerPage.class);
        intent.putExtra(WinnerPage.playerScoreA, "" + playerScoreA);
        intent.putExtra(WinnerPage.playerScoreB, "" + playerScoreB);
        startActivity(intent);
        finish();
    }
}