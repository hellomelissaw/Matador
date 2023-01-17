package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class CardDeed extends ChanceCard {
    /**
     * Constructs a card that involves moving player to the nearest shipyard (rederi)
     * @param cardName name of the card
     */
    public CardDeed(String cardName) {
        super(cardName);

    }


    public void playCard(Player currentPlayer) { //
        int[] shipyardIndex = {5, 15, 25, 35};
        int diff = 39;
        int closestShipIndex = 5;
        for (int i = 0 ; i < shipyardIndex.length ; i++) { // CALCULATES WHICH SHIPYARD IS THE NEAREST TO PLAYER
            int newDiff = Math.abs(currentPlayer.getPosition()-shipyardIndex[i]);

            if(newDiff < diff) {
                diff = newDiff;
                closestShipIndex = shipyardIndex[i];
            }
        }
        int distance = currentPlayer.getDistanceToMove(closestShipIndex, 40);
        currentPlayer.updatePosition(distance);
        board[closestShipIndex].landOn(currentPlayer);

    }
}
