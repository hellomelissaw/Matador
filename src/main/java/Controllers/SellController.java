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

    public void sellLot(Player seller, Player[] players){
        if (seller.getPropertiesDeed().length < 1) return;
        playerCount = players.length;
        String choosenButton = guiController.getUserButtonPressed("salgeGround");
        if (choosenButton.equals("Ja")) {
            // Who wants to buy
            String choosenButton_1 = guiController.getUserButtonPressed("erDerEnKøber");
            if (choosenButton_1.equals("Ja")) {

                String[] buyerArray = new String[playerCount-1];

                for (int i = 0; i < playerCount ; i++) { // showes player's name in a drop-down menu
                    if (players[i] != seller){

                        buyerArray[i-1] = players[i].getPlayerName();
                    }

                }
                String buyerName = guiController.getUserSelection("Vælg dit navn: ", buyerArray);

                String[] deedNames = new String[seller.getPropertiesDeed().length];
                int[] deedPrices = new int[seller.getPropertiesDeed().length];
                String[] messages = new String[seller.getPropertiesDeed().length];

                for (int i = 0; i < (seller.getPropertiesDeed()).length; i++) { // showes all the properties
                    //deedNames[i] = seller.getPropertiesDeed()[i].getDeedName();
                    //deedPrices[i] = seller.getPropertiesDeed()[i].getDeedPrice();
                    //messages[i] = deedNames[i] + deedPrices[i];
                    deedNames[i] = seller.getOwnedFields()[i].getDeedName();
                    deedPrices[i] = seller.getOwnedFields()[i].getDeedPrice();
                    messages[i] = deedNames[i] + deedPrices[i];

                }

                String boughtDeed = guiController.getUserSelection("Hvilken grund vil du købe? ", messages);


                //String userSelection = guiController.getUserSelection("",players.getPropertiesDeed());
                //players.getPropertiesDeed();
            }
        }
    }

}
