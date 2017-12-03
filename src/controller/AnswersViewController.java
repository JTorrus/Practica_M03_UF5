package controller;

import model.Answer;
import model.Question;
import view.AnswerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AnswersViewController implements ActionListener {
    private ArrayList<Answer> answers;
    private AnswerView view;
    private int posX, posY;
    private JButton[][] cells;
    private int turn;
    private HashMap<String, HashMap> categories;

    public AnswersViewController(HashMap categories, JButton[][] cells, int posX, int posY) {
        this.view = new AnswerView();
        this.categories = categories;
        this.cells = cells;
        this.posX = posX;
        this.posY = posY;
        this.turn = -1;
        answers = new ArrayList<>();
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
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        turn++;
        if (e.getSource() == view.res1) {
            for (Answer answer : answers) {
                System.out.println(answer.getText());
                if (answer.getText().equals(view.res1.getText())) {
                    if (answer.isCorrectAnswer()) {
                        view.res1.setBackground(Color.GREEN);
                    } else {
                        view.res1.setBackground(Color.RED);
                    }
                }
            }
        } else if (e.getSource() == view.res2) {
            for (Answer answer : answers) {
                System.out.println(answer.getText());
                if (answer.getText().equals(view.res2.getText())) {
                    if (answer.isCorrectAnswer()) {
                        view.res2.setBackground(Color.GREEN);
                    } else {
                        view.res2.setBackground(Color.RED);
                    }
                }
            }
        } else if (e.getSource() == view.res3) {
            for (Answer answer : answers) {
                System.out.println(answer.getText());
                if (answer.getText().equals(view.res3.getText())) {
                    if (answer.isCorrectAnswer()) {
                        view.res3.setBackground(Color.GREEN);
                    } else {
                        view.res3.setBackground(Color.RED);
                    }
                }
            }
        }
    }
}