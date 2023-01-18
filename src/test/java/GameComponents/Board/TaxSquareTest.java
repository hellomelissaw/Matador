package GameComponents.Board;

import GameComponents.Bank;
import GameComponents.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaxSquareTest {


    Player testPlayer = new Player("testPlayer");
    Bank bank = new Bank();
    int withdrawMoney = 0;
    int balance = 0;
    @Test
    public void testPayTenProcentTax() {
        testPlayer.setGuiIsOn(false);
        testPlayer.depositMoney(2000,false);
        assertEquals(2000,testPlayer.getCurrentBalance());

        balance = testPlayer.getCurrentBalance();
        withdrawMoney = (int) (balance / 100) * 10;
        testPlayer.withdrawMoney(withdrawMoney,false);
        assertEquals(1800,testPlayer.getCurrentBalance());
    }
}

