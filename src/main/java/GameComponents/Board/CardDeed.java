package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class CardDeed extends ChanceCard {
    private String color1;
    private String color2;
    Square[] currentColorArr = new Square[0];

    public CardDeed(String cardName, GuiController guiController, String color1, String color2) {
        super(cardName, guiController);
        this.color1 = color1;
        this.color2 = color2;

    }

    public void setOptionsArr() {
        int arraySize = 0;
        for (int i = 0; i < board.length; i++) {

            if (board[i].getColor() == color1 || board[i].getColor() == color2) {
                arraySize++;
                Square[] updatedColorArr = new Square[arraySize];
                updatedColorArr[0] = board[i];
                System.arraycopy(currentColorArr, 0, updatedColorArr, 1, currentColorArr.length);
                currentColorArr = updatedColorArr.clone();

            }
        }
    }

    public void playCard(Player currentPlayer) {
        int distance;
        int index = 0;
        Square selectedSquare = null;

       if (color1 == "na") {
           index = 10;

        } else {

           selectedSquare = guiController.getSelectedSquare(currentColorArr);

           if(((DeedSquare)selectedSquare).hasDeed()){
               ((DeedSquare)selectedSquare).setDeedToFree();
           }
           System.out.println("Index from getSquareIndex: " + index);
           index = guiController.getSquareIndex(board);
                } // end else

        distance = currentPlayer.getDistanceToMove(index, board.length);
        currentPlayer.updatePosition(distance);
        selectedSquare.landOn(currentPlayer);


        System.out.println("This is new position: " + currentPlayer.getPosition() + " and new square color is: " + board[currentPlayer.getPosition()].getColor());


    }
}
