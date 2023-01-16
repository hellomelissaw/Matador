package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class CardDeed extends ChanceCard {
    /**
     * Constructs a card that involves moving player to the nearest shipyard (rederi)
     * @param cardName name of the card
     * @param guiController GuiController used throughout the program
     */
    public CardDeed(String cardName, GuiController guiController) {
        super(cardName, guiController);

    }


    public void playCard(Player currentPlayer) { //
        int[] shipyardIndex = {5, 15, 25, 35};
        int diff = 39;
        if (currentPlayer.getPosition() == 2 || currentPlayer.getPosition() == 36 ){
            currentPlayer.updatePosition(shipyardIndex[0]-currentPlayer.getPosition());

        } if (currentPlayer.getPosition() == 7) {
            currentPlayer.updatePosition(shipyardIndex[1] - currentPlayer.getPosition());

        } if (currentPlayer.getPosition() == 17 || currentPlayer.getPosition() == 22 ) {
            currentPlayer.updatePosition(shipyardIndex[2] - currentPlayer.getPosition());

        } if (currentPlayer.getPosition() == 33) {
            currentPlayer.updatePosition(shipyardIndex[3] - currentPlayer.getPosition());
        }

    }

}
