package com.daniel.card_game_android.activities;

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
    private RecordItemAdapter itemAdapter;
    private TopTenRecords topTenRecords;
    private FragmentList fragmentList;
    private ImageView record_IMG_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_page);

        getTopTen();

        findViews();

        itemAdapter = new RecordItemAdapter(this,
                R.layout.record_item, topTenRecords.getRecords());

        fragmentList = new FragmentList(itemAdapter);
        getSupportFragmentManager().beginTransaction().add(R.id.record_LAY_list, fragmentList).commit();
    }

    private void findViews() {
        record_IMG_background = findViewById(R.id.record_IMG_background);
        MyScreenUtils.updateBackground(Constants.BACKGROUND_NAME, this, record_IMG_background);
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
}