package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;
import gui_fields.GUI_Player;

public class CardDeed extends ChanceCard {
    private String color1;
    private String color2;
    Square[] board;

    public CardDeed(String cardName, GuiController guiController, String color1, String color2) {
        super(cardName, guiController);
        this.color1 = color1;
        this.color2 = color2;

    }
    public void setBoard(Square[] board) {
       this.board = board;
    }
    public void playCard(Player currentPlayer) {
        int currentPos = currentPlayer.getPosition();
        int distance;
        int index = 0;
        boolean foundFreeSquare = false;

       if (color1 == "na") {
           index = 10;
            /*
            int skateParkIndex = 10;
            if (currentPos > skateParkIndex) {
                distance = board.length - currentPlayer.getPosition() + skateParkIndex;

            } else {
                distance = skateParkIndex - currentPos;
            }

            currentPlayer.updatePosition(distance);

            if (((DeedSquare) board[skateParkIndex]).hasDeed()) {
                ((DeedSquare) board[skateParkIndex]).setDeedOwner(currentPlayer, skateParkIndex);

            } else {
                board[skateParkIndex].landOn(currentPlayer);
            }
**/
        } else {
            int arraySize = 0;
            Square[] currentColorsArr = new Square[0];

            for (int i = 0; i < board.length; i++) {

                if (board[i].getColor() == color1 || board[i].getColor() == color2) {
                    arraySize++;
                    Square[] updatedColorArr = new Square[arraySize];
                    updatedColorArr[0] = board[i];
                    System.arraycopy(currentColorsArr, 0, updatedColorArr, 1, currentColorsArr.length);
                    currentColorsArr = updatedColorArr.clone();
                }
            }

            /*String[] currentColorSqrName = new String[currentColorsArr.length];
            for(int i = 0 ; i < currentColorsArr.length ; i++){
                currentColorSqrName[i] = currentColorsArr[i].getSquareName();

            }*/

           Square selectedSquare = guiController.getSelectedSquare(currentColorsArr);
           if(((DeedSquare)selectedSquare).hasDeed()){
               ((DeedSquare)selectedSquare).setDeedToFree();
           }

           /* for(int i = 0 ; i < board.length ; i++) {
                if (selectedSquare == board[i]) {
                    index = i ;
                    if(((DeedSquare)board[i]).hasDeed() == true) {
                        ((DeedSquare)board[i]).setDeedToFree();
                        break;
                    }

                }
            }*/
           System.out.println("Index is now: " + index);
               /*
            foundFreeSquare = ((DeedSquare) board[i]).hasDeed();


                     int indexFree = -1;
                        if (foundFreeSquare) {
                            board[i] = freeSquares[1];
                         if (currentPos > i) {
                                distance = board.length - currentPlayer.getPosition() + i;
                                System.out.println("New pos when currentPos > i: " + distance);

                            } else {
                                distance = board.length - currentPos - (board.length - i);
                                System.out.println("New pos when i > currentPos: " + distance);
                            }

                            currentPlayer.updatePosition(distance);
                            ((DeedSquare)board[i]).setDeedOwner(currentPlayer,i);

                            break;

                        } else {
                            index = i; }

                    } // end if-statement check color

                }// end for loop

                    if (!foundFreeSquare) {
                        board[index].landOn(currentPlayer);
                    }
*/
                } // end else

                    distance = currentPlayer.getDistanceToMove(index, board.length);
                    currentPlayer.updatePosition(distance);
                    board[index].landOn(currentPlayer);

        System.out.println("This is new position: " + currentPlayer.getPosition() + " and new square color is: " + board[currentPlayer.getPosition()].getColor());


    }
}
