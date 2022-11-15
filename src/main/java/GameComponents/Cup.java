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

    Die die1  = new Die();
    Die die2  = new Die();
    GuiController guiController = new GuiController();
    GUI gui;
    String guiMessage;

    public int[] getSum () { // GETS THE SUM OF THE VALUE OF BOTH DICE
        int d1 = die1.roll();
        int d2 = die2.roll();
        guiController.setDice(die1,die2);
        System.out.println("First die: " + d1 + " and second die: " + d2);
        guiMessage = "First die: " + die1 + " and second die: " + die2;
        guiController.showMessage(guiMessage);
        int sum;
        sum = d1 + d2;
        //guiController.diceSum(die1,die2);
        int DiceData[] = new int[3];
        DiceData[0]=d1;
        DiceData[1]=d2;
        DiceData[2]=sum;
        //guiController.diceSum(die1,die2);
        System.out.println(guiController.diceSum(d1,d2).toString());
        guiMessage = "You earn " + guiController.diceSum(d1,d2).toString() + " and move your car!"; // how convert to string
        guiController.showMessage(guiMessage);
        return DiceData;
    }
}
