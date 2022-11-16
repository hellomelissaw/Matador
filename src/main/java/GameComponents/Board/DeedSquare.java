package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;
public class DeedSquare extends Square{
    Deed deed;
    boolean sellDeed = true;
    int deedPrice;

    GuiController guiController;

    // public DeedSquare(String deedName , Deed deed) {
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
    public void sellDeed(Player player){ // Sets deed to false so Square no longer has a deed
        sellDeed = false ;
        deed.setOwner(player);

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
