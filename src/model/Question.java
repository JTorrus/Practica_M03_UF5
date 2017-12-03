package model;

import java.util.ArrayList;

public class Question {
    private String text,category;
    private int pts;
    private ArrayList<Answer> answers;

    public String getText() {
        return text;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", pts=" + pts +
                ", answers=" + answers +
                '}';
    }
}
