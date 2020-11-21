package com.daniel.card_game_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class WelcomePage extends AppCompatActivity {
    EditText login_INPT_name;
    ImageButton login_BTN_boy, login_BTN_girl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        findViews();
        initViews();
    }

    private void findViews() {
        login_INPT_name = findViewById(R.id.login_INPT_name);
        login_BTN_boy = findViewById(R.id.login_BTN_boy);
        login_BTN_girl = findViewById(R.id.login_BTN_girl);
    }

    private void initViews() {
        login_BTN_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame("boy");
            }
        });

        login_BTN_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame("girl");
            }
        });
    }

    private void startGame(String playerGander) {
        String name = login_INPT_name.getText().toString();
        if (name.matches("")) {
            Toast.makeText(this, "You did not enter a name", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.gander, playerGander);
        intent.putExtra(MainActivity.name, name);
        startActivity(intent);
        finish();
    }
}