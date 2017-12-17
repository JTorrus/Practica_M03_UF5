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
    private Question question;
    private QuestionsViewController questionsViewController;
    private AnswerView view;
    private int posX, posY;
    private JButton[][] cells;
    private HashMap<String, TreeMap<Integer, Question>> categories;

    public Question getQuestion() {
        return question;
    }

    public AnswersViewController(HashMap categories, JButton[][] cells, int posX, int posY, QuestionsViewController questionsViewController) {
        this.view = new AnswerView();
        this.questionsViewController = questionsViewController;
        this.categories = categories;
        this.cells = cells;
        this.posX = posX;
        this.posY = posY;
        answers = new ArrayList<>();
        question = new Question();
        view.setVisible(true);
        view.getRes1().addActionListener(this);
        view.getRes2().addActionListener(this);
        view.getRes3().addActionListener(this);
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
                        view.getQuestionText().setText(childValue.getText());
                        view.getRes1().setText(childValue.getAnswers().get(0).getText());
                        view.getRes2().setText(childValue.getAnswers().get(1).getText());
                        view.getRes3().setText(childValue.getAnswers().get(2).getText());

                        answers = childValue.getAnswers();
                        question = childValue;
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getRes1()) {
            for (Answer answer : answers) {
                if (answer.getText().equals(view.getRes1().getText())) {
                    if (answer.isCorrectAnswer()) {
                        view.getRes1().setBackground(Color.GREEN);
                        view.getRes2().setEnabled(false);
                        view.getRes3().setEnabled(false);
                        questionsViewController.printPositivePts();
                    } else {
                        view.getRes1().setBackground(Color.RED);
                        for (Answer correctAnswer : answers) {
                            if (correctAnswer.isCorrectAnswer()) {
                                if (correctAnswer.getText().equals(view.getRes2().getText())) {
                                    view.getRes2().setBackground(Color.GREEN);
                                    view.getRes3().setBackground(Color.RED);
                                } else if (correctAnswer.getText().equals(view.getRes3().getText())) {
                                    view.getRes2().setBackground(Color.RED);
                                    view.getRes3().setBackground(Color.GREEN);
                                }
                            }
                        }
                        questionsViewController.printNegativePts();
                    }
                }
            }
            questionsViewController.printTurns();

        } else if (e.getSource() == view.getRes2()) {
            for (Answer answer : answers) {
                if (answer.getText().equals(view.getRes2().getText())) {
                    if (answer.isCorrectAnswer()) {
                        view.getRes2().setBackground(Color.GREEN);
                        view.getRes1().setEnabled(false);
                        view.getRes3().setEnabled(false);
                        questionsViewController.printPositivePts();
                    } else {
                        view.getRes2().setBackground(Color.RED);
                        for (Answer correctAnswer : answers) {
                            if (correctAnswer.isCorrectAnswer()) {
                                if (correctAnswer.getText().equals(view.getRes1().getText())) {
                                    view.getRes1().setBackground(Color.GREEN);
                                    view.getRes3().setBackground(Color.RED);
                                } else if (correctAnswer.getText().equals(view.getRes3().getText())) {
                                    view.getRes1().setBackground(Color.RED);
                                    view.getRes3().setBackground(Color.GREEN);
                                }
                            }
                        }
                        questionsViewController.printNegativePts();
                    }
                }
            }

            questionsViewController.printTurns();

        } else if (e.getSource() == view.getRes3()) {
            for (Answer answer : answers) {
                if (answer.getText().equals(view.getRes3().getText())) {
                    if (answer.isCorrectAnswer()) {
                        view.getRes3().setBackground(Color.GREEN);
                        view.getRes1().setEnabled(false);
                        view.getRes2().setEnabled(false);
                        questionsViewController.printPositivePts();
                    } else {
                        view.getRes3().setBackground(Color.RED);
                        for (Answer correctAnswer : answers) {
                            if (correctAnswer.isCorrectAnswer()) {
                                if (correctAnswer.getText().equals(view.getRes1().getText())) {
                                    view.getRes1().setBackground(Color.GREEN);
                                    view.getRes2().setBackground(Color.RED);
                                } else if (correctAnswer.getText().equals(view.getRes2().getText())) {
                                    view.getRes1().setBackground(Color.RED);
                                    view.getRes2().setBackground(Color.GREEN);
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
                questionsViewController.getView().setEnabled(true);
                view.dispose();
            }
        }, 2000);
        questionsViewController.setDataToButtonsDouble();
        questionsViewController.endOfGame();
    }
}