package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;
import gui_fields.GUI_Player;

public class CardDeed extends ChanceCard {
    private String color1;
    private String color2;

    public CardDeed(String cardMessage, GuiController guiController, String color1, String color2) {
        super(cardMessage, guiController);
        this.color1 = color1;
        this.color2 = color2;

    }
    public void playCard(Player currentPlayer, GUI_Player currentGuiPlayer){
       if (color1 == "cyan" || color1 == "red") {

       } else if(color1 == "lightgrey"){

       } else if(color1 == "orange") {

       } else if(color1 == "pink"){

       } else if (color1 == "na") {

       }
    }
}
