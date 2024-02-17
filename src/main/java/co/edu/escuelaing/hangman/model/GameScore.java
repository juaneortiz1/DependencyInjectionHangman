package co.edu.escuelaing.hangman.model;

public interface GameScore {
    int calculateScore(int correctCount, int incorrectCount);
}
