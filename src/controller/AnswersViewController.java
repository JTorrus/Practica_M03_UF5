package controller;

import model.Answer;
import view.AnswerView;

import java.util.ArrayList;

public class AnswersViewController {
    private ArrayList<Answer> answers;
    private AnswerView view;

    public AnswersViewController() {
        this.view = new AnswerView();
        answers = new ArrayList<>();
        view.setVisible(true);
    }
}
