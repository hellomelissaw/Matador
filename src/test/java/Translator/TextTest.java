package Translator;

import Controllers.GuiController;
import gui_codebehind.GUI_BoardController;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextTest {
    Text testMessage;

    @Test
    public void getText() {
        testMessage = new Text("src/main/java/Translator/TestLang");
        assertEquals(testMessage.getText("amountOfPlayers"),  " Hvor mange spillere?");
        assertEquals(testMessage.getText("label"),  " message");
        assertEquals(testMessage.getText("someOtherLabel"), " Hello my name is Sir Pumpernickel The Third");
    }

    @Test
    public void getTextDansk() {
        testMessage = new Text("src/main/java/Translator/DanskTekst");
        assertEquals(testMessage.getText("whichLots"),  " Hvilke grunde vil du bygge på?");
        assertEquals(testMessage.getText("selectMoreLots"),  " Vil du vælge flere grunde?");
        assertEquals(testMessage.getText("houseOrHotel"), " Hvilken type bygning vil du bygge?");
    }


}