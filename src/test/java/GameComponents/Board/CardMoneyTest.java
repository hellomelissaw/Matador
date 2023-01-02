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
    Text msg = new Text("src/main/java/Translator/EnglishText", guiController);
    GUI_Player[] testGuiPlayers = new GUI_Player[4];

    public CardMoneyTest() {
        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[1] = new Player("TestPlayer 2");
        testPlayers[2] = new Player("TestPlayer 3");
        testPlayers[3] = new Player("TestPlayer 4");

        testGuiPlayers[0] = new GUI_Player("TestPlayer 1");
        testGuiPlayers[1] = new GUI_Player("TestPlayer 2");
        testGuiPlayers[2] = new GUI_Player("TestPlayer 3");
        testGuiPlayers[3] = new GUI_Player("TestPlayer 4");


        for (int i = 0 ; i < testPlayers.length ; i++) {
            testPlayers[i].setGui(testGuiPlayers[i], guiController, msg);
        }



    }
    @Test
    public void playChanceCardPay2() {

        ChanceCard testChanceCard = new CardMoney("chance5",guiController,"withdraw",2);
        testChanceCard.setCardLang(msg);
        testChanceCard.playCard(testPlayers[1]);
        assertEquals(-2, testPlayers[1].getCurrentBalance());

    }

    @Test
    public void playChanceCardCurrentPlayerGets3OthersPay1() {
        ChanceCard testChanceCard = new CardMoney("chance6",guiController,"hybrid", 1);
        testChanceCard.setCardLang(msg);
        testChanceCard.setPlayers(testPlayers);
        testChanceCard.playCard(testPlayers[0]);
        assertEquals(3, testPlayers[0].getCurrentBalance());
        assertEquals(-1, testPlayers[1].getCurrentBalance());
        assertEquals(-1, testPlayers[2].getCurrentBalance());
        assertEquals(-1, testPlayers[3].getCurrentBalance());

    }
    @Test
    public void playChanceCardReceive2() {
        ChanceCard testChanceCard = new CardMoney("chance7",guiController,"deposit", 2);
        testChanceCard.setCardLang(msg);
        testChanceCard.playCard(testPlayers[0]);
        assertEquals(2, testPlayers[0].getCurrentBalance());

    }
}