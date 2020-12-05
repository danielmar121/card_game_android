package com.daniel.card_game_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class FragmentList extends Fragment {
    private RecordItemAdapter itemAdapter;
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

        recordsListView.setAdapter(itemAdapter);

        return view;
    }

    private void findViews(View view) {
        recordsListView = (ListView) view.findViewById(R.id.record_LIV_records);
    }

    private void initViews() {
//        list_BTN_update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (callBack_top != null) {
//                    callBack_top.displayLocation(32.05889116392735, 34.811619248137916);
//                }
//            }
//        });
    }

//list_BTN_update    private CallBack_Top callBack_top;
//
//    public void setCallBack_top(CallBack_Top _callBack_top) {
//        this.callBack_top = _callBack_top;
//    }
}