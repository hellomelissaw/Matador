package Controllers;

import GameComponents.Board.Deed;
import GameComponents.Board.Deed_Buildable;
import GameComponents.Board.Deed_NonBuildable;
import GameComponents.Player;
import Translator.Text;

import java.util.ArrayList;

public class SellController {
    public SellController(GuiController guiController , Text msg ) {
        this.guiController = guiController;
        this.msg = msg;

    }
    GuiController guiController;
    Text msg;

    int deedPrice =0 ;
    int playerCount = 0;
    int boughtDeedPrice = 0;
    Player buyerPlayer;
    Deed deedToSell;

    int offeredPrice = 0;

    public void sellLot(Player seller, Player[] players){
        //if (seller.getPropertiesDeed().length < 1) return;
        playerCount = players.length;

                String[] deedNames = new String[ seller.getOwnedFields().length];
                int[] deedPrices = new int[ seller.getOwnedFields().length];
                //String[] messages = new String[ seller.getOwnedFields().length];

                for (int i = 0; i < ( seller.getOwnedFields()).length; i++) { // showes all the properties
                    deedNames[i] = seller.getOwnedFields()[i].getDeedName();
                    deedPrices[i] = seller.getOwnedFields()[i].getDeedPrice();
                    //boughtDeedPrice = seller.getOwnedFields()[i].getDeedPrice();
                    //messages[i] = deedNames[i] + deedPrices[i];

                }

        String deedToSellName = guiController.getUserSelection("Hvilken grund vil du sælge?", deedNames);
        for (int i = 0; i <  seller.getOwnedFields().length; i++){
            if(seller.getOwnedFields()[i].getDeedName() == deedToSellName)
                this.deedToSell = seller.getOwnedFields()[i];
        }

        offeredPrice = guiController.getUserInteger("Til hvor meget vil du sælge den?");

       // String choosenButton = guiController.getUserButtonPressed(msg.getText("salgeGround"));
       // if (choosenButton.equals("Ja")) {
            // Who wants to buy
        String message = seller.getPlayerName() + " sælger " + deedToSellName + " til " + offeredPrice + " DKK. " + msg.getText("erDerEnKøber");
            String choosenButton_1 = guiController.getUserButtonPressed(message);
            if (choosenButton_1.equals("Ja")) {

                String[] buyerArray = new String[playerCount - 1];
                int indexCount = -1;
                for (int i = 0; i < playerCount; i++) { // showes player's name in a drop-down menu

                    if (players[i] != seller) {
                        indexCount++;
                        buyerArray[indexCount] = players[i].getPlayerName();
                    }

                }
                String buyerName = guiController.getUserSelection("Køberen, vælg dit navn: ", buyerArray);

                // Find the buyer player
                for (int i = 0; i < playerCount; i++) {
                    players[i].getPlayerName();
                    if (players[i].getPlayerName() == buyerName)
                        buyerPlayer = players[i];
                }
            }


                for(int k = 0 ; k < seller.getOwnedFields().length ; k++){
                    System.out.println("Owned fields before removal: " + seller.getOwnedFields()[k].getDeedName());
                }

                seller.removeFromOwnedFields(this.deedToSell);
                seller.removeFromCardholder(this.deedToSell);
                for(int k = 0 ; k < seller.getOwnedFields().length ; k++){
                    System.out.println("Owned fields after removal: " + seller.getOwnedFields()[k].getDeedName());

                }
                buyerPlayer.addToOwnedFields(this.deedToSell);
                buyerPlayer.addToCardholder(this.deedToSell);

                msg.printText("erKøbt",buyerPlayer.getPlayerName());
                seller.depositMoney(offeredPrice, false);
                buyerPlayer.withdrawMoney(offeredPrice, false);
                msg.printText("overført",seller.getPlayerName());



            }

    // BUY LOT METHOD AND HELPER METHODS
    boolean testingBuyLot;
    String chosenDeedName = "";
    boolean offerAccepted = false;
    String[] lotOptions;

    /***
     * Call this method when testing buyLot method in order to pre-fill user input.
     * @param testingBuyLot takes true or false boolean argument, set to true when testing
     * @param chosenDeedName takes a String, set String to the name of the Deed to be bought
     * @param offeredPrice takes an Integer, set to the price for which the test player offers to buy lot
     * @param accept takes true or false boolean argument, set to true to test an accepted offer and false to test a rejected offer
     */
    public void setTestingBuyLot(boolean testingBuyLot, String chosenDeedName, int offeredPrice, boolean accept) {
        this.testingBuyLot = testingBuyLot;
        this.chosenDeedName = chosenDeedName;
        this.offeredPrice = offeredPrice;
        offerAccepted = accept;

    }

