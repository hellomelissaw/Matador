package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Bank;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;

public abstract class ChanceCard {
    protected String cardName;
    GuiController guiController;

    boolean guiIsOn;

    protected Text msg;
    protected Player[] players;
    protected GUI_Player[] guiPlayers;


    protected Square[] board;
    boolean pickAgain;

    public ChanceCard(String cardName){
        this.cardName = cardName;

    }

    public void setGuiIsOn(boolean guiIsOn) {
        this.guiIsOn = guiIsOn;
    }

    public void setGuiController(GuiController guiController) {
        this.guiController = guiController;
    }
    public void setBoard(Square[] board) {
        this.board = board;
    }

    protected void setCardLang(Text msg) {
       this.msg = msg;
    }

    protected void setPlayers(Player[] players){
        this.players = players;

    }


    public void printMessage(int cardIndex) {
        int cardNr = cardIndex+1;
        String card = "chance"+cardNr;
        msg.printText(card,"na");

    }

    public abstract void playCard(Player currentPlayer);

    public boolean checkPickAgain() {
        return pickAgain;
    }
}
