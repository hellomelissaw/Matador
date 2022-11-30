package GameComponents.Board;
import Controllers.GuiController;
import Translator.Text;

public abstract class ChanceCard {
    private String cardMessage;
    GuiController guiController;

    protected Text msg;

    public ChanceCard(String cardMessage, GuiController guiController){
        this.cardMessage = cardMessage;
        this.guiController = guiController;

    }

    protected void setLang(Text msg) {
       this.msg = msg;
    }

    public void printMessage(int cardIndex) {
        int cardNr = cardIndex+1;
        String card = "chance"+cardNr;
        cardMessage = msg.getText(card);
        System.out.println(cardMessage);
        guiController.showMessage(cardMessage);
    }
}
