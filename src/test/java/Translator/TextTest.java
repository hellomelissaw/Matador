package Translator;

import org.junit.Test;

import static org.junit.Assert.*;
import Controllers.GuiController;
public class TextTest {
GuiController guiController = new GuiController();
    @Test
    public void getTextChecksLabelReturnsMessage() {
        Text reader = new Text("src/main/java/Translator/EnglishText", guiController);

        String testMessage = " message";
        assertEquals(testMessage,reader.getText("label"));

        String testStartGame = " Press OK to start the game!";
        assertEquals(testStartGame,reader.getText("startGame"));

    }

}