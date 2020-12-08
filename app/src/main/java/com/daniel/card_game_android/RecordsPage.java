package com.daniel.card_game_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import static com.daniel.card_game_android.Constants.TOP_TEN;


public class RecordsPage extends AppCompatActivity {
    private RecordItemAdapter itemAdapter;
    private TopTenRecords topTenRecords;
    private FragmentList fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_page);

        getTopTen();

        itemAdapter = new RecordItemAdapter(this,
                R.layout.record_item, topTenRecords.getRecords());

        fragmentList = new FragmentList(itemAdapter);
        getSupportFragmentManager().beginTransaction().add(R.id.record_LAY_list, fragmentList).commit();
    }

    private void getTopTen() {
        topTenRecords = new TopTenRecords();
        SharedPreferences prefs = getSharedPreferences("MY_SP", MODE_PRIVATE);
        Gson gson = new Gson();

        String jsonFromMemory = prefs.getString(TOP_TEN, "");
        if (jsonFromMemory != "") {
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