package controller;

import model.Player;
import view.QuestionsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{



    public QuestionsView view;

    public Controller(QuestionsView view) {
        this.view = view;
    }

    @Override


    public void actionPerformed(ActionEvent e) {

    }
}
