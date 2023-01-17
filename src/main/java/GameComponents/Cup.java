package GameComponents;

import Controllers.GuiController;

/*
===================================================================================
This class is reused from our previous project CDIO 1 and translated to English.
===================================================================================
 */
public class Cup {
    Die  die1 = new Die();
    Die die2 = new Die();
    GuiController guiController;

    public boolean testRollDice;

    int d1;
    int d2;

    public Cup (GuiController guiController) {this.guiController = guiController;}

    public Cup() {

    }
    //public Cup (GUI gui) {this.gui = gui;}

    public Boolean rollAndCheckEqualValueOfDice(){

        boolean equalValue = false;

        //d1 = die1.roll();
        //d2 = die2.roll();
        if (!testRollDice) guiController.setDice(d1,d2);

        System.out.println("First die: " + d1 + " and second die: " + d2);

        if (d1 == d2){
            equalValue = true;
        }
        return equalValue;
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
    public boolean CheckForEqualValueOfDice(){

        boolean equalValue = false;

        if (d1 == d2){
            equalValue = true;
        }
        return equalValue;
    }
}

