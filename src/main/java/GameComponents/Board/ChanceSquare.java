package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import gui_fields.GUI_Player;

public class ChanceSquare extends Square{

    private Square[] square;
    private Player[] players;

    private GUI_Player[] guiPlayers;
    private GuiController guiController;
    private int playerCount;

    private ChanceCard[] chanceCards = new ChanceCard[18];

    public ChanceSquare(String squareName) { //INITIALISES DECK OF CHANCE CARDS
        super(squareName);
        chanceCards[0] = new CardMove("chance1", guiController);
        chanceCards[1] = new CardMove("chance2", guiController);
        chanceCards[2] = new CardMove("chance3", guiController);
        chanceCards[3] = new CardMove("chance4", guiController);
        chanceCards[4] = new CardMoney("chance5", guiController);
        chanceCards[5] = new CardMoney("chance6", guiController);
        chanceCards[6] = new CardMoney("chance7", guiController);
        chanceCards[7] = new CardDeed("chance8", guiController);
        chanceCards[8] = new CardDeed("chance9", guiController);
        chanceCards[9] = new CardDeed("chance10", guiController);
        chanceCards[10] = new CardDeed("chance11", guiController);
        chanceCards[11] = new CardDeed("chance12", guiController);
        chanceCards[12] = new CardDeed("chance13", guiController);
        chanceCards[13] = new CardDeed("chance14", guiController);
        chanceCards[14] = new CardDeed("chance15", guiController);

        for (int i = 0 ; i < chanceCards.length ; i++) { //SETS LANGUAGE FOR EACH CARD
            chanceCards[i].setLang(msg);
            chanceCards[i].setPlayers(players,guiPlayers);
        }


    }

    public void landOn(Player currentPlayer, GUI_Player currentGuiPlayer) {
        int cardIndex = (int) (Math.random()*(15-1));
        chanceCards[cardIndex].printMessage(cardIndex);
        System.out.println("Card picked: " + cardIndex);
    }

    /*public void initializer(Square[] square, Player[] player, GuiController guiController, GUI_Player[] guiPlayers, int playerCount){
        this.square = square;
        this.player = player;
        this.guiController = guiController;
        this.guiPlayers = guiPlayers;
        this.playerCount = playerCount;


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



    public void Roll(int currentPlayer, int currentPosition) //Square[] square, Player[] player, GuiController guiController, GUI_Player[] guiPlayers, int playerCount,Text msg
    {

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




     */
}
