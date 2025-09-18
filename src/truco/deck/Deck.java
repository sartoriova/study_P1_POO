package truco.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        generateSortedDeck();
    }

    private void generateSortedDeck() {
        for(Rank rank : Rank.values())
            for(Suit suit : Suit.values())
                if(rank != Rank.HIDDEN && suit != Suit.HIDDEN)
                    cards.add(Card.of(rank, suit));
    }

    public Card[] take(int numberOfCards) {
        final List<Card> cardsTaken = new ArrayList<>(cards.subList(0, numberOfCards));
        cards.removeAll(cardsTaken);
        Card[] cardsToDeal = new Card[cardsTaken.size()];
        return cardsTaken.toArray(cardsToDeal);
    }

    public Card takeOne() {
        return cards.remove(0);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }
}

