package view;

import model.Player;

import javax.swing.*;
import java.awt.*;

public class QuestionsView extends JFrame {
    public JButton[][] cells;
    private JPanel player1Board;
    private JPanel player2Board;
    private JSplitPane playersInfo;
    public JLabel player1, player2, player1Pts, player2Pts;
    private String p1Name, p2Name;
    public Player p1, p2;

    public QuestionsView(String p1, String p2, Player p1Pts, Player p2Pts) {
        this.p1Name = p1;
        this.p2Name = p2;
        this.p1 = p1Pts;
        this.p2 = p2Pts;
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
        player1Pts = new JLabel(String.valueOf(p1.getPts()) + " PTS");
        player2Pts = new JLabel(String.valueOf(p2.getPts()) + " PTS");

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
        cells = new JButton[6][6];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new JButton();
                cells[i][j].setPreferredSize(new Dimension(120,90));

                if (i == 0 && j < 6) {
                    cells[i][j].setEnabled(false);
                    cells[i][j].setBackground(Color.ORANGE);
                    cells[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 5, 2, Color.BLACK));
                } else {
                    cells[i][j].setBackground(Color.BLUE);
                    cells[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
                    cells[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                cellsPanel.add(cells[i][j]);
            }
        }

        pane.add(cellsPanel, BorderLayout.CENTER);
        pane.add(playersInfo, BorderLayout.SOUTH);

    }
}
