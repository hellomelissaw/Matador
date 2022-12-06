package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class CardMove extends ChanceCard {
    int distance;

    public CardMove(String cardMessage, GuiController guiController, int distance) {
        super(cardMessage, guiController);
        this.distance = distance;
    }

    public void playCard(Player currentPlayer) {

        if (distance == 1) {
            String message = msg.getText("prompt") + msg.getText("chance3");
            int choice = guiController.getUserInteger(message);
            if (choice == 2) {
                distance -= 1;
                pickAgain = true; //NEEDS TO BE TESTED
            }

            } else if (distance == 23 || distance == 24) {
                distance -= currentPlayer.getPosition();
                pickAgain = false;

            } else if (distance == 5) {
                pickAgain = false;

            }
            currentPlayer.updatePosition(distance);
        }
    }

