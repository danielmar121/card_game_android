package com.daniel.card_game_android;

import java.util.ArrayList;
import java.util.Collections;

public class TopTenRecords {

    private ArrayList<Record> records;

    public TopTenRecords() {
        records = new ArrayList<>();
    }

    public TopTenRecords(ArrayList<Record> records) {
        this.records = records;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public TopTenRecords setRecords(ArrayList<Record> records) {
        this.records = records;
        return this;
    }

    public boolean addRecord(Record record) {
        boolean isAdd = false;
        if (records.size() < 10) {
            records.add(record);
            isAdd = true;
        } else if (records.get(records.size() - 1).getScore() >= record.getScore()) {
            if (records.size() == 10) {
                records.remove(9);
            }
            records.add(record);
            isAdd = true;
        }

        if (isAdd) {
            Collections.sort(records);
        }
        return isAdd;
    }
}
