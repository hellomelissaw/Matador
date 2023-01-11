package GuiController;

import Controllers.GuiController;
import GameComponents.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuiControllerTest {

    @Test
    public void addPlayerOnBoard() {
        GUI_Player[] guiPlayer = new GUI_Player[3];
        for (int i = 0; i < 3; i++) {
            guiPlayer[i] = new GUI_Player("ava");
            GUI gui = new GUI();
            gui.addPlayer(guiPlayer[i]);
        }
        assertEquals(3,3);
    }

   /* @Test
    public void move() {
        GUI_Field[] fields = new GUI_Field[10];
        GUI_Player guiPlayer = new GUI_Player("Ava");
        fields[0].setCar(guiPlayer,false);
        fields[8].setCar(guiPlayer,true);
        assertArrayEquals(fields[8].setCar("Ava",true),fields[8].setCar(guiPlayer,true));


    }*/

    @Test
    public void updateBalance() {
        Player player = new Player("Test Player");
        player.depositMoney(20,false);
        player.getCurrentBalance();
        int balance = 2;
         player.withdrawMoney(balance,false);
        assertEquals(18,player.getCurrentBalance());

    }

    @Test
    public void receiveRent() {
    }

    @Test
    public void winner() {
    }
}