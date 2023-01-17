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

import java.util.ArrayList;
import java.util.Arrays;

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

            //K12 - Spiller besøger fængsel uden straf
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
            @Test
            public void LandOnGoToJail () {
                Player testPlayer = new Player("Test Player");
                testPlayer.guiIsOn(false);
                testPlayer.setLang(msg);
                int currentPosition = 0;
                testPlayer.updatePosition(30);
                currentPosition = testPlayer.getPosition();
                assertEquals(30, currentPosition);
    }
            //K26 - Spiller lander i fængsel og betaler bøde for at kunne komme ud af fængsel
            @Test
            public void LandOnGoToJailAndChooseToPostBail () {
                GameController game = new GameController();
                LoadedCup cup = new LoadedCup(new ArrayList<>(Arrays.asList(new int[]{15, 15})));
                game.init();
                game.run(cup);

            }
            //K30 - Spiller lander i fængsel og kaster terninger over 3 omgange og formår ikke at slå sig ud, så betaler bøde
            @Test
            public void LandOnGoToJailAndChooseToThrowDiceAndStillPostBail () {
                GameController game = new GameController();
                LoadedCup cup = new LoadedCup(new ArrayList<>(Arrays.asList(new int[]{15, 15})));
                game.init();
                game.run(cup);
            }
            //K8 - Spiller ender i fængsel når de slår terninger af samme slags tre gange i streg
            @Test
            public void TripleTurnAndGoToJail () {
                GameController game = new GameController();
                LoadedCup cup = new LoadedCup(new ArrayList<>(Arrays.asList(new int[]{5, 5}, new int[]{5, 5}, new int[]{5, 5})));
                game.init();
                game.run(cup);
    }


            }



