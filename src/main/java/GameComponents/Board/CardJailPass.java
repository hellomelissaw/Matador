package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;

public class CardJailPass extends ChanceCard {
    public CardJailPass(String cardName, GuiController guiController) {
        super(cardName, guiController);
    }


    public void playCard(Player currentPlayer, Player[] players) {
        currentPlayer.giveJailPass();
    }
}
