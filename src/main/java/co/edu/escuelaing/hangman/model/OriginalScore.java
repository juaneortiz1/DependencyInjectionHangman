package co.edu.escuelaing.hangman.model;

import org.springframework.stereotype.Component;

@Component("originalScore")
public class OriginalScore implements GameScore {
    @Override
    public int calculateScore(int correctCount, int incorrectCount) {
        int score = 100;
        score -= incorrectCount * 10;
        if (score < 0){
            score = 0;
        }
        return score;
    }
}
