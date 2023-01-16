package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Bank;
import GameComponents.Player;

public class CardMoney extends ChanceCard {
    private String transactionType;
    private int amount;
    private int alternativeAmount;

    private Bank bank = new Bank();

    /**
     * Constructs a chance card that involves a money transaction
     * @param cardName name of the card
     * @param guiController GuiController used throughout the program
     * @param transactionType determines if player pays or receives money and from who
     * @param amount how much money is involved in the transaction
     */
    public CardMoney(String cardName, GuiController guiController, String transactionType, int amount, int alternativeAmount) {
        super(cardName, guiController);
        this.transactionType = transactionType;
        this.amount = amount;
        this.alternativeAmount = alternativeAmount;

    }
    public CardMoney(String cardName, GuiController guiController, String transactionType, int amount) {
        super(cardName, guiController);
        this.transactionType = transactionType;
        this.amount = amount;

    }

    public void playCard(Player currentPlayer){
        if (transactionType.equals("deposit")) { // PLAYER RECEIVES MONEY FROM THE BANK
            currentPlayer.depositMoney(amount, true);
            currentPlayer.netWorth += amount;
            //currentPlayer.updateBank(amount, "deposit");

        } else if (transactionType.equals("withdraw")) { // PLAYER PAYS MONEY TO THE BANK
            currentPlayer.withdrawMoney(amount, true);
            //currentPlayer.updateBank(amount, "withdraw");

            if(cardName.equals("chance1") || cardName.equals("chance2")) {

                Deed[] playerFields = currentPlayer.getOwnedFields();
                int houseCounter = 0;
                int hotelCounter = 0;
                for (int i = 0; i < playerFields.length; i++) {
                    houseCounter += playerFields[i].houseCount;
                    if (playerFields[i].hasHotel) hotelCounter++;
                }

                int newAmount = amount*houseCounter + alternativeAmount*hotelCounter;
                currentPlayer.withdrawMoney(newAmount, true);
            } else currentPlayer.withdrawMoney(amount, true);

        } else if (transactionType.equals("hybrid")) { // PLAYER RECEIVES MONEY FROM OTHER PLAYERS
            for (int i = 0 ; i < players.length ; i++) {
                if (players[i] != currentPlayer) {
                    players[i].withdrawMoney(amount, false);
                }
            }
            int receive = amount * (players.length-1);
            currentPlayer.depositMoney(receive, false);

        }
    }
}