package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Bank;
import GameComponents.Player;
import Translator.Text;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardDeedTest {
    Player[] testPlayers = new Player[2];
    Text msg = new Text("src/main/java/Translator/DanskTekst");
    ChanceSquare testChanceSquare;
    Bank bank = new Bank();
    Square[] board;

    public CardDeedTest() {
        msg.setGuiIsOn(false);
        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[0].setLang(msg);
        testPlayers[0].setGuiIsOn(false);
        testPlayers[0].setBank(bank);
        testPlayers[0].setStartBalance(6000, true);

        testPlayers[1] = new Player("TestPlayer 2");
        testPlayers[1].setGuiIsOn(false);
        testPlayers[1].setLang(msg);
        testPlayers[1].setBank(bank);
        testPlayers[1].setStartBalance(6000, true);

        BoardInit squares = new BoardInit(msg, testPlayers);
        squares.initBoard();
        board = squares.getSquareArr();
        squares.initChanceSquare(board);

       /* testChanceSquare = new ChanceSquare("Chance Square", testPlayers);
        testChanceSquare.setGuiIsOn(false);
        testChanceSquare.setChanceCards(board);
        testChanceSquare.setLang(msg);
        testChanceSquare.setCardLang();*/

    }

    //unittests på 2 af chancesquaresne hvor man hopper til nærmeste redderi

    @Test
    public void chanceCard34GoToShipyard6From2() {
        ((DeedSquare_NonBuildable)board[5]).testing(true,"nej");
        ((ChanceSquare)board[2]).isTesting(true,34);
        testPlayers[0].updatePosition(2);
        board[2].landOn(testPlayers[0]);
        assertEquals(5,testPlayers[0].getPosition());

    }

    // user story. k11 Chancecard 36 og 36, tjekker om man også kan hoppe fra chancesquare index 7 til rederi index 15
    public void chanceCard35GoToShipyard15from7() {
        ((DeedSquare_NonBuildable)board[15]).testing(true,"ja");
        testChanceSquare.isTesting(true,35);
        testPlayers[0].updatePosition(7);
        board[2].landOn(testPlayers[0]);
        assertEquals(15,testPlayers[0].getPosition());
    }

}