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

    public void playCard(Player currentPlayer) {
        if (moveType == "index"){
            int distance = currentPlayer.getDistanceToMove(move, 40);
            currentPlayer.updatePosition(distance);
            pickAgain = false;

        } else if(moveType == "distance") {
            {currentPlayer.updatePosition(move);}

        } else {System.out.println("Card type not recognized.");}
    }

}

