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
    Text msg = new Text("src/main/java/Translator/EnglishText");

    GUI_Player testGuiPlayer1 = new GUI_Player("TestPlayer 1");

    @Test
    public void playCardMoveToStart() {
        ChanceCard testChanceCard = new CardMove("chance1",guiController,24);
        //testChanceSquare.setLang("EnglishText");
        testChanceCard.setLang(msg);
        testChanceCard.playCard(testPlayer1,testGuiPlayer1);
        assertEquals(0, testPlayer1.getPosition());

    }

    @Test
    public void playCardMoveToPromenade() {
        //testChanceSquare.setLang("EnglishText");
        ChanceCard testChanceCard = new CardMove("chance4",guiController,23);
        testChanceCard.setLang(msg);
        testChanceCard.playCard(testPlayer1,testGuiPlayer1);
        assertEquals(23, testPlayer1.getPosition());

    }
    @Test
    public void playCardMove5() {
        //testChanceSquare.setLang("EnglishText");
        ChanceCard testChanceCard = new CardMove("chance2",guiController,5);
        testChanceCard.setLang(msg);
        testPlayer1.updatePosition(5);
        testChanceCard.playCard(testPlayer1,testGuiPlayer1);
        assertEquals(10, testPlayer1.getPosition());

    }

    @Test
    public void playCardMove1() {
        //testChanceSquare.setLang("EnglishText");
        ChanceCard testChanceCard = new CardMove("chance3",guiController,1);
        testChanceCard.setLang(msg);
        testPlayer1.updatePosition(5);
        testChanceCard.playCard(testPlayer1,testGuiPlayer1);

        assertEquals(6, testPlayer1.getPosition());

    }
}