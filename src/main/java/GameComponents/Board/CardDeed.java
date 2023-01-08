package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class CardDeed extends ChanceCard {

    public CardDeed(String cardName, GuiController guiController) {
        super(cardName, guiController);

    }


    public void playCard(Player currentPlayer) {
        int[] shipyardIndex = {5, 15, 25, 35};
        int diff = 39;
        for (int i = 0 ; i < shipyardIndex.length ; i++) {
            int newDiff = Math.abs(currentPlayer.getPosition()-shipyardIndex[i]);

            if(newDiff < diff) {
                diff = newDiff;
            }
        }
        currentPlayer.updatePosition(diff);
        board[currentPlayer.getPosition() + diff].landOn(currentPlayer);

    }
}
