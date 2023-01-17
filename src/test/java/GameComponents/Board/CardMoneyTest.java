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

    ChanceSquare testChanceSquare;

    public CardMoneyTest() {
        for (int i = 0 ; i < testPlayers.length ; i++) {
            testPlayers[i] = new Player("TestPlayer "+i);
            testPlayers[i].setBank(bank);
            testPlayers[i].setGuiIsOn(false);
            testPlayers[i].setLang(msg);
        }
    }

    @Test
    // Userstory k11: kan man betale penge til banken.
    public void playChanceCardWithdrawMoney() {
        ChanceCard testChanceCard = new CardMoney("withdraw",guiController,"withdraw",500);
        testChanceCard.setCardLang(msg);
        testChanceCard.playCard(testPlayers[1]);
        assertEquals(-500, testPlayers[1].getCurrentBalance());


    }

    @Test
    // chancecard 1: Oliepriserne er steget, og De skal betale kr 500 pr hus og kr 2000 pr hotel.
    public void playChanceCardPay500or2000() {
        CardMoney testChanceCard = new CardMoney("Chance1",guiController,"withdraw",500, 2000);
        testChanceCard.setCardLang(msg);
        int[] rent = {0,0};
        //Deed_Buildable[] testDeed = {new Deed_Buildable(100,rent, "test", 100)};
        DeedSquare_Buildable DeedSquareTest = new DeedSquare_Buildable("Street", 0, rent, 100);
        DeedSquareTest.setGroup("Pink", 1);
        testPlayers[1].depositMoney(1000, true);
        DeedSquareTest.setOwnerForTesting(testPlayers[1]);
        DeedSquareTest.getDeed().setHouseCount(1);
        testChanceCard.playCard(testPlayers[1]);
        assertEquals(500, testPlayers[1].getCurrentBalance());

        //test af 4 huse
        // vi tilføjer 500 kr for at reset til 1000 kr

        testPlayers[1].depositMoney(500, true);
        DeedSquareTest.getDeed().setHouseCount(4);
        testChanceCard.playCard(testPlayers[1]);
        assertEquals(-1000, testPlayers[1].getCurrentBalance());

        //test for et hotel
        // vi tilføjer 2000 kr for at reset til 1000 kr

        DeedSquareTest.getDeed().setHouseCount(0);
        DeedSquareTest.getDeed().setHasHotel(true);
        testPlayers[1].depositMoney(2000,true);
        testChanceCard.playCard(testPlayers[1]);
        assertEquals(-1000, testPlayers[1].getCurrentBalance());


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
    // Userstory K11: chancecard 13, spiller skal kunne modtage 500 kr fra banken
    public void playChanceCardReceive200FromPlayers() {
        ChanceCard testChanceCard = new CardMoney("chance13",guiController,"deposit", 500);
        testChanceCard.setCardLang(msg);
        testChanceCard.playCard(testPlayers[0]);
        assertEquals(500, testPlayers[0].getCurrentBalance());

    }

}