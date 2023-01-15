package GameComponents.Board;

import static org.junit.Assert.*;
import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;


public class LandOnJailSquareTest {

    Player[] testPlayers = new Player[1];

    GUI_Player testGuiPlayer1 = new GUI_Player("TestGuiPlayer 1");
    GuiController guiController = new GuiController();
    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);
    Square[] board;

    public LandOnJailSquareTest() {
        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[0].setGui(testGuiPlayer1, guiController, msg);
        BoardInit squares = new BoardInit(guiController, msg, testPlayers);
        board = squares.getSquareArr();
    }


    @Test
    public void JailIsCorrectlyInitiated() {
        assertEquals("Fængsel", board[30].getSquareName());


    }

    @Test
    public void VisitJailIsCorrectlyInitiated() {
        assertEquals("I fængsel/På besøg", board[10].getSquareName());


    }

    //K12
    @Test
    public void LandOnVisitJailAndMoveToJail() {


    }




}