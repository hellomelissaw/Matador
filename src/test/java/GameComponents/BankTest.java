package GameComponents;


import org.junit.Test;
import static org.junit.Assert.*;


public class BankTest {


    Bank bank = new Bank();



    @Test
    public void testGiveMoneyToBank(){
        int deposit = 1000;
        bank.giveMoneyToBank(deposit);
        assertEquals(bank.getBankBalance(),375500);

    }

}


