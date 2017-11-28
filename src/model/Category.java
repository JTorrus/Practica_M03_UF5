package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Category {
    private String name;
    ArrayList<Question> questions;

    public Category(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }
}
