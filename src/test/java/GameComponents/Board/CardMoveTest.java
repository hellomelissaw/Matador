package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;



public class CardMoveTest {
    GuiController guiController = new GuiController();
    Player testPlayer1 = new Player("TestPlayer 1");

   // Square testChanceSquare = new ChanceSquare("Testing Chance", guiController);
    Text msg = new Text("src/main/java/Translator/EnglishText", guiController);

    public CardMoveTest() {
        testPlayer1.setGui(guiController.createGuiPlayer(testPlayer1),guiController,msg);

    }

    @Test
    public void playCardMoveToStart() {
        ChanceCard testChanceCard = new CardMove("chance1",guiController,0, "index");
        //testChanceSquare.setLang("EnglishText");
        testChanceCard.setCardLang(msg);
        testChanceCard.playCard(testPlayer1);
        assertEquals(0, testPlayer1.getPosition());

    }

    @Test
    public void playCardMoveToPromenade() {
        //testChanceSquare.setLang("EnglishText");
        ChanceCard testChanceCard = new CardMove("chance4",guiController,23, "index");
        testChanceCard.setCardLang(msg);
        testChanceCard.playCard(testPlayer1);
        assertEquals(23, testPlayer1.getPosition());

    }
    @Test
    public void playCardMove5() {
        //testChanceSquare.setLang("EnglishText");
        ChanceCard testChanceCard = new CardMove("chance2",guiController,5, "distance");
        testChanceCard.setCardLang(msg);
        testPlayer1.updatePosition(5);
        testChanceCard.playCard(testPlayer1);
        assertEquals(10, testPlayer1.getPosition());

    }

    @Test
    public void playCardMove1() {
        //testChanceSquare.setLang("EnglishText");
        ChanceCard testChanceCard = new CardMove("chance3",guiController,1, "distance");
        testChanceCard.setCardLang(msg);
        testPlayer1.updatePosition(5);
        testChanceCard.playCard(testPlayer1);

        assertEquals(6, testPlayer1.getPosition());

    }
    @Test
    public void playCardPickAgain() {
        //testChanceSquare.setLang("EnglishText");
        ChanceCard testChanceCard = new CardMove("chance3",guiController,1, "distance");
        testChanceCard.setCardLang(msg);
        testPlayer1.updatePosition(5);
        testChanceCard.playCard(testPlayer1);

        assertEquals(5, testPlayer1.getPosition());

    }
}