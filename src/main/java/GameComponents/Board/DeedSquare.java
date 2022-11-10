package GameComponents.Board;

public class DeedSquare extends Square{

    Deed deed;
    boolean sellDeed = true;

    int deedPrice;

    // public DeedSquare(String deedName , Deed deed) {
    public DeedSquare(String deedName, int deedPrice) {
        super(deedName);
        this.deed = new Deed(deedPrice, deedName);
        this.deedPrice = deedPrice;
    }
    Deed getDeed(){
        return deed;
    }
    public boolean hasDeed(){ // Checks if the square has a deed available to buy or if it's already sold
        return sellDeed;
    }
    public void sellDeed(){ // Sets deed to null so Square no longer has a deed
        sellDeed = false ;
    }

    @Override
    public String toString() {
        String priceString = Integer.toString(deedPrice);
        return priceString;
    }
}
