package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;
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

    /**
     * Sells the deed by setting sellDeed to false and setting owner name for the deed.
     * @param player gives the current player whose turn it is
     * @param currentSquareIndex gives the index of the Square that the current player is on
     */
    public void sellDeed(Player player, int currentSquareIndex){
        sellDeed = false ;
        deed.setOwner(player);
        guiController.displayOwnerName(player, currentSquareIndex);

    }

    public int getDeedPrice() {
        return deedPrice;
    }

    public Player getDeedOwner() {
        return deed.getOwner();
    }


    @Override
    public String toString() {
        String priceString = Integer.toString(deedPrice);
        return priceString;
    }
}
