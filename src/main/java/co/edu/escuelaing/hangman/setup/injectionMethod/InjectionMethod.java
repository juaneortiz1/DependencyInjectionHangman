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

