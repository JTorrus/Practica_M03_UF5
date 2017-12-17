package view;

import model.Player;

import javax.swing.*;
import java.awt.*;

public class EndGameView extends JFrame {

    private JLabel p1name, p2name, p1pts, p2pts, winner, pwinner;
    private Player p1, p2;
    private JButton bClose;

    public JLabel getPwinner() {
        return pwinner;
    }

    public JButton getbClose() {
        return bClose;
    }

    public EndGameView(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.setTitle("Resultados");
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(this.getContentPane());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addComponentsToPane(Container pane) {
        this.setSize(new Dimension(800, 200));
        p1name = new JLabel(p1.getName());
        p1pts = new JLabel(String.valueOf(p1.getPts()));
        p2name = new JLabel(p2.getName());
        p2pts = new JLabel(String.valueOf(p2.getPts()));
        winner = new JLabel("Ganador de la partida");
        pwinner = new JLabel("NOMBRE");
        bClose = new JButton("Cerrar");
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        gc.gridx = 0;
        gc.gridy = 0;
        pane.add(p1name, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        pane.add(p2name, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        pane.add(p1pts, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        pane.add(p2pts, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        pane.add(winner, gc);

        gc.gridx = 2;
        gc.gridy = 1;
        pane.add(pwinner, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        pane.add(bClose, gc);
    }
}
