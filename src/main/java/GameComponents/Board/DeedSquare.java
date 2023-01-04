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

    boolean ownsGroup = false;
    int houseCount;
    boolean hasHotel = false;

    int[] rent;

    /**
     * Constructs a Square of type DeedSquare (ownable Square)
     * @param deedName name of the Deed for the Square (for example "The Skate Park").
     * @param deedPrice the price of the deed, both for buying and amount of rent to pay once bought.
     * @param guiController The GuiController used throughout the classes.
     */
    public DeedSquare(String deedName, int deedPrice, int housePrice, int[] rent, GuiController guiController) {
        super(deedName);
        this.deed = new Deed(deedPrice, housePrice, rent, deedName);
        this.deedPrice = deedPrice;
        this.rent = rent;
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

        if(sellDeed == true) {

            if (guiOn) {
                String guiMessage = currentPlayer.getPlayerName() + msg.getText("haveBought") + deed.getDeedName();
                guiController.showMessage(guiMessage); // CAN BE DELETED ONCE IMPLEMENT BORDER AROUND SQUARE
            }
            currentPlayer.withdrawMoney(deedPrice);
            System.out.println(msg.getText("newBalance") + currentPlayer.getCurrentBalance());
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
                currentPlayer.withdrawMoney(rent[houseCount]);
                deedOwner.depositMoney(deedPrice);
                System.out.println(msg.getText("newBalance") + currentPlayer.getCurrentBalance());

            }

            System.out.println("");
        }

    }

    public void setOwnsGroup() {
        ownsGroup = true;
    }

    public void buyHouse(int houseCount) {
        if(ownsGroup) {
            this.houseCount += houseCount;
        } else {
            System.out.println("Du ejer ikke alle grunde i gruppen, derfor kan du ikke bygge endnu.");}
    }


    public int getHouseCount(){
        return houseCount;

    }

    public boolean hasHotel() {
        return hasHotel;
    }

    public void buyHotel() {
        if(houseCount == 4) {
            hasHotel = true;
        } else {
            System.out.println("Du har ikke nok huse til at købe et hotel.");
        }
    }
}
