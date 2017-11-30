package controller;

import model.Player;
import utilities.GameManager;
import view.QuestionsView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class QuestionsViewController implements ActionListener {
    private QuestionsView view;
    private Player p1;
    private Player p2;
    private HashMap m1;
    private HashMap m2;
    private int turn;

    public QuestionsViewController(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.turn = -1;
        this.view = new QuestionsView(p1, p2);

        for (int i = 0; i < view.cells.length; i++) {
            for (int j = 0; j < view.cells[i].length; j++) {
                this.view.cells[i][j].addActionListener(this);
            }
        }

        loadData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < view.cells.length; i++) {
            for (int j = 0; j < view.cells[i].length; j++) {
                if (view.cells[i][j] == e.getSource()) {
                    view.cells[i][j].setEnabled(false);
                    view.cells[i][j].setBackground(Color.BLACK);
                    turn++;
                    GameManager gc = new GameManager(p1, p2, turn);
                    AnswersViewController aw = new AnswersViewController();
                    if (gc.actualTurn(turn)) {
                        System.out.println("Turno de " + p2.getName());
                        p1.setPts(p1.getPts() + 100);
                        view.player1Pts.setText(String.valueOf(p1.getPts()));
                    } else {
                        System.out.println("Turno de " + p1.getName());
                        p2.setPts(p1.getPts() + 100);
                        view.player2Pts.setText(String.valueOf(p2.getPts()));
                    }
                }
            }
        }
    }

    private void loadData() {
        BufferedReader br = null;
        String[] datos = null;
        HashMap m1 = new HashMap();
        HashMap m2 = new HashMap();
        Path p1 = FileSystems.getDefault().getPath("./categories.txt");
        try {
            br = Files.newBufferedReader(p1, StandardCharsets.UTF_8);
            String linea;
            linea = br.readLine();
            datos = linea.split(";");
            for (int i = 0; i < 6; i++){
                view.cells[i][i].setText(datos[i]);
            }
            while ((linea = br.readLine()) != null) {
                datos = linea.split(";");

            }
        } catch (IOException e) {
            System.err.println("Error al acceder la fichero");
        }

    }
}
