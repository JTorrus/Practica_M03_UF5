package executable;

import controller.PlayersViewController;
import view.PlayersView;

public class GameApp {
    public static void main(String[] args) {
        PlayersView pv = new PlayersView();
        PlayersViewController pController = new PlayersViewController(pv);
    }
}
