package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardMoneyTest {
    GuiController guiController = new GuiController();
    Player[] testPlayers = new Player[4];

    // Square testChanceSquare = new ChanceSquare("Testing Chance", guiController);
    Text msg = new Text("src/main/java/Translator/EnglishText");

    GUI_Player testGuiPlayer1 = new GUI_Player("TestPlayer 1");
    @Test
    public void playChanceCard5Withdraw2() {
        testPlayers[1] = new Player("TestPlayer 1");
        testPlayers[1].depositMoney(20);
        ChanceCard testChanceCard = new CardMoney("chance5",guiController,testPlayers);
        //testChanceSquare.setLang("EnglishText");
        testChanceCard.setLang(msg);
        testChanceCard.playCard(testPlayers[1],testGuiPlayer1);
        assertEquals(18, testPlayers[1].getCurrentBalance());

    }

    @Test
    public void playChanceCard6CurrentPlayerGets3OthersWithdraw1() {
        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[1] = new Player("TestPlayer 2");
        testPlayers[2] = new Player("TestPlayer 3");
        testPlayers[3] = new Player("TestPlayer 4");


        ChanceCard testChanceCard = new CardMoney("chance6",guiController,testPlayers);
        //testChanceSquare.setLang("EnglishText");
        testChanceCard.setLang(msg);
        testChanceCard.playCard(testPlayers[0],testGuiPlayer1);
        assertEquals(3, testPlayers[0].getCurrentBalance());
        assertEquals(-1, testPlayers[1].getCurrentBalance());
        assertEquals(-1, testPlayers[2].getCurrentBalance());
        assertEquals(-1, testPlayers[3].getCurrentBalance());

    }
    @Test
    public void playChanceCard7Deposit2() {
        testPlayers[0] = new Player("TestPlayer 1");
        ChanceCard testChanceCard = new CardMoney("chance7",guiController,testPlayers);
        //testChanceSquare.setLang("EnglishText");
        testChanceCard.setLang(msg);
        testChanceCard.playCard(testPlayers[0],testGuiPlayer1);
        assertEquals(2, testPlayers[0].getCurrentBalance());

    }
}