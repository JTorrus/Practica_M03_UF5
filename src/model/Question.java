package model;

import java.util.ArrayList;

public class Question {
    private String text;
    private int pts;
    private ArrayList<Answer> answers;

    public Question(String text, int pts, ArrayList <Answer> answers) {
        this.text = text;
        this.pts = pts;
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", pts=" + pts +
                '}';
    }
}
