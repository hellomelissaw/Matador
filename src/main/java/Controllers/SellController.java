package Controllers;

import GameComponents.Board.Deed;
import GameComponents.Board.Deed_Buildable;
import GameComponents.Board.Deed_NonBuildable;
import GameComponents.Cardholder;
import GameComponents.Player;
import Translator.Text;

public class SellController {
    public SellController(GuiController guiController , Text msg ) {
        this.guiController = guiController;
        this.msg = msg;

    }
    private GuiController guiController;
    private Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);

    private int deedPrice =0 ;
    private int playerCount = 0;
    private int boughtDeedPrice = 0;
    private Player buyerPlayer;
    private Deed boughtDeed;

    public void sellLot(Player seller, Player[] players){
        //if (seller.getPropertiesDeed().length < 1) return;
        playerCount = players.length;
        String choosenButton = guiController.getUserButtonPressed(msg.getText("salgeGround"));
        if (choosenButton.equals("Ja")) {
            // Who wants to buy
            String choosenButton_1 = guiController.getUserButtonPressed(msg.getText("erDerEnKøber"));
            if (choosenButton_1.equals("Ja")) {

                String[] buyerArray = new String[playerCount-1];
                int indexCount = -1;
                for (int i = 0; i < playerCount ; i++) { // showes player's name in a drop-down menu

                    if (players[i] != seller){
                        indexCount++;
                        buyerArray[indexCount] = players[i].getPlayerName();
                    }

                }
                String buyerName = guiController.getUserSelection("Vælg dit navn: ", buyerArray);

                // Find the buyer player
                for (int i = 0; i <playerCount; i++) {
                    players[i].getPlayerName();
                    if(players[i].getPlayerName() == buyerName)
                        buyerPlayer = players[i];
                }

                String[] deedNames = new String[ seller.getOwnedFields().length];
                int[] deedPrices = new int[ seller.getOwnedFields().length];
                String[] messages = new String[ seller.getOwnedFields().length];

                for (int i = 0; i < ( seller.getOwnedFields()).length; i++) { // showes all the properties
                    deedNames[i] = seller.getOwnedFields()[i].getDeedName();
                    deedPrices[i] = seller.getOwnedFields()[i].getDeedPrice();
                    boughtDeedPrice = seller.getOwnedFields()[i].getDeedPrice();
                    //messages[i] = deedNames[i] + deedPrices[i];

                }

                String boughtDeedName = guiController.getUserSelection("Hvilken grund vil du købe? ", deedNames);
                for (int i = 0; i <  seller.getOwnedFields().length; i++){
                    if(seller.getOwnedFields()[i].getDeedName() == boughtDeedName)
                        boughtDeed = seller.getOwnedFields()[i];
                }
                for(int k = 0 ; k < seller.getOwnedFields().length ; k++){
                    System.out.println("Owned fields before removal: " + seller.getOwnedFields()[k].getDeedName());
                }
                seller.removeFromOwnedFields(boughtDeed);
                for(int k = 0 ; k < seller.getOwnedFields().length ; k++){
                    System.out.println("Owned fields after removal: " + seller.getOwnedFields()[k].getDeedName());
                }
                buyerPlayer.addToOwnedFields(boughtDeed);
                msg.printText("erKøbt",buyerName);
                seller.depositMoney(boughtDeedPrice, false);
                buyerPlayer.withdrawMoney(boughtDeedPrice, false);
                msg.printText("overført",seller.getPlayerName());



            }
        }
    }
}
