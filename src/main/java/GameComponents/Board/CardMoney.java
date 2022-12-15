package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class CardMoney extends ChanceCard {
    public CardMoney(String cardMessage, GuiController guiController, Player[] players) {
        super(cardMessage, guiController);
        this.players = players;

    }
    public void playCard(Player currentPlayer){
        if (cardName == "chance5") {
            currentPlayer.withdrawMoney(2);

        } else if (cardName == "chance6") {
            int otherPlayers = players.length-1;
            currentPlayer.depositMoney(otherPlayers);

            for (int i = 0 ; i < players.length ; i++) {
                if (players[i] != currentPlayer) {
                    players[i].withdrawMoney(1);
                }
            }

        } else if (cardName == "chance7") {
            currentPlayer.depositMoney(2);
        }
    }
}