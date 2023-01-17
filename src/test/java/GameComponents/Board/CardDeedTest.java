package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Bank;
import GameComponents.Player;
import Translator.Text;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardDeedTest {
    GuiController guiController = new GuiController();
    Player[] testPlayers = new Player[2];
    Text msg = new Text("src/main/java/Translator/DanskTekst");
    ChanceSquare testChanceSquare;

    Bank bank = new Bank();


    public CardDeedTest() {
        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[0].setGuiIsOn(false);
        testPlayers[0].setLang(msg);
        testPlayers[0].setBank(bank);
        testPlayers[0].setStartBalance(6000, true);

        testPlayers[1] = new Player("TestPlayer 2");
        testPlayers[1].setGuiIsOn(false);
        testPlayers[1].setLang(msg);
        testPlayers[1].setBank(bank);
        testPlayers[1].setStartBalance(6000, true);

        BoardInit squares = new BoardInit(guiController, msg, testPlayers);
        Square[] board = squares.getSquareArr();
        testChanceSquare = new ChanceSquare("Chance Square", guiController, testPlayers);
        testChanceSquare.setLang(msg);
        testChanceSquare.setCardLang();
        testChanceSquare.setChanceCards(board);


    }

    @Test
    public void chanceCard34GoToShipyard6From2() {
    testChanceSquare.isTesting(true,34);
    testPlayers[0].updatePosition(2);
    testChanceSquare.landOn(testPlayers[0]);
    assertEquals(5,testPlayers[0].getPosition());

    }

    @Test
    public void chanceCard34GoToShipyardAndBuyItFor4000() {
        testChanceSquare.isTesting(true,34);
        testPlayers[0].updatePosition(2);
        testChanceSquare.landOn(testPlayers[0]);
        assertEquals(5,testPlayers[0].getPosition());

    }

}