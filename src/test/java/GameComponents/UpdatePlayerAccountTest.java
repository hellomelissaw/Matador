package GameComponents;

import org.junit.Test;
import static org.junit.Assert.*;

public class UpdatePlayerAccountTest {
    player testPlayer = new player("Test Player");

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
    public void noNegativeBalanceZeroMinus3() {
        testPlayer.withdrawMoney(3);
        assertEquals(0,testPlayer.getCurrentBalance());
    }

    @Test
    public void deposit10withdraw3() {
        testPlayer.depositMoney(10);
        testPlayer.withdrawMoney(3);
        assertEquals(7,testPlayer.getCurrentBalance());
    }

}