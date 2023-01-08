package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;

public class TaxSquare extends Square{

    GuiController guiController;
    int userInput;
    int balance;
    int newBalance;
    Player[] players;
    int withdrawMoney;

    TaxSquare(String squareName , GuiController guiController , Player[] players) {
        super(squareName);
        this.guiController = guiController;
        this.players = players;
    }

    @Override
    public void landOn(Player currentPlayer) {
        int currentPosition = currentPlayer.getPosition();

        if (currentPosition==4){
            msg.printText("betaleSkat", "na");
            userInput = guiController.getUserInteger("indkomstSkat");
            if(userInput == 1) {
                balance = (currentPlayer.getCurrentBalance());
                withdrawMoney = (int) (balance/100) * 10 ;
                currentPlayer.withdrawMoney(withdrawMoney);
                newBalance = currentPlayer.getCurrentBalance();
                msg.printText("newBalance","na");
                System.out.println(msg.getText("newBalance")+ newBalance);
            }
            else {
                balance = currentPlayer.getCurrentBalance();
                currentPlayer.withdrawMoney(4000);
                newBalance = currentPlayer.getCurrentBalance();
                msg.printText("newBalance","na");
                System.out.println(msg.getText("newBalance")+ newBalance);

            }



        }
        if (currentPosition == 38){
            balance = currentPlayer.getCurrentBalance();
            currentPlayer.withdrawMoney(2000);
            newBalance = currentPlayer.getCurrentBalance();
            msg.printText("newBalance","na");
            System.out.println(msg.getText("newBalance")+ newBalance);

        }

    }
}
