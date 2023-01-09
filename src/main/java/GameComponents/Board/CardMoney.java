package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class CardMoney extends ChanceCard {
    private String transactionType;
    private int amount;

    /**
     * Constructs a chance card that involves a money transaction
     * @param cardName name of the card
     * @param guiController GuiController used throughout the program
     * @param transactionType determines if player pays or receives money and from who
     * @param amount how much money is involved in the transaction
     */
    public CardMoney(String cardName, GuiController guiController, String transactionType, int amount) {
        super(cardName, guiController);
        this.transactionType = transactionType;
        this.amount = amount;

    }

    public void playCard(Player currentPlayer){
        if (transactionType.equals("deposit")) { // PLAYER RECEIVES MONEY FROM THE BANK
            currentPlayer.depositMoney(amount);

        } else if (transactionType.equals("withdraw")) { // PLAYER PAYS MONEY TO THE BANK
            currentPlayer.withdrawMoney(amount);

        } else if (transactionType.equals("hybrid")) { // PLAYER RECEIVES MONEY FROM OTHER PLAYERS
            for (int i = 0 ; i < players.length ; i++) {
                if (players[i] != currentPlayer) {
                    players[i].withdrawMoney(amount);
                }
            }
            int receive = amount * (players.length-1);
            currentPlayer.depositMoney(receive);

        }
    }
}