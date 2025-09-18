package truco.game;

import truco.deck.Card;
import truco.deck.Deck;

public class Hand {
    private final Card vira;
    private final Player player1;
    private final Player player2;
    private final Round[] rounds;
    private int roundsLength;

    Hand(Player player1, Player player2) {
        final Deck deck = new Deck();
        deck.shuffle();

        this.player1 = player1;
        this.player2 = player2;
        this.vira = deck.takeOne();
        this.rounds = new Round[3];

        player1.setCards(deck.take(3));
        player2.setCards(deck.take(3));
    }

    public void playRound() {
        final Round round = new Round(player1.getName(), player1.chooseCard(), player2.getName(), player2.chooseCard(), vira);
        System.out.println(round.getWinner());
        rounds[roundsLength++] = round;
    }

    public boolean isDone() {
        if (roundsLength == 3) return true;

        if (roundsLength == 2) {
            final String winner1 = rounds[0].getWinner();
            final String winner2 = rounds[1].getWinner();

            return winner1 != null && winner1.equals(winner2);
        }

        return false;
    }

    public String getWinner() {
        if (!isDone()) return null;

        final String winner1 = rounds[0].getWinner();
        final String winner2 = rounds[1].getWinner();
        final String winner3 = rounds[2].getWinner();

        if (winner1 == null && winner2 != null) return winner2;
        if (winner2 == null && winner1 != null) return winner1;
        if (winner1 == null) return winner3;
        if (winner3 == null) return winner1;
        if (winner1.equals(winner2) || winner1.equals(winner3)) return winner1;
        if (winner2.equals(winner3)) return winner2;

        return null;
    }
}
