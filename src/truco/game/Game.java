package truco.game;

public class Game {
    private final Player player1;
    private final Player player2;
    private final Hand[] hands;
    private int handsLength;

    Game (Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.hands = new Hand[30];

        final Hand hand = new Hand(player1, player2);
        hands[handsLength++] = hand;
    }

    public void play() {
        Hand currentHand = hands[handsLength-1];

        if (currentHand.isDone()) {
            final String winner = currentHand.getWinner();

            if (winner != null) {
                if (winner.equals(player1.getName())) player1.incrementScore();
                else if (winner.equals(player2.getName())) player2.incrementScore();
            }

            System.out.println(winner);

            currentHand = new Hand(player1, player2);
            hands[handsLength++] = currentHand;
        }

        currentHand.playRound();
    }

    public boolean isDone() {
        return player1.getScore() == 12 || player2.getScore() == 12;
    }

    public Player getWinner() {
        if (!isDone()) return null;
        if (player1.getScore() == 12) return player1;
        return player2;
    }
}
