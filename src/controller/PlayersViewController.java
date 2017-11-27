package controller;

import model.Player;
import view.PlayersView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayersViewController implements ActionListener{


    private PlayersView view;

    public PlayersViewController(PlayersView view){
        this.view = view;
        this.view.start.addActionListener(this);
        this.view.exit.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.view.start){
            Player p1 = new Player(view.nameOne.getText());
            Player p2 = new Player(view.nameTwo.getText());
            QuestionsViewController c = new QuestionsViewController(p1, p2);
            view.dispose();
        }else{
            view.dispose();
        }
    }
}
