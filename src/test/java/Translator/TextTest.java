package Translator;

import Controllers.GuiController;
import gui_codebehind.GUI_BoardController;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextTest {
    GuiController guiController = new GuiController();
Text testMessage = new Text("src/main/java/Translator/TestLang", guiController);
    @Test
    public void getText() {
        assertEquals(testMessage.getText("amountOfPlayers"),  " Hvor mange spillere?");
        assertEquals(testMessage.getText("label"),  " message");
        assertEquals(testMessage.getText("someOtherLabel"), " Hello my name is Sir Pumpernickel The Third");
    }
}