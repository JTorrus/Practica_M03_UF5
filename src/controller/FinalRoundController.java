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

// Controlador encargado de empezar la Final Jeopardy Round
public class FinalRoundController implements ActionListener {

    private FinalRoundView view;
    private Player p1, p2;
    private int controlTurn;
    private ArrayList questions = new ArrayList<Question>();
    private Player player;
    private final int END_FINAL = 10;

    /* En el constructor de la clase pasamos los dos jugadoores y el turno actual. Dependiendo de si el turno ha llegado a su final, mostraremos una ventana informando de un empate o de lo contrario
    definiremos los atributos hasta que se termine la partida, ya que dicho controlador lo  llamaremos recursivamente más adelante
    */
    public FinalRoundController(Player p1, Player p2, int controlTurn) {
        if (controlTurn == END_FINAL) {
            JOptionPane.showMessageDialog(null, "Es un empate!");
        } else {
            this.view = new FinalRoundView();
            this.controlTurn = controlTurn;
            this.p1 = p1;
            this.p2 = p2;
            this.player = finalPlayerTurn(controlTurn);
            this.view.getRes1().addActionListener(this);
            this.view.getRes2().addActionListener(this);
            this.view.getRes3().addActionListener(this);
            view.getActualPlayer().setText("TURNO DE " + player.getName());
            loadFinalData();
            setQuestions();
        }
    }

    /* En el método actionPerformed controlamos tanto el turno actual del jugador como su respuesta, comprobamos si ha acertado su pregunta o no. De ser así el juego continuará con una llamada recursiva
    al controlador, de lo contrario abriremos un nuevo controlador de final de partida para mostrar los resultados
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        view.getActualPlayer().setText("TURNO DE " + player.getName());
        Question actualQuestion = (Question) questions.get(controlTurn);

        if (e.getSource() == this.view.getRes1()) {
            for (Answer cAns : actualQuestion.getAnswers()) {
                if (view.getRes1().getText().equals(cAns.getText())) {
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

        if (e.getSource() == this.view.getRes2()) {
            for (Answer cAns : actualQuestion.getAnswers()) {
                if (view.getRes2().getText().equals(cAns.getText())) {
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

        if (e.getSource() == this.view.getRes3()) {
            for (Answer cAns : actualQuestion.getAnswers()) {
                if (view.getRes3().getText().equals(cAns.getText())) {
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

    /* Con el método finalPlayerTurn realizamos una comprobación basada en pariedad para determinar a qué jugador le toca responder
    */
    private Player finalPlayerTurn(int controlTurn) {
        if (controlTurn % 2 == 0) {
            return p1;
        } else {
            return p2;
        }
    }

    /* El método loadFinalData() nos permite recoger toda la información del archivo que contiene todas las preguntas y respuestas, iteramos sobre su longitud y vamos almacenando su contenido en
    un ArrayList, para determinar que respuesta es la correcta hemos definido un carácter especial que nos servirá como referencia.
     */
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
            System.out.println("No se ha podido leer el fichero");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el BufferedReader");
            }
        }
    }

    /* Con el método setQuestions definimos correctamente qué pregunta se debe mostrar en cada turno cogiendo como índice del ArrayList el turno actual
     */
    public void setQuestions() {
        Question actualQuestion = (Question) questions.get(controlTurn);
        view.getQuestionText().setText(actualQuestion.getText());
        view.getRes1().setText(actualQuestion.getAnswers().get(0).getText());
        view.getRes2().setText(actualQuestion.getAnswers().get(1).getText());
        view.getRes3().setText(actualQuestion.getAnswers().get(2).getText());
    }

}
