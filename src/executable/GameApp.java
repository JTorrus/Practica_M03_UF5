package executable;

import controller.PlayersViewController;
import model.Player;
import view.PlayersView;
import view.QuestionsView;

public class GameApp {
    public static void main(String[] args) {

        PlayersView pv = new PlayersView();
        //Player p = new Player();
        PlayersViewController pController = new PlayersViewController(pv);
    }
}
