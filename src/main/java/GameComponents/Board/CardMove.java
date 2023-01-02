package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class CardMove extends ChanceCard {
    private int move;
    private String moveType;
    public CardMove(String cardName, GuiController guiController, int move, String moveType) {
        super(cardName, guiController);
        this.move = move;
        this.moveType = moveType;
    }

    public void setOptionsArr() {

    }

    public void playCard(Player currentPlayer) {
        if (moveType == "index"){
            int distance = currentPlayer.getDistanceToMove(move, board.length);
            currentPlayer.updatePosition(distance);
            pickAgain = false;

        } else if(moveType == "distance") {
            if(cardName == "chance3") {
                String[] buttons = {"Move 1", "Pick again"};
                String choice = guiController.getUserSelection(msg.getText("prompt"), buttons);
                if (choice == "Move 1") {
                    currentPlayer.updatePosition(move);
                    pickAgain = false;

                } else if (choice == "Pick again") {
                    pickAgain = true;
                }

            } else {currentPlayer.updatePosition(move);}

        } else {System.out.println("Card type not recognized.");}
    }

}

