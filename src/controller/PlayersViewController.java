package controller;

import model.Player;
import view.PlayersView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayersViewController implements ActionListener, KeyListener {
    private PlayersView view;

    public PlayersViewController() {
        this.view = new PlayersView();
        this.view.start.setEnabled(false);
        this.view.start.addActionListener(this);
        this.view.exit.addActionListener(this);
        this.view.nameOne.addKeyListener(this);
        this.view.nameTwo.addKeyListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Player p1 = new Player(view.nameOne.getText());
        Player p2 = new Player(view.nameTwo.getText());

        if (e.getSource() == this.view.start) {
            if (!p1.getName().equals(p2.getName())) {
                QuestionsViewController c = new QuestionsViewController(p1, p2);
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Los nombres de los jugadores no pueden ser iguales");
            }
        } else {
            view.dispose();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (this.view.nameOne.getText().length() > 0 && this.view.nameTwo.getText().length() > 0) {
            this.view.start.setEnabled(true);
        } else {
            this.view.start.setEnabled(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.view.nameOne.getText().length() > 0 && this.view.nameTwo.getText().length() > 0) {
            this.view.start.setEnabled(true);
        } else {
            this.view.start.setEnabled(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.view.nameOne.getText().length() > 0 && this.view.nameTwo.getText().length() > 0) {
            this.view.start.setEnabled(true);
        } else {
            this.view.start.setEnabled(false);
        }
    }
}
