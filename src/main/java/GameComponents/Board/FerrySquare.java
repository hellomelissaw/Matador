package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;

public class FerrySquare extends DeedSquare {
    public FerrySquare(String deedName, int deedPrice, int[] rent, GuiController guiController) {
        super(deedName);
    }

    @Override
    public void landOn(Player currentPlayer) {

    }
}
