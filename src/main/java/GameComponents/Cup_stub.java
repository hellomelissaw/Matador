package GameComponents;

import Controllers.GuiController;
import gui_main.GUI;

public class Cup_stub extends Cup {
    private int testSum = 20;

    public Cup_stub(GuiController guiController) {
        super(guiController);
    }
    /*public Cup_stub(GUI gui) {
        super(gui);
    }*/

    public int getSum () { // GETS THE SUM OF THE VALUE OF BOTH DICE
        int d1 = 1;
        int d2 = testSum-1;
        return d1+d2;
    }
}
