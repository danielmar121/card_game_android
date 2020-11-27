package com.daniel.card_game_android;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordItemAdapter extends ArrayAdapter<Record> {
    private Activity myContext;
    private ArrayList<Record> records;

    public RecordItemAdapter(Context context, int textViewResourceId,
                             ArrayList<Record> records) {
        super(context, textViewResourceId, records);
        myContext = (Activity) context;
        this.records = records;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = myContext.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.record_item, null);

        TextView postTitleView = (TextView) rowView
                .findViewById(R.id.record_TXT_player_name);

        postTitleView.setText(records.get(position).getName());

        TextView postDateView = (TextView) rowView
                .findViewById(R.id.record_TXT_player_score);

        postDateView.setText(records.get(position).getScore() + "");

        return rowView;
    }
}