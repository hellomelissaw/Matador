package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;

public class DeedSquare_NonBuildable extends DeedSquare {
    public DeedSquare_NonBuildable(String deedName, int deedPrice, int[] rent, GuiController guiController) {
        super(deedName, deedPrice, rent, guiController);
    }

    @Override
    public void landOn(Player currentPlayer) {

    }
}
