package GameComponents;

import org.junit.Test;

import static org.junit.Assert.*;

public class BankTest {
    Player[] players = new Player[1];

    public BankTest() {
        players[0] = new Player("Test Player 1");
        players[0].guiIsOn(false);

    }

    @Test
    public void playerReceives10FromBank() {
        players[0].depositMoney(10);
        players[0].updateBank(10, "deposit");

        assertEquals(10, players[0].getCurrentBalance());
        assertEquals(150000-10, players[0].getBankBalance());

    }

    @Test
    public void takeMoneyFromBank() {
    }

    @Test
    public void priceCalculator() {
    }

    @Test
    public void sellHouseToBank() {
    }

    @Test
    public void sellHotelToBank() {
    }

    @Test
    public void buyHouseFromBank() {
    }

    @Test
    public void buyHotelFromBank() {
    }
}