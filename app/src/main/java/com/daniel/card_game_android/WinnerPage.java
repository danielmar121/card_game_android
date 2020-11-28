package com.daniel.card_game_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class WinnerPage extends AppCompatActivity {
    public static final String playerScoreA = "PLAYER_A_SCORE";
    public static final String playerScoreB = "PLAYER_B_SCORE";
    TextView winner_LBL_game_over, winner_LBL_name;
    ImageView main_IMG_winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_page);

        findViews();

        displayWinner();
    }

    private void findViews() {
        winner_LBL_game_over = findViewById(R.id.winner_LBL_game_over);
        winner_LBL_name = findViewById(R.id.winner_LBL_name);
        main_IMG_winner = findViewById(R.id.main_IMG_winner);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.winner_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.winner_ITEM_replay:
                startNewGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startNewGame() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void displayWinner() {
        int imageId;
        Intent intent = getIntent();
        String scoreA = intent.getStringExtra(playerScoreA);
        String scoreB = intent.getStringExtra(playerScoreB);
        String playerName;

        if(Integer.parseInt(scoreA) > Integer.parseInt(scoreB)){
            imageId = this.getResources().getIdentifier("player_boy", "drawable", this.getPackageName());
            playerName = "Player_A";
        }else{
            imageId = this.getResources().getIdentifier("player_girl", "drawable", this.getPackageName());
            playerName = "Player_B";
        }

        main_IMG_winner.setImageDrawable(getDrawable(imageId));
        winner_LBL_name.setText(playerName);
    }
}