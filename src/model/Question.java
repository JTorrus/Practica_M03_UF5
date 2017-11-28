package model;

import java.util.ArrayList;

public class Question {
    private String text;
    private int pts;
    private ArrayList<Answer> answers;

    public Question(String text, int pts) {
        this.text = text;
        this.pts = pts;
        answers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", pts=" + pts +
                '}';
    }
}
