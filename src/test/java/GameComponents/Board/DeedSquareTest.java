package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.player;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeedSquareTest {
    GuiController guiController = null;
    player testPlayer1 = new player("TestPlayer 1");
    player testPlayer2 = new player("TestPlayer 2");
    DeedSquare testDeedSquare = new DeedSquare("Test Deed 1",5,guiController);


    @Test
    public void testDeedSquareHasDeed() {
        assertEquals(true,testDeedSquare.hasDeed());
    }
    @Test
    public void setTestDeedSquareHasDeedFalseafterSellDeed() {
        testDeedSquare.sellDeed(testPlayer1);
        assertEquals(false,testDeedSquare.hasDeed());
    }
    @Test
    public void getDeedPrice() {
        assertEquals(5, testDeedSquare.getDeedPrice());
    }
    @Test
    public void getDeedOwner() {
        testDeedSquare.sellDeed(testPlayer1);
        assertEquals(testPlayer1, testDeedSquare.getDeedOwner());
        testDeedSquare.sellDeed(testPlayer2);
        assertEquals(testPlayer2, testDeedSquare.getDeedOwner());
    }
}