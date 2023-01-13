package GameComponents.Board;
import GameComponents.Player;

public class DeedSquare_Buildable extends DeedSquare {
    int buildingPrice;
    //int houseCount = 0;
    boolean hasHotel = false;
    Deed_Buildable deed;
    /**
     * Constructs a Square of type DeedSquare which one can build on
     *
     * @param deedName      name of the Deed for the Square (for example "The Skate Park").
     * @param deedPrice     the price it costs to buy the deed to a lot
     * @param rent          an array of rent amounts player can receive according to buildings on lot
     * @param buildingPrice the cost of building one house or hotel
     */
    public DeedSquare_Buildable(String deedName, int deedPrice, int[] rent, int buildingPrice) {
        super(deedName, deedPrice, rent);
        this.buildingPrice = buildingPrice;
        deed = new Deed_Buildable(deedPrice, rent, deedName, buildingPrice);

    }

    public void setGroup(String color, int groupSize) {
        this.color = color;
        this.groupSize = groupSize;
        deed.setDeedGroup(color, groupSize);
    }

    public void setDeedIndex(int i) {
        deed.setIndex(i);
    }

    public Deed_Buildable getDeed() {
        return deed;
    }

    @Override
    protected String userInputBuying(boolean testing) {
        String[] choices = {"ja", "nej"};
        if(!testing) {
            buying = guiController.getUserSelection(msg.getText("buyLot") + " " + deed.getDeedName() + "?", choices);
        }
        return buying;
    }

    protected void buyingLot(Player currentPlayer) {
        currentPlayer.withdrawMoney(deedPrice, true);
        System.out.println(msg.getText("newBalance") + currentPlayer.getCurrentBalance());
        sellDeed = false;
        freeDeed = false;
        deed.setOwner(currentPlayer);
        currentPlayer.addToOwnedFields(deed); // from new methode
        currentPlayer.takeBuildableDeed(deed);
        if (guiIsOn) {
            guiController.setOwnerName(currentPlayer, currentPlayer.getPosition());
        }
    }

    protected void lotIsOwned(Player currentPlayer){
        Player deedOwner = deed.getOwner();
        if (currentPlayer==deedOwner) { // IF PLAYER HAS LANDED ON A LOT THAT THEY OWN
            msg.printText("ownerOfDeed", "na");

        } else { // IF A PLAYER LANDS ON A LOT THAT ANOTHER PLAYER OWNS
            int rentOwed = rent[deed.getHouseCount()];
            if(deedOwner.IsGroupOwner(deed.getColor())){
                rentOwed = rentOwed * 2;
                msg.printText("payDoubleRent", deedOwner.getPlayerName());
            } else {
            msg.printText("payRent",  deedOwner.getPlayerName());
            }

            currentPlayer.withdrawMoney(rent[rentOwed], false);
            deedOwner.depositMoney(deedPrice, false);
            System.out.println(msg.getText("newBalance") + currentPlayer.getCurrentBalance());

        }

    }


   /* public int getHouseCount(){
        return houseCount;

    }*/

    /*public void setHouseCount() {
        houseCount = deed.getHouseCount();
    }
*/
   /* public boolean hasHotel() {
        return hasHotel;
    }*/

   /* public void setHasHotel(boolean hasHotel) {
        this.hasHotel = hasHotel;
        deed.setHasHotel(hasHotel);
    }*/

    public Player getDeedOwner() {
        return deed.getOwner();
    }


    public void setOwnerForTesting(Player player){
        deed.setOwner(player);
        player.takeBuildableDeed(deed);
        player.addToOwnedFields(deed);

    }

}
