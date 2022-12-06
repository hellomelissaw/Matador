package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;
import gui_fields.GUI_Player;

public class CardDeed extends ChanceCard {
    private String color1;
    private String color2;

    Square[] board;

    public CardDeed(String cardMessage, GuiController guiController, String color1, String color2) {
        super(cardMessage, guiController);
        this.color1 = color1;
        this.color2 = color2;

    }

    public void setBoard(Square[] board) {
       this.board = board;
    }

    /*public void getSquaresByColor(Square[] board, String color) {
        for(int i = 0; i < board.length ; i++){
            if (board[i].getColor() == color) {
                sameColorSquares[i] = board[i];
                squareIndex = i;
            }
        }
    }*/

    public void playCard(Player currentPlayer, GUI_Player currentGuiPlayer) {
        int currentPos = currentPlayer.getPosition();
        int newPos;
        int index = 0;
        boolean foundFreeSquare = false;
        //Square correctColorSquare[] = {};
        if(color1 == "na") {
            int skateParkIndex = 10;
            if(currentPos > skateParkIndex){
                newPos = board.length - currentPlayer.getPosition() + skateParkIndex;

            } else { newPos = skateParkIndex - currentPos; }

            System.out.println("New position is: " + newPos);
            currentPlayer.updatePosition(newPos);

            if(((DeedSquare)board[skateParkIndex]).hasDeed()){
            ((DeedSquare)board[skateParkIndex]).setDeedOwner(currentPlayer, skateParkIndex);

            } else {board[skateParkIndex].landOn(currentPlayer,currentGuiPlayer);}



        } else {

                for (int i = 0 ; i < board.length ; i++) {
                    if (board[i].getColor() == color1 || board[i].getColor() == color2) {

                     foundFreeSquare = ((DeedSquare) board[i]).hasDeed();

                        if (foundFreeSquare) {

                            if (currentPos > i) {
                                newPos = board.length - currentPlayer.getPosition() + i;

                            } else {
                                newPos = board.length - currentPos - (board.length - i);

                            }

                            currentPlayer.updatePosition(newPos);
                            ((DeedSquare)board[i]).setDeedOwner(currentPlayer,newPos);

                            break;

                        } else {
                            index = i; }
                            //int index = -1;
                            //board[i] = correctColorSquare[index++];
                        // if the square is the right color but unavailable program goes here

                    } // check color

                }//for loop

                if (!foundFreeSquare) {
                    board[index].landOn(currentPlayer, currentGuiPlayer);
                }

                }

               /* color check loop
               // for (int i = 0 ; i < correctColorSquare.length ; i ++) {

            }*/
        System.out.println("This is new position: " + currentPlayer.getPosition() + " and new square color is: " + board[currentPlayer.getPosition()].getColor());
        }
    }





        /*int newSquareIndex = 0;
        for (int i = 0; i < board.length; i++) {
            if(board[i].getColor() == color1) {
                newSquareIndex = i;
            }
        }
        if (color1 == "cyan" || color1 == "red") {


                       if (((DeedSquare) sameColorSquares[i]).hasDeed()) {
                           sameColorSquares[i].landOn(currentPlayer, currentGuiPlayer);
                           int price = ((DeedSquare) sameColorSquares[i]).deedPrice;
                           System.out.println("Price of deed: " + price);
                           currentPlayer.depositMoney(price);
                           int currentPosition = currentPlayer.getPosition();
                           int newPosition = Math.abs(squareIndex - currentPosition);
                           currentPlayer.updatePosition(newPosition);

                       }


       } else if(color1 == "lightgrey"){

       } else if(color1 == "orange") {

       } else if(color1 == "pink"){

       } else if (color1 == "na") {
           int skateParkIndex = 10;
           int move = skateParkIndex - currentPlayer.getPosition();
           System.out.println("Absolute value: " + Math.abs(move));
           currentPlayer.updatePosition(Math.abs(move));

       }

         */

