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
    ChanceCard testChanceCard = new CardMove("chance1",guiController,24);

    GUI_Player testGuiPlayer1 = new GUI_Player("TestPlayer 1");

    @Test
    public void playCardMoveToStart() {
        //testChanceSquare.setLang("EnglishText");
        testChanceCard.setLang(msg);
        testChanceCard.playCard(testPlayer1,testGuiPlayer1);
        assertEquals(0, testPlayer1.getPosition());

    }
}