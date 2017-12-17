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
        this.view.getStart().setEnabled(false);
        this.view.getStart().addActionListener(this);
        this.view.getExit().addActionListener(this);
        this.view.getNameOne().addKeyListener(this);
        this.view.getNameTwo().addKeyListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Player p1 = new Player(view.getNameOne().getText());
        Player p2 = new Player(view.getNameTwo().getText());

        if (e.getSource() == this.view.getStart()) {
            if (!p1.getName().equals(p2.getName())) {
                new QuestionsViewController(p1, p2);
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
        if (this.view.getNameOne().getText().length() > 0 && this.view.getNameTwo().getText().length() > 0) {
            this.view.getStart().setEnabled(true);
        } else {
            this.view.getStart().setEnabled(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.view.getNameOne().getText().length() > 0 && this.view.getNameTwo().getText().length() > 0) {
            this.view.getStart().setEnabled(true);
        } else {
            this.view.getStart().setEnabled(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.view.getNameOne().getText().length() > 0 && this.view.getNameTwo().getText().length() > 0) {
            this.view.getStart().setEnabled(true);
        } else {
            this.view.getStart().setEnabled(false);
        }
    }
}
