package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class JailCardTest {

    Player[] testPlayers = new Player[1];
    Text msg = new Text("src/main/java/Translator/DanskTekst");
    Square[] board;

    public JailCardTest() {
        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[0].setGuiIsOn(false);
        msg.setGuiIsOn(false);
        BoardInit squares = new BoardInit(msg, testPlayers);
        squares.initBoard();
        board = squares.getSquareArr();
        squares.initChanceSquare(board);


    }

    @Test
    public void PlayerUsesJailPass() {  //Acceptance test ID:// K29_AT01

        int jailSquare = 30;                                    //Index nr of jail square
        testPlayers[0].giveJailPass();                          //Gives player a jail card
        assertEquals(testPlayers[0].getJailPass(),1);    //Checks if player has one jail card

        testPlayers[0].updatePosition(jailSquare);              //Updates players position to jail
        board[jailSquare].landOn(testPlayers[0]);

        assertTrue(testPlayers[0].checkInJail());               //Checks if player is in jail

        if (testPlayers[0].useJailPass()){                      //Uses a jail card and if it returns true then player gets moved out of jail
            testPlayers[0].moveOutJail();
        }

        assertFalse(testPlayers[0].checkInJail());              //Checks if the player is successfully out of jail


    }


}
