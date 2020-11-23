package com.daniel.card_game_android;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }

    public Card getCard() {
        if (!isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void addCard(String imageName, int strength) {
        cards.add(new Card(imageName, strength));
    }
}
