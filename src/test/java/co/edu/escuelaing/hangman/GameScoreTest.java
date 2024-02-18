package co.edu.escuelaing.hangman;
import co.edu.escuelaing.hangman.model.BonusScore;
import co.edu.escuelaing.hangman.model.OriginalScore;
import co.edu.escuelaing.hangman.model.PowerScore;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameScoreTest {

    // Clases de equivalencia y condiciones de frontera:
    //
    // - Para OriginalScore:
    //   - CE1: correctCount > 0, incorrectCount > 0
    //   - CE2: correctCount = 0, incorrectCount > 0
    //   - CE3: correctCount > 0, incorrectCount = 0
    //   - CE4: correctCount = 0, incorrectCount = 0
    //   - CF1: Puntuación mínima (0)
    //   - CF2: Letras incorrectas suficientes para reducir el puntaje a 0
    //   - CF3: Letras incorrectas suficientes para reducir el puntaje por debajo de 0

    @Test
    public void testOriginalScore() {
        OriginalScore originalScore = new OriginalScore();
        // CE1
        assertEquals(80, originalScore.calculateScore(5, 2));
        // CE2
        assertEquals(50, originalScore.calculateScore(0, 5));
        // CE3
        assertEquals(100, originalScore.calculateScore(5, 0));
        // CE4
        assertEquals(100, originalScore.calculateScore(0, 0));
        // CF1
        assertEquals(0, originalScore.calculateScore(10, 15));
        // CF2
        assertEquals(0, originalScore.calculateScore(0, 11));
        // CF3
        assertEquals(100, originalScore.calculateScore(12, 0));
    }
    // - Para BonusScore:
    //   - CE1: correctCount > 0, incorrectCount > 0
    //   - CE2: correctCount = 0, incorrectCount > 0
    //   - CE3: correctCount > 0, incorrectCount = 0
    //   - CE4: correctCount = 0, incorrectCount = 0
    //   - CF1: Puntuación mínima (0)
    //   - CF2: Letras correctas suficientes para aumentar el puntaje a más de 0
    //   - CF3: Letras incorrectas suficientes para reducir el puntaje a 0
    @Test
    public void testBonusScore() {
        BonusScore bonusScore = new BonusScore();
        // CE1
        assertEquals(20, bonusScore.calculateScore(3, 2));
        // CE2
        assertEquals(0, bonusScore.calculateScore(0, 5));
        // CE3
        assertEquals(30, bonusScore.calculateScore(3, 0));
        // CE4
        assertEquals(0, bonusScore.calculateScore(0, 0));
        // CF1
        assertEquals(0, bonusScore.calculateScore(0, 6));
        // CF2
        assertEquals(20, bonusScore.calculateScore(2, 0));
        // CF3
        assertEquals(5, bonusScore.calculateScore(3, 5));
    }
    // - Para PowerScore:
    //   - CE1: correctCount > 0, incorrectCount > 0
    //   - CE2: correctCount = 0, incorrectCount > 0
    //   - CE3: correctCount > 0, incorrectCount = 0
    //   - CE4: correctCount = 0, incorrectCount = 0
    //   - CF1: Puntuación máxima (500)
    //   - CF2: Letras incorrectas suficientes para reducir el puntaje a 0
    //   - CF3: Letras correctas suficientes para aumentar el puntaje a más de 500
    @Test
    public void testPowerScore() {
        PowerScore powerScore = new PowerScore();
        // CE1
        assertEquals(14, powerScore.calculateScore(2, 2));
        // CE2
        assertEquals(0, powerScore.calculateScore(0, 5));
        // CE3
        assertEquals(500, powerScore.calculateScore(5, 0));
        // CE4
        assertEquals(0, powerScore.calculateScore(0, 0));
        // CF1
        assertEquals(500, powerScore.calculateScore(10, 10));
        // CF2
        assertEquals(0, powerScore.calculateScore(0, 9));
        // CF3
        assertEquals(500, powerScore.calculateScore(7, 0));
    }
}
