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


    public void withdrawMoney(int newPoints) {
        PlayerAccount.withDraw(newPoints);
    }

    public void depositMoney(int newPoints){
        PlayerAccount.deposit(newPoints);
    }

    public int getCurrentBalance(){
        return (PlayerAccount.getBalance());
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

    public int goToSquare(int moveToThisSquare)
    {
        squareIndex = moveToThisSquare;
        return squareIndex;
    }
}