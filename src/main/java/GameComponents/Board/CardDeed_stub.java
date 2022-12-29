package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;
import gui_fields.GUI_Player;

public class CardDeed_stub extends ChanceCard {
    private String color1;
    private String color2;

    Square selectedSquare;

    public CardDeed_stub(String cardName, GuiController guiController, String color1, String color2) {
        super(cardName, guiController);
        this.color1 = color1;
        this.color2 = color2;

    }
    /*public void setBoard(Square[] board) {
        this.board = board;
    }*/

    public void setOptionsArr() {

    }
    public void setSelectedSquare(Square selectedSquare){
        this.selectedSquare = selectedSquare;
        System.out.println("The selected square's name is: " + selectedSquare.getSquareName());
    }
    public void playCard(Player currentPlayer) {
        int distance;
        int index = 0;

        if (color1 == "na") {
            index = 10;
            selectedSquare = board[index];

        } else {
            int arraySize = 0;
            Square[] currentColorsArr = new Square[0];
            int[] indexArray = {};
            for (int i = 0; i < board.length; i++) {

                if (board[i].getColor() == color1 || board[i].getColor() == color2) {
                    arraySize++;
                    Square[] updatedColorArr = new Square[arraySize];
                    updatedColorArr[0] = board[i];
                    System.arraycopy(currentColorsArr, 0, updatedColorArr, 1, currentColorsArr.length);
                    currentColorsArr = updatedColorArr.clone();
                }
            }

           // selectedSquare = guiController.getSelectedSquare(currentColorsArr);
            if(((DeedSquare)selectedSquare).hasDeed()){
                ((DeedSquare)selectedSquare).setDeedToFree();
            }
            for(int i = 0; i < board.length ; i++) {
                if (selectedSquare.getSquareName() == board[i].getSquareName()) {
                    index = i;
                }
            }


        } // end else

        distance = currentPlayer.getDistanceToMove(index, board.length);
        currentPlayer.updatePosition(distance);
        selectedSquare.landOn(currentPlayer);

        System.out.println("This is new position: " + currentPlayer.getPosition() + " and new square color is: " + board[currentPlayer.getPosition()].getColor());


    }
}
