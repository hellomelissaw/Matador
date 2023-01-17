package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Bank;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardMoneyTest {
    GuiController guiController = new GuiController();
    Player[] testPlayers = new Player[4];
    Text msg = new Text("src/main/java/Translator/DanskTekst");
    GUI_Player[] testGuiPlayers = new GUI_Player[4];

    Bank bank = new Bank();

    public CardMoneyTest() {
        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[0].setBank(bank);
        testPlayers[1] = new Player("TestPlayer 2");
        testPlayers[1].setBank(bank);
        testPlayers[2] = new Player("TestPlayer 3");
        testPlayers[2].setBank(bank);
        testPlayers[3] = new Player("TestPlayer 4");
        testPlayers[3].setBank(bank);

        testGuiPlayers[0] = new GUI_Player("TestPlayer 1");
        testGuiPlayers[1] = new GUI_Player("TestPlayer 2");
        testGuiPlayers[2] = new GUI_Player("TestPlayer 3");
        testGuiPlayers[3] = new GUI_Player("TestPlayer 4");


        for (int i = 0 ; i < testPlayers.length ; i++) {
            testPlayers[i].setGui(testGuiPlayers[i], guiController, msg);
        }



    }

    @Test
    public void playChanceCardPay500() {
        // chancecard 1, spiller skal betale 500 kr til banken
        ChanceCard testChanceCard = new CardMoney("Chance1",guiController,"withdraw",500);
        testChanceCard.setCardLang(msg);
        testChanceCard.playCard(testPlayers[1]);
        assertEquals(-500, testPlayers[1].getCurrentBalance());
    }

    @Test
    //chancecard 26, spiller modtager 200 kr fra resterende spillere
    public void playChanceCardCurrentPlayergets200fromeachplayer() {
        ChanceCard testChanceCard = new CardMoney("chance26",guiController,"hybrid", 200);
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
        ChanceCard testChanceCard = new CardMoney("chance13",guiController,"deposit", 500);
        testChanceCard.setCardLang(msg);
        testChanceCard.playCard(testPlayers[0]);
        assertEquals(500, testPlayers[0].getCurrentBalance());

    }
}