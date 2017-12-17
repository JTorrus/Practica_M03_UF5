package view;

import javax.swing.*;
import java.awt.*;

public class PlayersView extends JFrame {

    private JLabel playerOne,playerTwo;
    private JTextField nameOne,nameTwo;
    private JButton start,exit;

    public JLabel getPlayerOne() {
        return playerOne;
    }

    public JLabel getPlayerTwo() {
        return playerTwo;
    }

    public JTextField getNameOne() {
        return nameOne;
    }

    public JTextField getNameTwo() {
        return nameTwo;
    }

    public JButton getStart() {
        return start;
    }

    public JButton getExit() {
        return exit;
    }

    public PlayersView (){
        this.setTitle("Antes de empezar...");
        this.setResizable(false);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(this.getContentPane());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addComponentsToPane(Container contentPane) {

        playerOne = new JLabel("Jugador 1");
        playerTwo = new JLabel("Jugador 2");
        nameOne = new JTextField(30);
        nameTwo = new JTextField(30);
        start = new JButton("Empezar");
        exit = new JButton("Salir");

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        gc.gridx = 0;
        gc.gridy = 0;
        contentPane.add(playerOne, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        contentPane.add(nameOne, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        contentPane.add(playerTwo, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        contentPane.add(nameTwo, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        contentPane.add(start, gc);

        gc.gridx = 2;
        gc.gridy = 1;
        contentPane.add(exit, gc);

    }


}
