package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;

public abstract class ChanceCard {
    protected String cardMessage;
    GuiController guiController;

    protected Text msg;
    protected Player[] players;
    protected GUI_Player[] guiPlayers;

    boolean pickAgain;

    public ChanceCard(String cardMessage, GuiController guiController){
        this.cardMessage = cardMessage;
        this.guiController = guiController;
        guiPlayers = guiController.getGuiPlayersArr();


    }

    protected void setLang(Text msg) {
       this.msg = msg;
    }

    protected void setPlayers(Player[] players, GUI_Player[] guiPlayers){
        this.players = players;
        this.guiPlayers = guiPlayers;

    }

    public void printMessage(int cardIndex) {
        int cardNr = cardIndex+1;
        String card = "chance"+cardNr;
        cardMessage = msg.getText(card);
        System.out.println(cardMessage);
        guiController.showMessage(cardMessage);
    }

    public abstract void playCard(Player currentPlayer, GUI_Player currentGuiPlayer);

    public boolean checkPickAgain() {
        return pickAgain;
    }
}
