package controller;

import view.AnswerView;

public class AnswersViewController {
    private AnswerView view;

    public AnswersViewController() {
        this.view = new AnswerView();
        view.setVisible(true);
    }
}
