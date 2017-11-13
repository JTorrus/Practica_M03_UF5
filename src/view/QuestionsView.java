package view;

import javax.swing.*;
import java.awt.*;

public class QuestionsView extends JFrame {
    private JButton[] cells;

    public QuestionsView() {
        this.setTitle("JeoPardy");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        addComponentsToPane(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    void addComponentsToPane(Container pane) {
        pane.setLayout(new BorderLayout(8, 8));

        JPanel cellsPanel = new JPanel(new GridLayout(6, 6));
        final int GRID_SIZE = 36;

        cells = new JButton[GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            cells[i] = new JButton();
            cells[i].setPreferredSize(new Dimension(120,90));

            if (i < 6) {
                cells[i].setEnabled(false);
            }

            cellsPanel.add(cells[i]);
        }

        pane.add(cellsPanel);

    }
}
