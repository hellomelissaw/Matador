package GameComponents;

import Controllers.GuiController;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;
import static org.junit.Assert.*;

public class UpdatePlayerAccountTest {
    Player testPlayer = new Player("Test Player");

    Text msg = new Text("src/main/java/Translator/DanskTekst");

    public UpdatePlayerAccountTest() {
        msg.setGuiIsOn(false);
        testPlayer.setGuiIsOn(false);

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