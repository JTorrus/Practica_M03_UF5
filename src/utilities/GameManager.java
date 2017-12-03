package utilities;

import model.Player;

public class GameManager {
    private Player player1;
    private Player player2;
    private int turn;

    public GameManager(Player player1, Player player2, int turn) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = turn;
    }

    public boolean actualTurn(int turn) {
        if (turn % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }

    public int setPunctuation(int pts) {
        if (actualTurn(turn)) {
            System.out.println("Turno de " + player2.getName());
            player1.setPts(player1.getPts() + pts);
            return player1.getPts();
        } else {
            System.out.println("Turno de " + player1.getName());
            player2.setPts(player2.getPts() + pts);
            return player2.getPts();
        }
    }


}
