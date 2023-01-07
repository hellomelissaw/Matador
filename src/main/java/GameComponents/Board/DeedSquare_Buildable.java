package GameComponents.Board;

import Controllers.GuiController;

public class DeedSquare_Buildable extends DeedSquare {
    /**
     * Constructs a Square of type DeedSquare (ownable Square)
     *
     * @param deedName      name of the Deed for the Square (for example "The Skate Park").
     * @param deedPrice     the price of the deed, both for buying and amount of rent to pay once bought.
     * @param rent
     * @param guiController The GuiController used throughout the classes.
     */
    public DeedSquare_Buildable(String deedName, int deedPrice, int[] rent, GuiController guiController) {
        super(deedName, deedPrice, rent, guiController);
    }
}
