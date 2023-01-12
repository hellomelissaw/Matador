package Controllers;

import GameComponents.Board.Deed;
import GameComponents.Board.Deed_Buildable;
import GameComponents.Board.Deed_NonBuildable;
import GameComponents.Cardholder;
import GameComponents.Player;

public class SellController {
    public SellController(GuiController guiController ) {
        this.guiController = guiController;

    }

    Deed deed;
    Player[] players;
    GuiController guiController;
    Deed_Buildable deedBuildable;
    Deed_NonBuildable deedNonBuildable;
    //Deed deeds[] ;
    Cardholder cardholder;
    int deedPrice =0 ;
    int playerCount = 0;
    String[] playersName;

    public void sellLot(Player players){
        String choosenButton = guiController.getUserButtonPressed("salgeGround");
        Deed[] deeds = new Deed[(players.getPropertiesDeed()).length];
        if (choosenButton == "Ja") {
            // Who wants to buy
            String choosenButton_1 = guiController.getUserButtonPressed("erDerEnKøber");
            if (choosenButton_1 == "ja") {
                for (int i = 0; i < playerCount ; i++) {
                    playersName[i] = players.getPlayerName();
                    String buyerName = guiController.getUserSelection(playersName[i]);

                }

                for (int i = 0; i < (players.getPropertiesDeed()).length; i++) { // showes all the properties
                    deeds[i] = players.getPropertiesDeed()[i];
                    //deeds[i].getDeedName();
                    String userSelection = guiController.getUserSelection("",deeds[i].getDeedName());
                    deedPrice = deeds[i].getDeedPrice();
                }
                //String userSelection = guiController.getUserSelection("",players.getPropertiesDeed());
                //players.getPropertiesDeed();
            }
        }
    }

}
