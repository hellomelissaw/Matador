package GuiController;

import GameComponents.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuiControllerTest {

    @Test
    public void addPlayerOnBoard() {
    }

    @Test
    public void move() {
    }

    @Test
    public void updateBalance() {
        Player player = new Player();
        player.depositMoney(20);
        player.getCurrentBalance();
        int balance = 2;
         player.withdrawMoney(balance);
        assertEquals(18,player.getCurrentBalance());

    }

    @Test
    public void receiveRent() {
    }

    @Test
    public void winner() {
    }
}