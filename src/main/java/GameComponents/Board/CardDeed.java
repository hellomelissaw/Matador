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
    public void playCard(Player currentPlayer) {
        int currentPos = currentPlayer.getPosition();
        int distance;
        int index = 0;
        boolean foundFreeSquare = false;

        if(color1 == "na") {
            int skateParkIndex = 10;
            if(currentPos > skateParkIndex){
                distance = board.length - currentPlayer.getPosition() + skateParkIndex;

            } else { distance = skateParkIndex - currentPos; }

            currentPlayer.updatePosition(distance);

            if(((DeedSquare)board[skateParkIndex]).hasDeed()){
            ((DeedSquare)board[skateParkIndex]).setDeedOwner(currentPlayer, skateParkIndex);

            } else { board[skateParkIndex].landOn(currentPlayer); }

        } else {
                for (int i = 0 ; i < board.length ; i++) {
                    if (board[i].getColor() == color1 || board[i].getColor() == color2) {

                     foundFreeSquare = ((DeedSquare) board[i]).hasDeed();

                        if (foundFreeSquare) {

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

                } // end else

        System.out.println("This is new position: " + currentPlayer.getPosition() + " and new square color is: " + board[currentPlayer.getPosition()].getColor());
        }
    }