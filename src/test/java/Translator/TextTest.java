package Translator;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextTest {

    @Test
    public void getTextChecksLabelReturnsMessage() {
        Text reader = new Text("src/main/java/Translator/EnglishText");

        String testMessage = " message";
        assertEquals(testMessage,reader.getText("label"));

        String testStartGame = " Press OK to start the game!";
        assertEquals(testStartGame,reader.getText("startGame"));

    }

}