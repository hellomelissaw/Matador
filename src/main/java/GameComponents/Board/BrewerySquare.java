package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;

public class BrewerySquare extends DeedSquare {
    public BrewerySquare(String deedName, int deedPrice, int[] rent, GuiController guiController) {
        super(deedName);
    }

    @Override
    public void landOn(Player currentPlayer) {

    }
}
