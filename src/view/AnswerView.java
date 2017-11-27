package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AnswerView extends JFrame {
    private JPanel answers;
    private JButton res1, res2, res3, res4;
    private ArrayList<String> arrAnswers = new ArrayList<>();

    public AnswerView(JPanel answers) {
        arrAnswers.add("test1");
        arrAnswers.add("test2");
        arrAnswers.add("test3");
        arrAnswers.add("test4");
        this.setTitle("Answer this question");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        addComponentsToPane(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    public void addComponentsToPane(Container pane) {
        answers = new JPanel(new BorderLayout());
        res1 = new JButton(arrAnswers.get(0));
        res2 = new JButton(arrAnswers.get(1));
        res3 = new JButton(arrAnswers.get(2));
        res4 = new JButton(arrAnswers.get(3));

        answers.add(res1);
        answers.add(res2);
        answers.add(res3);
        answers.add(res4);

        pane.add(answers);

    }
}
