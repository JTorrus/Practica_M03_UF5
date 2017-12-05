package utilities;

import model.Player;

public class GameManager {
    private Player player1;
    private Player player2;
    private int turn;

    public void nextTurn() {
        this.turn++;
    }

    public GameManager(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = 0;
    }

    public Player actualPlayer(int turn) {
        if (turn % 2 == 0) {
            return player1;
        } else {
            return player2;
        }
    }

    public boolean doubleRound() {
        if (getTurn() > 9) {
            return true;
        }
        return false;
    }

    public int getTurn() {
        return turn;
    }
}
