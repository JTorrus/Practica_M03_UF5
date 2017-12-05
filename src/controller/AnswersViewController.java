package controller;

import model.Answer;
import model.Player;
import model.Question;
import view.AnswerView;
import view.QuestionsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnswersViewController implements ActionListener {
    private ArrayList<Answer> answers;
    private Question question;
    private AnswerView view;
    private QuestionsView questionsView;
    private int posX, posY;
    private JButton[][] cells;
    private Player player;
    private HashMap<String, HashMap> categories;

    public AnswersViewController(HashMap categories, JButton[][] cells, int posX, int posY, QuestionsView questionsView, Player player) {
        this.questionsView = questionsView;
        this.view = new AnswerView();
        this.view.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                questionsView.setVisible(true);
            }
        });
        this.categories = categories;
        this.cells = cells;
        this.posX = posX;
        this.posY = posY;
        this.player = player;
        answers = new ArrayList<>();
        question = new Question();
        view.setVisible(true);
        view.res1.addActionListener(this);
        view.res2.addActionListener(this);
        view.res3.addActionListener(this);
        detectQuestion();
    }

    private void detectQuestion() {
        for (Map.Entry<String, HashMap> elem : categories.entrySet()) {
            String key = elem.getKey();
            HashMap<Question, ArrayList<Answer>> value = elem.getValue();
            if (cells[0][posY].getText().equals(key)) {
                for (Map.Entry<Question, ArrayList<Answer>> child : value.entrySet()) {
                    Question childKey = child.getKey();
                    ArrayList<Answer> childValue = child.getValue();

                    if (childKey.getPts() == Integer.valueOf(cells[posX][posY].getText())) {
                        view.questionText.setText(childKey.getText());
                        view.res1.setText(childValue.get(0).getText());
                        view.res2.setText(childValue.get(1).getText());
                        view.res3.setText(childValue.get(2).getText());

                        answers = childValue;
                        question = childKey;
                    }
                }
            }
        }
    }

    public void printPositivePts() {
        if (questionsView.player1.getText().equals(player.getName())) {
            player.setPts(question.getPts());
            questionsView.player1Pts.setText(String.valueOf(player.getPts() + " PTS"));
        } else {
            player.setPts(question.getPts());
            questionsView.player2Pts.setText(String.valueOf(player.getPts() + " PTS"));
        }
    }

    public void printNegativePts() {
        if (questionsView.player1.getText().equals(player.getName())) {
            player.setNegativePts(question.getPts());
            questionsView.player1Pts.setText(String.valueOf(player.getPts() + " PTS"));
        } else {
            player.setNegativePts(question.getPts());
            questionsView.player2Pts.setText(String.valueOf(player.getPts() + " PTS"));
        }
    }

    public void printTurns() {
        if (questionsView.player1.getText().equals(player.getName())) {
            questionsView.player2Board.setBackground(Color.GREEN);
            questionsView.player1Board.setBackground(Color.WHITE);
        } else {
            questionsView.player2Board.setBackground(Color.WHITE);
            questionsView.player1Board.setBackground(Color.GREEN);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        questionsView.setVisible(false);
        if (e.getSource() == view.res1) {
            for (Answer answer : answers) {
                System.out.println(answer.getText());
                if (answer.getText().equals(view.res1.getText())) {
                    if (answer.isCorrectAnswer()) {
                        view.res1.setBackground(Color.GREEN);
                        view.res2.setEnabled(false);
                        view.res3.setEnabled(false);
                        printPositivePts();
                    } else {
                        view.res1.setBackground(Color.RED);
                        for (Answer correctAnswer : answers) {
                            if (correctAnswer.isCorrectAnswer()) {
                                if (correctAnswer.getText().equals(view.res2.getText())) {
                                    view.res2.setBackground(Color.GREEN);
                                    view.res3.setBackground(Color.RED);
                                } else if (correctAnswer.getText().equals(view.res3.getText())) {
                                    view.res2.setBackground(Color.RED);
                                    view.res3.setBackground(Color.GREEN);
                                }
                            }
                        }
                        printNegativePts();
                    }
                }
            }

            printTurns();

        } else if (e.getSource() == view.res2) {
            for (Answer answer : answers) {
                System.out.println(answer.getText());
                if (answer.getText().equals(view.res2.getText())) {
                    if (answer.isCorrectAnswer()) {
                        view.res2.setBackground(Color.GREEN);
                        view.res1.setEnabled(false);
                        view.res3.setEnabled(false);
                        printPositivePts();
                    } else {
                        view.res2.setBackground(Color.RED);
                        for (Answer correctAnswer : answers) {
                            if (correctAnswer.isCorrectAnswer()) {
                                if (correctAnswer.getText().equals(view.res1.getText())) {
                                    view.res1.setBackground(Color.GREEN);
                                    view.res3.setBackground(Color.RED);
                                } else if (correctAnswer.getText().equals(view.res3.getText())) {
                                    view.res1.setBackground(Color.RED);
                                    view.res3.setBackground(Color.GREEN);
                                }
                            }
                        }
                        printNegativePts();
                    }
                }
            }

            printTurns();

        } else if (e.getSource() == view.res3) {
            for (Answer answer : answers) {
                System.out.println(answer.getText());
                if (answer.getText().equals(view.res3.getText())) {
                    if (answer.isCorrectAnswer()) {
                        view.res3.setBackground(Color.GREEN);
                        view.res1.setEnabled(false);
                        view.res2.setEnabled(false);
                        printPositivePts();
                    } else {
                        view.res3.setBackground(Color.RED);
                        for (Answer correctAnswer : answers) {
                            if (correctAnswer.isCorrectAnswer()) {
                                if (correctAnswer.getText().equals(view.res1.getText())) {
                                    view.res1.setBackground(Color.GREEN);
                                    view.res2.setBackground(Color.RED);
                                } else if (correctAnswer.getText().equals(view.res2.getText())) {
                                    view.res1.setBackground(Color.RED);
                                    view.res2.setBackground(Color.GREEN);
                                }
                            }
                        }
                        printNegativePts();
                    }
                }
            }
            printTurns();
        }
    }
}