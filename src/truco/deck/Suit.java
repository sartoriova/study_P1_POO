package truco.deck;

public enum Suit {
    HIDDEN("X", 0),
    DIAMONDS("D", 1),
    SPADES("S", 2),
    HEARTS("H", 3),
    CLUBS("C", 4);

    private final String symbol;
    private final int ordinalValue;

    Suit(String symbol, int ordinalValue) {
        this.symbol = symbol;
        this.ordinalValue = ordinalValue;
    }

    @Override
    public String toString() {
        return symbol;
    }

    int value() {
        return ordinalValue;
    }

}

