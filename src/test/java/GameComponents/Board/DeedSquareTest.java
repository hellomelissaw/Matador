package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeedSquareTest {
    GuiController guiController = new GuiController();
    Player testPlayer1 = new Player("TestPlayer 1");
    Player testPlayer2 = new Player("TestPlayer 2");
    DeedSquare testDeedSquare = new DeedSquare("Test Deed 1",5,guiController);

    GUI_Player testGuiPlayer1 = new GUI_Player("TestPlayer 1");
    GUI_Player testGuiPlayer2 = new GUI_Player("TestPlayer 2");

    Text msg = new Text("src/main/java/Translator/EnglishText");
    @Test
    public void deedOwnerIsTestPlayer1AfterLandingOnIt() {
        testDeedSquare.setLang(msg);
        testPlayer1.updatePosition(1);
        testDeedSquare.landOn(testPlayer1);
        assertEquals(testPlayer1, testDeedSquare.getDeedOwner());
    }
    @Test
    public void testPlayer2PaysRentToDeedOwner() {
        testDeedSquare.setLang(msg);
        testPlayer1.depositMoney(20);
        testPlayer2.depositMoney(20);
        testPlayer1.updatePosition(1);
        testDeedSquare.landOn(testPlayer1);
        testPlayer2.updatePosition(1);
        testDeedSquare.landOn(testPlayer2);
        assertEquals(20, testPlayer1.getCurrentBalance());
        assertEquals(15, testPlayer2.getCurrentBalance());
    }
}