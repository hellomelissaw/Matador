package GameComponents;

import Controllers.GameController;
import Controllers.GuiController;
import gui_fields.GUI_Board;
import gui_main.GUI;

/*
===================================================================================
This class is reused from our previous project CDIO 1 and translated to English.
===================================================================================
 */
public class Cup {
    Die  die1 = new Die();
    Die die2 = new Die();
    GuiController guiController;

    int d1;
    int d2;

    public Cup (GuiController guiController) {this.guiController = guiController;}

    public boolean checkEqualValueOfDice(){

        return d1 == d2;
    }

    public int getSum () { // GETS THE SUM OF THE VALUE OF BOTH DICE
         d1 = die1.roll();
         d2 = die2.roll();
        guiController.setDice(d1,d2);
        System.out.println("First die: " + d1 + " and second die: " + d2);
        int sum;
        sum = d1 + d2;
        return sum;
    }
}
