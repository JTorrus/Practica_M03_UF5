package controller;

import model.Player;
import view.EndGameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Controlador encargado de mostrar la ventana con los datos de la partida al finalizar
public class EndGameController implements ActionListener {
    private EndGameView view;
    private Player p1, p2;

    // En el constructor de la clase pasamos simplemente los dos jugadores
    public EndGameController(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.view = new EndGameView(p1, p2);
        this.view.getbClose().addActionListener(this);
        setWinner();
    }

    /* En el metodo setWinner realizamos la comprobación para determinar que jugador es el vencedor comprobando los puntos de cada uno, nos servirá para mostrarlo en la vista
    y además escribirlo en un fichero
    */
    public void setWinner() {
        Path path = Paths.get("ActualWinner.txt");

        if (p1.getPts() > p2.getPts()) {
            view.getPwinner().setText(p1.getName());

            try {
                Files.write(path, view.getPwinner().getText().getBytes());
            } catch (IOException e) {
                System.out.println("No se ha podido escribir el fichero");
            }
        } else {
            view.getPwinner().setText(p2.getName());

            try {
                Files.write(path, view.getPwinner().getText().getBytes());
            } catch (IOException e) {
                System.out.println("No se ha podido escribir el fichero");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getbClose()) {
            view.dispose();
        }
    }
}
