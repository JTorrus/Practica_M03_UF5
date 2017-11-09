package classes;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Question> questions;

    public Category(String name, ArrayList<Question> questions) {
        this.name = name;
        this.questions = questions;
    }
}
