package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class CardMove extends ChanceCard {
    int distance;
    public CardMove(String cardMessage, GuiController guiController, int distance) {
        super(cardMessage, guiController);
        this.distance = distance;
    }

    public void playCard(Player currentPlayer, GUI_Player currentGuiPlayer){
        if (distance == 23 || distance == 24) {
            distance = distance - currentPlayer.getPosition();
        }
            currentPlayer.updatePosition(distance);
    }

}
