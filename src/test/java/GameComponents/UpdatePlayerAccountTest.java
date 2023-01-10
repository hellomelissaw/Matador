package GameComponents;

import Controllers.GuiController;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;
import static org.junit.Assert.*;

public class UpdatePlayerAccountTest {
    Player testPlayer = new Player("Test Player");
    GUI_Player testGuiPlayer = new GUI_Player("Test Player");

    GuiController guiController = new GuiController();

    Text msg = new Text("src/main/java/Translator/EnglishText", guiController);

    public UpdatePlayerAccountTest() {
        testPlayer.setGui(testGuiPlayer, guiController, msg);
    }

    @Test
    public void getCurrentBalance0() {
        assertEquals(0,testPlayer.getCurrentBalance());
    }

    @Test
    public void deposit10() {
        testPlayer.depositMoney(10,false);
        assertEquals(10,testPlayer.getCurrentBalance());
    }

    @Test
    public void ifBalanceNegativeDeclarePlayerBankrupt() {
        testPlayer.withdrawMoney(3,false);
        assertEquals(true,testPlayer.isBankrupt());
    }

    @Test
    public void deposit10withdraw3() {
        testPlayer.depositMoney(10,false);
        testPlayer.withdrawMoney(3,false);
        assertEquals(7,testPlayer.getCurrentBalance());
    }

}