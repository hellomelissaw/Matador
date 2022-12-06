package GameComponents;

import Controllers.GuiController;
import org.junit.Test;

import static org.junit.Assert.*;

public class winnerPlayerTest {
    GuiController guiController = new GuiController();
    @Test
    public void winner() {
        Player[] players = new Player[4];
        //Player winner = new Player("");
        players[0] = new Player("A",guiController);
        players[1] = new Player("B",guiController);
        players[2] = new Player("C",guiController);
        players[3] = new Player("D",guiController);
        players[0].depositMoney(12);
        players[1].depositMoney(140);
        players[2].depositMoney(18);
        players[3].depositMoney(20);

        for (int i = 0; i < players.length; i++) {
            assertEquals("B",players[i].winner(players));
        }

    }
}