package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;
import gui_fields.GUI_Player;
import Translator.*;

import java.util.Random;

public class LandOnSquare {
    Square[] square;
    Player[] player;

    GUI_Player[] guiPlayers;
    GuiController guiController;
    String guiMessage;
    Text msg;

    int playerCount;
    public LandOnSquare(Square[] square, Player[] player, GuiController guiController, GUI_Player[] guiPlayers, int playerCount) {
        this.square = square;
        this.player = player;
        this.guiController = guiController;
        this.guiPlayers = guiPlayers;
        this.playerCount = playerCount;
    }

    public void setLang(String langFile) {
        msg = new Text(langFile);
    }
    public void landOnDeedSquare(int newPosition, int currentPlayer) {
        int i = currentPlayer;
        int deedPrice = ((DeedSquare) square[newPosition]).getDeedPrice();

        if(((DeedSquare) square[newPosition]).hasDeed()) {
            /*System.out.println("This property is available for purchase for" +((DeedSquare) square[newPosition]).getDeedPrice()  + "M.");
            guiMessage = "This property is available for purchase.";*/
            guiMessage = player[i].getPlayerName() + msg.getText("haveBought") + square[newPosition].getSquareName();
            guiController.showMessage(guiMessage);

            player[i].withdrawMoney(deedPrice); // TO DO: must check if player has enough money to buy
            int currentBalance = player[i].getCurrentBalance();
            guiController.updateBalance(guiPlayers[i], currentBalance);
            System.out.println(msg.getText("newBalance") + currentBalance);

            ((DeedSquare) square[newPosition]).sellDeed(player[i], newPosition); // SETS sellDeed TO FALSE AND UPDATES OWNERSHIP

            } else if (((DeedSquare) square[newPosition]).hasDeed()==false) {
                Player deedOwner = ((DeedSquare) square[newPosition]).getDeedOwner();

                if (player[i]==deedOwner) {
                    String ownerMessage = msg.getText("ownerOfDeed");
                    System.out.println(ownerMessage);
                    guiController.showMessage(ownerMessage);

                } else {
                    String payRent = deedOwner.toString() + msg.getText("payRent");
                    System.out.println(payRent + ((DeedSquare) square[newPosition]).getDeedPrice());
                    player[i].withdrawMoney(deedPrice);
                    int currentBalance = player[i].getCurrentBalance();
                    guiController.updateBalance(guiPlayers[i], currentBalance);

                    System.out.println(msg.getText("newBalance") + currentBalance);
                    guiController.showMessage(payRent);

                    deedOwner.depositMoney(deedPrice);
                    currentBalance = deedOwner.getCurrentBalance();
                    guiController.receiveRent(deedOwner.getPlayerName(),currentBalance);


                }


                 System.out.println("");

        }
    }

    public void landOnStartSquare (int newPosition, int currentPlayer) {

    }
    public void landOnJailSquare(int newPosition, int currentPlayer){

       int i = currentPlayer;
        if (newPosition==18){
            String jailMessage = msg.getText("movedToJail");
            System.out.println(jailMessage);

            player[i].withdrawMoney(1);
            int currentBalance = player[i].getCurrentBalance();
            guiController.updateBalance(guiPlayers[i], currentBalance);
            System.out.println(msg.getText("newBalance")+ currentBalance);
            guiController.showMessage(jailMessage);
            player[i].updatePosition(12);
            guiController.move(guiPlayers[i],18,6);
            System.out.println(msg.getText("movedToSquare") + player[i].getPosition() );

        }  else if (newPosition==6) {
            System.out.println(msg.getText("visitJail"));
        }
    }

    public void landOnParkingSquare(int newPosition) {

         if (newPosition==12)
             System.out.println(msg.getText("freeParking"));
    }

    public void noChargeSquare(int noChargeSquareNumber, int currentPlayer) {

        player[currentPlayer].goToSquare(noChargeSquareNumber);


        if (((DeedSquare) square[noChargeSquareNumber]).hasDeed())
        {
            ((DeedSquare) square[noChargeSquareNumber]).sellDeed(player[currentPlayer], noChargeSquareNumber); // SETS sellDeed TO FALSE AND UPDATES OWNERSHIP
        }
        else if (((DeedSquare) square[noChargeSquareNumber]).hasDeed()==false)
        {
            Player deedOwner = ((DeedSquare) square[noChargeSquareNumber]).getDeedOwner();
            int deedPrice = ((DeedSquare) square[noChargeSquareNumber]).getDeedPrice();

            if (player[currentPlayer]==deedOwner) {
                String ownerMessage = msg.getText("ownerOfDeed");
                System.out.println(ownerMessage);
                guiController.showMessage(ownerMessage);

            } else {
                String payRent = deedOwner.toString() + msg.getText("payRent");
                System.out.println(payRent + ((DeedSquare) square[noChargeSquareNumber]).getDeedPrice());
                player[currentPlayer].withdrawMoney(deedPrice);
                int currentBalance = player[currentPlayer].getCurrentBalance();
                guiController.updateBalance(guiPlayers[currentPlayer], currentBalance);

                System.out.println(msg.getText("newBalance") + currentBalance);
                guiController.showMessage(payRent);

                deedOwner.depositMoney(deedPrice);
                currentBalance = deedOwner.getCurrentBalance();
                guiController.receiveRent(deedOwner.getPlayerName(),currentBalance);
            }

        }

    }


    public void landOnChanceSquare(int newPosition, int currentPlayer) {
        int cardNr = (int) (Math.random()*(19-1)) + 1;
        System.out.println(cardNr);
        boolean running = true;

        while(running) {
            switch (cardNr) {
                case 1: // Move forward to GO. Collect M2
                    player[currentPlayer].goToSquare(0);
                    player[currentPlayer].depositMoney(2); //Test this
                    System.out.println(player[currentPlayer].getPosition());
                    running = false;

                case 2: // Move 5 squares forward
                    player[currentPlayer].updatePosition(5);
                    running = false;

                case 3: //Move 1 square forward, or take one more chance card
                    int choice = 0;
                    //Write gui code for user prompt to pick between case 1 or 2
                    switch (choice) {
                        case 1:
                            player[currentPlayer].updatePosition(1);
                            running = false;
                        case 2:
                            cardNr = (int) (Math.random()*(19-1)) + 1;
                    }

                case 4: //Move forward to the Promenade
                    player[currentPlayer].goToSquare(23);

                case 5: // You have eaten a lot of candy. PAY M2 to the bank
                    player[currentPlayer].withdrawMoney(2);

                case 6: //It is your birthday!Each player will give you M1. HAPPY BIRTHDAY!
                    for (int i = 0; i < playerCount; i++) {
                        if (i == currentPlayer)
                        {
                            continue;
                        }
                        else
                        {
                            player[i].withdrawMoney(1);
                            player[currentPlayer].depositMoney(1);
                        }


                    }
                case 7: //You have done your homework! Collect M2 from the bank
                    player[currentPlayer].depositMoney(2);

                case 8: //NO CHARGE SQUARE! Move forward The Skate Park to make the perfect grind! If no one owns it,then you get it for free. Or you have to pay the owner.
                    noChargeSquare(10,currentPlayer);

                case 9: //NO CHARGE SQUARE! Move forward to lightblue or red square. If no one owns it,then you get it for free. Or you have to pay the owner






                default:
            }

        }


    }

}

