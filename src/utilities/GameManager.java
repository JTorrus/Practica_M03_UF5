package utilities;

import controller.QuestionsViewController;
import model.Player;
/* La clase GameManager es la gestora del juego, se encarga de comprobar los diferentes estados de la partida, así como avanzzar turno, definir a que jugador le toca responder, cuando es el final de la partida
y cuando empieza la DoubleRound
 */
public class GameManager {
    private Player player1;
    private Player player2;
    private int turn;
    private QuestionsViewController qvc;
    private final int DOUBLE_ROUND = 20;
    private final int END_GAME = 30;

    public GameManager(Player player1, Player player2, QuestionsViewController qvc) {
        this.player1 = player1;
        this.player2 = player2;
        this.qvc = qvc;
        this.turn = 0;
    }

    public void nextTurn() {
        this.turn++;
    }

    public Player actualPlayer(int turn) {
        if (turn % 2 == 0) {
            return player1;
        } else {
            return player2;
        }
    }

    public boolean doubleRound() {
        return this.turn == DOUBLE_ROUND;
    }

    public boolean endOfGame() {
        return this.turn == END_GAME;
    }

    public int getTurn() {
        return turn;
    }
}
