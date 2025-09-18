package truco.game;

import truco.deck.Card;

public class Round {
    private String winner;

    Round(String player1, Card card1, String player2, Card card2, Card vira) {
        int result = card1.compareValueTo(card2, vira);

        if (result > 0) winner = player1;
        if (result < 0) winner = player2;
    }

    public String getWinner() {
        return winner;
    }
}
