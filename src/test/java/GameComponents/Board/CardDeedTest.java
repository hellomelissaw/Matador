package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import GameComponents.Board.BoardInit;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardDeedTest {
    GuiController guiController = new GuiController();
    Player[] testPlayers = new Player[2];

    Text msg = new Text("src/main/java/Translator/EnglishText", guiController);
    ChanceSquare testChanceSquare;

    public CardDeedTest() {
        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[0].setLang(msg);
        testPlayers[1] = new Player("TestPlayer 2");
        testPlayers[1].setLang(msg);
        testChanceSquare = new ChanceSquare("Chance Square", guiController, testPlayers);
        testChanceSquare.setLang(msg);
        testChanceSquare.setCardLang();
    }

    @Test
    public void chanceCard31MoveBack3() {
    testChanceSquare.isTesting(true,31);
        System.out.println("Player is at index " + testPlayers[0].getPosition());
    testChanceSquare.landOn(testPlayers[0]);
    assertEquals(37,testPlayers[0].getPosition());

    }

}