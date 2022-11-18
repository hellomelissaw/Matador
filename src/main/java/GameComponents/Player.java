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

    private boolean bankrupt=false;
    Account playerAccount = new Account();
    private String winnerName;

    public Player(String playerName) {
        this.playerName = playerName;

    }


    public void withdrawMoney(int newPoints) {
        playerAccount.withDraw(newPoints);
        bankrupt = playerAccount.isBankrupt();
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public void depositMoney(int newPoints){
        playerAccount.deposit(newPoints);
    }

    public int getCurrentBalance(){
        return (playerAccount.getBalance());
    }

    public String getPlayerName (){
        return playerName;
    }

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

    public String winner(Player[] player) {
        int winner = player[0].getCurrentBalance();
        for (Player i : player) {
            if (i.getCurrentBalance() > winner)
                winner = i.getCurrentBalance();
            winnerName = i.getPlayerName();
        } return winnerName;
    }
}
