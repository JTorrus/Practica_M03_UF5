package model;

public class Answer {
    private String text;
    private boolean correctAnswer;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
