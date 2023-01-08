package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;

import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class LandOnSquareTest {

    Player[] testPlayers = new Player[1];

    GUI_Player testGuiPlayer1 = new GUI_Player("TestGuiPlayer 1");
    GuiController guiController = new GuiController();
    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);
    Square[] board;

    public LandOnSquareTest() {
        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[0].setGui(testGuiPlayer1, guiController, msg);
        BoardInit squares = new BoardInit(guiController, msg, testPlayers);
        board = squares.getSquareArr();
    }

    @Test
    public void RoedovrevejNameIsCorrectlyInitiated() {
        assertEquals("Rødovrevej", board[1].getSquareName());


    }

    @Test
    public void TuborgSquashNameIsCorrectlyInitiated() {
        assertEquals("Tuborg Squash", board[12].getSquareName());


    }


}