package controller;

import model.Player;
import view.AnswerView;
import view.QuestionsView;

import javax.swing.*;
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

    public QuestionsViewController(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.view = new QuestionsView(p1.getName(), p2.getName(), p1.getPts(), p2.getPts());
        for (int i = 0; i < view.cells.length; i++) {
            this.view.cells[i].addActionListener(this);
        }
        loadData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < view.cells.length; i++) {
            if (view.cells[i] == e.getSource()) {
                view.dispose();
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
            for (int i = 0; i <= 6; i++){
                view.cells[i].setText(datos[i]);
            }
            while ((linea = br.readLine()) != null) {
                datos = linea.split(";");

            }
        } catch (IOException e) {
            System.err.println("Error al acceder la fichero");
        }

    }
}
