package com.daniel.card_game_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class RecordsPage extends AppCompatActivity {

    ListView recordsListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_page);


        recordsListView = (ListView)findViewById(R.id.recordsListView);
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Hi");
            arrayList.add("Ma");
            arrayList.add("Kore");
            arrayList.add("Gal");
            arrayList.add("Hi");
            arrayList.add("Ma");
            arrayList.add("Kore");
            arrayList.add("Gal");
            arrayList.add("Hi");
            arrayList.add("Ma");
            arrayList.add("Kore");
            arrayList.add("Gal");

            ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, arrayList);

        recordsListView.setAdapter(arrayAdapter);


    }

}