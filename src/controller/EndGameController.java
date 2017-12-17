package controller;

import model.Player;
import view.EndGameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameController implements ActionListener {
    private EndGameView view;
    private Player p1, p2;

    public EndGameController(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.view = new EndGameView(p1, p2);
        this.view.bClose.addActionListener(this);
        setWinner();
    }

    public void setWinner() {
        if (p1.getPts() > p2.getPts()) {
            view.pwinner.setText(p1.getName());
        } else {
            view.pwinner.setText(p2.getName());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.bClose) {
            view.dispose();
        }
    }
}
