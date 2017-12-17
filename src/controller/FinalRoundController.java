package controller;

import model.Answer;
import model.Player;
import model.Question;
import view.FinalRoundView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FinalRoundController implements ActionListener {

    private FinalRoundView view;
    private Player p1, p2;
    private int controlTurn;
    private ArrayList questions = new ArrayList<Question>();
    private Player player;
    private final int END_FINAL = 10;

    public FinalRoundController(Player p1, Player p2, int controlTurn) {
        if (controlTurn == END_FINAL) {
            JOptionPane.showMessageDialog(null, "Es un empate!");
        } else {
            this.view = new FinalRoundView();
            this.controlTurn = controlTurn;
            this.p1 = p1;
            this.p2 = p2;
            this.player = finalPlayerTurn(controlTurn);
            this.view.res1.addActionListener(this);
            this.view.res2.addActionListener(this);
            this.view.res3.addActionListener(this);
            view.actualPlayer.setText("TURNO DE " + player.getName());
            loadFinalData();
            setQuestions();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.actualPlayer.setText("TURNO DE " + player.getName());
        Question actualQuestion = (Question) questions.get(controlTurn);

        if (e.getSource() == this.view.res1) {
            for (Answer cAns : actualQuestion.getAnswers()) {
                if (view.res1.getText().equals(cAns.getText())) {
                    if (cAns.isCorrectAnswer()) {
                        controlTurn++;
                        player = finalPlayerTurn(controlTurn);
                        JOptionPane.showMessageDialog(null, "Respuesta Correcta!");
                        view.dispose();
                        new FinalRoundController(p1, p2, controlTurn);
                    } else {
                        player.setPts(-1);
                        JOptionPane.showMessageDialog(null, "Respuesta Incorrecta!");
                        new EndGameController(p1, p2);
                        view.dispose();
                    }
                }
            }
        }

        if (e.getSource() == this.view.res2) {
            for (Answer cAns : actualQuestion.getAnswers()) {
                if (view.res2.getText().equals(cAns.getText())) {
                    if (cAns.isCorrectAnswer()) {
                        controlTurn++;
                        player = finalPlayerTurn(controlTurn);
                        JOptionPane.showMessageDialog(null, "Respuesta Correcta!");
                        view.dispose();
                        new FinalRoundController(p1, p2, controlTurn);
                    } else {
                        player.setPts(-1);
                        JOptionPane.showMessageDialog(null, "Respuesta Incorrecta!");
                        new EndGameController(p1, p2);
                        view.dispose();
                    }

                }
            }
        }

        if (e.getSource() == this.view.res3) {
            for (Answer cAns : actualQuestion.getAnswers()) {
                if (view.res3.getText().equals(cAns.getText())) {
                    if (cAns.isCorrectAnswer()) {
                        controlTurn++;
                        player = finalPlayerTurn(controlTurn);
                        JOptionPane.showMessageDialog(null, "Respuesta Correcta!");
                        view.dispose();
                        new FinalRoundController(p1, p2, controlTurn);
                    } else {
                        player.setPts(-1);
                        JOptionPane.showMessageDialog(null, "Respuesta Incorrecta!");
                        new EndGameController(p1, p2);
                        view.dispose();
                    }

                }
            }
        }
    }

    private Player finalPlayerTurn(int controlTurn) {
        if (controlTurn % 2 == 0) {
            return p1;
        } else {
            return p2;
        }
    }

    private void loadFinalData() {
        BufferedReader br = null;
        Path p1 = FileSystems.getDefault().getPath("finalround");

        try {
            br = Files.newBufferedReader(p1, StandardCharsets.UTF_8);
            String linea;
            String[] datos;
            while ((linea = br.readLine()) != null) {
                ArrayList answers = new ArrayList<>();

                datos = linea.split(":");
                for (int i = 1; i < datos.length; i++) {
                    Answer a = new Answer();
                    if (datos[i].contains("$")) {
                        a.setCorrectAnswer(true);
                        datos[i] = datos[i].replace("$", "");
                        a.setText(datos[i]);
                    } else {
                        a.setCorrectAnswer(false);
                        a.setText(datos[i]);
                    }
                    answers.add(a);
                }

                Question q = new Question();
                q.setText(datos[0]);
                q.setAnswers(answers);

                questions.add(q);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setQuestions() throws ArrayIndexOutOfBoundsException {
        Question actualQuestion = (Question) questions.get(controlTurn);
        view.questionText.setText(actualQuestion.getText());
        view.res1.setText(actualQuestion.getAnswers().get(0).getText());
        view.res2.setText(actualQuestion.getAnswers().get(1).getText());
        view.res3.setText(actualQuestion.getAnswers().get(2).getText());
    }

}
