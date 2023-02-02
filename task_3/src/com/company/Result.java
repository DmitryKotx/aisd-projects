package com.company;

public class Result {
    private int steps;
    private CardDeck[] table;
    private CardDeck[] deck;

    public Result(int steps, CardDeck[] table, CardDeck[] deck) {
        this.steps = steps;
        this.table = table;
        this.deck = deck;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public CardDeck[] getTable() {
        return table;
    }

    public void setTable(CardDeck[] table) {
        this.table = table;
    }

    public CardDeck[] getDeck() {
        return deck;
    }

    public void setDeck(CardDeck[] deck) {
        this.deck = deck;
    }

}
