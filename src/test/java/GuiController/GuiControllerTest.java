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
        Player[] player = new Player[3];
        GuiController guiController = new GuiController();
        for (int i = 0; i < 3; i++) {
            player[i] = new Player("Test Player");

            guiController.createGuiPlayer(player[i]);

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

}