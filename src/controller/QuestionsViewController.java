package controller;

import model.Player;
import view.QuestionsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionsViewController implements ActionListener{
    public QuestionsView view;
    public Player p1;
    public Player p2;

    public QuestionsViewController(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.view = new QuestionsView(p1.getName(), p2.getName(), p1.getPts(), p2.getPts());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
