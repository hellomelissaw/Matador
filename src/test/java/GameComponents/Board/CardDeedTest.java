package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Bank;
import GameComponents.Player;
import GameComponents.Board.BoardInit;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardDeedTest {
    GuiController guiController = new GuiController();
    Player[] testPlayers = new Player[2];
    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);
    ChanceSquare testChanceSquare;

    Bank bank = new Bank();


    public CardDeedTest() {
        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[0].guiIsOn(false);
        testPlayers[0].setLang(msg);
        testPlayers[0].setBank(bank);
        testPlayers[0].setStartBalance(6000, true);

        testPlayers[1] = new Player("TestPlayer 2");
        testPlayers[1].guiIsOn(false);
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

    //unittests på 2 af chancesquaresne hvor man hopper til nærmeste redderi

    @Test
    // K11 Chancecard 35 og 36: tjekker om man hopper fra chancefield til nærmeste rederi
    public void chanceCard35GoToShipyard5From2() {
    testChanceSquare.isTesting(true,34);
    testPlayers[0].updatePosition(2);
    testChanceSquare.landOn(testPlayers[0]);
    assertEquals(5,testPlayers[0].getPosition());

    }

    @Test
    // user story. k11 Chancecard 36 og 36, tjekker om man også kan hoppe fra chancesquare index 7 til rederi index 15
    public void chanceCard35GoToShipyard15from7() {
        testChanceSquare.isTesting(true,35);
        testPlayers[0].updatePosition(7);
        testChanceSquare.landOn(testPlayers[0]);
        assertEquals(15,testPlayers[0].getPosition());
    }

}