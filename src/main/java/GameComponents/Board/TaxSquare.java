package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;

public class TaxSquare extends Square {

    GuiController guiController;
    int userInput;
    int balance;
    int newBalance;
    Player[] players;
    int withdrawMoney;

    TaxSquare(String squareName, GuiController guiController, Player[] players) {
        super(squareName);
        this.guiController = guiController;
        this.players = players;
    }

    @Override
    public void landOn(Player currentPlayer) {
        int currentPosition = currentPlayer.getPosition();

        if (currentPosition == 4) {
            //boolean userInputInvalid = true;
            while (userInput != 1 || userInput != 2) {
                msg.printText("betalingSkat", "na");
                userInput = guiController.getUserInteger("Tast 1 for at betale 10% af alle dine værdier eller Tast 2 for at betale 4000 kr i skat. Beslut dig her og nu!");


                if (userInput == 1) {
                    balance = (currentPlayer.getCurrentBalance());
                    withdrawMoney = (int) (balance / 100) * 10;
                    currentPlayer.withdrawMoney(withdrawMoney,true,10);
                    newBalance = currentPlayer.getCurrentBalance();
                    msg.printText("newBalance", "na");
                    System.out.println(msg.getText("newBalance" + newBalance));
                    break;
                } else if (userInput == 2) {
                    balance = currentPlayer.getCurrentBalance();
                    currentPlayer.withdrawMoney(4000,true,10);
                    newBalance = currentPlayer.getCurrentBalance();
                    msg.printText("newBalance", "na");
                    System.out.println(msg.getText("newBalance") + newBalance);
                    break;

                } else {

                    msg.printText("invalidInput", "na");
                }

            }

            if (currentPosition == 38) {
                balance = currentPlayer.getCurrentBalance();
                currentPlayer.withdrawMoney(2000,true,10);
                newBalance = currentPlayer.getCurrentBalance();
                msg.printText("newBalance", "na");
                System.out.println(msg.getText("newBalance") + newBalance);

            }

        }
    }
}
