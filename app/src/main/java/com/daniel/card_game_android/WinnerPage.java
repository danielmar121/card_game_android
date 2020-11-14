package com.daniel.card_game_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.MeasureUnit;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WinnerPage extends AppCompatActivity {
    public static final String playerScoreA = "PLAYER_A_SCORE";
    public static final String playerScoreB = "PLAYER_B_SCORE";
    TextView winner_LBL_game_over, winner_LBL_name;
    ImageView main_IMG_winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_page);
        Log.d("winner","onStart");

        findViews();

        displayWinner();
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
                startNewGmae();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startNewGmae() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void displayWinner() {
        int imageId;
        Intent intent = getIntent();
        String scoreA = intent.getStringExtra(playerScoreA);
        String scoreB = intent.getStringExtra(playerScoreB);

        if(Integer.parseInt(scoreA) > Integer.parseInt(scoreB)){
            imageId = this.getResources().getIdentifier("player_boy", "drawable", this.getPackageName());
            main_IMG_winner.setImageDrawable(getDrawable(imageId));
            winner_LBL_name.setText("Player_A");
        }else{
            imageId = this.getResources().getIdentifier("player_girl", "drawable", this.getPackageName());
            main_IMG_winner.setImageDrawable(getDrawable(imageId));
            winner_LBL_name.setText("Player_B");
        }
    }


    private void findViews() {
        winner_LBL_game_over = findViewById(R.id.winner_LBL_game_over);
        winner_LBL_name = findViewById(R.id.winner_LBL_name);
        main_IMG_winner = findViewById(R.id.main_IMG_winner);
    }


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