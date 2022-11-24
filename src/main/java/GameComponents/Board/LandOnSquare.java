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


    public void landOnChanceSquare(int currentPlayer, int currentPosition) {
        int cardNr = 1; //(int) (Math.random()*(15-1)) + 1;
        System.out.println(cardNr);
        boolean running = true;
        int choice2 = 0;
        int choice1 = 0;
        String cardMessage;
        String prompt;

        //while(running) {
            switch (cardNr)
            {
                case 1: // Move forward to GO. Collect M2
                    //guiMessage = player[i].getPlayerName() + msg.getText("haveBought") + square[newPosition].getSquareName();
                    cardMessage = msg.getText("chance1");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    player[currentPlayer].goToSquare(0);
                    player[currentPlayer].depositMoney(2); //Test this
                    guiController.move(guiPlayers[currentPlayer],currentPosition,0);

                    System.out.println(player[currentPlayer].getPosition());
                    running = false;

                case 2: // Move 5 squares forward
                    cardMessage = msg.getText("chance2");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    player[currentPlayer].updatePosition(5);
                    running = false;

                case 3: //Move 1 square forward, or take one more chance card
                    cardMessage = msg.getText("chance3");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    prompt = msg.getText("prompt") + msg.getText("chance3");
                    System.out.println(prompt);
                    guiController.showMessage(prompt);
                    choice1 = guiController.getUserInteger();


                    //Write gui code for user prompt to pick between case 1 or 2
                    switch (choice1) {
                        case 1:
                            player[currentPlayer].updatePosition(1);
                            running = false;
                        case 2:
                            cardNr = (int)(Math.random()*(19-1)) + 1;
                    }

                case 4: //Move forward to the Promenade
                    cardMessage = msg.getText("chance4");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    player[currentPlayer].goToSquare(23);
                    running = false;

                case 5: // You have eaten a lot of candy. PAY M2 to the bank
                    cardMessage = msg.getText("chance5");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    player[currentPlayer].withdrawMoney(2);
                    running = false;

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
                        }

                    }
                    running = false;
                case 7: //You have done your homework! Collect M2 from the bank
                    cardMessage = msg.getText("chance7");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    player[currentPlayer].depositMoney(2);
                    running = false;

                case 8: //NO CHARGE SQUARE! Move forward The Skate Park to make the perfect grind! If no one owns it,then you get it for free. Or you have to pay the owner.
                    cardMessage = msg.getText("chance8");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    noChargeSquare(10,currentPlayer);
                    running = false;

                case 9: //NO CHARGE SQUARE! Move forward to cyan or red square. If no one owns it,then you get it for free. Or you have to pay the owner
                    cardMessage = msg.getText("chance9");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);


                    prompt = msg.getText("prompt" + "cyan" + "or" + "red");
                    System.out.println(prompt);
                    guiController.showMessage(prompt);
                    choice1 = guiController.getUserInteger();

                    switch (choice1) {
                        case 1:
                            prompt = msg.getText("prompt") + square[4].getSquareName() + "or" + square[5].getSquareName();
                            System.out.println(prompt);
                            guiController.showMessage(prompt);
                            choice2 = guiController.getUserInteger();

                            if (choice2 == 1) {
                                noChargeSquare(4,currentPlayer);
                            } else if (choice2 == 2) {
                                noChargeSquare(5, currentPlayer);
                            }

                        case 2:
                            prompt = msg.getText("prompt" + square[13].getSquareName() + "or" + square[14].getSquareName());
                            System.out.println(prompt);
                            guiController.showMessage(prompt);
                            choice2 = guiController.getUserInteger();
                            if (choice2 == 1) {
                                noChargeSquare(13,currentPlayer);
                            } else if (choice2 == 2) {
                                noChargeSquare(14, currentPlayer);
                            }

                    }
                    running = false;

                case 10: // NO CHARGE SQUARE! Move forward to lightgrey or yellow square. If no one owns it,then you get it for free. Or you have to pay the owner
                    cardMessage = msg.getText("chance10");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    prompt = msg.getText("prompt") + msg.getText("lightgrey") + msg.getText("or") + msg.getText("yellow");
                    System.out.println(prompt);
                    guiController.showMessage(prompt);
                    choice1 = guiController.getUserInteger();

                    switch (choice1) {
                        case 1:
                            prompt = msg.getText("prompt") + square[1].getSquareName() + msg.getText("or") + square[2].getSquareName();
                            System.out.println(prompt);
                            guiController.showMessage(prompt);
                            choice2 = guiController.getUserInteger();

                            if (choice2 == 1) {
                                noChargeSquare(1,currentPlayer);
                            } else if (choice2 == 2) {
                                noChargeSquare(2, currentPlayer);
                            }

                        case 2:
                            prompt = msg.getText("prompt") + square[16].getSquareName() + msg.getText("or") + square[17].getSquareName();
                            System.out.println(prompt);
                            guiController.showMessage(prompt);
                            choice2 = guiController.getUserInteger();

                            if (choice2 == 1) {
                                noChargeSquare(16,currentPlayer);
                            } else if (choice2 == 2) {
                                noChargeSquare(17, currentPlayer);
                            }
                    }
                    running = false;

                case 11: //NO CHARGE SQUARE! Move forward to orange square. If no one owns it,then you get it for free. Or you have to pay the owner
                    cardMessage = msg.getText("chance11");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    prompt = msg.getText("prompt") + square[10].getSquareName() + msg.getText("or") + square[11].getSquareName();
                    System.out.println(prompt);
                    guiController.showMessage(prompt);
                    choice2 = guiController.getUserInteger();

                    if (choice2 == 1) {
                        noChargeSquare(10,currentPlayer);
                    } else if (choice2 == 2) {
                        noChargeSquare(11, currentPlayer);
                    }
                    running = false;

                case 12: //NO CHARGE SQUARE! Move forward to cyan square. If no one owns it,then you get it for free. Or you have to pay the owner
                    cardMessage = msg.getText("chance12");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    prompt = msg.getText("prompt") + square[4].getSquareName() + msg.getText("or") + square[5].getSquareName();
                    System.out.println(prompt);
                    guiController.showMessage(prompt);
                    choice2 = guiController.getUserInteger();

                    if (choice2 == 1) {
                        noChargeSquare(4,currentPlayer);
                    } else if (choice2 == 2) {
                        noChargeSquare(5, currentPlayer);
                    }
                    running = false;

                case 13: //NO CHARGE SQUARE! Move forward to red square. If no one owns it,then you get it for free. Or you have to pay the owner
                    cardMessage = msg.getText("chance13");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);


                    prompt = msg.getText("prompt") + square[13].getSquareName() + msg.getText("or") + square[14].getSquareName();
                    System.out.println(prompt);
                    guiController.showMessage(prompt);
                    choice2 = guiController.getUserInteger();

                    if (choice2 == 1) {
                        noChargeSquare(13,currentPlayer);
                    } else if (choice2 == 2) {
                        noChargeSquare(14, currentPlayer);
                    }
                    running = false;

                case 14: //NO CHARGE SQUARE! Move forward to orange or green square. If no one owns it,then you get it for free. Or you have to pay the owner
                    cardMessage = msg.getText("chance14");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    prompt = msg.getText("prompt") + msg.getText("orange") + msg.getText("or") + msg.getText("green");
                    System.out.println(prompt);
                    guiController.showMessage(prompt);
                    choice1 = guiController.getUserInteger();

                    switch (choice1) {
                        case 1:
                            prompt = msg.getText("prompt") + square[10].getSquareName() + msg.getText("or") + square[11].getSquareName();
                            System.out.println(prompt);
                            guiController.showMessage(prompt);

                            choice2 = guiController.getUserInteger();
                            if (choice2 == 1) {
                                noChargeSquare(10,currentPlayer);
                            } else if (choice2 == 2) {
                                noChargeSquare(11, currentPlayer);
                            }
                        case 2:
                            prompt = msg.getText("prompt") + square[19].getSquareName() + msg.getText("or") + square[20].getSquareName();
                            System.out.println(prompt);
                            guiController.showMessage(prompt);
                            choice2 = guiController.getUserInteger();

                            if (choice2 == 1) {
                                noChargeSquare(19,currentPlayer);
                            } else if (choice2 == 2) {
                                noChargeSquare(20, currentPlayer);
                            }

                    }
                    running = false;

                case 15: //NO CHARGE SQUARE! Move forward to pink or dark blue square. If no one owns it,then you get it for free. Or you have to pay the owner
                    cardMessage = msg.getText("chance15");
                    System.out.println(cardMessage);
                    guiController.showMessage(cardMessage);

                    prompt = msg.getText("prompt") + msg.getText("pink") + msg.getText("or") + msg.getText("darkblue");
                    System.out.println(prompt);
                    guiController.showMessage(prompt);
                    choice1 = guiController.getUserInteger();

                    switch (choice1) {
                        case 1:
                            prompt = msg.getText("prompt") + square[7].getSquareName() + msg.getText("or") + square[8].getSquareName();
                            System.out.println(prompt);
                            guiController.showMessage(prompt);
                            choice2 = guiController.getUserInteger();

                            if (choice2 == 1) {
                                noChargeSquare(7,currentPlayer);
                            } else if (choice2 == 2) {
                                noChargeSquare(8, currentPlayer);
                            }
                            running = false;
                        case 2:
                            prompt = msg.getText("prompt") + square[22].getSquareName() + msg.getText("or") + square[23].getSquareName();
                            System.out.println(prompt);
                            guiController.showMessage(prompt);
                            choice2 = guiController.getUserInteger();
                            if (choice2 == 1) {
                                noChargeSquare(22,currentPlayer);
                            } else if (choice2 == 2) {
                                noChargeSquare(23, currentPlayer);
                            }
                    }
                    running = false;

                    default:
                        break;
            }

       // }


    }

}

