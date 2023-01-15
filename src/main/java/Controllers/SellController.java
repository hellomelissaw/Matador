package Controllers;

import GameComponents.Board.Deed;
import GameComponents.Board.Deed_Buildable;
import GameComponents.Board.Deed_NonBuildable;
import GameComponents.Cardholder;
import GameComponents.Player;
import Translator.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SellController {
    public SellController(GuiController guiController , Text msg ) {
        this.guiController = guiController;
        this.msg = msg;

    }
    GuiController guiController;
    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);

    int deedPrice =0 ;
    int playerCount = 0;
    int boughtDeedPrice = 0;
    Player buyerPlayer;
    Deed boughtDeed;

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
                seller.removeFromCardholder(boughtDeed);
                for(int k = 0 ; k < seller.getOwnedFields().length ; k++){
                    System.out.println("Owned fields after removal: " + seller.getOwnedFields()[k].getDeedName());

                }
                buyerPlayer.addToOwnedFields(boughtDeed);
                buyerPlayer.addToCardholder(boughtDeed);

                msg.printText("erKøbt",buyerName);
                seller.depositMoney(boughtDeedPrice, false);
                buyerPlayer.withdrawMoney(boughtDeedPrice, false);
                msg.printText("overført",seller.getPlayerName());



            }
        }
    }
    boolean testingBuyLot;
    String chosenDeedName = "";
    int offeredPrice = 0;

    boolean offerAccepted;
    String[] lotOptions;
    public void buyLot(Player buyer, Player[] players) {
        String[] options = setLotOptions(buyer, players);

        if(!testingBuyLot) {
            chosenDeedName = guiController.getUserSelection(msg.getText("whichLotBuy"), options);
            offeredPrice = guiController.getUserInteger(msg.getText("enterOfferedPrice"));
        }

        boolean insufficientFunds = offeredPrice > buyer.getCurrentBalance();
        while(insufficientFunds) {
            offeredPrice = guiController.getUserInteger(msg.getText("giveLowerPrice"));

            if(offeredPrice <= buyer.getCurrentBalance()){
                insufficientFunds = false;
            }
        }
                // String deedType = getDeedType();
                Deed chosenDeed = getDeedFromName(chosenDeedName, players);
                Player owner = chosenDeed.getOwner();
                String ownerName = owner.getPlayerName();

                if(!testingBuyLot) {
                    offerAccepted = guiController.getUserBoolean(ownerName + msg.getText("acceptOffer"));
                }

                if (offerAccepted) {
                        buyer.withdrawMoney(offeredPrice, false);
                        owner.depositMoney(offeredPrice, false);
                        owner.removeFromOwnedFields(chosenDeed);
                        owner.removeFromCardholder(chosenDeed);
                        buyerPlayer.addToOwnedFields(chosenDeed);
                        buyerPlayer.addToCardholder(chosenDeed);

                } else {msg.printText("offerNotAccepted", "na");}


        /*if(deedType.equals("buildable")){
            Deed_Buildable deed = getDeedFromName(String deedName);
        } else if (deedType.equals("nonBuildable")) {
            Deed_NonBuildable deed = getDeedFromName(String deedName);
        } else {
            System.out.println("Deed type not recognized");
        }*/
    }
    ArrayList<Deed> playersDeeds = new ArrayList<Deed>();
    private Deed getDeedFromName(String chosenDeedName, Player[] allPlayers) {
    int currentDeedIndex = 0;
        for(int i = 0 ; i < playersDeeds.size() ; i++) {
            String currentDeedName = playersDeeds.get(i).getDeedName();
            if(currentDeedName.equals(chosenDeedName)){
               currentDeedIndex = i;
            }
        }
        Deed deed = playersDeeds.get(currentDeedIndex);
        return deed;
    }

    private String[] setLotOptions(Player currentPlayer, Player[] allPlayers) {
        ArrayList<String> lotArrList = new ArrayList<String>();
        System.out.println("Players arr length: " + allPlayers.length);

        for (int m = 0 ; m < allPlayers.length ; m++) {
            if(!allPlayers[m].equals(currentPlayer)) {

                Deed_Buildable[] bd = allPlayers[m].getBuildableDeeds();
                Deed_NonBuildable[] nbd = allPlayers[m].getNonBuildableDeeds();

                if(bd.length>0) {
                    for (int l = 0; l < bd.length; l++) {
                        lotArrList.add(bd[l].getDeedName());
                        playersDeeds.add(bd[l]);
                    }
                }

                if(nbd.length > 0) {
                    for (int l = 0; l < nbd.length; l++) {
                        lotArrList.add(nbd[l].getDeedName());
                        playersDeeds.add(nbd[l]);
                    }
                }
            }
        }
        lotOptions = new String[lotArrList.size()];
        lotOptions = lotArrList.toArray(lotOptions);
        return lotOptions;
    }

    public void setTestingBuyLot(boolean testingBuyLot, String chosenDeedName, int offeredPrice, boolean accept) {
        this.testingBuyLot = testingBuyLot;
        this.chosenDeedName = chosenDeedName;
        this.offeredPrice = offeredPrice;

    }

    public String[] getLotOptions() {
        return lotOptions;
    }
}
