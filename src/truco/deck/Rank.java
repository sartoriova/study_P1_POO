package truco.deck;

public enum Rank {
    HIDDEN(0, 'X'), FOUR(1, '4'), FIVE(2, '5'),
    SIX(3, '6'), SEVEN(4, '7'), QUEEN(5, 'Q'),
    JACK(6, 'J'), KING(7, 'K'), ACE(8, 'A'),
    TWO(9, '2'), THREE(10, '3');

    private final int value;
    private final char symbol;

    Rank(int value, char symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    int value() {
        return value;
    }

    public Rank next() {
        return switch (value){
            case 0 -> HIDDEN;
            case 10 -> FOUR;
            default -> values()[value + 1];
        };
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
