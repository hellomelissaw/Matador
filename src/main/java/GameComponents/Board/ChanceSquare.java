package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;

public class ChanceSquare extends Square{

    private Square[] square;
    private Player[] player;

    private GUI_Player[] guiPlayers;
    private GuiController guiController;
    private int playerCount;
    private Text msg;

    public ChanceSquare(String squareName) {
        super(squareName);
    }

    public void initializer(Square[] square, Player[] player, GuiController guiController, GUI_Player[] guiPlayers, int playerCount, Text msg){
        this.square = square;
        this.player = player;
        this.guiController = guiController;
        this.guiPlayers = guiPlayers;
        this.playerCount = playerCount;
        this.msg = msg;

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

    public void noChargeOneCategory(String chanceText, int currentPlayer, int squareOne,int squareTwo) {

        //NO CHARGE SQUARE! Move forward to cyan square. If no one owns it,then you get it for free. Or you have to pay the owner
        String prompt;
        int choice = 0;
        System.out.println(msg.getText(chanceText));
        guiController.showMessage(msg.getText(chanceText));

        prompt = msg.getText("prompt") + square[squareOne].getSquareName() + msg.getText("or") + square[squareTwo].getSquareName();

        System.out.println(prompt);
        choice = guiController.getUserInteger(prompt);

        if (choice == 1) {
            noChargeSquare(squareOne,currentPlayer);
        } else if (choice == 2) {
            noChargeSquare(squareTwo, currentPlayer);
        }
    }

    public void noChargeTwoCategories(String chanceText, int currentPlayer, String colorOne, String colorTwo, int squareOne,int squareTwo, int squareThree, int squareFour) {
        String prompt;
        String pick;
        int choice1 = 0;

        System.out.println(msg.getText(chanceText));
        guiController.showMessage(msg.getText(chanceText));

        prompt = msg.getText("prompt") + msg.getText(colorOne) + msg.getText("or") + msg.getText(colorTwo);
        System.out.println(prompt);
        choice1 = guiController.getUserInteger(prompt);

        switch (choice1) {
            case 1 -> {
                pick = msg.getText("pick") + msg.getText(colorOne);
                noChargeOneCategory(pick, currentPlayer, squareOne, squareTwo);
            }
            case 2 -> {
                pick = msg.getText("pick") + msg.getText(colorTwo);
                noChargeOneCategory(pick, currentPlayer, squareThree, squareFour);
            }
        }
    }



    public void Roll(int currentPlayer, int currentPosition /*Square[] square, Player[] player, GuiController guiController, GUI_Player[] guiPlayers, int playerCount,Text msg*/)
    {
        int cardNr = (int) (Math.random()*(15-1)) + 1;
        System.out.println(cardNr);
        boolean running = true;
        int choice2 = 0;
        int choice1 = 0;
        String cardMessage;
        String prompt;

        while(running) {
            switch (cardNr)
            {
                case 1: // Move forward to GO. Collect M2
                    //guiMessage = player[i].getPlayerName() + msg.getText("haveBought") + square[newPosition].getSquareName();
                    cardMessage = msg.getText("chance1");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    player[currentPlayer].goToSquare(0);
                    player[currentPlayer].depositMoney(2); //Test this
                    guiController.updateBalance(guiPlayers[currentPlayer],player[currentPlayer].getCurrentBalance());
                    guiController.move(guiPlayers[currentPlayer],currentPosition,0);

                    System.out.println(player[currentPlayer].getPosition());
                    running = false;
                    break;

                case 2: // Move 5 squares forward
                    cardMessage = msg.getText("chance2");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    player[currentPlayer].updatePosition(5);
                    guiController.move(guiPlayers[currentPlayer],currentPosition,5);
                    running = false;
                    break;

                case 3: //Move 1 square forward, or take one more chance card
                    cardMessage = msg.getText("chance3");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    prompt = msg.getText("prompt") + msg.getText("chance3");
                    System.out.println(prompt);

                    choice1 = guiController.getUserInteger(prompt);

                    switch (choice1) {
                        case 1:
                            player[currentPlayer].updatePosition(1);
                            guiController.move(guiPlayers[currentPlayer],currentPosition,currentPosition+1);
                            running = false;
                            break;
                        case 2:
                            cardNr = (int)(Math.random()*(19-1)) + 1;
                    }
                    break;

                case 4: //Move forward to the Promenade
                    cardMessage = msg.getText("chance4");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    player[currentPlayer].goToSquare(23);
                    guiController.move(guiPlayers[currentPlayer],currentPosition,23);
                    running = false;
                    break;

                case 5: // You have eaten a lot of candy. PAY M2 to the bank
                    cardMessage = msg.getText("chance5");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    player[currentPlayer].withdrawMoney(2);
                    guiController.updateBalance(guiPlayers[currentPlayer],player[currentPlayer].getCurrentBalance());

                    running = false;
                    break;

                case 6: //It is your birthday!Each player will give you M1. HAPPY BIRTHDAY!
                    cardMessage = msg.getText("chance6");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    for (int i = 0; i < playerCount; i++) {
                        if (i == currentPlayer)
                        {
                            continue;
                        }
                        else
                        {
                            player[i].withdrawMoney(1);
                            player[currentPlayer].depositMoney(1);
                            guiController.updateBalance(guiPlayers[i],player[i].getCurrentBalance());
                        }

                    }
                    guiController.updateBalance(guiPlayers[currentPlayer],player[currentPlayer].getCurrentBalance());

                    running = false;
                    break;
                case 7: //You have done your homework! Collect M2 from the bank
                    cardMessage = msg.getText("chance7");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);
                    player[currentPlayer].depositMoney(2);
                    guiController.updateBalance(guiPlayers[currentPlayer],player[currentPlayer].getCurrentBalance());

                    running = false;
                    break;

                case 8: //NO CHARGE SQUARE! Move forward The Skate Park to make the perfect grind! If no one owns it,then you get it for free. Or you have to pay the owner.
                    cardMessage = msg.getText("chance8");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    noChargeSquare(10,currentPlayer);
                    running = false;
                    break;

                case 9: //NO CHARGE SQUARE! Move forward to cyan or red square. If no one owns it,then you get it for free. Or you have to pay the owner
                    noChargeTwoCategories("chance9",currentPlayer,"cyan","red",4,5,13,14);
                    running = false;
                    break;

                case 10: // NO CHARGE SQUARE! Move forward to lightgrey or yellow square. If no one owns it,then you get it for free. Or you have to pay the owner
                    noChargeTwoCategories("chance10",currentPlayer,"lightgrey","yellow",1,2,16,17);
                    running = false;
                    break;

                case 11: //NO CHARGE SQUARE! Move forward to orange square. If no one owns it,then you get it for free. Or you have to pay the owner
                    noChargeOneCategory("chance11",currentPlayer,10,11);
                    running=false;
                    break;

                case 12: //NO CHARGE SQUARE! Move forward to cyan square. If no one owns it,then you get it for free. Or you have to pay the owner
                    noChargeOneCategory("chance12",currentPlayer,4,5);
                    running = false;
                    break;

                case 13: //NO CHARGE SQUARE! Move forward to red square. If no one owns it,then you get it for free. Or you have to pay the owner
                    noChargeOneCategory("chance13",currentPlayer,13,14);
                    running = false;
                    break;

                case 14: //NO CHARGE SQUARE! Move forward to orange or green square. If no one owns it,then you get it for free. Or you have to pay the owner
                    noChargeTwoCategories("chance14",currentPlayer,"orange","green",10,11,19,20);
                    running = false;
                    break;

                case 15: //NO CHARGE SQUARE! Move forward to pink or dark blue square. If no one owns it,then you get it for free. Or you have to pay the owner
                    noChargeTwoCategories("chance15",currentPlayer,"pink","darkblue",7,8,22,23);

                    running = false;
                    break;

                default:
                    break;
            }

        }

    }

    void landOn() {

    }
}
