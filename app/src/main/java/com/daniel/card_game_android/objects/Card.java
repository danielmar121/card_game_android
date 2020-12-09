package com.daniel.card_game_android.objects;

public class Card {
    private String imageName;
    private int strength;

    public Card() {
    }

    public Card(String imageName, int strength) {
        this.imageName = imageName;
        this.strength = strength;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean isStronger(Card card) {
        return this.strength > card.strength;
    }
}
