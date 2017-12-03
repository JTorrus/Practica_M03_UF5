package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AnswerView extends JFrame {
    private JPanel answers, question;
    public JLabel questionText;
    public JButton res1, res2, res3;

    public AnswerView() {
        this.setTitle("Answer this question");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        addComponentsToPane(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    public void addComponentsToPane(Container pane) {
        answers = new JPanel(new GridLayout(3, 1));
        question = new JPanel(new BorderLayout());

        questionText = new JLabel("Pregunta");
        res1 = new JButton();
        res2 = new JButton();
        res3 = new JButton();

        answers.setPreferredSize(new Dimension(450,100));
        questionText.setPreferredSize(new Dimension(720, 75));
        questionText.setHorizontalAlignment(SwingConstants.CENTER);

        answers.add(res1);
        answers.add(res2);
        answers.add(res3);

        question.add(questionText, BorderLayout.NORTH);
        question.add(answers, BorderLayout.CENTER);

        pane.add(question);

    }
}
