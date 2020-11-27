package com.daniel.card_game_android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;


public class RecordsPage extends AppCompatActivity {

    ListView recordsListView;
    RecordItemAdapter itemAdapter;
    TopTenRecords topTenRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_page);

        getTopTen();

        recordsListView = (ListView) this.findViewById(R.id.record_LIV_records);
        itemAdapter = new RecordItemAdapter(this,
                R.layout.record_item, topTenRecords.getRecords());
        recordsListView.setAdapter(itemAdapter);
    }

    private void getTopTen() {
        topTenRecords = new TopTenRecords();
        SharedPreferences prefs = getSharedPreferences("MY_SP", MODE_PRIVATE);
        Gson gson = new Gson();

        String jsonFromMemory = prefs.getString("TopTen", "");
        if (jsonFromMemory == "") {
            finish();
        } else {
            topTenRecords = gson.fromJson(jsonFromMemory, TopTenRecords.class);
        }
    }
}