package model;

import java.util.HashMap;

public class Category {
    private String name;
    private HashMap<Integer, HashMap> questions;

    public Category(String name) {
        this.name = name;
        this.questions = new HashMap<>();
    }
}
