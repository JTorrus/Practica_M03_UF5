package view;

import model.Player;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.green;

/* La vista QuestionsView dispone de un BorderLayout segmentado en un Panel con GridLayout para mostrar todas las categorías y preguntas y un JSplitPane en la posición SOUTH para poder mostrar la información
actual de los jugadores en cada costado de dicho panel
 */
public class QuestionsView extends JFrame {
    private JButton[][] cells;
    private JPanel player1Board;
    private JPanel player2Board;
    private JSplitPane playersInfo;
    private JLabel player1, player2, player1Pts, player2Pts;
    private Player p1, p2;

    public JButton[][] getCells() {
        return cells;
    }

    public JPanel getPlayer1Board() {
        return player1Board;
    }

    public JPanel getPlayer2Board() {
        return player2Board;
    }

    public JLabel getPlayer1Pts() {
        return player1Pts;
    }

    public JLabel getPlayer2Pts() {
        return player2Pts;
    }

    public QuestionsView(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.setTitle("JeoPardy");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        addComponentsToPane(this.getContentPane());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addComponentsToPane(Container pane) {
        pane.setLayout(new BorderLayout(8, 8));
        player1 = new JLabel(p1.getName());
        player2 = new JLabel(p2.getName());
        player1Pts = new JLabel(String.valueOf(p1.getPts()) + " PTS");
        player2Pts = new JLabel(String.valueOf(p2.getPts()) + " PTS");

        player1.setHorizontalAlignment(SwingConstants.CENTER);
        player2.setHorizontalAlignment(SwingConstants.CENTER);
        player1Pts.setHorizontalAlignment(SwingConstants.CENTER);
        player2Pts.setHorizontalAlignment(SwingConstants.CENTER);

        player1.setFont(new Font("Arial", Font.BOLD, 25));
        player1.setForeground(Color.BLACK);
        player2.setFont(new Font("Arial", Font.BOLD, 25));
        player2.setForeground(Color.BLACK);
        player1Pts.setFont(new Font("Arial", Font.BOLD, 13));
        player1Pts.setForeground(Color.BLACK);
        player2Pts.setFont(new Font("Arial", Font.BOLD, 13));
        player2Pts.setForeground(Color.BLACK);

        player1Board = new JPanel();
        player2Board = new JPanel();

        player1Board.setLayout(new BorderLayout());
        player2Board.setLayout(new BorderLayout());

        player1Board.setBackground(Color.GREEN);
        player2Board.setBackground(Color.WHITE);

        player1Board.add(player1);
        player2Board.add(player2);
        player1Board.add(player1Pts, BorderLayout.SOUTH);
        player2Board.add(player2Pts, BorderLayout.SOUTH);

        playersInfo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, player1Board, player2Board);
        playersInfo.setResizeWeight(.5d);
        playersInfo.setEnabled(false);

        JPanel cellsPanel = new JPanel(new GridLayout(6, 6));
        cells = new JButton[6][6];

        pane.setSize(new Dimension(600, 480));

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new JButton();
                cells[i][j].setPreferredSize(new Dimension(200,100));

                if (i == 0 && j < 6) {
                    cells[i][j].setEnabled(false);
                    cells[i][j].setBackground(Color.WHITE);
                    cells[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                    cells[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 5, 2, Color.BLACK));
                } else {
                    cells[i][j].setBackground(Color.BLUE);
                    cells[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                    cells[i][j].setForeground(Color.WHITE);
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
