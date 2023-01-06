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

    public CardDeedTest() {
        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[1] = new Player("TestPlayer 2");
        ChanceSquare testChanceSquare = new ChanceSquare("Chance Square", guiController, testPlayers);
    }

    @Test
    public void chanceCard34GoToClosestShipyard() {

    }

}