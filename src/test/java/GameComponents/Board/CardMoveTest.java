package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;
import GameComponents.Board.BoardInit;
import gui_main.GUI;
import org.junit.Test;

import static org.junit.Assert.*;



public class CardMoveTest {
    GuiController guiController = new GuiController();
    Player[] testPlayers = new Player[1];

   // Square testChanceSquare = new ChanceSquare("Testing Chance", guiController);
    Text msg = new Text("src/main/java/Translator/DanskTekst");

    public CardMoveTest() {
        testPlayers[0] = new Player("TestPlayer 1");
        BoardInit board = new BoardInit(guiController, msg, testPlayers);
        testPlayers[0].setGui(guiController.createGuiPlayer(testPlayers[0]),guiController,msg);

    }

    @Test
    // start på "start" feltet og ryk videre, modtag ingen yderligere belønninger
    public void playCardDontGet4000() {
        ChanceCard testChanceCard = new CardMove("Start",0, "index");
        testChanceCard.setCardLang(msg);
        testChanceCard.playCard(testPlayers[0]);
        assertEquals(0, testPlayers[0].getPosition());

    }
    @Test
    //passér start og modtag 4000 kr.
    public void get4000WhenMoveToStartCard() {
        System.out.println(testPlayers[0].getCurrentBalance());
        ChanceCard testChanceCard = new CardMove("chance29",0, "index");
        testChanceCard.setCardLang(msg);
        testPlayers[0].updatePosition(20);
        testChanceCard.playCard(testPlayers[0]);
        assertEquals(4000, testPlayers[0].getCurrentBalance());
    }

    @Test
    //chancecard 42, flyt brik til rådhuspladsen
    public void playCardMoveToRådhusplads() {
        ChanceCard testChanceCard = new CardMove("chance42",39, "index");
        testChanceCard.setCardLang(msg);
        testChanceCard.playCard(testPlayers[0]);
        assertEquals(39, testPlayers[0].getPosition());

    }


    @Test
    // chancecard 32, Ryk 3 felter tilbage
    public void playCardMoveBack() {
        ChanceCard testChanceCard = new CardMove("chance32", -3, "distance");
        testChanceCard.setCardLang(msg);
        testPlayers[0].updatePosition(5);
        testChanceCard.playCard(testPlayers[0]);

        assertEquals(2, testPlayers[0].getPosition());
    }

    @Test
    // chancecard 34, ryk frem til frederiksberg Allé, Hvis de passerer start, modtager du 4000 kr
    public void PassStartGet4000() {
        ChanceCard testChanceCard = new CardMove("chance34", 3, "distance");
        testChanceCard.setCardLang(msg);
        testPlayers[0].updatePosition(39);
        testChanceCard.playCard(testPlayers[0]);

        assertEquals(4000, testPlayers[0].getCurrentBalance());
    }

    @Test
    // chancecard 32, Ryk 3 felter frem
    public void playCardMove3Squares() {
        ChanceCard testChanceCard = new CardMove("chance32", 3, "distance");
        testChanceCard.setCardLang(msg);
        testPlayers[0].updatePosition(5);
        testChanceCard.playCard(testPlayers[0]);

        assertEquals(8, testPlayers[0].getPosition());
    }
}
