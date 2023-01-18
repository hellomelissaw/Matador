package GameComponents;

import Controllers.GuiController;
import Translator.Text;
import org.junit.Test;

import static org.junit.Assert.*;

public class UpdatePlayerPositionTest {

    Text msg = new Text("src/main/java/Translator/DanskTekst");
    Player testPlayer = new Player("Test Player");

    public UpdatePlayerPositionTest() {
        msg.setGuiIsOn(false);
        testPlayer.setLang(msg);
        testPlayer.setGuiIsOn(false);

    }
    @Test
    public void updatePosition10Squares() {
        int currentPosition = 0;
        testPlayer.updatePosition(10);
        currentPosition = testPlayer.getPosition();
                assertEquals(10,currentPosition);
    }

    @Test
    public void updatePosition25Squares() {
        int currentPosition = 0;
        testPlayer.updatePosition(41);
        currentPosition = testPlayer.getPosition();
                assertEquals(1,currentPosition);
    }
}