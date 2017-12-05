package controller;

import model.Answer;
import model.Category;
import model.Player;
import model.Question;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class QuestionsViewController implements ActionListener {
    private QuestionsView view;
    private Player p1;
    private Player p2;
    private AnswersViewController answersViewController;
    private Player player;
    private HashMap categories = new HashMap<String, HashMap>();
    private GameManager gameManager;

    public QuestionsViewController(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.view = new QuestionsView(p1, p2);
        gameManager = new GameManager(p1, p2);

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
                    int posX = i;
                    int posY = j;
                    view.cells[i][j].setEnabled(false);
                    view.cells[i][j].setBackground(Color.BLACK);
                    System.out.println(posX + " " + posY);
                    answersViewController = new AnswersViewController(categories, view.cells, posX, posY, gameManager, this);
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
                    HashMap questions = new HashMap<Question, ArrayList<Answer>>();
                    for (int j = 0; j < c.getQuestions().size(); j++) {
                        questions.put(c.getQuestions().get(j), c.getQuestions().get(j).getAnswers());
                    }

                    categories.put(c.getName(), questions);
                    aux.clear();
                }
            }


            Iterator it;
            for (int i = 0; i < view.cells.length; i++) {
                it = categories.entrySet().iterator();
                while (it.hasNext()) {
                    for (int j = 0; j < view.cells[i].length; j++) {
                        HashMap.Entry e = (HashMap.Entry) it.next();
                        Iterator secondMap = ((HashMap) e.getValue()).entrySet().iterator();
                        while (secondMap.hasNext()) {
                            HashMap.Entry e2 = (HashMap.Entry) secondMap.next();
                            switch (i) {
                                case 0:
                                    view.cells[i][j].setText((String) e.getKey());
                                    break;
                                case 1:
                                    Question c = c = (Question) e2.getKey();
                                    if (c.getPts() == (i * 100)) {
                                        view.cells[i][j].setText(String.valueOf(c.getPts()));
                                    }
                                    break;
                                case 2:
                                    c = (Question) e2.getKey();
                                    if (c.getPts() == (i * 100)) {
                                        view.cells[i][j].setText(String.valueOf(c.getPts()));
                                    }
                                    break;
                                case 3:
                                    c = (Question) e2.getKey();
                                    if (c.getPts() == (i * 100)) {
                                        view.cells[i][j].setText(String.valueOf(c.getPts()));
                                    }
                                    break;
                                case 4:
                                    c = (Question) e2.getKey();
                                    if (c.getPts() == (i * 100)) {
                                        view.cells[i][j].setText(String.valueOf(c.getPts()));
                                    }
                                    break;
                                case 5:
                                    c = (Question) e2.getKey();
                                    if (c.getPts() == (i * 100)) {
                                        view.cells[i][j].setText(String.valueOf(c.getPts()));
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void printPositivePts() {
        if (p1.getName().equals(player.getName())) {
            player.setPts(answersViewController.question.getPts());
            view.player1Pts.setText(String.valueOf(player.getPts()) + " PTS");
        } else {
            player.setPts(answersViewController.question.getPts());
            view.player2Pts.setText(String.valueOf(player.getPts()) + " PTS");
        }
    }

    public void printNegativePts() {
        if (p1.getName().equals(player.getName())) {
            player.setNegativePts(answersViewController.question.getPts());
            view.player1Pts.setText(String.valueOf(player.getPts()) + " PTS");
        } else {
            player.setNegativePts(answersViewController.question.getPts());
            view.player2Pts.setText(String.valueOf(player.getPts() + " PTS"));
        }
    }

    public void printTurns() {
        if (p1.getName().equals(player.getName())) {
            view.player2Board.setBackground(Color.GREEN);
            view.player1Board.setBackground(Color.WHITE);
        } else {
            view.player2Board.setBackground(Color.WHITE);
            view.player1Board.setBackground(Color.GREEN);
        }
    }
}
