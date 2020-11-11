package com.daniel.card_game_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Deck warDeck;
    TextView main_LBL_score_player_A, main_LBL_score_player_B;
    ImageView main_IMG_player_A_card, main_IMG_player_B_card;
    Button main_BTN_play ;
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
        initDeck();
    }

    private void initViews() {
        main_BTN_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTurn();
            }
        });
    }

    private void initDeck() {
        warDeck = new Deck();
        // TODO: NEED TO DECIDE HOW TO ADD CARDS!
        warDeck.addCard("image_name", 8);
        warDeck.shuffleCards();
    }

    private void playTurn() {
        Card playerCardA = warDeck.getCard();
        Card playerCardB = warDeck.getCard();

        setNewCardsImage(playerCardA.getImageName(),playerCardB.getImageName());

        setScore(playerCardA,playerCardB);

        if(warDeck.isEmpty()){
            // TODO: NEED TO GO TO WINNER SCREEN
            // NEED TO SEND THE SCORE DETAILS TO THE NEW SCREEN
        }
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

    private void setNewCardsImage(String imageNameA, String imageNameB) {
        int playerDrawableA = this.getResources().getIdentifier(imageNameA, "drawable", this.getPackageName());
        int playerDrawableB = this.getResources().getIdentifier(imageNameB, "drawable", this.getPackageName());

        main_IMG_player_A_card.setImageDrawable(getDrawable(playerDrawableA));
        main_IMG_player_A_card.setImageDrawable(getDrawable(playerDrawableB));
    }

    @Override
    protected void onStart() {
        Log.d("pttt","onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("pttt","onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("pttt","onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("pttt","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("pttt","onDestroy");
        super.onDestroy();
    }
}