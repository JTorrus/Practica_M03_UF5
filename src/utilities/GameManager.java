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




}
