package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;
import gui_fields.GUI_Player;

public class CardMoney extends ChanceCard {
    public CardMoney(String cardMessage, GuiController guiController) {
        super(cardMessage, guiController);

    }

    public void playCard(Player currentPlayer, GUI_Player currentGuiPlayer){}
}