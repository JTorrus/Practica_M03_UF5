package controller;

import model.Player;
import view.AnswerView;
import view.QuestionsView;

import javax.swing.*;
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
        for (int i = 0; i < view.cells.length; i++) {
            this.view.cells[i].addActionListener(this);
        }
    }

   @Override
   public void actionPerformed(ActionEvent e) {
       for (int i = 0; i < view.cells.length; i++) {
           if (view.cells[i] == e.getSource()) {
               view.dispose();
           }
       }
    }
}
