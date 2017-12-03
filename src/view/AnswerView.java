package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AnswerView extends JFrame {
    private JPanel answers, question;
    private JLabel questionText;
    private JButton res1, res2, res3, res4;
    private ArrayList<String> arrAnswers = new ArrayList<>();

    public AnswerView() {
        arrAnswers.add("test1");
        arrAnswers.add("test2");
        arrAnswers.add("test3");
        arrAnswers.add("test4");
        this.setTitle("Answer this question");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        addComponentsToPane(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    public void addComponentsToPane(Container pane) {
        answers = new JPanel(new GridLayout(2, 2));
        question = new JPanel(new BorderLayout());

        questionText = new JLabel("Pregunta");
        res1 = new JButton(arrAnswers.get(0));
        res2 = new JButton(arrAnswers.get(1));
        res3 = new JButton(arrAnswers.get(2));
        res4 = new JButton(arrAnswers.get(3));

        answers.setPreferredSize(new Dimension(450,100));
        questionText.setPreferredSize(new Dimension(450, 75));

        answers.add(res1);
        answers.add(res2);
        answers.add(res3);
        answers.add(res4);

        question.add(questionText, BorderLayout.NORTH);
        question.add(answers, BorderLayout.CENTER);

        pane.add(question);

    }
}
