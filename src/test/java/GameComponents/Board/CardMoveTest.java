package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;
import GameComponents.Board.BoardInit;
import org.junit.Test;

import static org.junit.Assert.*;



public class CardMoveTest {
    GuiController guiController = new GuiController();
    Player[] testPlayers = new Player[1];

   // Square testChanceSquare = new ChanceSquare("Testing Chance", guiController);
    Text msg = new Text("src/main/java/Translator/EnglishText", guiController);

    public CardMoveTest() {
        testPlayers[0] = new Player("TestPlayer 1");
        BoardInit board = new BoardInit(guiController, msg, testPlayers);
        testPlayers[0].setGui(guiController.createGuiPlayer(testPlayers[0]),guiController,msg);

    }

    @Test
    public void playCardMoveToStart() {
        ChanceCard testChanceCard = new CardMove("chance1",guiController,0, "index");
        //testChanceSquare.setLang("EnglishText");
        testChanceCard.setCardLang(msg);
        testChanceCard.playCard(testPlayers[0]);
        assertEquals(0, testPlayers[0].getPosition());

    }
    @Test
    public void getM2WhenMoveToStartCard() {
        ChanceCard testChanceCard = new CardMove("chance1",guiController,0, "index");
        testChanceCard.setCardLang(msg);
        testPlayers[0].updatePosition(20);
        testChanceCard.playCard(testPlayers[0]);
        assertEquals(2, testPlayers[0].getCurrentBalance());
    }

    @Test
    public void playCardMoveToStartGetM2() {
        ChanceCard testChanceCard = new CardMove("chance1",guiController,0, "index");
        //testChanceSquare.setLang("EnglishText");
        testChanceCard.setCardLang(msg);
        testPlayers[0].updatePosition(3);
        testChanceCard.playCard(testPlayers[0]);
        assertEquals(2,testPlayers[0].getCurrentBalance());
    }

    @Test
    public void playCardMoveToPromenade() {
        //testChanceSquare.setLang("EnglishText");
        ChanceCard testChanceCard = new CardMove("chance4",guiController,23, "index");
        testChanceCard.setCardLang(msg);
        testChanceCard.playCard(testPlayers[0]);
        assertEquals(23, testPlayers[0].getPosition());

    }
    @Test
    public void playCardMove5() {
        //testChanceSquare.setLang("EnglishText");
        ChanceCard testChanceCard = new CardMove("chance2",guiController,5, "distance");
        testChanceCard.setCardLang(msg);
        testPlayers[0].updatePosition(5);
        testChanceCard.playCard(testPlayers[0]);
        assertEquals(10, testPlayers[0].getPosition());

    }

    @Test
    public void playCardMove1() {
        //testChanceSquare.setLang("EnglishText");
        ChanceCard testChanceCard = new CardMove("chance3",guiController,1, "distance");
        testChanceCard.setCardLang(msg);
        testPlayers[0].updatePosition(5);
        testChanceCard.playCard(testPlayers[0]);

        assertEquals(6, testPlayers[0].getPosition());

    }
    @Test
    public void playCardPickAgain() {
        //testChanceSquare.setLang("EnglishText");
        ChanceCard testChanceCard = new CardMove("chance3",guiController,1, "distance");
        testChanceCard.setCardLang(msg);
        testPlayers[0].updatePosition(5);
        testChanceCard.playCard(testPlayers[0]);

        assertEquals(5, testPlayers[0].getPosition());

    }
}