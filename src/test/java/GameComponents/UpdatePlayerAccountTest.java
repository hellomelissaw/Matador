package GameComponents;

import Controllers.GuiController;
import org.junit.Test;
import static org.junit.Assert.*;

public class UpdatePlayerAccountTest {
    Player testPlayer = new Player("Test Player");

    @Test
    public void getCurrentBalance0() {
        assertEquals(0,testPlayer.getCurrentBalance());
    }

    @Test
    public void deposit10() {
        testPlayer.depositMoney(10);
        assertEquals(10,testPlayer.getCurrentBalance());
    }

    @Test
    public void ifBalanceNegativeDeclarePlayerBankrupt() {
        testPlayer.withdrawMoney(3);
        assertEquals(true,testPlayer.isBankrupt());
    }

    @Test
    public void deposit10withdraw3() {
        testPlayer.depositMoney(10);
        testPlayer.withdrawMoney(3);
        assertEquals(7,testPlayer.getCurrentBalance());
    }

}