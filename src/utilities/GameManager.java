package utilities;

import controller.QuestionsViewController;
import model.Player;

public class GameManager {
    private Player player1;
    private Player player2;
    private int turn;
    private QuestionsViewController qvc;
    private final int DOUBLE_ROUND = 5;



    public GameManager(Player player1, Player player2, QuestionsViewController qvc) {
        this.player1 = player1;
        this.player2 = player2;
        this.qvc = qvc;
        this.turn = 0;
    }

    public void nextTurn() {
        this.turn++;
        System.out.println(this.turn);
        if (this.turn == DOUBLE_ROUND){
            this.qvc.setDataToButtonsDouble();
        }

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
