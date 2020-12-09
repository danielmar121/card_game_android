package com.daniel.card_game_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daniel.card_game_android.R;

public class WelcomePage extends AppCompatActivity {
    EditText welcome_INPT_name;
    ImageButton welcome_BTN_boy, welcome_BTN_girl;
    Button welcome_BTN_records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        findViews();
        initViews();
    }

    private void findViews() {
        welcome_INPT_name = findViewById(R.id.welcome_INPT_name);
        welcome_BTN_boy = findViewById(R.id.welcome_BTN_boy);
        welcome_BTN_girl = findViewById(R.id.welcome_BTN_girl);
        welcome_BTN_records = findViewById(R.id.welcome_BTN_records);
    }

    private void initViews() {
        welcome_BTN_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame("boy");
            }
        });

        welcome_BTN_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame("girl");
            }
        });

        welcome_BTN_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecords();
            }
        });
    }

    private void showRecords() {
        Intent intent = new Intent(this, RecordsPage.class);
        startActivity(intent);
    }

    private void startGame(String playerGander) {
        String name = welcome_INPT_name.getText().toString();
        if (name.matches("")) {
            Toast.makeText(this, "You did not enter a name", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.PLAYER_GENDER, playerGander);
        intent.putExtra(MainActivity.PLAYER_NAME, name);
        startActivity(intent);
        finish();
    }
}