package co.edu.escuelaing.hangman.model;

import org.springframework.stereotype.Component;

@Component("powerScore")
public class PowerScore implements GameScore{
    @Override
    public int calculateScore(int correctCount, int incorrectCount) {
        int score = 0;

        int totalScore = 0;

        for(int i = 1; i <= correctCount;i++ ){
            totalScore += Math.pow(5,i);
        }
        score += totalScore;
        score -= incorrectCount * 8;

        if (score < 0){
            score = 0;
        }
        else if (score > 500) {
            score = 500;
        }
        return score;
    }

}
