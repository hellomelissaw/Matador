package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class CardMove extends ChanceCard {
    int distance;
    public CardMove(String cardMessage, GuiController guiController, int distance) {
        super(cardMessage, guiController);
        this.distance = distance;
    }

    public void playCard(Player currentPlayer){
        if (distance == 23 || distance == 24) {
            distance = distance - currentPlayer.getPosition();
        }
            currentPlayer.updatePosition(distance);
    }

}
