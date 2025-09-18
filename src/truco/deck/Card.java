package truco.deck;

import java.util.Objects;

public final class Card {

    private static final Card[] cache = new Card[41];
    private final Suit suit;
    private final Rank rank;

    private Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit  = suit;
    }

    public static Card of(Rank rank, Suit suit){
        Objects.requireNonNull(rank);
        Objects.requireNonNull(suit);
        if(rank == Rank.HIDDEN ^ suit == Suit.HIDDEN)
            throw new IllegalArgumentException("Both rank and suit must be HIDDEN or none: " + rank + suit);

        return fromCache(rank, suit);
    }

    public static Card closed(){
        return fromCache(Rank.HIDDEN, Suit.HIDDEN);
    }

    private static Card fromCache(Rank rank, Suit suit){
        int rankValue = rank.value();
        int suitValue = suit.value();
        int cachePosition = rankValue == 0 || suitValue == 0 ? 0 : (rankValue - 1) * 4 + suitValue;

        if(cache[cachePosition] == null) cache[cachePosition] = new Card(rank, suit);
        return cache[cachePosition];
    }

    public int compareValueTo(Card otherCard, Card vira){
        return this.computeCardValue(vira) - otherCard.computeCardValue(vira);
    }

    private int computeCardValue(Card vira) {
        if (isManilha(vira))
            return switch (suit) {
                case Suit.DIAMONDS -> 10;
                case Suit.SPADES -> 11;
                case Suit.HEARTS -> 12;
                case Suit.CLUBS -> 13;
                case Suit.HIDDEN -> throw new IllegalStateException("Closed card can not be manilha!");
            };
        if(rank.value() > vira.rank.value()) return rank.value() - 1;
        return rank.value();
    }

    public boolean isManilha(Card vira){
        return getRank() == vira.getRank().next();
    }
    public boolean isClosed() {
        return rank.equals(Rank.HIDDEN) && suit.equals(Suit.HIDDEN);
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public String toString() {
        return "["+ rank + suit +"]";
    }
}

