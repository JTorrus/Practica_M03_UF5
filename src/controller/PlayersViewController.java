package controller;

import model.Player;
import view.PlayersView;
import view.QuestionsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayersViewController implements ActionListener{


    private PlayersView view;

    public PlayersViewController(PlayersView view){
        this.view = view;
        this.view.start.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.view.start){
            QuestionsView qv = new QuestionsView();
            Controller c = new Controller(qv);
            qv.setVisible(true);
            Player p1 = new Player(view.nameOne.getText());
            Player p2 = new Player(view.nameTwo.getText());
            view.dispose();
        }
    }
}
