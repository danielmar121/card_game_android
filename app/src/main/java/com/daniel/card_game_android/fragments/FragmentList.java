package com.daniel.card_game_android.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.daniel.card_game_android.R;
import com.daniel.card_game_android.objects.Record;
import com.daniel.card_game_android.services.RecordItemAdapter;

public class FragmentList extends Fragment {
    private final RecordItemAdapter itemAdapter;
    private ListView recordsListView;

    public FragmentList(RecordItemAdapter itemAdapter) {
        this.itemAdapter = itemAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        findViews(view);
        initViews();

        return view;
    }

    private void findViews(View view) {
        recordsListView = (ListView) view.findViewById(R.id.record_LIV_records);
    }

    private void initViews() {
        recordsListView.setAdapter(itemAdapter);
        recordsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                Record record = itemAdapter.getItem(position);
                Log.d("clickList", "onItemClick: " + record.toString());
            }
        });
    }

//list_BTN_update    private CallBack_Top callBack_top;
//
//    public void setCallBack_top(CallBack_Top _callBack_top) {
//        this.callBack_top = _callBack_top;
//    }
}