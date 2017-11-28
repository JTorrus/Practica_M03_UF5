package controller;

import model.Answer;
import model.Player;
import model.Question;

public class GameController {
    private Player player1;
    private Player player2;
    private int turn;
    private boolean player1turn;
    private boolean player2turn;

    public GameController(Player player1, Player player2, int turn) {
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
