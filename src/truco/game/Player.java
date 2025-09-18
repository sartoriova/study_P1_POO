package truco.game;

import truco.deck.Card;

public class Player {
    private final String name;
    private int score;
    private Card[] cards;
    private int cardsLength;

    Player(String name) {
        this.name = name;
        this.cards = new Card[3];
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
        cardsLength = 3;
    }

    public Card chooseCard() {
        final int index = (int) (Math.random()*cardsLength);
        final Card result = cards[index];

        cards[index] = cards[cardsLength - 1];
        cards[cardsLength - 1] = null;
        cardsLength--;

        return result;
    }

    public void incrementScore() {
        ++score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
