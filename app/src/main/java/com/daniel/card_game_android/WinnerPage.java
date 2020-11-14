package com.daniel.card_game_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WinnerPage extends AppCompatActivity {
    Deck warDeck;
    TextView winner_LBL_game_over, winner_LBL_name;
    ImageView main_IMG_player_B_card;
    private int playerScoreA = 0,  playerScoreB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_page);

        findViews();
        //initViews();
    }


    private void findViews() {
        winner_LBL_game_over = findViewById(R.id.winner_LBL_game_over);
        winner_LBL_name = findViewById(R.id.winner_LBL_name);
        main_IMG_player_B_card = findViewById(R.id.main_IMG_player_B_card);

    }
/*
    private void initViews() {
        main_BTN_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTurn();
            }
        });
    }*/

    @Override
    protected void onStart() {
        Log.d("winner","onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("winner","onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("winner","onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("winner","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("winner","onDestroy");
        super.onDestroy();
    }
}