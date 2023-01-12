package GameComponents;

import Controllers.GuiController;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class winnerPlayerTest {
    GuiController guiController = new GuiController();
    Player[] players = new Player[4];
    GUI_Player[] guiPlayers = new GUI_Player[4];

    Text msg = new Text("src/main/java/Translator/Dansktekst", guiController);

    public winnerPlayerTest() {
        players[0] = new Player("A");
        players[1] = new Player("B");
        players[2] = new Player("C");
        players[3] = new Player("D");

        guiPlayers[0] = new GUI_Player("A");
        guiPlayers[1] = new GUI_Player("B");
        guiPlayers[2] = new GUI_Player("C");
        guiPlayers[3] = new GUI_Player("D");

        for (int i = 0 ; i < players.length ; i++)
            players[i].setGui(guiPlayers[i], guiController, msg);

    }

    @Test
    public void winner() {

        players[0].depositMoney(12);
        players[1].depositMoney(140);
        players[2].depositMoney(18);
        players[3].depositMoney(20);

        for (int i = 0; i < players.length; i++) {
            assertEquals("B",players[i].winner(players));
        }

    }
}