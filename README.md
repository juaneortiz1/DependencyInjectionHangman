### Escuela Colombiana de Ingeniería

### Ciclo de Vida de Desarrollo de Software

### Desarrollo Dirigido por Pruebas + DIP + DI

# LAB04
# Integrantes
- Juan Esteban Ortiz Pastrana

Para este taller se va a trabajar sobre el juego del ahorcado.

# PARTE I
1. En la página del repositorio, se encuentra un botón en la esquina superior derecha que dice "Fork". se hace clic en este botón. Esto creará una copia del repositorio en mi cuenta de GitHub.  ```PDSW-ECI/DependencyInjectionHangman:main ```
2. Se realiza la implementación de los cascarones de cada una de las clases con sus respectivas especificicación
```  
- GameScore
  package co.edu.escuelaing.hangman.model;

public interface GameScore {
int calculateScore(int correctCount, int incorrectCount);
}
```  
:

* OriginalScore:
    * Es el esquema actual, se inicia con 100 puntos.
    * No se bonifican las letras correctas.
    * Se penaliza con 10 puntos con cada letra incorrecta.
    * El puntaje minimo es 0.

```  
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
```
* BonusScore:
    * El juego inicia en 0 puntos.
    * Se bonifica con 10 puntos letra correcta.
    * Se penaliza con 5 puntos cada letra incorrecta.
    * El puntaje mínimo es 0
```
package co.edu.escuelaing.hangman.model;

import org.springframework.stereotype.Component;

@Component("bonusScore")
public class BonusScore implements GameScore {
    @Override
    public int calculateScore(int correctCount, int incorrectCount) {
        int score = 0;
        score += correctCount * 10;
        score -= incorrectCount * 5;
        if (score < 0){
            score = 0;
        }
        return score;
    }
}
```  
* PowerBonusScore:
    * El juego inicia en 0 puntos.
    * La $i-ésima$ letra correcta se bonifica con $5^i$.
    * Se penaliza con 8 puntos cada letra incorrecta.
    * El puntaje mínimo es 0
    * Si con las reglas anteriores sobrepasa 500 puntos, el puntaje es
        500.

```  
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
```  
Se actualiza el archivo pom.xml con las dependencias necesarias
```  
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.13.2</version>
			<scope>test</scope>
		</dependency>

	</dependencies>
```  

Se realizan las pruebas necesarias previas a la elaboracion del los metodos
```  
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
```  


### Parte II

En la clase HangmanDefaultFactoryMethod creamos el nuevo modo GameScore donde creamos 
los nuevos objetos de la clase GameScore

```  
package co.edu.escuelaing.hangman.setup.factoryMethod;

import co.edu.escuelaing.hangman.model.English;
import co.edu.escuelaing.hangman.model.GameScore;
import co.edu.escuelaing.hangman.model.Language;
import co.edu.escuelaing.hangman.model.dictionary.HangmanDictionary;
import co.edu.escuelaing.hangman.view.HangmanPanel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HangmanDefaultFactoryMethod implements HangmanFactoryMethod {
    private Language language;
    private HangmanDictionary dictionary;
    private HangmanPanel hangmanPanel;
    private GameScore gameScore;

    public HangmanDefaultFactoryMethod(
            @Qualifier("englishLanguage") Language language,
            @Qualifier("englishDictionary") HangmanDictionary dictionary,
            @Qualifier("hangmanStickmanPanel") HangmanPanel hangmanPanel,
            @Qualifier("originalScore") GameScore gameScore
    ) {
        this.language = language;
        this.dictionary = dictionary;
        this.hangmanPanel = hangmanPanel;
        this.gameScore = gameScore;
    }

    public Language createLanguage() {
        return language;
    }

    public HangmanDictionary createDictionary() {
        return dictionary;
    }

    public HangmanPanel createHangmanPanel() {
        return hangmanPanel;
    }

    public GameScore createGameScore() {return gameScore;}
```  

* Mediante la configuración de la Inyección de
  Dependencias se pueda cambiar el comportamiento del mismo, obteniendo
```  
package co.edu.escuelaing.hangman.setup.injectionMethod;

import co.edu.escuelaing.hangman.model.GameScore;
import co.edu.escuelaing.hangman.model.Language;
import co.edu.escuelaing.hangman.model.dictionary.HangmanDictionary;
import co.edu.escuelaing.hangman.setup.factoryMethod.HangmanFactoryMethod;
import co.edu.escuelaing.hangman.view.HangmanPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class InjectionMethod implements HangmanFactoryMethod {
    private Language language;
    private HangmanDictionary dictionary;
    private HangmanPanel hangmanPanel;
    private GameScore gameScore;

    public InjectionMethod(
            @Qualifier("frenchLanguage") Language language,
            @Qualifier("frenchDictionary") HangmanDictionary dictionary,
            @Qualifier("hangmanStickmanPanel") HangmanPanel hangmanPanel,
            @Qualifier("bonusScore") GameScore gameScore
    ) {
        this.language = language;
        this.dictionary = dictionary;
        this.hangmanPanel = hangmanPanel;
        this.gameScore = gameScore;
    }


    @Override
    public Language createLanguage() {
        return language;
    }

    @Override
    public HangmanDictionary createDictionary() {
        return dictionary;
    }

    @Override
    public HangmanPanel createHangmanPanel() {
        return hangmanPanel;
    }

    @Override
    public GameScore createGameScore() {
        return gameScore;
    }
}
```  