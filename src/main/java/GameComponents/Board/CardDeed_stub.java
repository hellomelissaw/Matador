package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;


public class CardDeed_stub extends ChanceCard {
    private String color1;
    private String color2;
    private String selectedSquare;
    private String squareName;
    Square[] board;

    public CardDeed_stub(String cardName, GuiController guiController, String color1, String color2) {
        super(cardName, guiController);
        this.color1 = color1;
        this.color2 = color2;

    }

    public void setBoard(Square[] board) {
        this.board = board;
    }

    public void setSquareTest(String squareName) {
        this.squareName = squareName;
    }

    public void setSelectedSquare(String selectedSquare) {this.selectedSquare = selectedSquare; }

        public void playCard (Player currentPlayer){
           // int currentPos = currentPlayer.getPosition();
            int distance;
            int index = 0;
            //boolean foundFreeSquare = false;

            if (color1 == "na") {
                index = 10;
                selectedSquare = msg.getText("skatePark");

            } else {
              /*  int arraySize = 0;
                Square[] currentColorsArr = new Square[0];

                for (int i = 0; i < board.length; i++) {

                    if (board[i].getColor() == color1 || board[i].getColor() == color2) {
                        arraySize++;
                        Square[] updatedColorArr = new Square[arraySize];
                        updatedColorArr[0] = board[i];
                        System.arraycopy(currentColorsArr, 0, updatedColorArr, 1, currentColorsArr.length);
                        currentColorsArr = updatedColorArr.clone();
                    }
                }*/


                selectedSquare = squareName;


                System.out.println("Index is now: " + index);


            } // end else

            for (int i = 0; i < board.length; i++) {
                System.out.println("Do we ever enter this loop????");
                if (selectedSquare == board[i].getSquareName()) {
                    index = i;
                    if (((DeedSquare) board[i]).hasDeed() == true) {
                        ((DeedSquare) board[i]).setDeedToFree();
                        break;
                    }

                }
            }

            distance = currentPlayer.getDistanceToMove(index, board.length);
            currentPlayer.updatePosition(distance);
            board[index].landOn(currentPlayer);

            System.out.println("This is new position: " + currentPlayer.getPosition() + " and new square color is: " + board[currentPlayer.getPosition()].getColor());


        }
    }


