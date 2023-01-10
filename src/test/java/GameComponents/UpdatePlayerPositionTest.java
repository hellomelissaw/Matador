package GameComponents;

import Controllers.GuiController;
import Translator.Text;
import org.junit.Test;

import static org.junit.Assert.*;

public class UpdatePlayerPositionTest {

    GuiController guiController = new GuiController();
    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);

    @Test
    public void updatePosition10Squares() {
        Player testPlayer = new Player("Test Player");
        testPlayer.guiIsOn(false);
        testPlayer.setLang(msg);
        int currentPosition = 0;
        testPlayer.updatePosition(10);
        currentPosition = testPlayer.getPosition();
                assertEquals(10,currentPosition);
    }

    @Test
    public void updatePosition25Squares() {
        Player testPlayer = new Player("Test Player");
        testPlayer.guiIsOn(false);
        testPlayer.setLang(msg);
        int currentPosition = 0;
        testPlayer.updatePosition(41);
        currentPosition = testPlayer.getPosition();
                assertEquals(1,currentPosition);
    }
}