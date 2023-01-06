package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class CardMove extends ChanceCard {
    private int move;
    private String moveType;
    private String choice;
    String[] buttons;
    public CardMove(String cardName, GuiController guiController, int move, String moveType) {
        super(cardName, guiController);
        this.move = move;
        this.moveType = moveType;
    }

    public void setOptionsArr() {
        buttons = new String[]{"Move 1", "Pick again"};

    }

    public void playCard(Player currentPlayer) {
        if (moveType == "index"){
            int distance = currentPlayer.getDistanceToMove(move, 40);
            currentPlayer.updatePosition(distance);
            pickAgain = false;

        } else if(moveType == "distance") {
           /* if(cardName == "chance3") {
                choice = guiController.getUserSelection(msg.getText("prompt"), buttons);
                if (choice == "Pick again") {
                    pickAgain = true;

                } else {currentPlayer.updatePosition(move);}

            } else {currentPlayer.updatePosition(move);}*/

            currentPlayer.updatePosition(move);

        } else {System.out.println("Card type not recognized.");}
    }

}

