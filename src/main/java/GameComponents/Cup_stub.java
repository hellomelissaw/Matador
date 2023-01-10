package GameComponents;

import Controllers.GuiController;

public class Cup_stub extends Cup {
    private int testSum = 30;


    public Cup_stub(GuiController guiController) {
        super(guiController);
    }

    public int getSum () { // GETS THE SUM OF THE VALUE OF BOTH DICE
        int d1 = 1;
        int d2 = testSum-1;

       /* int DiceData[] = new int[3];
        DiceData[0]=d1;
        DiceData[1]=d2;
        DiceData[2]=testSum;*/
        return d1+d2;
    }
}
