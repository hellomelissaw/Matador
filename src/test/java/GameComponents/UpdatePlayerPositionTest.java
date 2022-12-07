package GameComponents;

import Controllers.GuiController;
import org.junit.Test;

import static org.junit.Assert.*;

public class UpdatePlayerPositionTest {

    @Test
    public void updatePosition10Squares() {
        Player testPlayer = new Player("Test Player");
        int currentPosition = 0;
        testPlayer.updatePosition(10);
        currentPosition = testPlayer.getPosition();
                assertEquals(10,currentPosition);
    }

    @Test
    public void updatePosition25Squares() {
        Player testPlayer = new Player("Test Player");
        int currentPosition = 0;
        testPlayer.updatePosition(25);
        currentPosition = testPlayer.getPosition();
                assertEquals(1,currentPosition);
    }
}