package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Bank;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardMoneyTest {
    Player[] testPlayers = new Player[4];
    Text msg = new Text("src/main/java/Translator/DanskTekst");

    Bank bank = new Bank();

    public CardMoneyTest() {
        for (int i = 0 ; i < testPlayers.length ; i++) {
            testPlayers[i] = new Player("TestPlayer "+i);
            testPlayers[i].setBank(bank);
            testPlayers[i].setGuiIsOn(false);
            testPlayers[i].setLang(msg);
        }
    }

    @Test
    public void playChanceCardPay500() {
        // chancecard 1, spiller skal betale 500 kr til banken
        ChanceCard testChanceCard = new CardMoney("Chance1","withdraw",500);
        testChanceCard.setCardLang(msg);
        testChanceCard.playCard(testPlayers[1]);
        assertEquals(-500, testPlayers[1].getCurrentBalance());
    }

    @Test
    //chancecard 26, spiller modtager 200 kr fra resterende spillere
    public void playChanceCardCurrentPlayergets200fromeachplayer() {
        ChanceCard testChanceCard = new CardMoney("chance26","hybrid", 200);
        testChanceCard.setCardLang(msg);
        testChanceCard.setPlayers(testPlayers);
        testChanceCard.playCard(testPlayers[0]);
        assertEquals(600, testPlayers[0].getCurrentBalance());
        assertEquals(-200, testPlayers[1].getCurrentBalance());
        assertEquals(-200, testPlayers[2].getCurrentBalance());
        assertEquals(-200, testPlayers[3].getCurrentBalance());

    }
    @Test
    // chancecard 13, spiller skal kunne modtage 500 kr fra banken
    public void playChanceCardReceive500() {
        ChanceCard testChanceCard = new CardMoney("chance13","deposit", 500);
        testChanceCard.setCardLang(msg);
        testChanceCard.playCard(testPlayers[0]);
        assertEquals(500, testPlayers[0].getCurrentBalance());

    }
}