package GameComponents;

import Controllers.GuiController;
import gui_fields.GUI_Player;

/*
===================================================================================
This class is reused from our CDIO2 project and built upon.
===================================================================================
 */
public class Player {
    private boolean testing = true;
    GUI_Player guiPlayer;
    GuiController guiController;
    private int squareIndex = 0;
    private String playerName;
    Account playerAccount = new Account();
    private String winnerName;

    public Player(String playerName) {
        this.playerName = playerName;

    }

    public void setGui(GUI_Player guiPlayer, GuiController guiController) {
        this.guiPlayer = guiPlayer;
        this.guiController = guiController;
        testing = false;
    }

    public void setStartBalance(int startBalance) {
        playerAccount.deposit(startBalance);
    }

    /**
     * Deposits money in Player's Account
     * @param newPoints amount of Monopoly money to deposit
     */
    public void withdrawMoney(int newPoints) {
        playerAccount.withDraw(newPoints);
        guiPlayer.setBalance(playerAccount.getBalance());
    }

    /**
     * Withdraws money from Player's Account
     * @param newPoints amount of Monopoly Money to withdraw
     */
    public void depositMoney(int newPoints){
        playerAccount.deposit(newPoints);
        guiPlayer.setBalance(playerAccount.getBalance());
    }

    public int getCurrentBalance(){
        return (playerAccount.getBalance());
    }

    public String getPlayerName (){
        return playerName;
    }

    /**
     * Updates the position of the Player according to the sum of the dice in rings from square 0 to 23
     * @param sumDice sum of the face values of dice in Cup
     * @return index of the Square that the Player is moved to after throwing dice
     */
    public void updatePosition(int sumDice) {
        for(int i = 0; i < sumDice; i++) {
            if (squareIndex < 23) {
                squareIndex++;
            } else {
                squareIndex = 0;
            }
        }
        System.out.println("Square Index after for loop: " + squareIndex);
        if(!testing){ guiController.move(guiPlayer, squareIndex - sumDice, squareIndex);}
    }

    public int getPosition(){
        return squareIndex;
    }

    public boolean isBankrupt() {
        return playerAccount.getAccountStatus();

    }
    @Override
    public String toString() {
        return playerName;
    }

    // Methode is inspired from internet https://www.geeksforgeeks.org/java-program-for-program-to-find-largest-element-in-an-array/
    public String winner(Player[] player) {
        int winner = player[0].getCurrentBalance();
        for (int i = 0; i < player.length; i++) {
            if (player[i].getCurrentBalance() > winner) {
                winner = player[i].getCurrentBalance();
                winnerName = player[i].getPlayerName();
            }
        }
        return winnerName;
    }


    public int goToSquare(int moveToThisSquare)
    {
        squareIndex = moveToThisSquare;
        return squareIndex;
    }

}

