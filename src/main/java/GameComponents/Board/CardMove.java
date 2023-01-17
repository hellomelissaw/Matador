package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class CardMove extends ChanceCard {
    private int move;
    private String moveType;
    private String choice;
    String[] buttons;

    /**
     * Constructs a chance card involving moving the player on the board
     * @param cardName name of the card
     * @param guiController GuiController used throughout the program
     * @param move determines either the amount of squares to be moved or the index to be moved to according to moveType
     * @param moveType determines the player is moved a certain amount of squares or to a particular index
     */
    public CardMove(String cardName, int move, String moveType) {
        super(cardName);
        this.move = move;
        this.moveType = moveType;
    }

    public void playCard(Player currentPlayer) {
        if (moveType == "index"){ // MOVES PLAYER TO A GIVEN INDEX
            int distance = currentPlayer.getDistanceToMove(move, 40);
            currentPlayer.updatePosition(distance);
            pickAgain = false;

        } else if(moveType == "distance") { // MOVES PLAYER A GIVEN AMOUNT OF SQUARES
            {currentPlayer.updatePosition(move);}

        } else {System.out.println("Card type not recognized.");}
    }

}

