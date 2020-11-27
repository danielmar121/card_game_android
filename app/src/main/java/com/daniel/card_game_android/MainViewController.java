package com.daniel.card_game_android;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainViewController {

    private MainActivity activity;

    private TextView main_LBL_score_player_A, main_LBL_score_player_B;
    private ImageView main_IMG_player_A_card, main_IMG_player_B_card;
    private ImageView main_IMG_player_A;
    private Sound tickingSound;
    private ProgressBar main_PGR_game_progress;
    private ImageButton main_BTN_play;

    public MainViewController(MainActivity activity) {
        this.activity = activity;
        findViews();
        initViews();
    }

    private void findViews() {
        main_LBL_score_player_A = activity.findViewById(R.id.main_LBL_score_player_A);
        main_LBL_score_player_B = activity.findViewById(R.id.main_LBL_score_player_B);
        main_IMG_player_A_card = activity.findViewById(R.id.main_IMG_player_A_card);
        main_IMG_player_B_card = activity.findViewById(R.id.main_IMG_player_B_card);
        main_IMG_player_A = activity.findViewById(R.id.main_IMG_player_A);
        tickingSound = new Sound();
        main_PGR_game_progress = activity.findViewById(R.id.main_PGR_game_progress);
        main_BTN_play = activity.findViewById(R.id.main_BTN_play);
    }

    private void initViews() {
        main_BTN_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("pttt", "onClick");
                main_BTN_play.setEnabled(false);
                activity.startCounting();
            }
        });
    }


    public void setPlayerImage(Drawable playerImage) {
        main_IMG_player_A.setImageDrawable(playerImage);
    }

    public void setPlayerCardImage(Drawable cardImage) {
        main_IMG_player_A_card.setImageDrawable(cardImage);
    }

    public void setComputerCardImage(Drawable cardImage) {
        main_IMG_player_B_card.setImageDrawable(cardImage);
    }

    public void setPlayerScore(String score) {
        main_LBL_score_player_A.setText(score);
    }

    public void setComputerScore(String score) {
        main_LBL_score_player_B.setText(score);
    }

    public void setProgressBar(int progress) {
        main_PGR_game_progress.setProgress(progress);
    }

    public void playSound() {
        if (!tickingSound.isPlaying()) {
            tickingSound.setSound(activity, R.raw.ticking_clock_sound);
            tickingSound.playSound();
        }
    }

    public void stopSound() {
        tickingSound.stopSound();
    }

//  public void updateBackground(int id) {
//        Glide
//                .with(activity)
//                .load(id)
//                .into(main_IMG_background);
//    }
}
