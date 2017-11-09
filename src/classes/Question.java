package classes;

import java.util.ArrayList;

public class Question {
    private String name;
    private int pts;
    private ArrayList<Answer> answers;

    public Question(String name, int pts, ArrayList<Answer> answers) {
        this.name = name;
        this.pts = pts;
        this.answers = answers;
    }
}
