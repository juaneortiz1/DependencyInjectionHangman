package co.edu.escuelaing.hangman;

import co.edu.escuelaing.hangman.setup.factoryMethod.HangmanDefaultFactoryMethod;
import co.edu.escuelaing.hangman.setup.injectionMethod.InjectionMethod;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringBootSwingApplication {

    public static final String CONTRIBUTORS[] = {
            "Omar Rodriguez, 008796203",
            "Nahid Enayatzadeh, 010164622",
            "Marc Deaso, 011179285",
            "Christopher Santos, ",
            "Jazmin Guerrero, 009007193"};

    public static final String PROJECT_NAME = "CS 245 - Swing Project v1";

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBootSwingApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}