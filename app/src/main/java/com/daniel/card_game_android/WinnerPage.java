package com.daniel.card_game_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import static com.daniel.card_game_android.Constants.*;

public class WinnerPage extends ActivityBase {
    public static final String playerScoreA = "PLAYER_A_SCORE";
    public static final String playerScoreB = "PLAYER_B_SCORE";
    public static final String playerImageA = "PLAYER_A_IMAGE";
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
        winSound = new Sound();
        winSound.setSound(this, R.raw.win_sound);
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
        //TO DO: refactor object player
        int imageId;
        Intent intent = getIntent();
        int scoreA = intent.getIntExtra(WinnerPage.playerScoreA, 0);
        int scoreB = intent.getIntExtra(WinnerPage.playerScoreB, 0);
        String playerImage = intent.getStringExtra(WinnerPage.playerImageA);
        String playerName;
        //String gander = intent.getStringExtra(MainActivity.gender);

        if (scoreA > scoreB) {
            playerName = intent.getStringExtra(MainActivity.name);
            saveScore(scoreA, playerName);
        } else {
            playerImage = COMPUTER_CARD;
            playerName = "Computer";
            saveScore(scoreB, playerName);
        }

        imageId = this.getResources().getIdentifier(playerImage, "drawable", this.getPackageName());
        main_IMG_winner.setImageDrawable(getDrawable(imageId));
        winner_LBL_name.setText(playerName);
    }

    private void saveScore(int score, String playerName) {
        TopTenRecords topTenRecords;

        SharedPreferences prefs = getSharedPreferences("MY_SP", MODE_PRIVATE);
        Gson gson = new Gson();

        String jsonFromMemory = prefs.getString("TopTen", "");
        if (jsonFromMemory == "") {
            topTenRecords = new TopTenRecords();
        } else {
            topTenRecords = gson.fromJson(jsonFromMemory, TopTenRecords.class);
        }

        Record record = new Record(playerName, score);
        boolean isAdd = topTenRecords.addRecord(record);

        if (isAdd) {
            SharedPreferences.Editor editor = prefs.edit();
            String json = gson.toJson(topTenRecords);
            editor.putString("TopTen", json);
            editor.apply();
        }

        jsonFromMemory = prefs.getString("TopTen", "");
        topTenRecords = gson.fromJson(jsonFromMemory, TopTenRecords.class);
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, WelcomePage.class);
        startActivity(intent);
        super.onDestroy();
    }
}