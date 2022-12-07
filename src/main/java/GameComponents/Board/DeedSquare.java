package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;
import gui_fields.GUI_Player;

public class DeedSquare extends Square{
    Deed deed;
    boolean sellDeed = true;
    int deedPrice;
    GuiController guiController;

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
    public Deed getDeed(){
        return deed;
    }
   public boolean hasDeed(){ // Checks if the square has a deed available to buy or if it's already sold
        return sellDeed;
    }

    /*
     * Sells the deed by setting sellDeed to false and setting owner name for the deed.
     * @param player gives the current player whose turn it is
     * @param currentSquareIndex gives the index of the Square that the current player is on
     */
   public void setDeedOwner(Player currentPlayer, int currentSquareIndex){
        sellDeed = false ;
        deed.setOwner(currentPlayer);
        guiController.displayOwnerName(currentPlayer, currentSquareIndex);

    }

    public Player getDeedOwner() {
        return deed.getOwner();
    }

    @Override
    public String toString() {
        String priceString = Integer.toString(deedPrice);
        return priceString;
    }

    public void landOn(Player currentPlayer) {
        currentPlayer.withdrawMoney(deedPrice);
        int currentBalance = currentPlayer.getCurrentBalance();
        System.out.println(msg.getText("newBalance") + currentBalance);

        if(sellDeed == true) {

            String guiMessage = currentPlayer.getPlayerName() + msg.getText("haveBought") + deed.getDeedName();
            guiController.showMessage(guiMessage); // CAN BE DELETED ONCE IMPLEMENT BORDER AROUND SQUARE

            sellDeed = false ;
            deed.setOwner(currentPlayer);
            guiController.displayOwnerName(currentPlayer, currentPlayer.getPosition());

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

}
