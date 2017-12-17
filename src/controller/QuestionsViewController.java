package controller;

import model.Answer;
import model.Category;
import model.Player;
import model.Question;
import utilities.GameManager;
import view.QuestionsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class QuestionsViewController implements ActionListener {
    private QuestionsView view;
    private Player p1;
    private Player p2;
    private Player player;
    private HashMap<String, TreeMap> categories = new HashMap<String, TreeMap>();
    private AnswersViewController answersViewController;
    private GameManager gameManager;

    public QuestionsView getView() {
        return view;
    }

    public QuestionsViewController(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.view = new QuestionsView(p1, p2);
        gameManager = new GameManager(p1, p2, this);

        for (int i = 0; i < view.getCells().length; i++) {
            for (int j = 0; j < view.getCells()[i].length; j++) {
                this.view.getCells()[i][j].addActionListener(this);
            }
        }

        loadData();
        setDataToButtons();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < view.getCells().length; i++) {
            for (int j = 0; j < view.getCells()[i].length; j++) {
                if (view.getCells()[i][j] == e.getSource()) {
                    int posX = i;
                    int posY = j;
                    view.getCells()[i][j].setEnabled(false);
                    view.getCells()[i][j].setBackground(Color.BLACK);
                    view.setEnabled(false);
                    answersViewController = new AnswersViewController(categories, view.getCells(), posX, posY, this);
                }
            }
        }

        answersViewController.detectQuestion();
        player = gameManager.actualPlayer(gameManager.getTurn());
        gameManager.nextTurn();
    }

    private void loadData() {
        BufferedReader br = null;
        String[] datos = null;
        Path p1 = FileSystems.getDefault().getPath("./categories.txt");
        Path p2 = FileSystems.getDefault().getPath("namecategories.txt");

        try {
            br = Files.newBufferedReader(p1, StandardCharsets.UTF_8);
            String linea;
            ArrayList<Question> aux = new ArrayList<>();
            ArrayList<Answer> auxA;
            ArrayList<Question> auxQ = new ArrayList<>();

            while ((linea = br.readLine()) != null) {
                auxA = new ArrayList<>();

                datos = linea.split(":");
                for (int i = 3; i < datos.length; i++) {
                    Answer a = new Answer();
                    if (datos[i].contains("$")) {
                        a.setCorrectAnswer(true);
                        datos[i] = datos[i].replace("$", "");
                        a.setText(datos[i]);
                    } else {
                        a.setCorrectAnswer(false);
                        a.setText(datos[i]);
                    }
                    auxA.add(a);
                }

                Question q = new Question();
                q.setText(datos[2]);
                q.setAnswers(auxA);
                q.setCategory(datos[0]);
                q.setPts(Integer.parseInt(datos[1]));
                auxQ.add(q);
            }

            br = Files.newBufferedReader(p2, StandardCharsets.UTF_8);
            while ((linea = br.readLine()) != null) {
                datos = linea.split(":");
                for (int i = 0; i < datos.length; i++) {
                    Category c = new Category();
                    c.setName(datos[i]);
                    for (Question item : auxQ) {
                        if (item.getCategory().equals(datos[i])) {
                            aux.add(item);
                        }
                    }
                    c.setQuestions(aux);
                    TreeMap questions = new TreeMap<Integer, Question>();
                    for (int j = 0; j < c.getQuestions().size(); j++) {
                        questions.put(c.getQuestions().get(j).getPts(), c.getQuestions().get(j));
                    }

                    categories.put(c.getName(), questions);
                    aux.clear();
                }
            }


        } catch (IOException e) {
            System.out.println("No se ha podido leer el fichero");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar BufferedReader");
            }
        }


    }

    public void setDataToButtons() {
        ArrayList nameCategories = new ArrayList<String>();
        ArrayList price = new ArrayList<Integer>();
        for (Map.Entry<String, TreeMap> elem : categories.entrySet()) {
            String key = elem.getKey();
            nameCategories.add(key);
            TreeMap<Integer, Question> value = elem.getValue();
            for (Map.Entry<Integer, Question> child : value.entrySet()) {
                int childKey = child.getKey();
                price.add(childKey);
            }
        }


        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < view.getCells()[i].length; j++) {
                view.getCells()[i][j].setText((String) nameCategories.get(j));
            }
        }

        for (int i = 0; i < view.getCells().length; i++) {
            for (int j = 1; j < view.getCells()[i].length; j++) {
                if (view.getCells()[j][i].isEnabled()) {
                    view.getCells()[j][i].setText(String.valueOf(price.get(j - 1)));
                }

            }
        }
    }

    public void setDataToButtonsDouble() {
        if (gameManager.doubleRound()) {
            int count = 0;
            Iterator it;
            it = categories.entrySet().iterator();
            ArrayList doublePrice = new ArrayList<TreeMap>();
            while (it.hasNext()) {
                HashMap.Entry e = (HashMap.Entry) it.next();
                Iterator secondMap = ((TreeMap) e.getValue()).entrySet().iterator();
                TreeMap tm = new TreeMap();
                while (secondMap.hasNext()) {
                    Map.Entry e2 = (Map.Entry) secondMap.next();
                    Question qt = (Question) e2.getValue();
                    qt.setPts(qt.getPts() * 2);
                    tm.put((Integer) e2.getKey() * 2, qt);
                }
                doublePrice.add(tm);
            }

            it = categories.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry e = (HashMap.Entry) it.next();
                e.setValue(doublePrice.get(count));
                count++;
            }
            JOptionPane.showMessageDialog(null, "Empieza la JeoPardy DoubleRound");
            setDataToButtons();
        }
    }

    public void printPositivePts() {
        if (p1.getName().equals(player.getName())) {
            player.setPts(answersViewController.getQuestion().getPts());
            view.getPlayer1Pts().setText(String.valueOf(player.getPts()) + " PTS");
        } else {
            player.setPts(answersViewController.getQuestion().getPts());
            view.getPlayer2Pts().setText(String.valueOf(player.getPts()) + " PTS");
        }
    }

    public void printNegativePts() {
        if (p1.getName().equals(player.getName())) {
            player.setNegativePts(answersViewController.getQuestion().getPts());
            view.getPlayer1Pts().setText(String.valueOf(player.getPts()) + " PTS");
        } else {
            player.setNegativePts(answersViewController.getQuestion().getPts());
            view.getPlayer2Pts().setText(String.valueOf(player.getPts() + " PTS"));
        }
    }

    public void printTurns() {
        if (p1.getName().equals(player.getName())) {
            view.getPlayer2Board().setBackground(Color.GREEN);
            view.getPlayer1Board().setBackground(Color.WHITE);
        } else {
            view.getPlayer2Board().setBackground(Color.WHITE);
            view.getPlayer1Board().setBackground(Color.GREEN);
        }
    }

    public void endOfGame() {
        if (gameManager.endOfGame()) {
            if (p1.getPts() == p2.getPts()) {
                view.dispose();
                JOptionPane.showMessageDialog(null, "Hay un empate! Empieza la Final Round");
                new FinalRoundController(p1, p2,0);
            } else {
                view.dispose();
                JOptionPane.showMessageDialog(null, "Partida Finalizada!");
                new EndGameController(p1, p2);
            }

        }
    }
}
