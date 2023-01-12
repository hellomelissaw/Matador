package Controllers;

import GameComponents.Board.Deed;
import GameComponents.Board.Deed_Buildable;
import GameComponents.Board.Deed_NonBuildable;
import GameComponents.Cardholder;
import GameComponents.Player;

public class SellController {

    Deed deed;
    Player[] players;
    GuiController guiController = new GuiController();
    Deed_Buildable deedBuildable;
    Deed_NonBuildable deedNonBuildable;
    Cardholder cardholder;
    int deedPrice =0 ;

    public void sellLot(Player players){
        String choosenButton = guiController.getUserButtonPressed("salgeGround");
        if (choosenButton == "Ja"){
           // String userSelection = guiController.getUserSelection("",propertiesName[i]);
            players.getPropertiesDeed();
        }
    }

}
