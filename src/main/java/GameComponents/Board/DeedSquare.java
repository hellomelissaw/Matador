package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class DeedSquare extends Square{
    boolean guiOn = false;
    Deed deed;
    boolean sellDeed = true;
    boolean freeDeed = false;
    int deedPrice;
    GuiController guiController;
    int houseCount;

    boolean hasHotel = false;

    /**
     * Constructs a Square of type DeedSquare (ownable Square)
     * @param deedName name of the Deed for the Square (for example "The Skate Park").
     * @param deedPrice the price of the deed, both for buying and amount of rent to pay once bought.
     * @param guiController The GuiController used throughout the classes.
     */
    public DeedSquare(String deedName, int deedPrice, GuiController guiController) {
        super(deedName);
        this.deed = new Deed(deedPrice, deedName);
        this.deedPrice = deedPrice;
        this.guiController = guiController;
    }

   public boolean hasDeed(){ // Checks if the square has a deed available to buy or if it's already sold
        return sellDeed;
    }

   public void setDeedOwner(Player currentPlayer, int deedIndex){
        sellDeed = false ;
        deed.setOwner(currentPlayer);
        if (guiOn) {guiController.setOwnerName(currentPlayer, deedIndex); }

    }

    public Player getDeedOwner() {
        return deed.getOwner();
    }

    public void setDeedToFree() {
       freeDeed = true;
    }

    @Override
    public String toString() {
        String priceString = Integer.toString(deedPrice);
        return priceString;
    }

    public void landOn(Player currentPlayer) {
       if(freeDeed == false && currentPlayer != deed.getOwner()){ currentPlayer.withdrawMoney(deedPrice); }

        System.out.println(msg.getText("newBalance") + currentPlayer.getCurrentBalance());

        if(sellDeed == true) {

            if (guiOn) {
                String guiMessage = currentPlayer.getPlayerName() + msg.getText("haveBought") + deed.getDeedName();
                guiController.showMessage(guiMessage); // CAN BE DELETED ONCE IMPLEMENT BORDER AROUND SQUARE
            }

            sellDeed = false ;
            freeDeed = false ;
            deed.setOwner(currentPlayer);
            if (guiOn) {guiController.setOwnerName(currentPlayer, currentPlayer.getPosition()); }

        } else {
           Player deedOwner = deed.getOwner();
            if (currentPlayer==deedOwner) {
                msg.printText("ownerOfDeed", "na");

            } else {

                msg.printText("payRent", "na");
                deedOwner.depositMoney(deedPrice);

            }

            System.out.println("");
        }

    }

    public void buyHouse(int houseCount) {
        this.houseCount += houseCount;
    }


    public int getHouseCount(){
        return houseCount;

    }
}
