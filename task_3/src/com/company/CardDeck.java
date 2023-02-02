package com.company;

public class CardDeck {
    private String value;
    private String suit;

    public CardDeck(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }
    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }
}
