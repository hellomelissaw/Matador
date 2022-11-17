package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Account;
import GameComponents.Player;
import gui_fields.GUI_Player;

public class LandOnSquare {
    Square[] square;
    Player[] player;

    GUI_Player[] guiPlayers;
    GuiController guiController;
    String guiMessage;



    public LandOnSquare(Square[] square, Player[] player, GuiController guiController, GUI_Player[] guiPlayers) {
        this.square = square;
        this.player = player;
        this.guiController = guiController;
        this.guiPlayers = guiPlayers;
    }
    public void landOnDeedSquare(int newPosition, int currentPlayer) {
        int i = currentPlayer;
        //guiController.getCurrentPlayer(currentPlayer);
        int deedPrice = ((DeedSquare) square[newPosition]).getDeedPrice();

        if(((DeedSquare) square[newPosition]).hasDeed()) {
            System.out.println("This property is available for purchase.");
            guiMessage = "This property is available for purchase.";
            guiController.showMessage(guiMessage);

            player[i].withdrawMoney(deedPrice); // TO DO: must check if player has enough money to buy
            int currentBalance = player[i].getCurrentBalance();
            guiController.updateBalance(guiPlayers[i], currentBalance);
            System.out.println("You now have " + currentBalance + " in your bank account.");
            guiMessage = "You now have " + currentBalance + " in your bank account.";
            guiController.showMessage(guiMessage);

            System.out.println("owner before " + ((DeedSquare) square[newPosition]).getDeedOwner());

            ((DeedSquare) square[newPosition]).sellDeed(player[i], newPosition); // SETS sellDeed TO FALSE AND UPDATES OWNERSHIP
            System.out.println("owner after " + ((DeedSquare) square[newPosition]).getDeedOwner());

            } else if (((DeedSquare) square[newPosition]).hasDeed()==false) {
                Player deedOwner = ((DeedSquare) square[newPosition]).getDeedOwner();
                if (player[i]==deedOwner) {
                    System.out.println("Girl u own this joint! U don't pay anything.");
                    guiMessage = "Girl u own this joint! U don't pay anything.";
                    guiController.showMessage(guiMessage);

                } else {
                    System.out.println(deedOwner.toString() + " owns this place. " + player[i].getPlayerName() + ", you gotta pay " + ((DeedSquare) square[newPosition]).getDeedPrice() + " in rent.");
                    player[i].withdrawMoney(deedPrice);
                    deedOwner.depositMoney(deedPrice);
                    int currentBalance = player[i].getCurrentBalance();
                    guiController.updateBalance(guiPlayers[i], currentBalance);
                    System.out.println(player[i].getPlayerName() + ", you now have " + currentBalance + " in your bank account.");
                    guiMessage = player[i].getPlayerName() + ", you now have " + currentBalance + " in your bank account.";
                    guiController.showMessage(guiMessage);

                    currentBalance = deedOwner.getCurrentBalance();
                    guiController.updateBalance(guiPlayers[i], currentBalance);
                    System.out.println(deedOwner.getPlayerName() + ", you now have " + currentBalance + " in your bank account.");
                    guiMessage = deedOwner.getPlayerName() + ", you now have " + currentBalance + " in your bank account.";
                    //guiController.showMessage(guiMessage);
                }


                 System.out.println("");

        }
    }

    public void landOnChanceSquare(int newPosition, int currentPlayer) {

    }

    public void landOnStartSquare (int newPosition, int currentPlayer) {

    }

    public void landOnJailSquare(int newPosition, int currentPlayer){

       int i= currentPlayer;
        if (newPosition==18){
            System.out.println("Go to jail,and pay M1 for the bank ");

            player[i].withdrawMoney(1);
            int currentBalance = player[i].getCurrentBalance();
            System.out.println("You now have " + currentBalance + " in your bank account.");
            guiController.showMessage("You are going to jail now, press OK to be driven there.");
            player[i].updatePosition(12);
            guiController.move(guiPlayers[i],18,6);
            System.out.println("You have been moved to square #" + player[i].getPosition() );

        }  else if (newPosition==6) {
            System.out.println("You are now visiting jail, think about your life choices! :(");
        }
    }



    public void landOnParkingSquare(int newPosition, int currentPlayer) {

         if (newPosition==12)
             System.out.println("You now have free parking, take a well deserved break! :)");
    }
}

