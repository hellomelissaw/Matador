package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class CardMove extends ChanceCard {
    int distanceToMove;

    public CardMove(String cardMessage, GuiController guiController, int distanceToMove) {
        super(cardMessage, guiController);
        this.distanceToMove = distanceToMove;
    }

    public void playCard(Player currentPlayer) {
        if (distanceToMove == 24 || distanceToMove == 23) {
            distanceToMove -= currentPlayer.getPosition();
            pickAgain = false;

        } else if (distanceToMove == 5) {
            pickAgain = false;

        } else if (distanceToMove == 1) {
            String[] buttons = {"Move 1", "Pick again"};
            String choice = guiController.getUserButtonPressed(msg.getText("prompt"), buttons);
            if (choice == "Move 1") {
                pickAgain = false;

            } else {
                distanceToMove -= 1;
                pickAgain = true;

            }

            }

            currentPlayer.updatePosition(distanceToMove);
        }
    }

