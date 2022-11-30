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

    public void printMessage() {
        cardMessage = msg.getText("chance5");
        System.out.println(cardMessage);
        guiController.showMessage(cardMessage);
    }
}
