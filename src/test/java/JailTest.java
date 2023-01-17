import Controllers.GameController;
import GameComponents.LoadedCup;
import Controllers.GuiController;
import GameComponents.Board.BoardInit;
import GameComponents.Board.Square;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class JailTest {
    Player[] testPlayers = new Player[1];

    GUI_Player testGuiPlayer1 = new GUI_Player("TestGuiPlayer 1");
    GuiController guiController = new GuiController();
    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);
    Square[] board;

    public JailTest() {
        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[0].setGui(testGuiPlayer1, guiController, msg);
        BoardInit squares = new BoardInit(guiController, msg, testPlayers);
        board = squares.getSquareArr();}

    @Test
    //K8 - Spiller ender i fængsel når de slår terninger af samme slags tre gange i streg
    //K12 - Spiller besøger fængsel uden straf
    //K26 - Spiller lander i fængsel og betaler bøde for at kunne komme ud af fængsel
    //K30 - Spiller lander i fængsel og kaster terninger over 3 omgange og formår ikke at slå sig ud, så betaler bøde
    public static void main(String[] args) {

        GameController game = new GameController();
        LoadedCup cup = new LoadedCup(new ArrayList<>(Arrays.asList(new int[]{5, 5}, new int[]{5, 5}, new int[]{5, 5})));
        game.init();
        game.run(cup);
    }
}