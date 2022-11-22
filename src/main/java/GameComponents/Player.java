package GameComponents;

/*
===================================================================================
This class is reused from our CDIO2 project and built upon.
===================================================================================
 */
public class Player {

    private int balance;
    private int squareIndex = 0;
    private String playerName;
    Account PlayerAccount = new Account();

    public Player(String playerName) {
        this.playerName = playerName;

    }
    public  Player(){

    }

    /**
     * Deposits money in Player's Account
     * @param newPoints amount of Monopoly money to deposit
     */
    public void withdrawMoney(int newPoints) {
        PlayerAccount.withDraw(newPoints);
    }

    /**
     * Withdraws money from Player's Account
     * @param newPoints amount of Monopoly Money to withdraw
     */
    public void depositMoney(int newPoints){
        PlayerAccount.deposit(newPoints);
    }

    public int getCurrentBalance(){
        return (PlayerAccount.getBalance());
    }

    public String getPlayerName (){
        return playerName;
    }

    /**
     * Updates the position of the Player according to the sum of the dice in rings from square 0 to 23
     * @param sumDice sum of the face values of dice in Cup
     * @return index of the Square that the Player is moved to after throwing dice
     */
    public int updatePosition(int sumDice) {
        for(int i = 0; i < sumDice; i++) {
            if (squareIndex < 23) {
                squareIndex++;
            } else {
                squareIndex = 0;
            }
        }
        return squareIndex;
    }

    public int getPosition(){
        return squareIndex;
    }

    @Override
    public String toString() {
        return playerName;
    }
}