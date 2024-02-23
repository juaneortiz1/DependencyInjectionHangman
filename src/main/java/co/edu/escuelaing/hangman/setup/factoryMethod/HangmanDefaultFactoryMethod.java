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
}
