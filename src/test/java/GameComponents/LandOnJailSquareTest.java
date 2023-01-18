package GameComponents;

import static org.junit.Assert.*;
import Controllers.GuiController;
import GameComponents.Board.BoardInit;
import GameComponents.Board.Square;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

public class LandOnJailSquareTest {

    Player[] testPlayers = new Player[1];
    Text msg = new Text("src/main/java/Translator/DanskTekst");
    Square[] board;

    public LandOnJailSquareTest() {
        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[0].setGuiIsOn(false);
        BoardInit squares = new BoardInit(msg, testPlayers);
        squares.initBoard();
        board = squares.getSquareArr();}

            @Test
            public void JailIsCorrectlyInitiated () {
                assertEquals("Fængsel", board[30].getSquareName());


            }

            @Test
            public void VisitJailIsCorrectlyInitiated () {
                assertEquals("I fængsel/På besøg", board[10].getSquareName());


            }

            //K12
            @Test
            public void LandOnVisitJail () {
                Player testPlayer = new Player("Test Player");
                testPlayer.setGuiIsOn(false);
                testPlayer.setLang(msg);
                int currentPosition = 0;
                testPlayer.updatePosition(10);
                currentPosition = testPlayer.getPosition();
                assertEquals(10, currentPosition);
            }
            @Test
            public void LandOnJail () {
                Player testPlayer = new Player("Test Player");
                testPlayer.setGuiIsOn(false);
                testPlayer.setLang(msg);
                int currentPosition = 0;
                testPlayer.updatePosition(30);
                currentPosition = testPlayer.getPosition();
                assertEquals(30, currentPosition);
    }
            //K26
            @Test
            public void LandOnJailAndChooseToPostBail () {
                Player testPlayer = new Player("Test Player");
                testPlayer.setGuiIsOn(false);
                testPlayer.setLang(msg);
                int currentPosition = 0;
                int fine = 1000;
                testPlayer.updatePosition(30);
                currentPosition = testPlayer.getPosition();
                testPlayer.withdrawMoney(1000,false);
                assertEquals(-1000,testPlayer.getCurrentBalance());

            }




            }



