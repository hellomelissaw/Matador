package GameComponents.Board;

import GameComponents.Player;

public class DeedSquare_NonBuildable extends DeedSquare {
    Deed_NonBuildable deed;
    /**
     * Constructs a Square of type DeedSquare which cannot be built on
     *
     * @param deedName      name of the Deed for the Square (for example "The Skate Park").
     * @param deedPrice     the price it costs to buy the deed to a lot
     * @param rent          an array of rent amounts player can receive according to buildings on lot
     */
    public DeedSquare_NonBuildable(String deedName, int deedPrice, int[] rent) {
        super(deedName, deedPrice, rent);
        deed = new Deed_NonBuildable(deedPrice, rent, deedName);
        deed.setDeedGroup(color, groupSize);


    }
    protected String userInputBuying(boolean testing) {
        String[] choices = {"ja", "nej"};
        if(!testing) {
            buying = guiController.getUserSelection(msg.getText("buyLot") + " " + deed.getDeedName() + "?", choices);
        }
        return buying;
    }
    @Override
    protected void buyingLot(Player currentPlayer) {
        currentPlayer.withdrawMoney(deedPrice, true, 10);
        System.out.println(msg.getText("newBalance") + currentPlayer.getCurrentBalance());
        sellDeed = false;
        freeDeed = false;
        deed.setOwner(currentPlayer);
        currentPlayer.addToOwnedFields(deed);
        currentPlayer.takeNonBuildableDeed(deed);
        if (guiIsOn) {
            guiController.setOwnerName(currentPlayer, currentPlayer.getPosition());
        }
    }


    protected void lotIsOwned(Player currentPlayer, Player[] players) {
        Player deedOwner = deed.getOwner();
        int playerNumber = 10;
        Player owner;
        if (currentPlayer==deedOwner) { // IF PLAYER HAS LANDED ON A LOT THAT THEY OWN
            msg.printText("ownerOfDeed", "na");

        } else { // IF A PLAYER LANDS ON A LOT THAT ANOTHER PLAYER OWNS

            int rentIndex = 0;
            int counter = 0;

            Deed_NonBuildable[] deeds = deedOwner.getNonBuildableDeeds();
            for(int j = 0 ; j < deeds.length ; j++) {
                if(deeds[j].getColor().equals(deed.getColor())){
                    counter++;
                }
            }

            if(counter > 1) {
                rentIndex = counter-1;
                msg.printText("payHigherRent", deedOwner.getPlayerName());
            } else {
                msg.printText("payRent",  deedOwner.getPlayerName());
            }
            System.out.println("Rent index is: " + rentIndex);

            int rentOwed = rent[rentIndex];

            for (int i = 0; i < players.length ; i++) {
                owner = deed.getOwner();
                if (players[i] == owner){
                    playerNumber = i;
                }
            }

            currentPlayer.withdrawMoney(rentOwed, false, playerNumber); //TO DO: CHECK IF PLAYER OWNS OTHER LOTS IN GROUP TO CHANGE RENT
            deedOwner.depositMoney(rentOwed, false);
            System.out.println(msg.getText("newBalance") + currentPlayer.getCurrentBalance());

        }
    }

    public Player getDeedOwner() {
        return deed.getOwner();
    }
    public void setGroup(String color, int groupSize) {
        this.color = color;
        this.groupSize = groupSize;
        deed.setDeedGroup(color, groupSize);
    }

    public void setOwnerForTesting(Player player){
        deed.setOwner(player);
        player.takeNonBuildableDeed(deed);
        player.addToOwnedFields(deed);

    }

}
