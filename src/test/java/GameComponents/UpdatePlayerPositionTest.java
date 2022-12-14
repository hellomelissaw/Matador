package GameComponents;

import org.junit.Test;

import static org.junit.Assert.*;

public class UpdatePlayerPositionTest {

    @Test
    public void updatePosition10Squares() {
        Player testPlayer = new Player("Test Player");
        int currentPosition = 0;
        currentPosition = testPlayer.updatePosition(10);
        assertEquals(10,currentPosition);
    }

    @Test
    public void updatePosition25Squares() {
        Player testPlayer = new Player("Test Player");
        int currentPosition = 0;
        currentPosition = testPlayer.updatePosition(25);
        assertEquals(1,currentPosition);
    }
}