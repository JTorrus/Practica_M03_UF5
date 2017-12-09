package controller;

import model.Answer;
import model.Player;
import model.Question;
import utilities.GameManager;
import view.AnswerView;
import view.QuestionsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;

public class AnswersViewController implements ActionListener {
    private ArrayList<Answer> answers;
    public Question question;
    private QuestionsViewController questionsViewController;
    private AnswerView view;
    private GameManager gameManager;
    private int posX, posY;
    private JButton[][] cells;
    private HashMap<String, TreeMap<Integer, Question>> categories;

    public AnswersViewController(HashMap categories, JButton[][] cells, int posX, int posY, GameManager gameManager, QuestionsViewController questionsViewController) {
        this.view = new AnswerView();
        this.questionsViewController = questionsViewController;
        this.categories = categories;
        this.cells = cells;
        this.posX = posX;
        this.posY = posY;
        answers = new ArrayList<>();
        question = new Question();
        this.gameManager = gameManager;
        view.setVisible(true);
        view.res1.addActionListener(this);
        view.res2.addActionListener(this);
        view.res3.addActionListener(this);
        detectQuestion();
    }

    public void detectQuestion() {
        for (Map.Entry<String, TreeMap<Integer, Question>> elem : categories.entrySet()) {
            String key = elem.getKey();
            TreeMap<Integer, Question> value = elem.getValue();
            if (cells[0][posY].getText().equals(key)) {
                for (Map.Entry<Integer, Question> child : value.entrySet()) {
                    int childKey = child.getKey();
                    Question childValue = child.getValue();

                    if (childKey == Integer.valueOf(cells[posX][posY].getText())) {
                        view.questionText.setText(childValue.getText());
                        view.res1.setText(childValue.getAnswers().get(0).getText());
                        view.res2.setText(childValue.getAnswers().get(1).getText());
                        view.res3.setText(childValue.getAnswers().get(2).getText());

                        answers = childValue.getAnswers();
                        question = childValue;
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.res1) {
            for (Answer answer : answers) {
                System.out.println(answer.getText());
                if (answer.getText().equals(view.res1.getText())) {
                    if (answer.isCorrectAnswer()) {
                        view.res1.setBackground(Color.GREEN);
                        view.res2.setEnabled(false);
                        view.res3.setEnabled(false);
                        questionsViewController.printPositivePts();
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
                        questionsViewController.printNegativePts();
                    }
                }
            }

            questionsViewController.printTurns();

        } else if (e.getSource() == view.res2) {
            for (Answer answer : answers) {
                System.out.println(answer.getText());
                if (answer.getText().equals(view.res2.getText())) {
                    if (answer.isCorrectAnswer()) {
                        view.res2.setBackground(Color.GREEN);
                        view.res1.setEnabled(false);
                        view.res3.setEnabled(false);
                        questionsViewController.printPositivePts();
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
                        questionsViewController.printNegativePts();
                    }
                }
            }

            questionsViewController.printTurns();

        } else if (e.getSource() == view.res3) {
            for (Answer answer : answers) {
                System.out.println(answer.getText());
                if (answer.getText().equals(view.res3.getText())) {
                    if (answer.isCorrectAnswer()) {
                        view.res3.setBackground(Color.GREEN);
                        view.res1.setEnabled(false);
                        view.res2.setEnabled(false);
                        questionsViewController.printPositivePts();
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
                        questionsViewController.printNegativePts();
                    }
                }
            }
            questionsViewController.printTurns();
        }

        new Timer().schedule(new TimerTask() {
            public void run() {
                questionsViewController.view.setEnabled(true);
                view.dispose();
            }
        }, 2000);
    }
}