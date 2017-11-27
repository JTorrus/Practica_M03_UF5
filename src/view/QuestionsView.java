package view;

import javax.swing.*;
import java.awt.*;

public class QuestionsView extends JFrame {
    private JButton[] cells;
    private JPanel player1Board;
    private JPanel player2Board;
    private JSplitPane playersInfo;
    private JLabel player1, player2, player1Pts, player2Pts;
    private String p1Name, p2Name;
    private int p1Pts, p2Pts;

    public QuestionsView(String p1, String p2, int p1Pts, int p2Pts) {
        this.p1Name = p1;
        this.p2Name = p2;
        this.p1Pts = p1Pts;
        this.p2Pts = p2Pts;
        this.setTitle("JeoPardy");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        addComponentsToPane(this.getContentPane());
        this.pack();
        this.setVisible(true);

    }

    void addComponentsToPane(Container pane) {
        pane.setLayout(new BorderLayout(8, 8));

        player1 = new JLabel(p1Name);
        player2 = new JLabel(p2Name);
        player1Pts = new JLabel(String.valueOf(p1Pts) + " PTS");
        player2Pts = new JLabel(String.valueOf(p2Pts) + " PTS");

        player1.setHorizontalAlignment(SwingConstants.CENTER);
        player2.setHorizontalAlignment(SwingConstants.CENTER);
        player1Pts.setHorizontalAlignment(SwingConstants.CENTER);
        player2Pts.setHorizontalAlignment(SwingConstants.CENTER);

        player1Board = new JPanel();
        player2Board = new JPanel();

        player1Board.setLayout(new BorderLayout());
        player2Board.setLayout(new BorderLayout());

        player1Board.add(player1);
        player2Board.add(player2);
        player1Board.add(player1Pts, BorderLayout.SOUTH);
        player2Board.add(player2Pts, BorderLayout.SOUTH);

        playersInfo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, player1Board, player2Board);
        playersInfo.setDividerLocation(355);

        JPanel cellsPanel = new JPanel(new GridLayout(6, 6));
        final int GRID_SIZE = 36;
        cells = new JButton[GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            cells[i] = new JButton();
            cells[i].setPreferredSize(new Dimension(120,90));

            if (i < 6) {
                cells[i].setEnabled(false);
                cells[i].setBackground(Color.ORANGE);
                cells[i].setBorder(BorderFactory.createMatteBorder(2, 2, 5, 2, Color.BLACK));
            } else {
                cells[i].setBackground(Color.BLUE);
                cells[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
                cells[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            cellsPanel.add(cells[i]);
        }

        pane.add(cellsPanel, BorderLayout.CENTER);
        pane.add(playersInfo, BorderLayout.SOUTH);

    }
}
