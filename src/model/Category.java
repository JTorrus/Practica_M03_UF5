package model;

import java.util.ArrayList;

public class Category {
    private String name;
    ArrayList<Question> questions;

    public String getName() {
        return name;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
