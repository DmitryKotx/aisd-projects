package com.company;

import java.util.LinkedList;
import java.util.Queue;


public class Solution {
    public static Result myVersion(CardDeck[] cardDeck) {
        myQueue<CardDeck> deck = new myQueue<>();
        myQueue<CardDeck> table = new myQueue<>();
        for (int i = 0; i < cardDeck.length; i++) {
            deck.add(cardDeck[i]);
        }
        int steps = 0;
        steps = mySolution(steps, deck, table);
        Result result = new Result(0, new CardDeck[table.size()], new CardDeck[deck.size()]);
        int size1 = deck.size();
        int size2 = table.size();
        for (int j = size1 - 1; j >= 0; j--) {
            result.getDeck()[j] = deck.poll();
        }
        for (int i = size2 - 1; i >= 0; i--) {
            result.getTable()[i] = table.poll();
        }
        result.setSteps(result.getSteps() + steps);
        return result;
    }
    public static Result integrated(CardDeck[] cardDeck) {
        Queue<CardDeck> deck = new LinkedList<>();
        Queue<CardDeck> table = new LinkedList<>();
        for (int i = 0; i < cardDeck.length; i++) {
            deck.add(cardDeck[i]);
        }
        int steps = 0;
        steps = solution(steps, deck, table);
        Result result = new Result(0, new CardDeck[table.size()], new CardDeck[deck.size()]);
        int size1 = deck.size();
        int size2 = table.size();
        for (int j = size1 - 1; j >= 0; j--) {
            result.getDeck()[j] = deck.poll();
        }
        for (int i = size2 - 1; i >= 0; i--) {
            result.getTable()[i] = table.poll();
        }
        result.setSteps(result.getSteps()+steps);
        return result;
    }
    private static int mySolution(int steps, myQueue<CardDeck> deck, myQueue<CardDeck> table) {
        if (deck.isEmpty()) {
            return 0;
        }
        table.add(deck.poll());
        CardDeck fCard = table.peek();
        CardDeck nCard = deck.poll();
        int counter = 0;

        while (true) {
            if (fCard.getSuit().equals(nCard.getSuit()) || fCard.getValue().equals(nCard.getValue())) {
                table.add(nCard);
                counter = 0;
                steps++;
                if (deck.isEmpty()) {
                    break;
                }
                fCard = nCard;
            } else {
                deck.add(nCard);
                counter++;
                if (counter == deck.size()) {
                    steps -= counter - 1;
                    break;
                }
                steps++;
            }
            nCard = deck.poll();
        }
        return steps;
    }
    private static int solution(int steps, Queue<CardDeck> deck, Queue<CardDeck> table) {
        if (deck.isEmpty()) {
            return 0;
        }
        table.add(deck.poll());
        CardDeck fCard = table.peek();
        CardDeck nCard = deck.poll();
        int counter = 0;

        while (true) {
            if (fCard.getSuit().equals(nCard.getSuit()) || fCard.getValue().equals(nCard.getValue())) {
                table.add(nCard);
                counter = 0;
                steps++;
                if (deck.isEmpty()) {
                    break;
                }
                fCard = nCard;
            } else {
                deck.add(nCard);
                counter++;
                if (counter == deck.size()) {
                    steps -= counter - 1;
                    break;
                }
                steps++;
            }
            nCard = deck.poll();
        }
        return steps;
    }
}