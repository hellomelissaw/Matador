package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;

import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class LandOnSquareTest {

    Player[] testPlayers = new Player[1];

    Text msg = new Text("src/main/java/Translator/DanskTekst");
    Square[] board;

    public LandOnSquareTest() {
        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[0].setGuiIsOn(false);
        BoardInit squares = new BoardInit(msg, testPlayers);
        squares.initBoard();
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

    @Test
    //Land i fængsel
    public void JailNameIsCorrectlyInitiated() {
        assertEquals("Fængsel", board[30].getSquareName());

    }


}