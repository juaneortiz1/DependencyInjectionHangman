package co.edu.escuelaing.hangman.model;

import org.springframework.stereotype.Component;

@Component("bonusScore")
public class BonusScore implements GameScore {
    @Override
    public int calculateScore(int correctCount, int incorrectCount) {
        int score = 0;
        score += correctCount * 10;
        score -= correctCount * 5;
        if (score < 0){
            score = 0;
        }
        return score;
    }
}