    /***
     * Call this method when one player should offer to buy another player's lot for an offered price. The owner of the lot to be bought can either accept or refuse the offer.
     * @param buyer Player which will choose a lot to buy from all the other players' lots and offer a price for it
     * @param players All the players in the game
     */
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
                        chosenDeed.setOwner(buyer);
                        buyer.addToOwnedFields(chosenDeed);
                        buyer.addToCardholder(chosenDeed);

                } else {msg.printText("offerNotAccepted", "na");}

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


    public String[] getLotOptions() {
        return lotOptions;
    }

    // TRADE LOT METHOD AND HELPER METHODS
    boolean testingTradeLot = false;
    String currentPlayerDeed;
    String otherPlayerDeed;

    /***
     * Call this method when testing tradeLot() in order to pre-fill user input.
     * @param testingTradeLot takes true or false boolean argument, set to true when testing
     * @param deedToOffer Deed that one player wishes to trade another player with
     * @param deedToGet Deed that one player wishes to get in return for their offered Deed
     * @param offerAccepted takes true or false boolean argument, set to true to test an accepted offer and false to test a rejected offer
     */
    public void setTestingTradeLot(boolean testingTradeLot, String deedToOffer, String deedToGet, boolean offerAccepted) {
        this.testingTradeLot = testingTradeLot;
        this.currentPlayerDeed = deedToOffer;
        this.otherPlayerDeed = deedToGet;
        this.offerAccepted = offerAccepted;
    }

    /***
     * Call this method when one player should ask to trade lots with another player
     * @param currentPlayer Player which offers another player to trade lots
     * @param players All the players in the game
     */
    public void tradeLot(Player currentPlayer, Player[] players){
        String[] currentPlayerDeeds = new String[ currentPlayer.getOwnedFields().length];

        for(int i = 0 ; i < currentPlayer.getOwnedFields().length ; i++){
            currentPlayerDeeds[i] = currentPlayer.getOwnedFields()[i].getDeedName();
        }

        String[] otherPlayerDeeds = setLotOptions(currentPlayer, players);

        if(!testingTradeLot) {
            currentPlayerDeed = guiController.getUserSelection(msg.getText("whichLotToTrade"), currentPlayerDeeds);
            otherPlayerDeed = guiController.getUserSelection(msg.getText("whichLotToGet"), otherPlayerDeeds);
        }
        System.out.println("Current player deed is: " + currentPlayerDeed);
        System.out.println("Other player deed is: " + otherPlayerDeed);
        Deed deedToGet = getDeedFromName(otherPlayerDeed, players);
        Player otherPlayer = deedToGet.getOwner();

        System.out.println("Owner of " + deedToGet.getDeedName() + " is " + otherPlayer);

        if(!testingTradeLot) {
            offerAccepted = guiController.getUserBoolean(otherPlayer.getPlayerName() + msg.getText("acceptOffer"));
        }

        if (offerAccepted) {
            Deed deedToOffer = null;
            Deed[] ownedFields = currentPlayer.getOwnedFields();
            for(int i = 0 ; i < ownedFields.length ; i++){
                if(ownedFields[i].getDeedName().equals(currentPlayerDeed)) {
                    deedToOffer = ownedFields[i];
                } else { deedToOffer = null; }
            }
            currentPlayer.removeFromOwnedFields(deedToOffer);
            currentPlayer.removeFromCardholder(deedToOffer);
            deedToOffer.setOwner(otherPlayer);
            System.out.println(deedToOffer.getDeedName() + " owner after setOwner() is " + deedToOffer.getOwner().getPlayerName());
            otherPlayer.addToOwnedFields(deedToOffer);
            otherPlayer.addToCardholder(deedToOffer);

            otherPlayer.removeFromOwnedFields(deedToGet);
            otherPlayer.removeFromCardholder(deedToGet);
            deedToGet.setOwner(currentPlayer);
            currentPlayer.addToOwnedFields(deedToGet);
            currentPlayer.addToCardholder(deedToGet);

        } else {msg.printText("offerNotAccepted", "na");}


    }

    //  AUCTION LOT METHOD AND HELPER METHODS
    boolean testingAuctionLot = false;
    String bidderName;
    int offer;
    boolean higherBidder = true;

    /***
     * Call when testing auctionLot in order to pre-fill user input
     * @param testingAuctionLot takes true or false boolean argument, set to true when testing
     * @param bidderName takes a String, the name of the player placing a bid
     * @param offer takes an Integer, the amount the bidder is offering
     * @param higherBidder takes a boolean argument true or false, true if there is a player who wants to bid higher, false if there is not
     */
    public void setTestingAuctionLot (boolean testingAuctionLot, String bidderName, int offer, boolean higherBidder) {
        this.testingAuctionLot = testingAuctionLot;
        this.bidderName = bidderName;
        this.offer = offer;
        this.higherBidder = higherBidder;
    }

    /***
     * Call auctionLot when a player lands on a lot available for purchase and does not buy it so that other players can bid on it
     * @param nonParticipant Player which has landed on the square and chooses not to buy it and will not participate in the auction
     * @param players All the Players in the game
     * @param deed the Deed up for auction (the deed for the lot that has been landed on and not purchased)
     */
    public void auctionLot(Player nonParticipant, Player[] players, Deed deed) {
        String[] bidderArray = new String[players.length - 1];
        int indexCount = -1;
        for (int i = 0; i < players.length; i++) {

            if (!players[i].equals(nonParticipant)) {
                indexCount++;
                bidderArray[indexCount] = players[i].getPlayerName();
            }
        }
        int previousOffer = 0;
        while(higherBidder) {
            if (!testingAuctionLot) {
                bidderName = guiController.getUserSelection(msg.getText("areThereBidders"), bidderArray);
                offer = guiController.getUserInteger(bidderName + ", " + msg.getText("enterOfferedPrice"));
                if(offer < previousOffer) {
                    msg.printText("lowerThanPrevious", "na");
                }

                higherBidder = guiController.getUserBoolean(msg.getText("higherBidder"));

                if(higherBidder && offer > previousOffer){
                    previousOffer = offer;
                }
            }
        }
        int playerIndex = 0;
        for(int i = 0 ; i < players.length ; i++) {
            if(players[i].getPlayerName().equals(bidderName)){
                playerIndex = i;
            }
        }
        Player auctionWinner = players[playerIndex];
        auctionWinner.withdrawMoney(offer, true);
        deed.setOwner(auctionWinner);
        auctionWinner.addToCardholder(deed);
        auctionWinner.addToOwnedFields(deed);
        msg.printText("soldHighestBidder", "Tillykke " + bidderName + "! ");
    }

}
