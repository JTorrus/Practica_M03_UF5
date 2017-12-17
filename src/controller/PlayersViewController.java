package controller;

import model.Player;
import view.PlayersView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/* Controlador encargado de crear los jugadores que jugarán la partida, implementa la interfaz ActionListener y KeyListener para poder realizar comprobaciones tanto del botón que pulsa el usuario como
de lo que ha escrito en cada TextField. Realizamos un chequeo de si están los dos TextFields rellenados y si los nombres no son iguales, de ser así, permitimos comenzar una partida. De lo contrario,
informamos al usuario
 */
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
