package Controllers;

import GameComponents.Board.Deed;
import GameComponents.Player;
import Translator.Text;

public class SellController_loadable extends SellController{

    public SellController_loadable(GuiController guiController, Text msg) {
        super(guiController, msg);
    }

    String[] bidderName;
    int[] offer;
    boolean[] higherBidder;

    boolean isHigherBidder = true;

    String currentBidderName;

    public void setTestingAuctionLot (String[] bidderName, int[] offer, boolean[] higherBidder) {
        this.bidderName = bidderName;
        this.offer = offer;
        this.higherBidder = higherBidder;
    }

    public void auctionLot(Player nonParticipant, Player[] players, Deed deed) {

        String[] bidderArray = new String[players.length - 1];

            int indexCount = -1;
            for (int j = 0; j < players.length; j++) {

                if (!players[j].equals(nonParticipant)) {
                    indexCount++;
                    bidderArray[indexCount] = players[j].getPlayerName();
                }
            }


            while (isHigherBidder) {

                for (int i = 0; i < bidderName.length; i++) {
                    currentBidderName = bidderName[i];
                    System.out.println(currentBidderName + "'s offer is: " + offer[i]);
                if(i > 0) {
                    if(offer[i] < offer[i-1]) {
                    msg.printText("lowerThanPrevious", "na");
                     }
                }

                isHigherBidder = higherBidder[i];

            }
        }
            int playerIndex = 0;
            for (int j = 0; j < players.length; j++) {
                if (players[j].getPlayerName().equals(currentBidderName)) {
                    playerIndex = j;
                }
            }

            Player auctionWinner = players[playerIndex];
            auctionWinner.withdrawMoney(offer[offer.length-1], true);
            deed.setOwner(auctionWinner);
            auctionWinner.addToCardholder(deed);
            auctionWinner.addToOwnedFields(deed);
            System.out.println("Solgt til højstbydende! Tillykke " + currentBidderName + "! ");
        }

}
