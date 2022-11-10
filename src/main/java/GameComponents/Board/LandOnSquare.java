package GameComponents.Board;

import GameComponents.Player;

public class LandOnSquare {
    Square[] square;

    Player[] player;

    public LandOnSquare(Square[] square, Player[] player) {
        this.square = square;
        this.player = player;
    }
    public void landOnDeedSquare(int newPosition, int currentPlayer) {
        int i = currentPlayer;
        int deedPrice = ((DeedSquare) square[newPosition]).getDeedPrice();

        if(((DeedSquare) square[newPosition]).hasDeed()) {
                System.out.println("This property is available for purchase.");
                ((DeedSquare) square[newPosition]).sellDeed();
                player[i].withdrawMoney(deedPrice);
                int currentBalance = player[i].getCurrentBalance();
            System.out.println("You now have " + currentBalance + " in your bank account.");

            } else if (!((DeedSquare) square[newPosition]).hasDeed()) {
                System.out.println("Sorry but u gotta pay rent.");
            player[i].withdrawMoney(deedPrice); // NOTE: Maybe make a "transferMoney" method to withdraw form one account and deposit in another?
            int currentBalance = player[i].getCurrentBalance();
            System.out.println("You now have " + currentBalance + " in your bank account.");


        }
    }

    public void landOnChanceSquare(int newPosition, int currentPlayer) {

    }

    public void landOnStartSquare (int newPosition, int currentPlayer) {

    }

    public void landOnJailSquare(int newPosition, int currentPlayer) {

    }

    public void landOnParkingSquare(int newPosition, int currentPlayer) {

    }
}
