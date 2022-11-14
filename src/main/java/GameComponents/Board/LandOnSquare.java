package GameComponents.Board;
import GameComponents.Account;
import GameComponents.Player;

import java.sql.SQLOutput;

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

            player[i].withdrawMoney(deedPrice); // TO DO: must check if player has enough money to buy
            int currentBalance = player[i].getCurrentBalance();
            System.out.println("You now have " + currentBalance + " in your bank account.");

            System.out.println("owner before " + ((DeedSquare) square[newPosition]).getDeedOwner());
            ((DeedSquare) square[newPosition]).sellDeed(player[i]); // SETS sellDeed TO FALSE AND UPDATES OWNERSHIP
            System.out.println("owner after " + ((DeedSquare) square[newPosition]).getDeedOwner());

            } else if (((DeedSquare) square[newPosition]).hasDeed()==false) {
                Player deedOwner = ((DeedSquare) square[newPosition]).getDeedOwner();
                if (player[i]==deedOwner) {
                    System.out.println("Girl u own this joint! U don't pay anything.");

                } else {
                    player[i].withdrawMoney(deedPrice);
                    deedOwner.depositMoney(deedPrice);
                    int currentBalance = player[i].getCurrentBalance();
                    System.out.println(player[i].getPlayerName() + ", you now have " + currentBalance + " in your bank account.");

                    currentBalance = deedOwner.getCurrentBalance();
                    System.out.println(deedOwner.getPlayerName() + ", you now have " + currentBalance + " in your bank account.");
                }


                 System.out.println("");

        }
    }

    public void landOnChanceSquare(int newPosition, int currentPlayer) {

    }

    public void landOnStartSquare (int newPosition, int currentPlayer) {

    }

    public void landOnJailSquare(int newPosition, int currentPlayer) {

       int i= currentPlayer;
        if (newPosition==18)
            System.out.println("Go to jail,and pay M1 for the bank ");

        player[i].withdrawMoney(1);
        int currentBalance = player[i].getCurrentBalance();
            System.out.println("You now have " + currentBalance + " in your bank account.");





    }


    public void landOnParkingSquare(int newPosition, int currentPlayer) {
        int i = currentPlayer;
         if (newPosition==12)
             System.out.println("You now have free parking, take a well deserved break! :)");

         Player deedOwner = ((DeedSquare) square[newPosition]).getDeedOwner();
         if (player[i]==deedOwner) {
                 System.out.println("Girl u own this joint! U don't pay anything.");

    }
}

}