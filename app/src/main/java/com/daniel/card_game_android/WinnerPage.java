package com.daniel.card_game_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class WinnerPage extends AppCompatActivity {
    public static final String playerScoreA = "PLAYER_A_SCORE";
    public static final String playerScoreB = "PLAYER_B_SCORE";
    private TextView winner_LBL_name;
    private ImageView main_IMG_winner;
    private Sound winSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_page);

        findViews();

        displayWinner();
        winSound.playSound();
    }

    private void findViews() {
        winner_LBL_name = findViewById(R.id.winner_LBL_name);
        main_IMG_winner = findViewById(R.id.main_IMG_winner);
        winSound = new Sound(this, R.raw.win_sound);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.winner_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.winner_ITEM_replay:
                startNewGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startNewGame() {
        Intent intent = new Intent(this, WelcomePage.class);
        startActivity(intent);
        finish();
    }

    private void displayWinner() {
        int imageId;
        Intent intent = getIntent();
        int scoreA = intent.getIntExtra(WinnerPage.playerScoreA, 0);
        int scoreB = intent.getIntExtra(WinnerPage.playerScoreB, 0);
        String playerName, playerImage;
        String gander = intent.getStringExtra(MainActivity.gander);

        if (scoreA > scoreB) {
            if (gander.matches("girl"))
                playerImage = "player_girl";
            else
                playerImage = "player_boy";

            playerName = intent.getStringExtra(MainActivity.name);
        } else {
            playerImage = "player_computer";
            playerName = "Computer";
        }

        imageId = this.getResources().getIdentifier(playerImage, "drawable", this.getPackageName());
        main_IMG_winner.setImageDrawable(getDrawable(imageId));
        winner_LBL_name.setText(playerName);
    }
}