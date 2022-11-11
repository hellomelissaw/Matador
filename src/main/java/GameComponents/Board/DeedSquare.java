package GameComponents.Board;
import GameComponents.Player;
public class DeedSquare extends Square{
    Deed deed;
    boolean sellDeed = true;

    Player player;
    int deedPrice;

    // public DeedSquare(String deedName , Deed deed) {
    public DeedSquare(String deedName, int deedPrice) {
        super(deedName);
        this.deed = new Deed(deedPrice, deedName);
        this.deedPrice = deedPrice;
    }
    public Deed getDeed(){
        return deed;
    }
    public boolean hasDeed(){ // Checks if the square has a deed available to buy or if it's already sold
        return sellDeed;
    }
    public void sellDeed(Player player){ // Sets deed to null so Square no longer has a deed
        sellDeed = false ;
        deed.setOwner(player);
    }

    public int getDeedPrice() {
        return deedPrice;
    }

    @Override
    public String toString() {
        String priceString = Integer.toString(deedPrice);
        return priceString;
    }
}
