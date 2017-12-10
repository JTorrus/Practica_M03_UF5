package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AnswerView extends JFrame {
    private JPanel answers, question;
    public JLabel questionText;
    public JButton res1, res2, res3;

    public AnswerView() {
        this.setTitle("Responde la pregunta");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        addComponentsToPane(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    public void addComponentsToPane(Container pane) {
        answers = new JPanel(new GridLayout(3, 1));
        question = new JPanel(new BorderLayout());
        questionText = new JLabel();
        questionText.setFont(new Font("Arial", Font.BOLD, 16));
        questionText.setForeground(Color.WHITE);
        question.setBackground(Color.BLUE);

        res1 = new JButton();
        res2 = new JButton();
        res3 = new JButton();

        res1.setBackground(Color.WHITE);
        res1.setForeground(Color.BLACK);
        res1.setFont(new Font("Arial", Font.BOLD, 18));

        res2.setBackground(Color.WHITE);
        res2.setForeground(Color.BLACK);
        res2.setFont(new Font("Arial", Font.BOLD, 18));

        res3.setBackground(Color.WHITE);
        res3.setForeground(Color.BLACK);
        res3.setFont(new Font("Arial", Font.BOLD, 18));

        answers.setPreferredSize(new Dimension(1200,100));
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
