package co.edu.escuelaing.hangman.setup.factoryMethod;

import co.edu.escuelaing.hangman.model.English;
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

    public HangmanDefaultFactoryMethod(
            @Qualifier("spanishLanguage") Language language,
            @Qualifier("spanishDictionary") HangmanDictionary dictionary,
            @Qualifier("hangmanStickmanPanel") HangmanPanel hangmanPanel
    ) {
        this.language = language;
        this.dictionary = dictionary;
        this.hangmanPanel = hangmanPanel;
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
}
