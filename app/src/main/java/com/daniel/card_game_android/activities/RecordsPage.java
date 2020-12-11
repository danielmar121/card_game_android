package com.daniel.card_game_android.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.daniel.card_game_android.R;
import com.daniel.card_game_android.fragments.FragmentList;
import com.daniel.card_game_android.objects.TopTenRecords;
import com.daniel.card_game_android.services.RecordItemAdapter;
import com.daniel.card_game_android.utils.Constants;
import com.daniel.card_game_android.utils.MyScreenUtils;
import com.google.gson.Gson;

import static com.daniel.card_game_android.utils.Constants.TOP_TEN;


public class RecordsPage extends AppCompatActivity {
    private TopTenRecords topTenRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_page);

        getTopTen();

        findViews();

        RecordItemAdapter itemAdapter = new RecordItemAdapter(this,
                R.layout.record_item, topTenRecords.getRecords());

        FragmentList fragmentList = new FragmentList(itemAdapter);
        getSupportFragmentManager().beginTransaction().add(R.id.record_LAY_list, fragmentList).commit();
    }

    private void findViews() {
        ImageView record_IMG_background = findViewById(R.id.record_IMG_background);
        MyScreenUtils.updateBackground(Constants.BACKGROUND_NAME, this, record_IMG_background);
    }

    private void getTopTen() {
        topTenRecords = new TopTenRecords();
        SharedPreferences prefs = getSharedPreferences("MY_SP", MODE_PRIVATE);
        Gson gson = new Gson();

        String jsonFromMemory = prefs.getString(TOP_TEN, "");
        if (!jsonFromMemory.equals("")) {
            topTenRecords = gson.fromJson(jsonFromMemory, TopTenRecords.class);
        }
    }
/*
    private void insertLocation(int index) {
        Intent intent = new Intent(RecordsPage.this, MapsActivity.class);
        intent.putExtra(MapsActivity.LATITUDE, rec[index].getLocation()[0]);
        intent.putExtra(MapsActivity.LONGITUDE, rec[index].getLocation()[1]);
        intent.putExtra(MapsActivity.NAME, rec[index].getName());
        startActivity(intent);
    }*/
}