package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class CardMoney extends ChanceCard {
    private String transactionType;
    private int amount;
    public CardMoney(String cardName, GuiController guiController, String transactionType, int amount) {
        super(cardName, guiController);
        this.transactionType = transactionType;
        this.amount = amount;

    }

    public void playCard(Player currentPlayer){
        if (transactionType.equals("deposit")) {
            currentPlayer.depositMoney(amount);

        } else if (transactionType.equals("withdraw")) {
            currentPlayer.withdrawMoney(amount);

        } else if (transactionType.equals("hybrid")) {
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