package com.daniel.card_game_android;

public class Record implements Comparable {

    private String name;
    private String date;
    private int score;
    private double lon, lat;

    public Record() {
    }

    public Record(String name, int score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public Record setName(String name) {
        this.name = name;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Record setDate(String date) {
        this.date = date;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Record setScore(int score) {
        this.score = score;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public Record setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public Record setLon(double lon) {
        this.lon = lon;
        return this;
    }

    @Override
    public int compareTo(Object record) {
        return ((Record) record).getScore() - this.score;
    }

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", score=" + score +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
