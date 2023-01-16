package GameComponents;

import static org.junit.Assert.*;
import Controllers.GuiController;
import Controllers.GameController;
import GameComponents.Board.BoardInit;
import GameComponents.Board.Square;
import GameComponents.Cup;
import GameComponents.Die;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import gui_main.GUI;
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
                testPlayer.guiIsOn(false);
                testPlayer.setLang(msg);
                int currentPosition = 0;
                testPlayer.updatePosition(10);
                currentPosition = testPlayer.getPosition();
                assertEquals(10, currentPosition);
            }
            //K26
            @Test
            public void LandOnJailAndPostBail () {
                Player testPlayer = new Player("Test Player");
                testPlayer.guiIsOn(false);
                testPlayer.setLang(msg);
                int currentPosition = 0;
                int fine = 1000;
                testPlayer.updatePosition(30);
                currentPosition = testPlayer.getPosition();
                testPlayer.withdrawMoney(1000,false);
                assertEquals(-1000,testPlayer.getCurrentBalance());

            }



            @Test
            //K8
            public void TripleTurnAndMoveToJail () {
                Player testPlayer = new Player("Test Player");
                testPlayer.guiIsOn(false);
                testPlayer.setLang(msg);
                int currentPosition = 0;


            }
        }

